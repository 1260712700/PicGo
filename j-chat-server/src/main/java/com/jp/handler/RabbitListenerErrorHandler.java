package com.jp.handler;

import cn.hutool.http.server.filter.HttpFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.stereotype.Component;

/**
 * @Author JP
 * @Date 2024/11/1 8:13
 */
@Component
@Slf4j
public class RabbitListenerErrorHandler implements MessageRecoverer, org.springframework.amqp.rabbit.listener.api.RabbitListenerErrorHandler {

    @Override
    public Object handleError(Message amqpMessage, org.springframework.messaging.Message<?> message, ListenerExecutionFailedException exception) throws Exception {
        log.error("消息处理失败，即将重新发送", exception);
        throw exception;
    }

    @Override
    public void recover(Message message, Throwable cause) {
        log.error("消息重试失败，即将丢弃", cause);
    }
}
