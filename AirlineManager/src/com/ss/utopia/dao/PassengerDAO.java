package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.entity.Passenger;

public class PassengerDAO extends BaseDAO<Passenger>{
	
	public PassengerDAO(Connection conn) {
		super(conn);
	}

	public void addPassenger(Passenger passenger) throws ClassNotFoundException, SQLException {
		save("INSERT into passenger (booking_id, given_name, family_name, dob, gender, address) values (?, ?, ?, ?, ?, ?)",
				new Object[] {passenger.getBookingId(), passenger.getName(), passenger.getFamilyName(), passenger.getBirthdate(),
						passenger.getGender(), passenger.getAddress()});
	}

	public void updatePassenger(Passenger passenger) throws ClassNotFoundException, SQLException {
		save("UPDATE passenger set booking_id = ?, given_name = ?, family_name = ?, dob = ?, gender = ?, address = ?",
				new Object[] {passenger.getBookingId(), passenger.getName(), passenger.getFamilyName(), passenger.getBirthdate(),
						passenger.getGender(), passenger.getAddress()});
	}
	
	public void deleteUser(Passenger passenger) throws ClassNotFoundException, SQLException {
		save("DELETE from passenger where id = ?", new Object[] {passenger.getPassengerId()});
	}
	
	public List<Passenger> readAllPassengers() throws ClassNotFoundException, SQLException {
		return read("SELECT * from passenger", null);
	}
	
//	public List<Route> readAllRoutesByAirportCode(String airportCode) throws ClassNotFoundException, SQLException {
//		return read("SELECT * from ROUTE where origin_id = ? OR destination_id = ?", new Object[] {airportCode, airportCode});
//	}

	@Override
	public List<Passenger> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Passenger> passengers = new ArrayList<>();
		while (rs.next()) {
			Passenger passenger = new Passenger();
			passenger.getBookingId().setBooking(rs.getInt("booking_id"));
			passenger.setName(rs.getString("given_name"));
			passenger.setFamilyName(rs.getString("family_name"));
			passenger.setBirthdate(rs.getDate("dob"));
			passenger.setGender(rs.getString("gender"));
			passenger.setAddress(rs.getString("address"));
			passengers.add(passenger);
		}
		return passengers;
	}
}
