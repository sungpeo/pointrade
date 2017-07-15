package com.kook.pointrade.service;

import com.kook.pointrade.dao.UserDAO;
import com.kook.pointrade.domain.UserDTO;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

	@Autowired
	UserDAO userDAO;

	public Map<String, Object> signin(String name, String birth, String hp){
		Map<String, Object> rMap = new HashMap<String, Object>();
		rMap.put("login",false);

		//user 정보 조회
		UserDTO inuser = new UserDTO();
		inuser.setName(name);
		inuser.setBirth(birth);
		inuser.setHp(hp);
		UserDTO user = userDAO.selectByNameAndBirthAndHp(inuser);
		if(user==null){
			rMap.put("r", "noUser");
		}else{
			rMap.put("login",true);
			rMap.put("userKey",user.getUserkey());
			rMap.put("name",user.getName());
			rMap.put("birth",user.getBirth());
			rMap.put("hp",user.getHp());
		}
		return rMap;
		
	}

	public Map<String, Object> signup (String name, String birth, String hp) {

		UserDTO inuser = new UserDTO();
		inuser.setName(name);
		inuser.setBirth(birth);
		inuser.setHp(hp);

		int result = userDAO.insertUser(inuser);

		Map<String, Object> rMap = new HashMap<>();
		rMap.put("result", result);
		return rMap;
	}


//	private static String encryptString(String plain){
//		try {
//			MessageDigest md = MessageDigest.getInstance("SHA-512");
//			byte[] bytes = plain.getBytes(Charset.forName("UTF-8"));
//			md.update(bytes);
//			return Base64.encode(md.digest());
//		} catch (Exception e) {
//			System.out.println("Sha512 error.");
//			e.printStackTrace();
//			return null;
//		}
//	}
}
