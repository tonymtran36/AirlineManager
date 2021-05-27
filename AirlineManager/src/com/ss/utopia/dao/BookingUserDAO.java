package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.entity.BookingUser;

public class BookingUserDAO extends BaseDAO<BookingUser>{
	
	public BookingUserDAO(Connection conn) {
		super(conn);
	}

	public void addBookingUser(BookingUser bookingUser) throws ClassNotFoundException, SQLException {
		save("INSERT into booking_user (booking_id, agent_id) values (?, ?)",
				new Object[] {bookingUser.getId(), bookingUser.getUserId()});
	}

	public void updateBookingUser(BookingUser bookingUser) throws ClassNotFoundException, SQLException {
		save("UPDATE booking_user set booking_id = ?, agent_id = ?",
				new Object[] {bookingUser.getId(), bookingUser.getUserId()});
	}
	
	public void deleteUser(BookingUser bookingUser) throws ClassNotFoundException, SQLException {
		save("DELETE from booking_user where id = ?", new Object[] {bookingUser.getId()});
	}
	
	public List<BookingUser> readAllBookingUsers() throws ClassNotFoundException, SQLException {
		return read("SELECT * from booking_user", null);
	}
	
//	public List<Route> readAllRoutesByAirportCode(String airportCode) throws ClassNotFoundException, SQLException {
//		return read("SELECT * from ROUTE where origin_id = ? OR destination_id = ?", new Object[] {airportCode, airportCode});
//	}

	@Override
	public List<BookingUser> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<BookingUser> bookingUsers = new ArrayList<>();
		while (rs.next()) {
			BookingUser bookingUser = new BookingUser();
			bookingUser.getId().setBooking(rs.getInt("booking_id"));;
			bookingUser.setUserId(rs.getInt("agent_id"));
			bookingUsers.add(bookingUser);
		}
		return bookingUsers;
	}
}
