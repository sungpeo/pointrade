package com.kook.pointrade.controller;

import com.kook.pointrade.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by Sungpyo on 2016-06-22.
 */
@Controller
@RequestMapping(value="/basket")
public class BasketController {

    @Autowired
    private BasketService basketService;

    @RequestMapping(value="/list", method= RequestMethod.GET)
    @ResponseBody
    public Map getUserBaskets(@RequestParam("userKey")int userKey,
                              @RequestParam("criteria")int criteria) throws Exception {
        return basketService.selectBasketByUserWithCriteria(userKey, criteria);
    }

}
