package com.sweet.apple.service;

import com.sweet.apple.dto.CardDto;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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


    public void sendMessage (CardDto cardDto) {
        // MessagePostProcessor是rabbitmq队列中消息的处理器 此处只是定义消息的时间

        MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                MessageProperties messageProperties = message.getMessageProperties();
                messageProperties.setTimestamp(new Date()); // 放入消息的推送时间
//                messageProperties.setConsumerQueue("sweetQueue"); // 放入消息的消费队列,这里可以不写
//                messageProperties.setConsumerTag(rabbitCustomizedProperties.getUnifiedBillingQueue());// 放入消息的tag
                return message;
            }
        };
        rabbitTemplate.convertAndSend("sweetQueue", cardDto, messagePostProcessor);
//        rabbitTemplate.convertAndSend("topicexchange","sweettopic.yyy", cardDto, messagePostProcessor);//topic 模式
//        rabbitTemplate.convertAndSend("topicexchange","sweettopic.zzz", cardDto, messagePostProcessor);
        System.out.println("send..............");

    }


    public void  sendMessage(Map map){
        System.out.println("=====Sender===== : " + map);
        rabbitTemplate.convertAndSend("sweetExchange1","sweetRoutingKey",map);
        rabbitTemplate.convertAndSend("sweetExchange1","appleRoutingKey",map);
//        rabbitTemplate.convertAndSend("appleRoutingKey",map);//当queue的名称与routingkey一样时可以调用这个方法
    }

}
