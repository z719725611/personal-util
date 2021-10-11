package com.personal.personalutil.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * @Author: zengqb
 * @Description:
 * @Date: Created in 2021/10/11
 * @Modified By:
 */
@Slf4j
@Component
public class RedisLock {

    private static final String LOCK_PREFIX = "personal_util:";

    @Value("${lua.lockScript}")
    private String lockLuaScript;
    @Value("${lua.unLockScript}")
    private String unLockScript;

    @Autowired
    private RedisTemplate redisTemplate;

    public boolean lockByTime(String key, int lockExpire, String requestId) {
        String lockKey = LOCK_PREFIX + key;
        RedisScript longDefaultRedisScript = RedisScript.of(lockLuaScript, Long.class);
        return ((Long)redisTemplate.execute(longDefaultRedisScript ,new StringRedisSerializer(),new StringRedisSerializer()
                , Collections.singletonList(lockKey), requestId, String.valueOf(lockExpire))) == 1L;
    }

    public boolean unlock(String key, String requestId) {
        String lockKey = LOCK_PREFIX + key;
        RedisScript longDefaultRedisScript = RedisScript.of(unLockScript, Long.class);
        return ((Long)redisTemplate.execute(longDefaultRedisScript,new StringRedisSerializer(),new StringRedisSerializer()
                , Collections.singletonList(lockKey), requestId)) == 1L;
    }

}
