package com.ss.utopia.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ss.utopia.dao.AirplaneDAO;
import com.ss.utopia.dao.AirportDAO;
import com.ss.utopia.dao.FlightDAO;
import com.ss.utopia.dao.RouteDAO;
import com.ss.utopia.entity.Airport;
import com.ss.utopia.entity.BookingAgent;
import com.ss.utopia.entity.BookingUser;
import com.ss.utopia.entity.Flight;
import com.ss.utopia.entity.Passenger;
import com.ss.utopia.entity.Route;

public class Admin {

	ConnectionUtil connUtil = new ConnectionUtil();
	
	private void promptOptions() {
		System.out.println("1. CRUD Flights");
		System.out.println("2. CRUD Seats");
		System.out.println("3. CRUD Tickets and Passengers");
		System.out.println("4. CRUD Airports");
		System.out.println("5. CRUD Travelers");
		System.out.println("6. CRUD Employees");
		System.out.println("7. Quit");
	}
	
	private void crudOptions(String option) {
		System.out.println("1. create " + option);
		System.out.println("2. read " + option);
		System.out.println("3. update " + option);
		System.out.println("4. delete " + option);
		System.out.println("5. quit");
	}
	
	public void enterOptions(Scanner scan) {
		promptOptions();
		if (scan.hasNextInt()) {
			int option =  scan.nextInt();
			switch(option) {
			case 1:
				crudFlights(scan);
				return;
			case 2:
				crudSeats(scan);
				return;
			case 3:
				crudTicketPassengers(scan);
				return;
			case 4:
				crudAirports(scan);
				return;
			case 5:
				crudTravelers(scan);
				return;
			case 6:
				crudEmployees(scan);
				return;
			case 7:
				return;
						
			}
		}
		return;		
	}

	private void crudFlights(Scanner scan) {
		crudOptions("Flights");
		if (scan.hasNextInt()) {
			int option = scan.nextInt();
			switch(option) {
			case 1:
				createFlight(scan);
			case 2:
				try {
					readFlights();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			case 3:
				updateFlight(scan);
			case 4:
				deleteFlight(scan);			
			}
		}
		
	}
	
	private void createFlight(Scanner scan) {
//		System.out.println("You have chosen to update Flight: " + flight.getFlightId() + " Origin Airport: "
//				+ flight.getRouteId().getOriginAirport().getAirportCode() + " " +  flight.getRouteId().getOriginAirport().getCity() 
//				+" Destination Airport: " + flight.getRouteId().getDestAirport().getAirportCode() + " " 
//				+ flight.getRouteId().getDestAirport().getCity() + "Type quit at any time to cancel operation");
//		
//		System.out.println("Enter new Origin Airport and City or N/A for no change");
//		String newOriginAirport = scan.next();
//		Flight fTemp = new Flight();
//		
//		if (newOriginAirport.equalsIgnoreCase("N/A") ) {
//			
//		}
//		else if (newOriginAirport.equalsIgnoreCase("Quit") ) {
//			return;
//		}
//		else {
//			String newOriginCity = scan.next();
//			fTemp.getRouteId().getOriginAirport().setAirportCode(newOriginAirport);
//			fTemp.getRouteId().getOriginAirport().setAirportCode(newOriginCity);
//		}
//
//		System.out.println("Enter new Destination Airport and City or N/A for no change");
//		String newDestAirport = scan.next();
//		if (newDestAirport.equalsIgnoreCase("N/A") ) {
//			
//		}
//		else if (newDestAirport.equalsIgnoreCase("Quit") ) {
//			return;
//		}
//		else {
//			String newDestCity = scan.nextLine();
//			fTemp.getRouteId().getDestAirport().setAirportCode(newDestAirport);
//			fTemp.getRouteId().getDestAirport().setCity(newDestCity);
//		}
//		
//		System.out.println("Enter new Departure Date or N/A for no change (yyyy-mm-dd)");
//		
//		String newDate = scan.nextLine();
//		if (newDate.equalsIgnoreCase("N/A") ) {
//			
//		}
//		else if (newDate.equalsIgnoreCase("Quit") ) {
//			return;
//		}
//		else {
//			fTemp.setDepartureTime(LocalDate.parse(newDate));
//		}
	}
	
	private void readFlights() throws SQLException {
		Connection conn = null;
		List<Flight> flights = new ArrayList<>();
		try {
			conn = connUtil.getConnection();
			FlightDAO flightDao = new FlightDAO(conn);
			flights = flightDao.readAllFlights();
			flights.forEach((n) -> System.out.println(n));
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}
	
	private void updateFlight(Scanner scan) {
		
	}
	
	private void deleteFlight(Scanner scan) {
		
	}
	//--------------------------------------------------
	private void crudSeats(Scanner scan) {
		crudOptions("Seats");
		if (scan.hasNextInt()) {
			int option = scan.nextInt();
			switch(option) {
			case 1:
				createSeat(scan);
			case 2:
				try {
					readSeats(scan);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			case 3:
				updateSeat(scan);
			case 4:
				deleteSeat(scan);			
			}
		}
		
	}
	private void createSeat(Scanner scan) {
		
	}
	
	private void readSeats(Scanner scan) throws SQLException {
		Connection conn = null;
		List<Flight> flights = new ArrayList<>();
		try {
			conn = connUtil.getConnection();
			FlightDAO flightDao = new FlightDAO(conn);
			flights = flightDao.readAllFlights();
			flights.forEach((n) -> System.out.println(n));
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}
	
	private void updateSeat(Scanner scan) {
		
	}
	
	private void deleteSeat(Scanner scan) {
		
	}
	
	//--------------------------------------------------
	private void crudTicketPassengers(Scanner scan) {
		crudOptions("Tickets and Passengers");
		if (scan.hasNextInt()) {
			int option = scan.nextInt();
			switch(option) {
			case 1:
				createTicketPassenger(scan);
			case 2:
				try {
					readTicketPassenger(scan);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			case 3:
				updateTicketPassenger(scan);
			case 4:
				deleteTicketPassenger(scan);			
			}
		}
	}
	
	private void createTicketPassenger(Scanner scan) {
		
	}
	
	private void readTicketPassenger(Scanner scan) throws SQLException {
		Connection conn = null;
		List<Flight> flights = new ArrayList<>();
		try {
			conn = connUtil.getConnection();
			FlightDAO flightDao = new FlightDAO(conn);
			flights = flightDao.readAllFlights();
			flights.forEach((n) -> System.out.println(n));
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}
	
	private void updateTicketPassenger(Scanner scan) {
		
	}
	
	private void deleteTicketPassenger(Scanner scan) {
		
	}
	//--------------------------------------------------
	
	private void crudAirports(Scanner scan) {
		crudOptions("Airports");
		if (scan.hasNextInt()) {
			int option = scan.nextInt();
			switch(option) {
			case 1:
				createAirport(scan);
				break;
			case 2:
				try {
					readAirports();
					break;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			case 3:
				try {
					updateAirport(scan);
					break;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			case 4:
				try {
					deleteAirport(scan);
					break;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
			}
		}
		
	}
	
	private void createAirport(Scanner scan) {
		String nAirportCode = null;
		String nCity = null;
		Airport nAirport = new Airport();
		if (scan.hasNext()) {
			nAirportCode = scan.nextLine();
		}
		if (scan.hasNext()) {
			nCity = scan.nextLine();
		}
		nAirport.setAirportCode(nAirportCode);
		nAirport.setAirportCode(nCity);
		
		Connection conn = null; 
		try {
			conn = connUtil.getConnection();
			AirportDAO airportDao = new AirportDAO(conn);
			airportDao.addRoute(nAirport);
			conn.commit(); //this makes the change permanent. 
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private List<Airport> readAirports() throws SQLException {
		Connection conn = null;
		List<Airport> airports = new ArrayList<>();
		try {
			conn = connUtil.getConnection();
			AirportDAO airportDao = new AirportDAO(conn);
			airports = airportDao.readAllAirports();
			airports.forEach((n) -> System.out.println(n.toString()));
			return airports;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return airports;
	}
	
	private void updateAirport(Scanner scan) throws SQLException {
		List<Airport> airports = readAirports();
		//Airport originalAirport = new Airport();
		System.out.println("Choose from available airports or type " + (airports.size()+1) +" To quit");
		Airport airport = new Airport();
		if (scan.hasNextInt()) {
			int option = scan.nextInt();
			if ( option >= 0 && option <= airports.size()) {
				if (scan.hasNext()) {
					String newAirportCode = scan.next();
					if (scan.hasNext()) {
						String newCity = scan.next();
						airport.setAirportCode(newAirportCode);
						airport.setCity(newCity);
					}
				}
			}
			else if (option == airports.size()+1) {
				return;
			}
		}
		
		Connection conn = null; 
		try {
			conn = connUtil.getConnection();
			AirportDAO airportDao = new AirportDAO(conn);
			airportDao.updateAirport(airport);
			conn.commit(); //this makes the change permanent. 
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void deleteAirport(Scanner scan) throws SQLException {
		List<Airport> airports = readAirports();
		System.out.println("Choose from available airports or type " + (airports.size()+1) +" To quit");
		int airportCodeIndex;
		if (scan.hasNext()) {
			airportCodeIndex = scan.nextInt();
		}
		else {
			return;
		}
		Airport airport = airports.get(airportCodeIndex);
		
		Connection conn = null; 
		try {
			conn = connUtil.getConnection();
			AirportDAO airportDao = new AirportDAO(conn);
			airportDao.deleteAirport(airport);
			conn.commit(); //this makes the change permanent. 
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//--------------------------------------------------
	private void crudTravelers(Scanner scan) {
		crudOptions("Travelers");
		if (scan.hasNextInt()) {
			int option = scan.nextInt();
			switch(option) {
			case 1:
				createTraveler(scan);
			case 2:
				try {
					readTravelers(scan);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			case 3:
				updateTraveler(scan);
			case 4:
				deleteTraveler(scan);			
			}
		}
		
	}
	
	private void createTraveler(Scanner scan) {
		
	}
	
	private void readTravelers(Scanner scan) throws SQLException {
		Connection conn = null;
		List<Flight> flights = new ArrayList<>();
		try {
			conn = connUtil.getConnection();
			FlightDAO flightDao = new FlightDAO(conn);
			flights = flightDao.readAllFlights();
			flights.forEach((n) -> System.out.println(n));
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}
	private void updateTraveler(Scanner scan) {
		
	}
	
	private void deleteTraveler(Scanner scan) {
		
	}
	//--------------------------------------------------
	private void crudEmployees(Scanner scan) {
		crudOptions("Employees");
		if (scan.hasNextInt()) {
			int option = scan.nextInt();
			switch(option) {
			case 1:
				createEmployee(scan);
			case 2:
				try {
					readEmployees(scan);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			case 3:
				updateEmployee(scan);
			case 4:
				deleteEmployee(scan);			
			}
		}
		
	}
	
	private void createEmployee(Scanner scan) {
		
	}
	
	private void readEmployees(Scanner scan) throws SQLException {
		Connection conn = null;
		List<Flight> flights = new ArrayList<>();
		try {
			conn = connUtil.getConnection();
			FlightDAO flightDao = new FlightDAO(conn);
			flights = flightDao.readAllFlights();
			flights.forEach((n) -> System.out.println(n));
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}
	private void updateEmployee(Scanner scan) {
		
	}
	
	private void deleteEmployee(Scanner scan) {
		
	}

}
