package com.jp;

import com.jp.utils.RedisCache;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class JChatServerApplicationTests {
    @Autowired
    private RedisCache redisCache;

}
