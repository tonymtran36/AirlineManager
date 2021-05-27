package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.entity.BookingGuest;

public class BookingGuestDAO extends BaseDAO<BookingGuest>{
	
	public BookingGuestDAO(Connection conn) {
		super(conn);
	}

	public void addBookingGuest(BookingGuest bookingGuest) throws ClassNotFoundException, SQLException {
		save("INSERT into booking_guest (booking_id, contact_email, contact_phone) values (?, ?, ?)",
				new Object[] {bookingGuest.getId(), bookingGuest.getEmail(), bookingGuest.getPhoneNum()});
	}

	public void updateBookingGuest(BookingGuest bookingGuest) throws ClassNotFoundException, SQLException {
		save("UPDATE booking_guest set booking_id = ?, contact_email = ?, contact_phone = ?",
				new Object[] {bookingGuest.getId(), bookingGuest.getEmail(), bookingGuest.getPhoneNum()});
	}
	
	public void deleteBookingGuest(BookingGuest bookingGuest) throws ClassNotFoundException, SQLException {
		save("DELETE from booking_guest where id = ?", new Object[] {bookingGuest.getId()});
	}
	
	public List<BookingGuest> readAllBookingGuests() throws ClassNotFoundException, SQLException {
		return read("SELECT * from booking_guest", null);
	}
	
//	public List<Route> readAllRoutesByAirportCode(String airportCode) throws ClassNotFoundException, SQLException {
//		return read("SELECT * from ROUTE where origin_id = ? OR destination_id = ?", new Object[] {airportCode, airportCode});
//	}

	@Override
	public List<BookingGuest> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<BookingGuest> bookingGuests = new ArrayList<>();
		while (rs.next()) {
			BookingGuest bookingGuest = new BookingGuest();
			bookingGuest.getId().setBooking(rs.getInt("booking_id"));
			bookingGuest.setEmail(rs.getString("contact_email"));
			bookingGuest.setPhoneNum(rs.getString("contact_phone"));
			bookingGuests.add(bookingGuest);
		}
		return bookingGuests;
	}
}
