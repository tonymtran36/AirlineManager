package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.entity.UserRole;

public class UserRoleDAO extends BaseDAO<UserRole> {
	
	public UserRoleDAO(Connection conn) {
		super(conn);
	}

	public void addUserRole(UserRole userRole) throws ClassNotFoundException, SQLException {
		save("INSERT into user_role (id, name) values (?, ?)",
				new Object[] {userRole.getRoleId(), userRole.getName()});
	}

	public void updateUserRole(UserRole userRole) throws ClassNotFoundException, SQLException {
		save("UPDATE user_role set name = ? where id = ?",
				new Object[] {userRole.getName()});
	}
	
	public void deleteAirplane(UserRole userRole) throws ClassNotFoundException, SQLException {
		save("DELETE from user_role", new Object[] {userRole.getRoleId()});
	}
	
	public List<UserRole> readAllUserRoles() throws ClassNotFoundException, SQLException {
		return read("SELECT * from user_role", null);
	}
	
//	public List<Route> readAllRoutesByAirportCode(String airportCode) throws ClassNotFoundException, SQLException {
//		return read("SELECT * from ROUTE where origin_id = ? OR destination_id = ?", new Object[] {airportCode, airportCode});
//	}

	@Override
	public List<UserRole> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<UserRole> userRoles = new ArrayList<>();
		while (rs.next()) {
			UserRole userRole = new UserRole();
			userRole.setRoleId(rs.getInt("id"));
			userRole.setName(rs.getString("name"));
			userRoles.add(userRole);
		}
		return userRoles;
	}
}
