package com.example.springdemo.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class DemoMapper implements RowMapper<DemoVO> {

	public DemoVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		DemoVO demoVO = new DemoVO();
		demoVO.setId(rs.getInt("id"));
		demoVO.setName(rs.getString("name"));
		return demoVO;
	}

}
