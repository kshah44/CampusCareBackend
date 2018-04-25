package com.ssdi.campuscare.dao;

import org.springframework.jdbc.core.RowMapper;
import com.ssdi.campuscare.model.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdministratorRowMapper implements RowMapper<Administrator> {
	
	public Administrator mapRow(ResultSet row, int rowNum) throws SQLException {
		Administrator administrator = new Administrator();
		administrator.setUserName(row.getString("username"));
		administrator.setFirstName(row.getString("firstname"));
		administrator.setLastName(row.getString("lastname"));
		administrator.setEmail(row.getString("email"));
		administrator.setPassword(row.getString("password"));
		return administrator;		
	}
}
