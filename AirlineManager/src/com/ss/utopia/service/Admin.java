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
			System.out.println("Enter the number for which CRUD Function you want.");
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
			System.out.println("Enter the number for which CRUD Flight Function you want.");
			int option = scan.nextInt();
			switch(option) {
			case 1:
				createFlight(scan);
				return;
			case 2:
				try {
					readFlights();
					return;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			case 3:
				updateFlight(scan);
				return;
			case 4:
				deleteFlight(scan);
				return;
			}
		}
		
	}
	
	private void createFlight(Scanner scan) {
		System.out.println("Enter new Origin Airport followed by the City");
		
		Flight nFlight = new Flight();

		if (scan.hasNext()) {
			String newOriginAirport = scan.next();

			nFlight.getRouteId().getOriginAirport().setAirportCode(newOriginAirport);
		}
		
		if (scan.hasNext()) {
			String newOriginCity = scan.next();
			nFlight.getRouteId().getOriginAirport().setAirportCode(newOriginCity);			
		}

		System.out.println("Enter new Destination Airport followed by the City");
		if (scan.hasNext()) {
			String newDestAirport = scan.next();

			nFlight.getRouteId().getDestAirport().setAirportCode(newDestAirport);
		}
		
		if (scan.hasNext()) {
			String newDestCity = scan.next();
			nFlight.getRouteId().getOriginAirport().setAirportCode(newDestCity);			
		}
		
		System.out.println("Enter new Departure Date (yyyy-mm-dd)");
		if (scan.hasNext()) {
			String newDepartDate = scan.next();
			nFlight.setDepartureTime(LocalDate.parse(newDepartDate));
		}
		
		Connection conn = null; 
		try {
			conn = connUtil.getConnection();
			FlightDAO flightDao = new FlightDAO(conn);
			flightDao.addFlight(nFlight);
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
	
	private List<Flight> readFlights() throws SQLException {
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
		return flights;
	}
	
	private void updateFlight(Scanner scan) {
		List<Flight> flights = null;
		try {
			flights = readFlights();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		System.out.println("Choose from available flights or type " + (flights.size()+1) +" To quit");
		Flight fTemp = new Flight();
		if (scan.hasNextInt()) {
			int option = scan.nextInt();
			if ( !(option >= 0 && option <= flights.size())) {
				return;
			}
			
			System.out.println("Enter new Origin Airport and City");
			if (scan.hasNext() && scan.hasNext()) {
				String newOriginAircode = scan.next();
				String newOriginCity = scan.next();
				fTemp.getRouteId().getOriginAirport().setAirportCode(newOriginAircode);
				fTemp.getRouteId().getOriginAirport().setCity(newOriginCity);
			}

			System.out.println("Enter new Destination Airport and City");
			if (scan.hasNext() && scan.hasNext()) {
				String newDestAircode = scan.next();
				String newDestCity = scan.next();
				fTemp.getRouteId().getDestAirport().setAirportCode(newDestAircode);
				fTemp.getRouteId().getDestAirport().setCity(newDestCity);
			}
			
			System.out.println("Enter new Airplane Typing");
			if (scan.hasNext() && scan.hasNext()) {
				int newAirplaneId = scan.nextInt();
				int newMaxCap = scan.nextInt();
				fTemp.getAirplaneId().getAirplaneType().setAirplaneType(newAirplaneId);
				fTemp.getAirplaneId().getAirplaneType().setAirplaneType(newMaxCap);
			}
			
			System.out.println("Enter new reserved seats");
			if (scan.hasNext()) {
				int newReserved = scan.nextInt();
				fTemp.setReservedSeats(newReserved);
			}
			System.out.println("Enter new seat price");
			if (scan.hasNext()) {
				float newPrice = scan.nextFloat();
				fTemp.setSeatPrice(newPrice);
			}
		}


		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			FlightDAO flightDao = new FlightDAO(conn);
			flightDao.updateFlight(fTemp);
			conn.commit(); // this makes the change permanent.
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
	
	private void deleteFlight(Scanner scan) {
		List<Flight> flights = null;
		try {
			flights = readFlights();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		System.out.println("Choose from available flights or type " + (flights.size()+1) +" To quit");
		int flightCodeIndex;
		if (scan.hasNext()) {
			flightCodeIndex = scan.nextInt();
		}
		else {
			return;
		}
		Flight flight = flights.get(flightCodeIndex-1);
		
		Connection conn = null; 
		try {
			conn = connUtil.getConnection();
			FlightDAO flightDao = new FlightDAO(conn);
			flightDao.deletesFlight(flight);
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
		System.out.println("Enter Airport Code and City Name: ");
		if (scan.hasNext()) {
			nAirportCode = scan.next();
		}
		if (scan.hasNext()) {
			nCity = scan.next();
		}
		nAirport.setAirportCode(nAirportCode);
		nAirport.setCity(nCity);
		
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
		System.out.println("Choose from available airports or type " + (airports.size()+1) +" To quit");
		Airport airport = new Airport();
		if (scan.hasNextInt()) {
			int option = scan.nextInt();
			if ( option >= 0 && option <= airports.size()) {
				if (scan.hasNext()) {
					String nAirportCode = scan.next();
					if (scan.hasNext()) {
						String newCity = scan.next();
						airport.setAirportCode(nAirportCode);
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
		Airport airport = airports.get(airportCodeIndex-1);
		
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
