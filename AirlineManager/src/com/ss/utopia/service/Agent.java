package com.ss.utopia.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.ss.utopia.dao.AirplaneDAO;
import com.ss.utopia.dao.AirplaneTypeDAO;
import com.ss.utopia.dao.AirportDAO;
import com.ss.utopia.dao.FlightDAO;
import com.ss.utopia.entity.Flight;

public class Agent {
	ConnectionUtil connUtil = new ConnectionUtil();

	private void empOne() {
		System.out.println("1. Enter Flights you manage: ");
		System.out.println("2. Quit to the previous");
	}

	private void empThree() {
		System.out.println("1. View more details\n2. Update the details\n3. Add Seats\n4. Quit ");
	}
	
	public void enterFlights(Scanner scan) {
		empOne();		
		Connection conn = null;
		
		try {
			conn = connUtil.getConnection();
			int option = scan.nextInt();
			switch (option) {
			case (1):
				viewFlights(scan, conn);
			case (2): 
				return;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void viewFlights(Scanner scan, Connection conn) {
		Flight flight;
		FlightDAO flightDao = new FlightDAO(conn);
		try {
			List<Flight> flights = flightDao.readAllFlights();
			for (int i = 0; i < flights.size(); i++) {
				System.out.println(i+1 + " " + flights.get(i).getRouteId().getOriginAirport().getAirportCode() + " "
						+ flights.get(i).getRouteId().getOriginAirport().getCity() + " -> " +
						flights.get(i).getRouteId().getDestAirport().getAirportCode() + " "
						+ flights.get(i).getRouteId().getDestAirport().getCity());
			}
			System.out.println((flights.size() + 1) + " Quit to previous");
			int flightChoice = scan.nextInt();
			if (flightChoice == flights.size()+1) {
				enterFlights(scan);
			}
			else {
				flight = flights.get(flightChoice);
				manageFlight(flight, scan, flightDao, conn);				
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void manageFlight(Flight flight, Scanner scan, FlightDAO flightDao, Connection conn) throws ClassNotFoundException, SQLException {
		empThree();
		int option;
		while (scan.hasNextInt()) {
			option = scan.nextInt();
			switch (option) {
			case (1): // view
				viewFlight(flight, scan, flightDao, conn);
				break;
			case (2): // update
				updateFlight(flight, scan, flightDao, conn);
				break;
			case (3): // add seats
				addSeat(flight, scan, flightDao, conn);
				break;
			case (4): // quit
				viewFlights(scan, conn);
			}
		}

	}
	private void addSeat(Flight flight, Scanner scan, FlightDAO flightDao, Connection conn) {
		System.out.println("1. First\n2. Business\n3. Economy\n4.Quit");
		int option;
		if (scan.hasNextInt()) {
			option = scan.nextInt();
			if (option == 4) return;
			System.out.println("Remaining seats:" + flight.getAirplaneId().getAirplaneType().getMaxCapacity());
			int seats;
//			if (scan.hasNextInt()) {
				System.out.println("Enter new number of seats: ");
				seats = scan.nextInt();
				flight.getAirplaneId().getAirplaneType().setMaxCapacity(seats);
				AirplaneTypeDAO airplaneTypeDao = new AirplaneTypeDAO(conn);
				try {
					airplaneTypeDao.updateAirplaneTypeByCapacity(flight.getAirplaneId().getAirplaneType());
					manageFlight(flight, scan, flightDao, conn);
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			//}
		}
		
	}

	private void updateFlight(Flight flight, Scanner scan, FlightDAO flightDao, Connection conn) {
		System.out.println("You have chosen to update Flight: " + flight.getFlightId() + " Origin Airport: "
				+ flight.getRouteId().getOriginAirport().getAirportCode() + " " +  flight.getRouteId().getOriginAirport().getCity() 
				+" Destination Airport: " + flight.getRouteId().getDestAirport().getAirportCode() + " " 
				+ flight.getRouteId().getDestAirport().getCity() + "Type quit at any time to cancel operation");
		
		System.out.println("Enter new Origin Airport and City or N/A for no change");
		String newOriginAirport = scan.next();
		Flight fTemp = new Flight();
		
		if (newOriginAirport.equalsIgnoreCase("N/A") ) {
			
		}
		else if (newOriginAirport.equalsIgnoreCase("Quit") ) {
			return;
		}
		else {
			String newOriginCity = scan.next();
			fTemp.getRouteId().getOriginAirport().setAirportCode(newOriginAirport);
			fTemp.getRouteId().getOriginAirport().setAirportCode(newOriginCity);
		}

		System.out.println("Enter new Destination Airport and City or N/A for no change");
		String newDestAirport = scan.next();
		if (newDestAirport.equalsIgnoreCase("N/A") ) {
			
		}
		else if (newDestAirport.equalsIgnoreCase("Quit") ) {
			return;
		}
		else {
			String newDestCity = scan.nextLine();
			fTemp.getRouteId().getDestAirport().setAirportCode(newDestAirport);
			fTemp.getRouteId().getDestAirport().setCity(newDestCity);
		}
		
		System.out.println("Enter new Departure Date or N/A for no change (yyyy-mm-dd)");
		
		String newDate = scan.nextLine();
		if (newDate.equalsIgnoreCase("N/A") ) {
			
		}
		else if (newDate.equalsIgnoreCase("Quit") ) {
			return;
		}
		else {
				//flight.getDepartureTime().format(DateTimeFormatter.ofPattern("yyyy-mm-dd"));
			fTemp.setDepartureTime(LocalDate.parse(newDate));
			//fTemp.getDepartureTime().format(DateTimeFormatter.ofPattern("yyyy-mm-dd"));

		}
//		System.out.println("Enter new Departure Time or N/A for no change");
//		String newDepartTime = scan.next();
//		if (newDepartTime.equalsIgnoreCase("N/A") ) {
//			
//		}
//		else if (newDepartTime.equalsIgnoreCase("Quit") ) {
//			return;
//		}
//		else {
//			fTemp.getDepartureTime().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
//		}
		try {
			flightDao.updateFlight(fTemp);
			conn.commit();
			manageFlight(flight, scan, flightDao, conn);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	private void viewFlight(Flight flight, Scanner scan, FlightDAO flightDao, Connection conn) throws ClassNotFoundException, SQLException {
		System.out.println(
				"Flight id: " + flight.getFlightId() + "\nOrigin Airport: " + flight.getRouteId().getOriginAirport().getAirportCode()
						+ " " + flight.getRouteId().getOriginAirport().getCity() + " "
						+ "| Destination Airport: " + flight.getRouteId().getDestAirport().getAirportCode() + " " 
						+ flight.getRouteId().getDestAirport().getCity() + " " + "|\nDeparture Date: "
						+ flight.getDepartureTime().toString() + "\nTime: " + flight.getDepartureTime());
		seating(flight);
		int option = scan.nextInt();

		switch (option) {
		case (1): // First
			flight.getAirplaneId().getAirplaneType().setMaxCapacity(flight.getAirplaneId().getAirplaneType().getMaxCapacity() - 1);

			break;
		case (2): // Business
			flight.getAirplaneId().getAirplaneType().setMaxCapacity(flight.getAirplaneId().getAirplaneType().getMaxCapacity() - 1);
			break;
		case (3): // Economy
			flight.getAirplaneId().getAirplaneType().setMaxCapacity(flight.getAirplaneId().getAirplaneType().getMaxCapacity() - 1);
			break;
		case (4): // quit
			manageFlight(flight, scan, flightDao, conn);
		}
		manageFlight(flight, scan, flightDao, conn);
	}

	private void seating(Flight flight) {
		System.out.println("Seating\n1. First ->" + (flight.getAirplaneId().getAirplaneType().getMaxCapacity() - flight.getReservedSeats()) 
				+ "\n2. Business " + (flight.getAirplaneId().getAirplaneType().getMaxCapacity() - flight.getReservedSeats())
						+ "\n3. Economy " + (flight.getAirplaneId().getAirplaneType().getMaxCapacity() - flight.getReservedSeats())  +"\n4 Quit ");
		
	}

	public void addFlight() throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			
			AirportDAO airportDAO = new AirportDAO(conn);
			AirplaneDAO airplaneDAO = new AirplaneDAO(conn);
			FlightDAO fightDAO = new FlightDAO(conn);
			airportDAO.save("INSERT", null); //IAD
			airportDAO.save("INSERT", null); //CDG
			airplaneDAO.save("INSERT", null); //save airplane
			fightDAO.save("INSERT", null); //insert flight
			
			
			//> airportDao.save(IAD)
			//> airportDao.save(CDG)
			//> airplaneDao.save
			//> flightDAO.save()
			conn.commit(); //this makes change permanent
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		}
		finally {
			conn.close();
		}
	}
}
