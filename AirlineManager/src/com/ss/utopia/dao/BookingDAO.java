package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.entity.Booking;


public class BookingDAO extends BaseDAO<Booking> {
	
	public BookingDAO(Connection conn) {
		super(conn);
	}

	public void addBooking(Booking booking) throws ClassNotFoundException, SQLException {
		save("INSERT into booking (is_active, confirmation_code) values (?, ?)",
				new Object[] {booking.isActive(), booking.getConfirmCode()});
	}

	public void updateBooking(Booking booking) throws ClassNotFoundException, SQLException {
		save("UPDATE booking set is_active= ?, confirmation_code = ? where id = ?",
				new Object[] {booking.isActive(), booking.getConfirmCode()});
	}
	
	public void deleteBooking(Booking booking) throws ClassNotFoundException, SQLException {
		save("DELETE from booking where id = ?", new Object[] {booking.getBooking()});
	}
	
	public List<Booking> readAllBookings() throws ClassNotFoundException, SQLException {
		return read("SELECT * from booking", null);
	}
	
//	public List<Route> readAllRoutesByAirportCode(String airportCode) throws ClassNotFoundException, SQLException {
//		return read("SELECT * from ROUTE where origin_id = ? OR destination_id = ?", new Object[] {airportCode, airportCode});
//	}

	@Override
	public List<Booking> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Booking> bookings = new ArrayList<>();
		while (rs.next()) {
			Booking booking = new Booking();
			booking.setBooking(rs.getInt("id"));
			booking.setActive(rs.getBoolean("is_active"));
			booking.setConfirmCode(rs.getString("refunded"));
			bookings.add(booking);
		}
		return bookings;
	}
}
