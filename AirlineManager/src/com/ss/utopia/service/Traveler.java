package com.ss.utopia.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.ss.utopia.dao.FlightBookingsDAO;
import com.ss.utopia.dao.FlightDAO;
import com.ss.utopia.dao.UserRoleDAO;
import com.ss.utopia.entity.Booking;
import com.ss.utopia.entity.Flight;
import com.ss.utopia.entity.FlightBookings;
import com.ss.utopia.entity.UserRole;

public class Traveler {
	ConnectionUtil connUtil = new ConnectionUtil();

	private void membershipNumber() {
		System.out.println("Enter your membership number: ");
	}
	private void travelOne() {
		System.out.println("1. Book a Ticket\n2. Cancel an Upcoming Trip\n3. Quit to Previous");
	}
	
	public void validateTicket(Scanner scan) {
		membershipNumber();
		
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			UserRoleDAO userRoleDao = new UserRoleDAO(conn);
			List<UserRole> userRoles = userRoleDao.readAllUserRoles();
			//User traveler = new User();

			while (scan.hasNextInt()) {
				int memNum = scan.nextInt();
				for (int i = 0; i<userRoles.size();i++) {
					if (memNum == userRoles.get(i).getRoleId()) {
						travelOne();
						int option = scan.nextInt();

						switch (option) {
						case (1): // Book a ticket
							bookTicket(conn, userRoles.get(i), scan);
							break;
						case (2): // Cancel a ticket
							deleteTicket(conn, userRoles.get(i), scan);
							break;
						case (3): // Quit to Previous
							return;
						}
					}
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void viewSeatingPrompt() {
		System.out.println("1. View more details\n2. First\n3. Business \n4. Economy\n5.Quit ");
	}
	public void bookTicket(Connection conn, UserRole userRole, Scanner scan) { //After choosing option 1 in Trav1
		try {			
			FlightDAO flightsDao = new FlightDAO(conn);
			List<Flight> flights = flightsDao.readAllFlights();
			printFlights(flights);
			System.out.println((flights.size() + 1) + " Quit");
			int flightChoice = scan.nextInt();
			if (flightChoice > 0 && flightChoice <= flights.size() ) {
				Flight flight = flights.get(flightChoice);
				viewSeatingOptions(scan, flight, userRole);
			}
			else validateTicket(scan);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void viewSeatingOptions(Scanner scan, Flight flight, UserRole userRole) {
		viewSeatingPrompt();
		if (scan.hasNextInt()) {
			int option = scan.nextInt();
			switch(option) {
			case 1:
				System.out.println(flight.toString());
				break;
			case 2:
				try {
					addTicket(flight, userRole, 1);
					break;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			case 3:
				try {
					addTicket(flight, userRole, 2);
					break;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			case 4: 
				try {
					addTicket(flight, userRole, 3);
					break;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			case 5:
				validateTicket(scan);
			}
		}
	
	}

	private void printFlights(List<Flight> flights) {
		for (int i = 0; i < flights.size(); i++) {
			System.out.println((i+1) + " " + flights.get(i).getRouteId().getOriginAirport().getAirportCode() 
					+ ", " + flights.get(i).getRouteId().getOriginAirport().getCity() + " -> " 
					+ flights.get(i).getRouteId().getDestAirport().getAirportCode() + ", "
					+ flights.get(i).getRouteId().getDestAirport().getCity()); 
		}
	}
	
	public void deleteTicket(Connection conn, UserRole userRole, Scanner scan) { //After choosing option 1 in Trav1
		try {			
			FlightDAO flightsDao = new FlightDAO(conn);
			List<Flight> flights = flightsDao.readAllFlights();
			printFlights(flights);
			System.out.println((flights.size() + 1) + " Quit");
			int flightChoice = scan.nextInt();
			Flight flight = flights.get(flightChoice);
			if (flightChoice > 0 && flightChoice <= flights.size() ) {
				viewCancelOptions(scan, flight, userRole);
			}
			else validateTicket(scan);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	private void viewCancelOptions(Scanner scan, Flight flight, UserRole userRole) {
		viewSeatingPrompt();
		if (scan.hasNextInt()) {
			int option = scan.nextInt();
			switch(option) {
			case 1:
				System.out.println(flight.toString());
				break;
			case 2:
				try {
					cancelTicket(userRole);
					break;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			case 3:
				try {
					cancelTicket(userRole);
					break;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			case 4: 
				try {
					cancelTicket(userRole);
					break;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			case 5:
				validateTicket(scan);
			}
		}
	
	}
	
	public void cancelTicket(UserRole userRole) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			FlightBookingsDAO flightBookingsDao = new FlightBookingsDAO(conn);
			
			
			Booking booking = new Booking();
			booking.setBooking(userRole.getRoleId());
			FlightBookings flightBookings = new FlightBookings();
			
			flightBookings.setBookingId(booking);
			
			flightBookingsDao.deletesFlightBookings(flightBookings);
			conn.commit(); //this makes change permanent			
			System.out.println("Deleted Ticket Successfully!");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		}
		finally {
			conn.close();
		}	
	}
	
	
	public void addTicket(Flight flight, UserRole userRole, int i) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			FlightBookingsDAO flightBookingsDao = new FlightBookingsDAO(conn);
			
			
			Booking booking = new Booking();
			booking.setBooking(userRole.getRoleId());
			FlightBookings flightBookings = new FlightBookings();
			
			flightBookings.setFlights(flight);
			flightBookings.setBookingId(booking);
			

			flightBookingsDao.addFlightBookings(flightBookings);
			conn.commit(); //this makes change permanent
			System.out.println("Added Ticket Successfully!");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		}
		finally {
			conn.close();
		}
	}
}
