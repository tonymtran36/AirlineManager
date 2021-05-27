package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.entity.Airport;

public class AirportDAO extends BaseDAO<Airport>{

	public AirportDAO(Connection conn) {
		super(conn);
	}

	public void addRoute(Airport airport) throws ClassNotFoundException, SQLException {
		save("INSERT into airport (iata_id, city) values (?, ?)",
				new Object[] {airport.getAirportCode(), airport.getCity()});
	}

	public void updateAirport(Airport airport) throws ClassNotFoundException, SQLException {
		save("UPDATE airport set = ?, city = ?",
				new Object[] {airport.getAirportCode(), airport.getCity()});
	}
	
	public void deleteAirport(Airport airport) throws ClassNotFoundException, SQLException {
		save("DELETE from airport where iata_id = ?", new Object[] {airport.getAirportCode()});
	}
	
	public List<Airport> readAllAirports() throws ClassNotFoundException, SQLException {
		return read("SELECT * from airport", null);
	}
	
	public List<Airport> readAllRoutesByAirportCode(String airportCode) throws ClassNotFoundException, SQLException {
		return read("SELECT * from airport where city = ?", new Object[] {airportCode});
	}

	@Override
	public List<Airport> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Airport> airports = new ArrayList<>();
		while (rs.next()) {
			Airport airport = new Airport();
			airport.setAirportCode(rs.getString("iata_id"));
			airport.setCity(rs.getString("city"));
			airports.add(airport);
		}
		return airports;
	}
}
