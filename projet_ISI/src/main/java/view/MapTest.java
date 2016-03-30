package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

import controler.MapControler;
import model.MapChangedEvent;

public class MapTest extends MapView implements ActionListener {

	Hashtable<String, Integer> dates = new Hashtable<String, Integer>();
	
	public MapTest(MapControler controler) {
		super(controler);
		actionPerformed();
	}

	public void mapChanged(MapChangedEvent event) {
		ResultSet rs = event.getNewValues();
		
		try {
			while (rs.next()) {
				// TODO
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void actionPerformed(ActionEvent e) {
		ArrayList<String> pays = new ArrayList<String>();
		pays.add("UKR");
		pays.add("GBR");
		dates.put("Chomage", 1995);
		dates.put("MortaliteInfantile", 1995);
		dates.put("HomicidesIntentionnels", 1995);
		getControler().userQuery(dates, pays);
	}
	
	public void actionPerformed() {
		ArrayList<String> pays = new ArrayList<String>();
		pays.add("UKR");
		pays.add("GBR");
		dates.put("Chomage", 1995);
		dates.put("MortaliteInfantile", 1995);
		dates.put("HomicidesIntentionnels", 1995);
		getControler().userQuery(dates, pays);
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		System.out.println("Affichage de l'interface graphique");
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		System.out.println("Fermeture de l'interface graphique");
		
	}

}
