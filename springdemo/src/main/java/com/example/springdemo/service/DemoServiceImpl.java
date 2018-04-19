package com.example.springdemo.service;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONObject;
import com.example.springdemo.model.DemoVO;
import com.example.springdemo.repository.DemoDao;

public class DemoServiceImpl implements DemoService {
	@Resource(name = "demoDao")
	private DemoDao demoDao;

	public String test(int id) {
		DemoVO demoVO = demoDao.queryDemoById(id);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", demoVO.getId());
		jsonObject.put("name", demoVO.getName());
		return jsonObject.toJSONString();
	}

}
