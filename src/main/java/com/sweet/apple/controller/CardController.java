package com.sweet.apple.controller;


import com.sweet.apple.dto.CardDto;
import com.sweet.apple.dto.QuotaResult;
import com.sweet.apple.service.CardService;
import com.sweet.apple.service.RabbitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


/**
 * @Author zhujialing
 * @Create 2018-10-10 下午5:34
 * @Description:
 */
@RestController
@RequestMapping("/find")
public class CardController {
    @Autowired CardService cardService;
    @Autowired RabbitService rabbitService;

    @RequestMapping(path = "/index", method = RequestMethod.POST)
    public Object getCard(@RequestBody CardDto cardDto){
        Map<String,QuotaResult> map = new HashMap<>();
        map = cardService.getCard(cardDto);
        rabbitService.sendMessage(map);
       return map;
    }
}
