package com.ssdi.campuscare.dao;

import org.springframework.jdbc.core.RowMapper;
import com.ssdi.campuscare.model.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsumerRowMapper implements RowMapper<Consumer> {
	
	public Consumer mapRow(ResultSet row, int rowNum) throws SQLException {
		Consumer consumer = new Consumer();
		consumer.setUserName(row.getString("username"));
		consumer.setFirstName(row.getString("firstname"));
		consumer.setLastName(row.getString("lastname"));
		consumer.setEmail(row.getString("email"));
		consumer.setPassword(row.getString("password"));
		return consumer;
	}
	
	@Override
	public boolean equals(Object rhs) {
		ConsumerRowMapper rhs_actual = (ConsumerRowMapper) rhs;
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
