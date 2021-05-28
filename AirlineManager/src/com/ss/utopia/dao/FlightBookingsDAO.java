package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.entity.Booking;
import com.ss.utopia.entity.Flight;
import com.ss.utopia.entity.FlightBookings;

public class FlightBookingsDAO extends BaseDAO<FlightBookings> {
	
	public FlightBookingsDAO(Connection conn) {
		super(conn);
	}

	public void addFlightBookings(FlightBookings flightBookings) throws ClassNotFoundException, SQLException {
		save("insert into flight_bookings (flight_id, booking_id) values (?, ?)",
				new Object[] {flightBookings.getFlights().getFlightId(), flightBookings.getBookingId().getBooking()});
	}

	public void updateFlightBookings(FlightBookings flightBookings) throws ClassNotFoundException, SQLException {
		save("UPDATE flight_bookings set flight_id = ? booking_id = ?",
				new Object[] {flightBookings.getFlights(), flightBookings.getBookingId()});
	}
	
	public void deletesFlightBookings(FlightBookings flightBookings) throws ClassNotFoundException, SQLException {
		save("DELETE from flight_bookings where booking_id = ?", new Object[] {flightBookings.getBookingId().getBooking()});
	}
	
	public List<FlightBookings> readAllFlightBookingss() throws ClassNotFoundException, SQLException {
		return read("SELECT * from flight_bookings", null);
	}
	
//	public List<Route> readAllRoutesByAirportCode(String airportCode) throws ClassNotFoundException, SQLException {
//		return read("SELECT * from ROUTE where origin_id = ? OR destination_id = ?", new Object[] {airportCode, airportCode});
//	}

	@Override
	public List<FlightBookings> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<FlightBookings> flightBookingsList = new ArrayList<>();
		while (rs.next()) {
			FlightBookings flightBookings = new FlightBookings();

			Booking b = new Booking();
			b.setActive(false);
			b.setConfirmCode(rs.getString("confirmation_code"));
			flightBookings.setBookingId(b);
			
			Flight flight = new Flight();
			flight.getRouteId().setId(rs.getInt("route_id"));
			flight.getAirplaneId().setAirplaneId(rs.getInt("airplane_id"));
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-0M-dd hh:mm:ss");
			flight.setDepartureTime(LocalDate.parse(rs.getString("departure_time"), format));
			flight.setReservedSeats(rs.getInt("reserved_seats"));
			flight.setSeatPrice(rs.getFloat("seat_price"));
			flight.getRouteId().getOriginAirport().setAirportCode(rs.getString("iata_id"));
			flight.getRouteId().getOriginAirport().setCity(rs.getString("city"));
			flight.getRouteId().getDestAirport().setAirportCode(rs.getString(12));
			flight.getRouteId().getDestAirport().setCity(rs.getString(13));
			flight.getAirplaneId().getAirplaneType().setAirplaneType(rs.getInt("id"));
			flight.getAirplaneId().getAirplaneType().setMaxCapacity(rs.getInt("max_capacity"));
			flightBookings.setFlights(flight);
			
			flightBookings.getFlights().setFlightId(rs.getInt("flight_id"));
			flightBookings.getBookingId().setBooking(rs.getInt("booking_id"));
			flightBookingsList.add(flightBookings);
		}
		return flightBookingsList;
	}
}
