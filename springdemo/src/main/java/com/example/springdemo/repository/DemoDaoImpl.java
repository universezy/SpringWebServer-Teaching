package com.example.springdemo.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.example.springdemo.model.DemoMapper;
import com.example.springdemo.model.DemoVO;
import com.example.springdemo.others.Constant;

public class DemoDaoImpl implements DemoDao {
	private JdbcTemplate jdbcTemplate;

	public int addDemo(int demoId, String demoName) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int deleteDemo(int demoId) {
		// TODO Auto-generated method stub
		return 0;
	}

	public DemoVO queryDemoById(int demoId) {
		String SQL = "select * from " + Constant.Demo.TABLE_NAME + " where " + Constant.Demo.COLUMN_ID + " = ?";
		return jdbcTemplate.queryForObject(SQL, new DemoMapper(), demoId);
	}

	public List<DemoVO> queryDemosByName(String demoName) {
		// TODO Auto-generated method stub
		return null;
	}

	public int updateDemoById(int demoId, String demoName) {
		// TODO Auto-generated method stub
		return 0;
	}

}
