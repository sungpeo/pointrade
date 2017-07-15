package com.kook.pointrade.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kook.pointrade.domain.TotalTradeDTO;
import com.kook.pointrade.service.object.Rate;

/**
 * Created by Sungpyo on 2016-06-12.
 */
@Component
public class TotalTradeDAO {

    @Autowired
    private SqlSessionTemplate sqlSession;

    public List<TotalTradeDTO> selectTradeByPointKey(int fromPointKey, int toPointKey) {
        TotalTradeDTO in = new TotalTradeDTO();
        in.setFromPointKey(fromPointKey);
        in.setToPointKey(toPointKey);
        return this.sqlSession.selectList("selectTotalTradeByPointKey", in);
    }

    public TotalTradeDTO selectTradeByPk(int fromPointKey, int toPointKey, Rate rate) {
        TotalTradeDTO in = new TotalTradeDTO();
        in.setFromPointKey(fromPointKey);
        in.setToPointKey(toPointKey);
        in.setRateSon(rate.getRateSon());
        in.setRateMom(rate.getRateMom());
        return this.sqlSession.selectOne("selectTotalTradeByPk", in);
    }

    public int updateTrade(int fromPointKey, int toPointKey,
                           Rate rate, String tradeCode, int amount) {
        TotalTradeDTO in = new TotalTradeDTO();
        in.setFromPointKey(fromPointKey);
        in.setToPointKey(toPointKey);
        in.setRateSon(rate.getRateSon());
        in.setRateMom(rate.getRateMom());
        in.setTradeCode(tradeCode);
        in.setAmount(amount);
        return this.sqlSession.update("updateTotalTrade", in);
    }

    public TotalTradeDTO insertTrade(int fromPointKey, int toPointKey,
                           Rate rate, String tradeCode, int amount) {
        TotalTradeDTO in = new TotalTradeDTO();
        in.setFromPointKey(fromPointKey);
        in.setToPointKey(toPointKey);
        in.setRateSon(rate.getRateSon());
        in.setRateMom(rate.getRateMom());
        in.setTradeCode(tradeCode);
        in.setAmount(amount);
        this.sqlSession.insert("insertTotalTrade", in);
        return in;
    }


}
