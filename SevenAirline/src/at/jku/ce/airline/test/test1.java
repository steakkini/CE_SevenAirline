package at.jku.ce.airline.test;

import java.util.List;

import at.jku.ce.airline.service.Airline;
import at.jku.ce.airline.service.Airport;
import at.jku.ce.airline.service.Flight;

public class test1 {
	private static List<Airport> airports;
	private static List<Airline> airlines;
	public static void main(String[] args) {
		Adapter a = new Adapter();
		System.out.println(a.getAirline().getName());
		for(Flight f : a.getAirline().getFlightPlan()){
			System.out.println(f.getDepartesFrom().getCity()+">>"+f.getArrivesAt().getCity());
		}
	}
	
	

}
