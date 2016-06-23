package com.kook.pointrade.dao;

import com.kook.pointrade.domain.PbasketDTO;
import com.kook.pointrade.domain.UserDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Sungpyo on 2016-06-12.
 */
@Component
public class BasketDAO {

    @Autowired
    private SqlSession sqlSession;

    public List<PbasketDTO> selectBasketByUserWithCriteria(long userKey, long criteria) {
        PbasketDTO in = new PbasketDTO();
        in.setUserKey(userKey);
        in.setCriteria(criteria);
        return this.sqlSession.selectList("selectBasketByUserWithCriteria", userKey);
    }


}
