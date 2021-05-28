package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
//import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.entity.Flight;

public class FlightDAO extends BaseDAO<Flight> {
	
	public FlightDAO(Connection conn) {
		super(conn);
	}

	public void addFlight(Flight flight) throws ClassNotFoundException, SQLException {
		save("INSERT into flight (id, route_id, airplane_id, departure_time, reserved_seats, seat_price) values (?, ?, ?, ?, ?, ?)",
				new Object[] {flight.getFlightId(), flight.getRouteId(), flight.getAirplaneId(), flight.getDepartureTime(), flight.getDepartureTime(),
						flight.getReservedSeats(), flight.getSeatPrice()});
	}

	public void updateFlight(Flight flight) throws ClassNotFoundException, SQLException {
		save("UPDATE flight set route_id = ?, airplane_id = ?, departure_time = ?, reserved_seats = ?, seat_price = ? where id = ?;",
				new Object[] {flight.getRouteId().getId(), flight.getAirplaneId().getAirplaneId(), flight.getDepartureTime().atStartOfDay(),
						flight.getReservedSeats(), flight.getSeatPrice(), flight.getFlightId()});
	}
	
	public void deletesFlight(Flight flight) throws ClassNotFoundException, SQLException {
		save("DELETE from flight where id = ?", new Object[] {flight.getFlightId()});
	}
	
	public List<Flight> readAllFlights() throws ClassNotFoundException, SQLException {
//		return read("SELECT * from flight join route on flight.route_id = route.id "
//				+ "join airport a1 on route.origin_id = a1.iata_id join airport a2 on route.origin_id = a2.iata_id "
//				+ "join airplane on flight.airplane_id = airplane.id " + 
//				"join airplane_type on airplane.id = airplane_type.id", null);
		return read("select * from flight join route on flight.route_id = route.id " + 
				"join airport a1 on route.origin_id = a1.iata_id " + 
				"join airport a2 on  route.destination_id = a2.iata_id " + 
				"join airplane on flight.airplane_id = airplane.id " + 
				"join airplane_type on airplane.id = airplane_type.id", null);
	}
	
	public List<Flight> readAllFlightsByOriginDest(String originCode, String destCode) throws ClassNotFoundException, SQLException {
		return read("SELECT * from flight where origin_id = ? AND destination_id = ?", new Object[] {originCode, destCode});
	}
	
//	public List<Route> readAllRoutesByAirportCode(String airportCode) throws ClassNotFoundException, SQLException {
//		return read("SELECT * from ROUTE where origin_id = ? OR destination_id = ?", new Object[] {airportCode, airportCode});
//	}

	@Override
	public List<Flight> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Flight> flights = new ArrayList<>();
		while (rs.next()) {
			Flight flight = new Flight();
			flight.setFlightId(rs.getInt("id"));
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
			flights.add(flight);
		}
		return flights;
	}
}
