package view;

import java.awt.Color;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

import javax.swing.JFrame;

import model.CountryListChangedEvent;
import model.MapChangedEvent;

import org.geotools.data.FeatureSource;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.factory.CommonFactoryFinder;
import org.geotools.geometry.GeometryBuilder;
import org.geotools.geometry.jts.CircularString;
import org.geotools.map.FeatureLayer;
import org.geotools.map.Layer;
import org.geotools.map.MapContent;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.geotools.styling.FeatureTypeStyle;
import org.geotools.styling.Fill;
import org.geotools.styling.Graphic;
import org.geotools.styling.LineSymbolizer;
import org.geotools.styling.Mark;
import org.geotools.styling.PointSymbolizer;
import org.geotools.styling.PolygonSymbolizer;
import org.geotools.styling.Rule;
import org.geotools.styling.SLDParser;
import org.geotools.styling.Stroke;
import org.geotools.styling.Style;
import org.geotools.styling.StyleFactory;
import org.geotools.swing.JMapFrame;

import org.geotools.swing.dialog.JExceptionReporter;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.filter.FilterFactory;
import org.opengis.geometry.coordinate.GeometryFactory;
import org.opengis.geometry.coordinate.LineString;
import org.opengis.geometry.coordinate.Polygon;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.geotools.swing.styling.JSimpleStyleDialog;

import com.vividsolutions.jts.geom.MultiLineString;
import com.vividsolutions.jts.geom.MultiPolygon;


import controler.MapControler;

public class StyleLab extends MapView {
	static StyleFactory styleFactory = CommonFactoryFinder.getStyleFactory();
    static FilterFactory filterFactory = CommonFactoryFinder.getFilterFactory();
    private MapControler controler;
    private Hashtable<String, String> countryList = new Hashtable<String, String>();
    
    public StyleLab(MapControler controler) {
    	super(controler);
    	this.controler = controler;
    }
    
    /**
     * Prompts the user for a shapefile (unless a filename is provided
     * on the command line; then creates a simple Style and displays
     * the shapefile on screen
     */
    void displayShapefile() throws Exception {
    	this.controler.getCountryList();
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
        map.setTitle("MyMap");

        // Create a basic Style to render the features
        org.geotools.styling.Style style = createStyle(file, featureSource);

        // Add the features and the associated Style object to
        // the MapContent as a new Layer
        Layer layer = new FeatureLayer(featureSource, style);
        map.addLayer(layer);
        layer.setVisible(true);

        // Now display the map
        JMapFrame.showMap(map);

        JFrame window = new JFrame();
        view.WindowSourcesView windowChoice = new view.WindowSourcesView(controler, countryList);    
    }
    
    /**
     * Create a Style to display the features. If an SLD file is in the same
     * directory as the shapefile then we will create the Style by processing
     * this. Otherwise we display a JSimpleStyleDialog to prompt the user for
     * preferences.
     */
    private Style createStyle(File file, FeatureSource<SimpleFeatureType, SimpleFeature> featureSource) {
        SimpleFeatureType schema = (SimpleFeatureType)featureSource.getSchema();
        Class geomType = schema.getGeometryDescriptor().getType().getBinding();

        if (Polygon.class.isAssignableFrom(geomType)
                || MultiPolygon.class.isAssignableFrom(geomType)) {
            return createPolygonStyle();

        } else if (LineString.class.isAssignableFrom(geomType)
                || MultiLineString.class.isAssignableFrom(geomType)) {
            return createLineStyle();

        } else if (CircularString.class.isAssignableFrom(geomType)
                || MultiLineString.class.isAssignableFrom(geomType)) {
            return createPointStyle();
        }
        return null;
    }
    
    private Style createLineStyle() {
        Stroke stroke = styleFactory.createStroke(
                filterFactory.literal(Color.BLUE),
                filterFactory.literal(1));

        /*
         * Setting the geometryPropertyName arg to null signals that we want to
         * draw the default geomettry of features
         */
        LineSymbolizer sym = styleFactory.createLineSymbolizer(stroke, null);

        Rule rule = styleFactory.createRule();
        rule.symbolizers().add(sym);
        FeatureTypeStyle fts = styleFactory.createFeatureTypeStyle(new Rule[]{rule});
        Style style = styleFactory.createStyle();
        style.featureTypeStyles().add(fts);

        return style;
    }

	private Style createPolygonStyle() {

        // create a partially opaque outline stroke
        Stroke stroke = styleFactory.createStroke(
                filterFactory.literal(Color.BLACK),
                filterFactory.literal(1),
                filterFactory.literal(0.5));

        // create a partial opaque fill
        Fill fill = styleFactory.createFill(
                filterFactory.literal(Color.WHITE),
                filterFactory.literal(0.5));

        /*
         * Setting the geometryPropertyName arg to null signals that we want to
         * draw the default geomettry of features
         */
        PolygonSymbolizer sym = styleFactory.createPolygonSymbolizer(stroke, fill, null);

        Rule rule = styleFactory.createRule();
        rule.symbolizers().add(sym);
        FeatureTypeStyle fts = styleFactory.createFeatureTypeStyle(new Rule[]{rule});
        Style style = styleFactory.createStyle();
        style.featureTypeStyles().add(fts);

        return style;
    }

    
    private Style createPointStyle() {
        Graphic gr = styleFactory.createDefaultGraphic();

        Mark mark = styleFactory.getCircleMark();

        mark.setStroke(styleFactory.createStroke(
                filterFactory.literal(Color.BLUE), filterFactory.literal(1)));

        mark.setFill(styleFactory.createFill(filterFactory.literal(Color.CYAN)));

        gr.graphicalSymbols().clear();
        gr.graphicalSymbols().add(mark);
        gr.setSize(filterFactory.literal(5));

        /*
         * Setting the geometryPropertyName arg to null signals that we want to
         * draw the default geomettry of features
         */
        PointSymbolizer sym = styleFactory.createPointSymbolizer(gr, null);

        Rule rule = styleFactory.createRule();
        rule.symbolizers().add(sym);
        FeatureTypeStyle fts = styleFactory.createFeatureTypeStyle(new Rule[]{rule});
        Style style = styleFactory.createStyle();
        style.featureTypeStyles().add(fts);

        return style;
    }

	public void mapChanged(MapChangedEvent event) {
		ResultSet values = event.getNewValues();
		System.out.println("Name\tCode\tLongitude\tLattitude\tRateC\tRateHI\tRateMI");
		
		try {
			while (values.next()) {
				System.out.println(values.getString(1) + "\t" + values.getString(2) + "\t" + values.getFloat(3) + "\t" + values.getFloat(4) + "\t" + values.getFloat(5) + "\t" + values.getFloat(6) + "\t" + values.getFloat(7));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	public void countryListChanged(CountryListChangedEvent event) {
		// Set le hashtable avec le resultSet (event.getCountryList())
		// 1 -> nomdu pays
		// 2 -> codeCOuntry
		ResultSet values = event.getCountryList();

		
		try {
			while (values.next()) {
				countryList.put(values.getString(1), values.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void display() {
		try {
			displayShapefile();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}
    
    
}
