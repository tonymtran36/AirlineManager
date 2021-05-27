package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.entity.Airplane;

public class AirplaneDAO extends BaseDAO<Airplane> {

	public AirplaneDAO(Connection conn) {
		super(conn);
	}

	public void addAirplane(Airplane airplane) throws ClassNotFoundException, SQLException {
		save("INSERT into airplane (id, type_id) values (?, ?)",
				new Object[] {airplane.getAirplaneId(), airplane.getAirplaneType()});
	}

	public void updateAirplane(Airplane airplane) throws ClassNotFoundException, SQLException {
		save("UPDATE airplane set type_id = ? where id = ?",
				new Object[] {airplane.getAirplaneType().getAirplaneType()});
	}
	
	public void deleteAirplane(Airplane airplane) throws ClassNotFoundException, SQLException {
		save("DELETE from airplane where id = ?", new Object[] {airplane.getAirplaneId()});
	}
	
	public List<Airplane> readAllAirplanes() throws ClassNotFoundException, SQLException {
		return read("SELECT * from airplane", null);
	}
	
//	public List<Route> readAllRoutesByAirportCode(String airportCode) throws ClassNotFoundException, SQLException {
//		return read("SELECT * from ROUTE where origin_id = ? OR destination_id = ?", new Object[] {airportCode, airportCode});
//	}

	@Override
	public List<Airplane> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Airplane> airplanes = new ArrayList<>();
		while (rs.next()) {
			Airplane airplane = new Airplane();
			airplane.setAirplaneId(rs.getInt("id"));
			airplane.getAirplaneType().setAirplaneType(rs.getInt("type_id"));
			airplanes.add(airplane);
		}
		return airplanes;
	}
}
