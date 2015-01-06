package at.jku.ce.airline.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import sun.dc.pr.PathStroker;
import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;
import at.jku.ce.airline.service.Airport;
import at.jku.ce.airline.service.Flight;
import at.jku.ce.airline.service.Time;
public class DB {

	private static List<Airport> airports;
	private static List<Flight> flights;
	
	public DB(){
		airports = createAirports();
		try {
			flights = createFlights();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private List<Flight> createFlights() throws Exception {
		List<Flight> flights = new LinkedList<Flight>();
		BufferedReader br = new BufferedReader(new FileReader("src/at/jku/ce/airline/test/flightPlanCSV.csv"));
		br.readLine();//skip head
		String[] line;
		String sLine;
		Flight flight;
		Time timeD, timeA;
		while((sLine = br.readLine()) != null){
			line = sLine.split(",");
			flight = new Flight();
			flight.setDepartesFrom(getAirportByCountry(line[4]));
			timeD = new Time();
			timeD.setIndexDayOfWeek(Day.valueOf(line[0]).ordinal());
			timeD.setTimeOfDay(Long.parseLong(line[3]));
			flight.setDepartureTime(timeD);
			flight.setArrivesAt(getAirportByCountry(line[6]));
			timeA = new Time();
			timeA.setIndexDayOfWeek(Day.valueOf(line[0]).ordinal());
			timeA.setTimeOfDay(Long.parseLong(line[5]));
			flight.setArrivalTime(timeA);
			flight.setPlaneName(line[2]);
			flight.setFlightId(line[1]);
			flight.setStdFee(new BigDecimal(flight.getArrivalTime().getTimeOfDay()-flight.getDepartureTime().getTimeOfDay()));
			flights.add(flight);
		}
		br.close();
		return flights;
	}
	private Airport getAirportByCountry(String name) throws Exception{
		for(Airport a : airports){
			if(a.getCountry().equalsIgnoreCase(name)){
				return a;
			}
		}
		throw new Exception("Airport not found: "+name);
	}
	private static List<Airport> createAirports(){
		List<Airport> airports = new LinkedList<Airport>();
    	Airport spanien = new Airport();
    	spanien.setCity("Barcelona");
    	spanien.setCountry("Spanien");
    	spanien.setIcao("LEBL");
    	spanien.setName("Flughafen Barcelona");
    	spanien.setAirportTax(new BigDecimal(452.1));
    	airports.add(spanien);

    	Airport frankreich = new Airport();
    	frankreich.setCity("Paris");
    	frankreich.setCountry("Frankreich");
    	frankreich.setIcao("LFPG");
    	frankreich.setName("Flughafen Paris-Charles de Gaulle");
    	frankreich.setAirportTax(new BigDecimal(716.1));
    	airports.add(frankreich);
    	
    	Airport uk = new Airport();
    	uk.setCity("London");
    	uk.setCountry("Vereintes Königreich");
    	uk.setIcao("EGKK");
    	uk.setName("Flughafen London-Heathrow");
    	uk.setAirportTax(new BigDecimal(442.4));
    	airports.add(uk);
    	
    	Airport irland = new Airport();
    	irland.setCity("Dublin");
    	irland.setCountry("Irland");
    	irland.setIcao("EIDW");
    	irland.setName("Flughafen Irland");
    	irland.setAirportTax(new BigDecimal(290.9));
    	airports.add(irland);
    	
    	Airport belgien = new Airport();
    	belgien.setCity("Brüssel");
    	belgien.setCountry("Belgien");
    	belgien.setIcao("EBBR");
    	belgien.setName("Flughafen Brüssel-Zaventem");
    	belgien.setAirportTax(new BigDecimal(289.7));
    	airports.add(belgien);
    	
    	Airport nl = new Airport();
    	nl.setCity("Amsterdam");
    	nl.setCountry("Niederlande");
    	nl.setIcao("EHAM");
    	nl.setName("Amsterdam – Schiphol");
    	nl.setAirportTax(new BigDecimal(610.4));
    	airports.add(nl);
    	
    	Airport portugal = new Airport();
    	portugal.setCity("Lissabon");
    	portugal.setCountry("Portugal");
    	portugal.setIcao("LPPT");
    	portugal.setName("Flughafen Lissabon");
    	portugal.setAirportTax(new BigDecimal(253.0));
    	airports.add(portugal);
    	
    	Airport luxemburg = new Airport();
    	luxemburg.setCity("Luxemburg");
    	luxemburg.setCountry("Luxemburg");
    	luxemburg.setIcao("ELLX");
    	luxemburg.setName("Flughafen Luxemburg");
    	luxemburg.setAirportTax(new BigDecimal(119.2));
    	airports.add(luxemburg);
    	
    	Airport island = new Airport();
    	island.setCity("Reykjavík/Keflavík");
    	island.setCountry("Island");
    	island.setIcao("BIKF");
    	island.setName("Leifur Eriksson");
    	island.setAirportTax(new BigDecimal(123.8));
    	airports.add(island);
    	
    	Airport schweiz = new Airport();
    	schweiz.setCity("Zürich");
    	schweiz.setCountry("Schweiz");
    	schweiz.setIcao("LSZH");
    	schweiz.setName("Flughafen Zürich");
    	schweiz.setAirportTax(new BigDecimal(348.0));
    	airports.add(schweiz);
    	
    	Airport italien = new Airport();
    	italien.setCity("Rom");
    	italien.setCountry("Italien");
    	italien.setIcao("LIRF");
    	italien.setName("Flughafen Rom-Fiumicino");
    	italien.setAirportTax(new BigDecimal(469.8));
    	airports.add(italien);
    	
    	Airport deutschland = new Airport();
    	deutschland.setCity("Frankfurt");
    	deutschland.setCountry("Deutschland");
    	deutschland.setIcao("EDDF");
    	deutschland.setName("Frankfurt am Main");
    	deutschland.setAirportTax(new BigDecimal(680.36));
    	airports.add(deutschland);
    	
    	Airport dm = new Airport();
    	dm.setCity("Kopenhagen");
    	dm.setCountry("Dänemark");
    	dm.setIcao("EKCH");
    	dm.setName("Kastrup Airport");
    	dm.setAirportTax(new BigDecimal(333.4));
    	airports.add(dm);
    	return airports;
	}
	
	@Override
	public String toString(){
		StringBuilder result = new StringBuilder();
		result.append("##########################\n#######Airports###########\n##########################\n");
		for(Airport a : airports){
			result.append(a.getCity()).append(":").append(a.getName());
			result.append("\n");
		}
		result.append("##########################\n#######Flights############\n##########################\n");
		for(Flight f : flights){
			result.append(f.getFlightId()).append(": ").append(f.getDepartesFrom().getName()).append(f.getDepartureTime().getIndexDayOfWeek()).append(f.getDepartureTime().getTimeOfDay()).append(" >> ").append(f.getArrivesAt().getName()).append(f.getArrivalTime().getTimeOfDay()).append(f.getStdFee());
			
			result.append("\n");
		}
		return result.toString();
	}
	
	
}
