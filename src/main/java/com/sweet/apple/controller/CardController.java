package com.sweet.apple.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sweet.apple.dto.CardDto;
import com.sweet.apple.dto.Nam;
import com.sweet.apple.dto.QuotaResult;
import com.sweet.apple.service.CardService;
import com.sweet.apple.service.RabbitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


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

    List<CardDto> cardlist = new ArrayList<>();
    @RequestMapping(path = "/index", method = RequestMethod.POST)
    public Object getCard(@RequestBody CardDto cardDto) throws JsonProcessingException {
        Map<String,QuotaResult> map = new HashMap<>();
        map = cardService.getCard(cardDto);
//        rabbitService.sendMessage(map);


        ObjectMapper objectMapper = new ObjectMapper();
//        String s = objectMapper.writer().writeValueAsString(cardDto);
        rabbitService.sendMessage(cardDto);
        return map;
    }


    @GetMapping(path = "/heap")
    public Object heap() throws JsonProcessingException {

        while (true) {
            cardlist.add(new CardDto("mjjjj", "jjjj"));
        }
    }
}

