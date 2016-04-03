package controler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;


import model.MapModel;
import view.MapTest;
import view.MapView;
import view.StyleLab;

public class MapControler {
	private MapView graphicInterface;
 
	private MapModel model;
 
	public MapControler (MapModel model){
		this.model = model;
		//this.graphicInterface = new MapTest(this);
		this.graphicInterface = new StyleLab(this);
		addListenersToModel();		
	}
 
	private void addListenersToModel() {
		model.addMapListener(graphicInterface);
	}
 
	public void displayViews(){
		graphicInterface.display();
		//((MapTest) graphicInterface).actionPerformed();
		//((MapTest) graphicInterface).actionPerformed2();
	}
 
	public void closeViews(){
		graphicInterface.close();
	}
 
	public void userQuery(Hashtable<String, Integer> dates, ArrayList<String> pays) {
		try {
			model.doQuery(dates, pays);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void getCountryList() {
		try {
			model.searchCountries();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
}
