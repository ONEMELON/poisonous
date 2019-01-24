package com.sweet.apple.service;

import com.sweet.apple.dto.CardDto;
import com.sweet.apple.dto.QuotaResult;
import com.sweet.apple.util.HbaseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;


/**
 * @Author zhujialing
 * @Create 2018-10-10 下午5:35
 * @Description:
 */
@Service
public class CardService {
    @Autowired HbaseTemplate hbaseTemplate;
    @Autowired Ifly flyImpl;
    @Autowired Ifly flyImpl2;

    public Map<String,QuotaResult> getCard(CardDto cardDto){
        Set<String> indexes = cardDto.getIndex();
        Set<String> cards = cardDto.getCard();
        flyImpl.flywing();
        flyImpl2.flywing();

        return hbaseTemplate.findCard(cards,indexes);

    }


}
