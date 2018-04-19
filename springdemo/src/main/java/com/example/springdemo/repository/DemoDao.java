package com.example.springdemo.repository;

import java.util.List;

import com.example.springdemo.model.DemoVO;

public interface DemoDao {
	int addDemo(int demoId, String demoName);

	int deleteDemo(int demoId);

	DemoVO queryDemoById(int demoId);

	List<DemoVO> queryDemosByName(String demoName);

	int updateDemoById(int demoId, String demoName);
}
