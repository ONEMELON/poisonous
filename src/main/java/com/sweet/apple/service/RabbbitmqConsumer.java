package com.sweet.apple.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.sweet.apple.dto.CardDto;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.backoff.ExponentialBackOff;

import java.util.Map;

/**
 * @Author zhujialing
 * @Create 2018-10-24 下午6:00
 * @Description:
 */
@Component
//@RabbitListener(queues = "sweetQueue")
public class RabbbitmqConsumer {

    @Autowired
    private CachingConnectionFactory cachingConnectionFactory;
    @Autowired
    private MessageConverter messageConverter;
    //ChannelAwareMessageListener通过调用channel.basicAck()实现手动ack
    // 是消费端通知rabbit服务器，告诉服务器那条mq已经处理完毕，可以剔除。
    // 对于处理异常的，则可以重新回到mq服务器的队列中
    class AllMessageListener implements ChannelAwareMessageListener {
        @Override public void onMessage(Message message, Channel channel) throws Exception {
            System.out.println("==========");
            MessageProperties properties = message.getMessageProperties();
            byte[] body = message.getBody();
            System.out.println(properties.getTimestamp());
            System.out.println(new String(body));
            System.out.println("=====DeliveryTag===="+properties.getDeliveryTag());
            channel.basicAck(properties.getDeliveryTag(),false);
        }
    }


    @Bean
    public SimpleMessageListenerContainer bindListener() {
        SimpleMessageListenerContainer container = null;
        container = new SimpleMessageListenerContainer(cachingConnectionFactory);
        container.setQueueNames("sweetQueue");// 丢入queuename
        container.setExposeListenerChannel(true); //
        container.setMaxConcurrentConsumers(10);// 可以提高消息并发处理
        container.setConcurrentConsumers(1);// 设置为1,在无活跃消费者保持链接
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); // 设置确认模式手工确认
        container.setMessageConverter(messageConverter);
        container.setMessageListener(new AllMessageListener());
        container.setRecoveryBackOff(new ExponentialBackOff());// 设置指数退避
        System.out.println(".......成功初始化 simpleMessageListenerContainer!......");
        return container;
    }
//    @RabbitHandler
//    @RabbitListener(queues = "sweetQueue")
//    public void consumer(Map map){
//        System.out.println("=====receive sweetQueue===== : " + map);
//
//    }
//    @RabbitHandler
//    @RabbitListener(queues = "appleQueue")
//    public void appleConsumer(Map map){
//        System.out.println("=====receive appleQueue===== : " + map);
//
//    }
}
