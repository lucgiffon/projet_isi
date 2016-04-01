package model;

import java.sql.ResultSet;
import java.util.EventObject;

public class CountryListChangedEvent extends EventObject {
	private ResultSet countryList;
 
	public CountryListChangedEvent(Object source, ResultSet values) {
		super(source);
		this.countryList = values;
	}

	public ResultSet getCountryList() {
		return countryList;
	}
}
