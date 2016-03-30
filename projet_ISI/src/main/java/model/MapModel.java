package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import mediator.CountryWrapper;
import mediator.MainDataWrapper;
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
		mediator.setupDB(dates, pays);
//		ResultSet values = null;
		// TODO
//		fireMapChanged(dates, values);
	}

	public void addMapListener(MapListener listener){
		this.listener = listener;
	}

	public void fireMapChanged(Hashtable<String, Integer> dates, ResultSet values){
		((MapListener) listener).mapChanged(new MapChangedEvent(this, dates, values));
	}
}
