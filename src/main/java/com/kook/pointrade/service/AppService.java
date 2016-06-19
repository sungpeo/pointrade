package com.kook.pointrade.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppService {
	
	@Autowired
	TestDAO testDAO;
	
	public Map testdb() throws Exception{
		
		TestDTO testDTO = testDAO.selectTestById(1);
		System.out.println("testGet: " + testDTO.getTestValue());
		
		Map map = new HashMap<Integer, String>();
		map.put(testDTO.getTestKey(), testDTO.getTestValue());
		return map;
	}

}
