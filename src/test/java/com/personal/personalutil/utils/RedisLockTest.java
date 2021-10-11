package com.personal.personalutil.utils;

import com.personal.personalutil.PersonalUtilApplication;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: zengqb
 * @Description:
 * @Date: Created in 2021/10/11
 * @Modified By:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PersonalUtilApplication.class)
class RedisLockTest {

    @Autowired
    private RedisLock redisLock;

    @Test
    void lockByTime() {
        boolean result = redisLock.lockByTime("1", 100, "1");
        Assert.assertTrue(result);
    }

    @Test
    void unlock() {
        boolean result = redisLock.unlock("1", "1");
        Assert.assertTrue(result);
    }
}