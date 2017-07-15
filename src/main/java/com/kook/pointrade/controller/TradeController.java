package com.kook.pointrade.controller;

import com.kook.pointrade.constants.AppConstants;
import com.kook.pointrade.service.BasketService;
import com.kook.pointrade.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by Sungpyo on 2016-06-22.
 */
@Controller
@RequestMapping(value="/trade")
public class TradeController {


    @Autowired
    private TradeService tradeService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Map getTradeListAndCurrentRate(@RequestParam("fromPoint") int fromPoint,
                              @RequestParam("toPoint") int toPoint) throws Exception {
        return tradeService.getTradeListAndCurrentRate(fromPoint, toPoint);
    }

    @RequestMapping(value = "/sell", method = RequestMethod.POST)
    @ResponseBody
    public Map sellPoint(int userKey, int fromPoint, int toPoint, BigDecimal rateSon, BigDecimal rateMom, int amount, String tradeCode) {
        System.out.println("rateSon="+rateSon+", rateMom="+rateMom);
        return tradeService.sellPoint(userKey, fromPoint, toPoint, rateSon, rateMom, amount, tradeCode);
    }

}
