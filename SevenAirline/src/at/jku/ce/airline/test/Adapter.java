package at.jku.ce.airline.test;

import at.jku.ce.airline.service.Airline;
import at.jku.ce.airline.service.Flight;
import at.jku.ce.airline.test.DB;

public class Adapter {
	static Airline airline;
	static Adapter instance;
	
	private Adapter(){
		airline = new Airline();
		airline.setName("SEVEN");
		setStats();
	}
	
	private void setStats(){
		for(Flight f : DB.getInstance().getFlights()){
			airline.getFlightPlan().add(f);
		}
	}
	
	public Airline getAirline(){
		return airline;
	}
	
	public synchronized static Adapter getInstance(){
		if(instance == null) instance = new Adapter();
		return instance;
	}
	
}
