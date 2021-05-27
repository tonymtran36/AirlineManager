package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.entity.FlightBookings;

public class FlightBookingsDAO extends BaseDAO<FlightBookings> {
	
	public FlightBookingsDAO(Connection conn) {
		super(conn);
	}

	public void addFlightBookings(FlightBookings flightBookings) throws ClassNotFoundException, SQLException {
		save("insert into flight_bookings (flight_id, booking_id) values (?, ?)",
				new Object[] {flightBookings.getFlights(), flightBookings.getBookingId()});
	}

	public void updateFlightBookings(FlightBookings flightBookings) throws ClassNotFoundException, SQLException {
		save("UPDATE flight_bookings set flight_id = ? booking_id = ?",
				new Object[] {flightBookings.getFlights(), flightBookings.getBookingId()});
	}
	
	public void deletesFlightBookings(FlightBookings flightBookings) throws ClassNotFoundException, SQLException {
		save("DELETE from flight_bookings where booking_id = ?", new Object[] {flightBookings.getBookingId()});
	}
	
	public List<FlightBookings> readAllFlightBookingss() throws ClassNotFoundException, SQLException {
		return read("SELECT * from flight_bookings", null);
	}
	
//	public List<Route> readAllRoutesByAirportCode(String airportCode) throws ClassNotFoundException, SQLException {
//		return read("SELECT * from ROUTE where origin_id = ? OR destination_id = ?", new Object[] {airportCode, airportCode});
//	}

	@Override
	public List<FlightBookings> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<FlightBookings> flightBookingss = new ArrayList<>();
		while (rs.next()) {
			FlightBookings flightBookings = new FlightBookings();
			flightBookings.setFlights(rs.getInt("flight_id"));
			flightBookings.getBookingId().setBooking(rs.getInt("booking_id"));
			flightBookingss.add(flightBookings);
		}
		return flightBookingss;
	}
}
