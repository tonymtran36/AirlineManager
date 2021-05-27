package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.entity.User;

public class UserDAO extends BaseDAO<User> {
	
	public UserDAO(Connection conn) {
		super(conn);
	}

	public void addUser(User user) throws ClassNotFoundException, SQLException {
		save("INSERT into user (id, role_id, given_name, family_name, username, email, password, phone) values (?, ?, ?, ?, ?, ?, ?, ?)",
				new Object[] {user.getUserId(), user.getRoleId(), user.getName(), user.getFamilyName(), user.getUsername(),
						user.getEmail(), user.getPassword(), user.getPhoneNum()});
	}

	public void updateUser(User user) throws ClassNotFoundException, SQLException {
		save("UPDATE airplane set role_id = ?, given_name = ?, family_name = ?, username = ?, email = ?, password = ?, phone = ? where id = ?",
				new Object[] {user.getRoleId(), user.getName(), user.getFamilyName(), user.getUsername(), user.getEmail(),
						user.getPassword(), user.getPhoneNum()});
	}
	
	public void deleteUser(User user) throws ClassNotFoundException, SQLException {
		save("DELETE from user where id = ?", new Object[] {user.getUserId()});
	}
	
	public List<User> readAllUser() throws ClassNotFoundException, SQLException {
		return read("SELECT * from user", null);
	}
	
//	public List<Route> readAllRoutesByAirportCode(String airportCode) throws ClassNotFoundException, SQLException {
//		return read("SELECT * from ROUTE where origin_id = ? OR destination_id = ?", new Object[] {airportCode, airportCode});
//	}

	@Override
	public List<User> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<User> users = new ArrayList<>();
		while (rs.next()) {
			User user = new User();
			user.setUserId(rs.getInt("id"));
			user.getRoleId().setRoleId(rs.getInt("role_id"));
			user.setName(rs.getString("given_name"));
			user.setFamilyName(rs.getString("family_name"));
			user.setUsername(rs.getString("username"));
			user.setEmail(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setPhoneNum(rs.getString("phone"));
			users.add(user);
		}
		return users;
	}
}
