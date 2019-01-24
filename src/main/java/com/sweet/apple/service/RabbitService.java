package com.sweet.apple.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author zhujialing
 * @Create 2018-10-23 下午3:40
 * @Description:
 */
@Service
public class RabbitService {
    @Autowired
    RabbitTemplate rabbitTemplate;

    public void  sendMessage(Map map){
        System.out.println("=====Sender===== : " + map);
        rabbitTemplate.convertAndSend("sweetExchange","sweetRoutingKey",map);
    }

}
