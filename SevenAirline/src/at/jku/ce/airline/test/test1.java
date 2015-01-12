package at.jku.ce.airline.test;

import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import at.jku.ce.airline.service.Airline;
import at.jku.ce.airline.service.AirlineServiceImplImpl;
import at.jku.ce.airline.service.Airport;
import at.jku.ce.airline.service.Flight;

public class test1 {
	private static List<Airport> airports;
	private static List<Airline> airlines;
	public static void main(String[] args) {
		Adapter a = Adapter.getInstance();
		AirlineServiceImplImpl test = new AirlineServiceImplImpl();
		GregorianCalendar cal = new GregorianCalendar();
        XMLGregorianCalendar n = null;
		try {
			n = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.bookFlight("012bbedf-7310-487e-8f74-fd25c1a6750c", "f320", n);
//		System.out.println(a.getAirline().getName());
//		for(Flight f : a.getAirline().getFlightPlan()){
//			System.out.println(f.getFlightId()+": "+f.getDepartesFrom().getCity()+" "+f.getDepartesFrom().getAirportTax()+">>"+f.getArrivesAt().getCity()+" "+f.getArrivesAt().getAirportTax()+" Preis: "+f.getStdFee());
		}
	}
	
	


