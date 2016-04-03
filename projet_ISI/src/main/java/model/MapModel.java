package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.Hashtable;

import mediator.Mediator;

public class MapModel {
 
	private EventListener listener;
	private Connection connection;
	private Statement statement;
	private Mediator mediator;

	public MapModel(Connection connection, Mediator mediator) throws SQLException{
		super();
		this.connection = connection;
		this.mediator = mediator;
 	}
 
	public void doQuery(Hashtable<String, Integer> dates, ArrayList<String> pays) throws SQLException {
		//TODO parseQuery method?
		String queryString;
		mediator.setupDB(dates, pays);
		queryString = "SELECT CT.name, CT.code, CT.longitude, CT.lattitude, C.value, HI.value, MI.value "
				+ "FROM ViewCountries CT "
				+ "LEFT JOIN ViewChomage C ON C.countryCode = CT.code "
				+ "LEFT JOIN ViewHomicidesIntentionnels HI ON HI.countryCode = CT.code "
				+ "LEFT JOIN ViewMortaliteInfantile MI ON MI.countryCode = CT.code "
				+ "WHERE C.countryCode = CT.code OR HI.countryCode = CT.code OR MI.countryCode = CT.code;" ;
		System.out.println("[QUERY] " + queryString);
		statement = connection.createStatement();
		ResultSet values = statement.executeQuery(queryString);
		
		// TODO
		fireMapChanged(dates, values);
	}

	public void addMapListener(MapListener listener){
		this.listener = listener;
	}

	public void fireMapChanged(Hashtable<String, Integer> dates, ResultSet values){
		((MapListener) listener).mapChanged(new MapChangedEvent(this, dates, values));
	}
	
	public void fireCountryListChanged(ResultSet values) {
		((MapListener) listener).countryListChanged(new CountryListChangedEvent(this, values));
	}

	public void searchCountries() throws SQLException {
		String queryString;
		queryString = "SELECT name, code FROM ViewCountries;";
		System.out.println("[QUERY] " + queryString);
		statement = connection.createStatement();
		ResultSet values = statement.executeQuery(queryString);
		
		fireCountryListChanged(values);
		
	}
}
