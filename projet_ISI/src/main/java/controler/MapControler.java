package controler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

import model.MapModel;
import view.MapTest;
import view.MapView;

public class MapControler {
	private MapView graphicInterface;
 
	private MapModel model;
 
	public MapControler (MapModel model){
		this.model = model;
		this.graphicInterface = new MapTest(this);
		addListenersToModel();
		((MapTest) graphicInterface).actionPerformed();
	}
 
	private void addListenersToModel() {
		model.addMapListener(graphicInterface);
	}
 
	public void displayViews(){
		graphicInterface.display();
	}
 
	public void closeViews(){
		graphicInterface.close();
	}
 
	public void userQuery(Hashtable<String, Integer> dates, ArrayList<String> pays) {
		try {
			model.doQuery(dates, pays);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
