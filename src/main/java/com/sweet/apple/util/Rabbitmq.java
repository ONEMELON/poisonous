package com.sweet.apple.util;

import org.springframework.amqp.core.*;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @Author zhujialing
 * @Create 2018-10-23 下午3:17
 * @Description:
 */
@Configuration
public class Rabbitmq {



    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange("sweetExchange");
    }
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("topicexchange");
    }
//    @Bean
//    public DirectExchange appleExchange() {
//        return new DirectExchange("appleExchange");
//    }
    @Bean
    public Queue queue() {
        Queue queue = new Queue("sweetQueue",  true, false, false);
        return queue;
    }
    @Bean
    public Queue appleQueue() {
        Queue queue = new Queue("appleQueue",  true, false, false);
        return queue;
    }
    @Bean
    public Queue topicQueue() {
        Queue queue = new Queue("topicQueue",  true, false, false);
        return queue;
    }
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(defaultExchange()).with("sweetRoutingKey");
    }


    @Bean
    public Binding bindingQueue2() {
        return BindingBuilder.bind(appleQueue()).to(defaultExchange()).with("appleRoutingKey");
    }

    @Bean
    public Binding bindingtopic() {
        return BindingBuilder.bind(topicQueue()).to(topicExchange()).with("sweettopic.*");
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    @Bean public CachingConnectionFactory cachingConnectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setUsername("upsmart");
        factory.setPassword("upsmart");
        factory.setVirtualHost("/zjl");
        factory.setHost("warwick01");
//        try {
//            factory.createConnection().createChannel(true).basicPublish();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        // factory.setPort(15672);
         factory.setPublisherConfirms(true);// 保证消息的事务性处理rabbitmq默认的处理方式为auto // ack，这意味着当你从消息队列取出一个消息时，ack自动发送，mq就会将消息删除。而为了保证消息的正确处理，我们需要将消息处理修改为手动确认的方式
         return factory; }

}
