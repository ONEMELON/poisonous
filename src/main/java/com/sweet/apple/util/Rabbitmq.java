package com.sweet.apple.util;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    public Queue queue() {
        return new Queue("sweetQueue", true);
    }
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(defaultExchange()).with("sweetRoutingKey");
    }



}
