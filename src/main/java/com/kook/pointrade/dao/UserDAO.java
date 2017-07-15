package com.kook.pointrade.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kook.pointrade.domain.UserDTO;

/**
 * Created by Sungpyo on 2016-06-12.
 */
@Component
public class UserDAO {

    @Autowired
    private SqlSessionTemplate sqlSession;

    public UserDTO selectByNameAndBirthAndHp(UserDTO user) {
        return this.sqlSession.selectOne("selectByNameAndBirthAndHp", user);
    }

    public int insertUser(UserDTO user) {
        return this.sqlSession.insert("insertUser", user);
    }


}
