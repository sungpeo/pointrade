package com.kook.pointrade.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kook.pointrade.service.AppService;

@RestController
public class AppController {
	
	@Autowired
	AppService appService;
	
    @RequestMapping("/greeting")
    public String index() {
        return "Greetings from Spring Boot!";
    }
    
    @RequestMapping("/hello")
    public Map hello() {
    	Map<String, String> map = new HashMap<String, String>();
    	map.put("id", "1");
    	map.put("contents", "Hello, World!");
        return map;
    }
    
    @RequestMapping("/testdb")
    public Map testdb() throws Exception {
    	return appService.testdb();
    }
}
