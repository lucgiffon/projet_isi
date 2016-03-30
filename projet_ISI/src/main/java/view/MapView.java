package view;

import controler.MapControler;
import model.MapListener;

public abstract class MapView implements MapListener{
	private MapControler controler = null;
	 
	public MapView(MapControler controler){
		super();
 
		this.controler = controler;
	}
 
	public final MapControler getControler(){
		return controler;
	}
 
	public abstract void display();
	public abstract void close();
}
