package com.kook.pointrade.dao;

import com.kook.pointrade.domain.PbasketDTO;
import com.kook.pointrade.domain.PtradeDTO;
import com.kook.pointrade.service.object.Rate;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Sungpyo on 2016-06-12.
 */
@Component
public class PtradeDAO {

    @Autowired
    private SqlSession sqlSession;

    public int insertTrade(int userKey, int fromPointKey, int toPointKey,
                           Rate rate, String tradeCode, int amount) {

        PtradeDTO ptradeDTO = new PtradeDTO();
        ptradeDTO.setUserKey(userKey);
        ptradeDTO.setFromPointKey(fromPointKey);
        ptradeDTO.setToPointKey(toPointKey);
        ptradeDTO.setRateSon(rate.getRateSon());
        ptradeDTO.setRateMom(rate.getRateMom());

        ptradeDTO.setTradeCode(tradeCode);
        ptradeDTO.setAmount(amount);
        ptradeDTO.setTimeMillis(System.currentTimeMillis());


        return this.sqlSession.insert("insertTrade", ptradeDTO);
    }

    public PtradeDTO selectTradeByPk(int userKey, int fromPointKey, int toPointKey,
                                     Rate rate){

        PtradeDTO ptradeDTO = new PtradeDTO();
        ptradeDTO.setUserKey(userKey);
        ptradeDTO.setFromPointKey(fromPointKey);
        ptradeDTO.setToPointKey(toPointKey);
        ptradeDTO.setRateSon(rate.getRateSon());
        ptradeDTO.setRateMom(rate.getRateMom());

        return this.sqlSession.selectOne("selectTradeByPk", ptradeDTO);
    }

    public int updateTradeByPk(int userKey, int fromPointKey, int toPointKey,
                               Rate rate, String tradeCode, int amount){
        PtradeDTO ptradeDTO = new PtradeDTO();
        ptradeDTO.setUserKey(userKey);
        ptradeDTO.setFromPointKey(fromPointKey);
        ptradeDTO.setToPointKey(toPointKey);
        ptradeDTO.setRateSon(rate.getRateSon());
        ptradeDTO.setRateMom(rate.getRateMom());
        ptradeDTO.setTradeCode(tradeCode);
        ptradeDTO.setAmount(amount);
        ptradeDTO.setTimeMillis(System.currentTimeMillis());

        return this.sqlSession.update("updatePtradeByPk", ptradeDTO);
    }

    public List<PtradeDTO> selectPtradesByPointRate(int fromPointKey, int toPointKey,
                                                    Rate rate, String tradeCode) {

        PtradeDTO ptradeDTO = new PtradeDTO();
        ptradeDTO.setFromPointKey(fromPointKey);
        ptradeDTO.setToPointKey(toPointKey);
        ptradeDTO.setRateSon(rate.getRateSon());
        ptradeDTO.setRateMom(rate.getRateMom());
        ptradeDTO.setTradeCode(tradeCode);

        return this.sqlSession.selectList("selectPtradesByPointRate", ptradeDTO);
    }

    public int deletePtradeByPk(int userKey, int fromPointKey, int toPointKey, Rate rate) {
        PtradeDTO ptradeDTO = new PtradeDTO();
        ptradeDTO.setUserKey(userKey);
        ptradeDTO.setFromPointKey(fromPointKey);
        ptradeDTO.setToPointKey(toPointKey);
        ptradeDTO.setRateSon(rate.getRateSon());
        ptradeDTO.setRateMom(rate.getRateMom());

        return this.sqlSession.delete("deletePtradeByPk", ptradeDTO);
    }
}
