package com.kook.pointrade.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kook.pointrade.service.UserService;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/user")
public class UserController {


	@Autowired
	private UserService userService;
	
//    @RequestMapping("/hello")
//    public Map hello() {
//    	Map<String, String> map = new HashMap<String, String>();
//    	map.put("id", "1");
//    	map.put("contents", "Hello, World!");
//        return map;
//    }
//    
//    @RequestMapping("/testdb")
//    public Map testdb() throws Exception {
//    	return appService.testdb();
//    }
    
    @RequestMapping(value="/signin", method=RequestMethod.POST)
	@ResponseBody
    public Map signin(@RequestParam("name")String name,
					  @RequestParam("birth")String birth,
					  @RequestParam("hp")String hp) throws Exception {
    	return userService.signin(name, birth, hp);
    }

	@RequestMapping(value="/signup", method=RequestMethod.PUT)
	@ResponseBody
	public Map signup(@RequestParam("name")String name,
					  @RequestParam("birth")String birth,
					  @RequestParam("hp")String hp) {
		//user insert
		Map ret = userService.signup(name, birth, hp);
		//basket insert

		return ret;
	}
}
