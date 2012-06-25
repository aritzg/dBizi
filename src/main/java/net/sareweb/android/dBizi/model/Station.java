package net.sareweb.android.dBizi.model;

import net.sareweb.android.dBizi.exception.NoSuchDockException;

public class Station {
	
	private String  name = "";
	private String docks="";
	private int available=0;
	
	public Station(String name, String docks){
		this.name=name;
		if(docks!=null){
			for (int i = 0; i < docks.length(); i++) {
				if(docks.charAt(i)!=DOCK_STATUS_UNAVAILABLE){
					this.docks=this.docks+docks.charAt(i);
					if(docks.charAt(i)==DOCK_STATUS_AVAILABLE)available=available+1;
				}
			}
		}
	}
	
	public String getStationName(){
		return name;
	}

	public int getDockAmount(){
		return docks.length();
	}
	
	public int getAvailableDockAmount(){
		return available;
	}
	
	public String getDockStatus(int i) throws NoSuchDockException{
		try {
			return Character.toString(docks.charAt(i));
		} catch (Exception e) {
			throw new NoSuchDockException();
		}
	}
	
	private static final char DOCK_STATUS_AVAILABLE= '4';
	private static final char DOCK_STATUS_UNAVAILABLE= 'x';
}
