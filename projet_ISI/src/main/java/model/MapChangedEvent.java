package model;

import java.util.EventObject;
import java.util.Hashtable;

import java.sql.ResultSet;

public class MapChangedEvent extends EventObject {
	private Hashtable<String, Integer> newDates;
	private ResultSet newValues;
 
	public MapChangedEvent(Object source, Hashtable<String, Integer> newDates, ResultSet values) {
		super(source);
		this.newDates = newDates;
		this.newValues = values;
	}
 
	public Hashtable<String, Integer> getNewDates(){
		return newDates;
	}
	
	public ResultSet getNewValues() {
		return newValues;
	}
}
