package com;

import java.io.File;

import org.geotools.data.FeatureSource;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.factory.CommonFactoryFinder;
import org.geotools.map.FeatureLayer;
import org.geotools.map.Layer;
import org.geotools.map.MapContent;
import org.geotools.styling.SLDParser;
import org.geotools.styling.StyleFactory;
import org.geotools.swing.JMapFrame;

import org.geotools.swing.dialog.JExceptionReporter;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.filter.FilterFactory;
import org.geotools.swing.styling.JSimpleStyleDialog;

public class StyleLab {
	static StyleFactory styleFactory = CommonFactoryFinder.getStyleFactory();
    static FilterFactory filterFactory = CommonFactoryFinder.getFilterFactory();
    
    public static void main(String[] args) throws Exception {
        StyleLab me = new StyleLab();
        me.displayShapefile();
    }
    
    /**
     * Prompts the user for a shapefile (unless a filename is provided
     * on the command line; then creates a simple Style and displays
     * the shapefile on screen
     */
    void displayShapefile() throws Exception {
    	File jar= new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
//    	System.out.println(jar.getParent() + "/classes/countries/countries.shp");
        File file = new File(jar.getParent() + "/classes/countries/countries.shp");
        if (file == null) {
            return;
        }

        FileDataStore store = FileDataStoreFinder.getDataStore(file);
        FeatureSource<SimpleFeatureType, SimpleFeature> featureSource = store.getFeatureSource();

        // Create a map content and add our shapefile to it
        MapContent map = new MapContent();
        map.setTitle("StyleLabMap2");

        // Create a basic Style to render the features
        org.geotools.styling.Style style = createStyle(file, featureSource);

        // Add the features and the associated Style object to
        // the MapContent as a new Layer
        Layer layer = new FeatureLayer(featureSource, style);
        map.addLayer(layer);
        layer.setVisible(true);

        // Now display the map
        JMapFrame.showMap(map);
    }
    
    /**
     * Create a Style to display the features. If an SLD file is in the same
     * directory as the shapefile then we will create the Style by processing
     * this. Otherwise we display a JSimpleStyleDialog to prompt the user for
     * preferences.
     */
    private org.geotools.styling.Style createStyle(File file, FeatureSource<SimpleFeatureType, SimpleFeature> featureSource) {
        File sld = toSLDFile(file);
        if (sld != null) {
            return createFromSLD(sld);
        }

        SimpleFeatureType schema = (SimpleFeatureType)featureSource.getSchema();
        return JSimpleStyleDialog.showDialog(null, schema);
    }
    
    /**
     * Figure out if a valid SLD file is available.
    */
    public File toSLDFile(File file)  {
        String path = file.getAbsolutePath();
        String base = path.substring(0,path.length()-4);
        String newPath = base + ".sld";
        File sld = new File( newPath );
        if( sld.exists() ){
            return sld;
        }
        newPath = base + ".SLD";
        sld = new File( newPath );
        if( sld.exists() ){
            return sld;
        }
        return null;
    }
	
  
  
    /**
     * Create a Style object from a definition in a SLD document
     */
    private org.geotools.styling.Style createFromSLD(File sld) {
        try {
            SLDParser stylereader = new SLDParser(styleFactory, sld.toURI().toURL());
            org.geotools.styling.Style[] style = stylereader.readXML();
            return style[0];
            
        } catch (Exception e) {
            JExceptionReporter.showDialog(e, "Problem creating style");
        }
        return null;
    }
    
    
}
