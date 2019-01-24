package com.sweet.apple.service;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author zhujialing
 * @Create 2018-10-24 下午6:00
 * @Description:
 */
@Component
@RabbitListener(queues = "sweetQueue")
public class RabbbitmqConsumer {



    @RabbitHandler
    public void consumer(Map map){

        System.out.println("=====receive===== : " + map);
        System.out.println(map);

    }
}
