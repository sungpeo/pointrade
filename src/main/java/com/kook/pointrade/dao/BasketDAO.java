package com.kook.pointrade.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kook.pointrade.domain.PbasketDTO;

/**
 * Created by Sungpyo on 2016-06-12.
 */
@Component
public class BasketDAO {

    @Autowired
    private SqlSessionTemplate sqlSession;

    public List<PbasketDTO> selectBasketByUserWithCriteria(int userKey, int criteria) {
        PbasketDTO in = new PbasketDTO();
        in.setUserKey(userKey);
        in.setCriteria(criteria);
        return this.sqlSession.selectList("selectBasketByUserWithCriteria", in);
    }

    public PbasketDTO selectBasketByUserPoint(int userKey, int pointKey){
        PbasketDTO in = new PbasketDTO();
        in.setUserKey(userKey);
        in.setPointKey(pointKey);
        return this.sqlSession.selectOne("selectBasketByUserPoint", in);
    }


    public int updatePbasketByUserPoint(int userKey, int pointKey, int balance){

        PbasketDTO in = new PbasketDTO();
        in.setUserKey(userKey);
        in.setPointKey(pointKey);
        in.setBalance(balance);
        return this.sqlSession.update("updatePbasketByUserPoint", in);

    }


}
