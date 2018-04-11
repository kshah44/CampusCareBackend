package com.ssdi.campuscare.dao;

import org.springframework.jdbc.core.RowMapper;
import com.ssdi.campuscare.model.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProviderRowMapper implements RowMapper<Provider> {

	public Provider mapRow(ResultSet row, int rowNum) throws SQLException {
		Provider provider = new Provider();
		provider.setUserName(row.getString("username"));
		provider.setFirstName(row.getString("firstname"));
		provider.setLastName(row.getString("lastname"));
		provider.setEmail(row.getString("email"));
		provider.setPassword(row.getString("password"));
		return provider;
	}
	@Override
	public boolean equals(Object rhs) {
		ProviderRowMapper rhs_actual = (ProviderRowMapper) rhs;
		if(rhs_actual.getClass() == this.getClass())
		{	
			return true;
		}
		else
		{
			return false;
		}
			
	}
}

