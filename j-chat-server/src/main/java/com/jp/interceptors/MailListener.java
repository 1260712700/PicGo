package com.jp.interceptors;

import com.jp.constants.RedisConstant;
import com.jp.utils.RedisCache;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author JP
 * @Date 2024/11/1 7:52
 */
@Component
@Slf4j
public class MailListener {
    @Resource
    private RedisCache redisCache;
    public final String MAIL_QUEUE="email_queue";

    public final String MAIL_EXCHANGE="email_exchange";

    public final String MAIL_ROUTING_KEY="email_routing_key";
    @RabbitListener(
            errorHandler = "rabbitListenerErrorHandler",
            bindings ={
                    @QueueBinding(
                            value=@Queue(name = MAIL_QUEUE, durable = "true")
                            , exchange = @Exchange(name = MAIL_EXCHANGE, type = ExchangeTypes.DIRECT),
                            key = {MAIL_ROUTING_KEY}
                    ),

            }
    )
    public void handleMailMessage(Map<String,Object> data){
        String email = (String) data.get("email");
        String code = (String) data.get("code");
        String type = (String) data.get("type");
        log.info("----------------------------------------------");
        System.out.println(email+":"+code+":"+type);
        System.out.println((String)redisCache.getCacheObject(RedisConstant.VERIFY_CODE + type + RedisConstant.SEPARATOR + email));
    }

}
