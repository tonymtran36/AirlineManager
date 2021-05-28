package com.ss.utopia.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.ss.utopia.entity.Booking;
import com.ss.utopia.entity.Flight;
import com.ss.utopia.entity.FlightBookings;
import com.ss.utopia.entity.Route;

public class ReadRoutes {
	
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/utopia";
	private static final String username = "root";
	private static final String password = "Tonytran6474";

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//1. Register the driver (MySQL)
		Class.forName(driver);
		//2. Connection
		Connection conn = DriverManager.getConnection(url, username, password);

		String query3 = "insert into flight_bookings (flight_id, booking_id) values (?, ?)";
		
		

//		PreparedStatement pstmt = conn.prepareStatement("UPDATE route set origin_id = ? destination_id = ? where id = ? ");
//		pstmt.setString(1, "POC");
//		pstmt.setString(2, "NYC");
//		pstmt.setInt(3, 3);
		LocalDate date = LocalDate.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-0MM-dd hh:mm:ss");
		//flight.setDepartureTime(LocalDate.parse(rs.getString("departure_time"), format));
		//preparedStatement.setTimestamp(index, new Timestamp(System.currentTimeMillis()));
		
		
		PreparedStatement pstmt = conn.prepareStatement("UPDATE flight set route_id = ? airplane_id = ? "
				+ "departure_time = ? reserved_seats = ? seat_price = ? where id = ?;");
		Route r1 = new Route();
		r1.getOriginAirport().setAirportCode("POC");
		r1.getOriginAirport().setCity("Pocatello");
		Route r2 = new Route();
		r2.getOriginAirport().setAirportCode("NYC");
		r2.getOriginAirport().setCity("New York-NYC");
		pstmt.setInt(1, 2);
		pstmt.setInt(2, 3);
		
		//pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
		
		pstmt.setString(3, "2021-05-29 00:00:00");
		pstmt.setInt(4, 1);
		pstmt.setFloat(5, 25);
		pstmt.setInt(6, 3);
		

		System.out.println(pstmt);
//UPDATE flight set route_id = 2 airplane_id = 3 departure_time = '2021-05-29 00:00:00' reserved_seats = 1 seat_price = 25.0 where id = 3;
//UPDATE flight set route_id = 2 airplane_id = 3 departure_time = '2021-06-29 00:00:00' reserved_seats = 1 seat_price = 25.0 where id = 3;
	}

}