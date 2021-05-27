package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.entity.BookingPayment;

public class BookingPaymentDAO extends BaseDAO<BookingPayment>{
	
	public BookingPaymentDAO(Connection conn) {
		super(conn);
	}

	public void addBookingPayment(BookingPayment bookingPayment) throws ClassNotFoundException, SQLException {
		save("INSERT into booking_payment (booking_id, stripe_id, refunded) values (?, ?, ?)",
				new Object[] {bookingPayment.getBookingId(), bookingPayment.getStripeId(), bookingPayment.getRefund()});
	}

	public void updateBookingPayment(BookingPayment bookingPayment) throws ClassNotFoundException, SQLException {
		save("UPDATE booking_payment set booking_id = ?, stripe_id = ?, refunded = ?",
				new Object[] {bookingPayment.getBookingId(), bookingPayment.getStripeId(), bookingPayment.getRefund()});
	}
	
	public void deletesBookingPayment(BookingPayment bookingPayment) throws ClassNotFoundException, SQLException {
		save("DELETE from booking_payment where id = ?", new Object[] {bookingPayment.getBookingId()});
	}
	
	public List<BookingPayment> readAllBookingPayments() throws ClassNotFoundException, SQLException {
		return read("SELECT * from booking_payment", null);
	}
	
//	public List<Route> readAllRoutesByAirportCode(String airportCode) throws ClassNotFoundException, SQLException {
//		return read("SELECT * from ROUTE where origin_id = ? OR destination_id = ?", new Object[] {airportCode, airportCode});
//	}

	@Override
	public List<BookingPayment> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<BookingPayment> bookingPayments = new ArrayList<>();
		while (rs.next()) {
			BookingPayment bookingPayment = new BookingPayment();
			bookingPayment.getBookingId().setBooking(rs.getInt("booking_id"));
			bookingPayment.setStripeId(rs.getString("stripe_id"));
			bookingPayment.setRefund(rs.getBoolean("refunded"));
			bookingPayments.add(bookingPayment);
		}
		return bookingPayments;
	}
}
