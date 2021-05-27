package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.entity.AirplaneType;

public class AirplaneTypeDAO extends BaseDAO<AirplaneType>{
	
	public AirplaneTypeDAO(Connection conn) {
		super(conn);
	}

	public void addAirplaneType(AirplaneType airplaneType) throws ClassNotFoundException, SQLException {
		save("INSERT into airplane_type (id, max_capacity) values (?, ?)",
				new Object[] {airplaneType.getAirplaneType(), airplaneType.getMaxCapacity()});
	}

	public void updateAirplaneType(AirplaneType airplaneType) throws ClassNotFoundException, SQLException {
		save("UPDATE airplane_type set id = ?, max_capacity = ?",
				new Object[] {airplaneType.getAirplaneType(), airplaneType.getMaxCapacity()});
	}

	public void updateAirplaneTypeByCapacity(AirplaneType airplaneType) throws ClassNotFoundException, SQLException {
		save("update airplane_type set airplane_type.max_capacity = ? where id = ?",
				new Object[] {airplaneType.getMaxCapacity(), airplaneType.getAirplaneType()});
	}
	
	public void deleteAirplaneType(AirplaneType airplaneType) throws ClassNotFoundException, SQLException {
		save("DELETE from airplane_type where id = ?", new Object[] {airplaneType.getAirplaneType()});
	}
	
	public List<AirplaneType> readAllAirplaneTypes() throws ClassNotFoundException, SQLException {
		return read("SELECT * from airplane_type", null);
	}
	
//	public List<AirplaneType> readAllRoutesByAirportCode(String airplaneType) throws ClassNotFoundException, SQLException {
//		return read("SELECT * from airplane_type where city = ?", new Object[] {airportCode});
//	}

	@Override
	public List<AirplaneType> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<AirplaneType> airplaneTypes = new ArrayList<>();
		while (rs.next()) {
			AirplaneType airplaneType = new AirplaneType();
			airplaneType.setAirplaneType(rs.getInt("id"));
			airplaneType.setMaxCapacity(rs.getInt("max_capacity"));
			airplaneTypes.add(airplaneType);
		}
		return airplaneTypes;
	}
}
