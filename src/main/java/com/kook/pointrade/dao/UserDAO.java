package com.kook.pointrade.dao;

import com.kook.pointrade.domain.UserDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Sungpyo on 2016-06-12.
 */
@Component
public class UserDAO {

    @Autowired
    private SqlSession sqlSession;

    public UserDTO selectByNameAndBirthAndHp(UserDTO user) {
        return this.sqlSession.selectOne("selectByNameAndBirthAndHp", user);
    }

    public int insertUser(UserDTO user) {
        return this.sqlSession.insert("insertUser", user);
    }


}
