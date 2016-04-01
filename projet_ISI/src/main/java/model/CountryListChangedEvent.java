package model;

import java.sql.ResultSet;
import java.util.EventObject;

public class CountryListChangedEvent extends EventObject {
	private ResultSet newCountryList;
 
	public CountryListChangedEvent(Object source, ResultSet values) {
		super(source);
		this.newCountryList = values;
	}

	public ResultSet getCountryList() {
		return newCountryList;
	}
}
