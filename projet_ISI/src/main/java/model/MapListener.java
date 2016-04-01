package model;

import java.util.EventListener;

public interface MapListener extends EventListener {
	public void mapChanged(MapChangedEvent event);
	public void countryListChanged(CountryListChangedEvent countryListChangedEvent);
}
