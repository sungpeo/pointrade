package com.kook.pointrade.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kook.pointrade.domain.CurrentRateDTO;
import com.kook.pointrade.service.object.Rate;

/**
 * Created by Sungpyo on 2016-06-12.
 */
@Component
public class CurrentRateDAO {

    @Autowired
    private SqlSessionTemplate sqlSession;

    public CurrentRateDTO selectCurrentRateByPk(int fromPointKey, int toPointKey) {
        CurrentRateDTO in = new CurrentRateDTO();
        in.setFromPointKey(fromPointKey);
        in.setToPointKey(toPointKey);
        return this.sqlSession.selectOne("selectCurrentRateByPk", in);
    }

    public int updateCurrentRate(int fromPointKey, int toPointKey, String tradeCode, Rate currentRate) {
        CurrentRateDTO in = new CurrentRateDTO();
        in.setFromPointKey(fromPointKey);
        in.setToPointKey(toPointKey);
        in.setTradeCode(tradeCode);
        in.setCurrentRateSon(currentRate.getRateSon());
        in.setCurrentRateMom(currentRate.getRateMom());

        return this.sqlSession.update("updateCurrentRate", in);
    }

    //insert (신규 포인트가 나올때)

}
