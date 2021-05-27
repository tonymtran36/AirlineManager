package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.entity.BookingAgent;

public class BookingAgentDAO extends BaseDAO<BookingAgent> {
	
	public BookingAgentDAO(Connection conn) {
		super(conn);
	}

	public void addBookingAgent(BookingAgent bookingAgent) throws ClassNotFoundException, SQLException {
		save("INSERT into booking_agent (booking_id, user_id) values (?, ?)",
				new Object[] {bookingAgent.getId(), bookingAgent.getAgentId()});
	}

	public void updateBookingAgent(BookingAgent bookingAgent) throws ClassNotFoundException, SQLException {
		save("UPDATE booking_agent set booking_id = ?, user_id = ?",
				new Object[] {bookingAgent.getId(), bookingAgent.getAgentId()});
	}
	
	public void deleteUser(BookingAgent bookingAgent) throws ClassNotFoundException, SQLException {
		save("DELETE from booking_agent where id = ?", new Object[] {bookingAgent.getId()});
	}
	
	public List<BookingAgent> readAllBookingAgents() throws ClassNotFoundException, SQLException {
		return read("SELECT * from booking_agent", null);
	}
	
//	public List<Route> readAllRoutesByAirportCode(String airportCode) throws ClassNotFoundException, SQLException {
//		return read("SELECT * from ROUTE where origin_id = ? OR destination_id = ?", new Object[] {airportCode, airportCode});
//	}

	@Override
	public List<BookingAgent> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<BookingAgent> bookingAgents = new ArrayList<>();
		while (rs.next()) {
			BookingAgent bookingAgent = new BookingAgent();
			bookingAgent.getId().setBooking(rs.getInt("booking_id"));;
			bookingAgent.setAgentId(rs.getInt("agent_id"));
			bookingAgents.add(bookingAgent);
		}
		return bookingAgents;
	}
}
