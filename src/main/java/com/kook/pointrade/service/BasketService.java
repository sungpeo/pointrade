package com.kook.pointrade.service;

import com.kook.pointrade.dao.BasketDAO;
import com.kook.pointrade.dao.UserDAO;
import com.kook.pointrade.domain.PbasketDTO;
import com.kook.pointrade.domain.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BasketService {

	@Autowired
	BasketDAO basketDAO;

	public Map<String, Object> selectBasketByUserWithCriteria(int userKey, int criteria){
		Map<String, Object> rMap = new HashMap<String, Object>();

		List<PbasketDTO> list = basketDAO.selectBasketByUserWithCriteria(userKey, criteria);
		rMap.put("basketList", list);
		return rMap;
		
	}



}
