package com.jp.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;

/**
 * @Author JP
 * @Date 2024/11/1 7:54
 */
public class RabbitMqConfig {
    @Bean
    public MessageConverter messageConverter(){
        return  new Jackson2JsonMessageConverter();
    }
}
