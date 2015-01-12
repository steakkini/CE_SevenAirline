package at.jku.ce.airline.test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import at.jku.ce.airline.service.Airline;
import at.jku.ce.airline.service.Airport;
import at.jku.ce.airline.service.Flight;
//import java.util.UUID;
import at.jku.ce.airline.service.Time;



public class DBService {

	private static DBService ref;
	
	/**
	 * Private Constructor, necessary for Singleton pattern. 
	 *
	 */
	private DBService() {
	}
	
	
	public static DBService getDBService() {
		if (ref == null) {
			ref = new DBService();
		}
		return ref;
	}
	
	/**
	 * connect to the database
	 * connection string: jdbc:mysql://140.78.73.67:3306/airlineDB
	 * user: ceue
	 * password: ceair14db
	 * @return a Connection object representing the database connection, null, if connection has not been established
	 */
	private Connection getConnection() {
		Connection cn = null;
		
		try {
			// insert driver load statement here
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			// insert connection request statement here
			cn = DriverManager
					.getConnection(
							"jdbc:mysql://140.78.73.67:3306/airlineDB", "ceue", "ceair14db");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cn;
	}
	
	
	
	public boolean bookFlight(String bookingUuid,Flight f,XMLGregorianCalendar flightDate, Airline a) {
		Connection cn = this.getConnection();
		
		try{
			
			Statement st = cn.createStatement();
			String query = "INSERT INTO flightBookingRecord(uuid_booking, id_flight, flight_date, airline, dep_airport_tax, dest_airport_tax, service_tax, air_fare) "
					+ "VALUES ( '"+ bookingUuid+"','" + f.getFlightId()+"',"+"STR_TO_DATE('"+ flightDate.getYear()+","+flightDate.getMonth()+","+ flightDate.getDay() +"', '%Y, %m, %d')" + ",'"+ a.getName()+"', "+ f.getDepartesFrom().getAirportTax()+ ","+ f.getArrivesAt().getAirportTax()+",10,"+f.getStdFee()+")";
			st.executeUpdate(query);
		}
		catch (SQLException e) {
			System.out.println("Couldn't book Flight "+f.getFlightId());
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	 public boolean cancelFlight(java.lang.String bookingUuid,java.lang.String flightId){
		 Connection cn = this.getConnection();
		 Statement st;
		try {
			st = cn.createStatement();
			String query = "DELETE FROM flightBookingRecord WHERE uuid_booking ='"+bookingUuid+"' AND id_flight ='"+flightId+"'";
			st.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("Canceling Flight "+flightId+" of User "+bookingUuid+" failed");
			e.printStackTrace();
			return false;
		}
		 
		 
		 return true;
	 }
	
	
//	/**
//	 * Demo method 4 printing table info and content
//	 * available table: order
//	 * 
//	 * @param table
//	 */
	public void jdbcDemo(String table) {
		Connection cn = this.getConnection();
		System.out.println("cn: "+ cn);
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * from "+table);
	       
			// Get meta data:
	        ResultSetMetaData rsmd = rs.getMetaData();
	        int i, n = rsmd.getColumnCount();
	       
	        // Print table content:
	        for( i=0; i<n; i++ )
	          System.out.print( "+---------------" );
	        System.out.println( "+" );
	        for( i=1; i<=n; i++ )    // Attention: first column with 1 instead of 0
	          System.out.format( "| %-14s", rsmd.getColumnName(i));
	        System.out.println( "|" );
	        for( i=0; i<n; i++ )
	          System.out.print( "+---------------" );
	        System.out.println( "+" );
	        while( rs.next() ) {
	          for( i=1; i<=n; i++ )  // Attention: first column with 1 instead of 0
	            System.out.format( "| %-14s", rs.getString(i));
	          System.out.println( "|" );
	        }
	        
	        for( i=0; i<n; i++ )
	          System.out.print( "+---------------" );
	        System.out.println( "+" );
			rs.close();
			st.close();
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	public static void main(String[] args) {
		  DBService db2;
	        Flight F;
	        Airline A;
	        Time t1;
	        Time t2;
	        Airport a1;
	        Airport a2;
		//....
		 db2 = DBService.getDBService();
         F = new Flight();
         F.setFlightId("asdf1234");
         F.setPlaneName("FlyingCharlie1");        
         t1 = new Time();
         t1.setIndexDayOfWeek(0);
         t1.setTimeOfDay(1400);
         F.setDepartureTime(t1);
         t2 = new Time();
         t2.setIndexDayOfWeek(0);
         t2.setTimeOfDay(1500);
         F.setArrivalTime(t2);
         A = new Airline();
         a1 = new Airport();
         a1.setAirportTax(new BigDecimal(442.4).round(new MathContext(2)));
         a2 = new Airport();
         a2.setAirportTax(new BigDecimal(442.4).round(new MathContext(2)));
         A.setName("O2");
         F.setStdFee(new BigDecimal(442.4).round(new MathContext(2)));
         F.setArrivesAt(a2);
         F.setDepartesFrom(a1);
         
         GregorianCalendar cal = new GregorianCalendar();
         XMLGregorianCalendar n = null;
		try {
			n = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
        db2.bookFlight("012bbedf-7310-487e-8f74-fd25c1a6750c", DB.getInstance().getFlights().get(3), n, A);
		DBService service = DBService.getDBService();
		service.jdbcDemo("flightBookingRecord");
		service.jdbcDemo("passengerBookingRecord");
		
	}
	
	
}
