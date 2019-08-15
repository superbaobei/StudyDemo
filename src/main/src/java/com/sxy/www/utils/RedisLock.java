package com.sxy.www.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class RedisLock implements DistributedLock {


    private static final Logger log = LoggerFactory.getLogger(RedisLock.class);

    private final String DEFAULT_LOCK_VALUE = "ok";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private DefaultRedisScript<Long> lockScript; // 锁脚本

    private DefaultRedisScript<Long> unlockScript; // 解锁脚本

    public RedisLock() {
        init();
    }

    public RedisLock(StringRedisTemplate stringRedisTemplate) {
        this();
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    private void init() {
        // Lock script
        lockScript = new DefaultRedisScript<Long>();
        lockScript.setScriptSource(
                new ResourceScriptSource(new ClassPathResource("lock.lua")));
        lockScript.setResultType(Long.class);
        // unlock script
        unlockScript = new DefaultRedisScript<Long>();
        unlockScript.setScriptSource(
                new ResourceScriptSource(new ClassPathResource("unlock.lua")));
        unlockScript.setResultType(Long.class);
    }

    @Override
    public boolean lock(String lockKey) {
        return lock(lockKey, DEFAULT_LOCK_VALUE);
    }

    @Override
    public boolean lock(String lockKey, String lockValue) {

        Assert.notNull(lockKey, "lockKey can't be null!");
        while (true) {
            List<String> keyList = new ArrayList<String>();
            keyList.add(lockKey);
            keyList.add(lockValue);
            if (stringRedisTemplate.execute(lockScript, keyList, String.valueOf(10 * 1000)) > 0) {
                break;
            } else {
                try {
                    Thread.sleep(10, (int) (Math.random() * 500));
                } catch (InterruptedException e) {
                    break;
                }
            }
        }

        return false;
    }

    @Override
    public boolean waitUntilLocked(String lockKey, String lockValue, long lockExpireTime, long timeout, TimeUnit unit) {

        Assert.notNull(lockKey, "lockKey can't be null!");

        lockExpireTime = unit.toMillis(lockExpireTime);
        timeout = unit.toMillis(timeout);
        if (log.isDebugEnabled()) {
            log.debug("lockExpireTime = {}, timeout = {}", lockExpireTime, timeout);
        }
        while (timeout > 0) {
            List<String> keyList = new ArrayList<String>();
            keyList.add(lockKey);
            keyList.add(lockValue);
            Long rs = stringRedisTemplate.execute(lockScript, keyList, String.valueOf(lockExpireTime));
            if (log.isDebugEnabled()) {
                log.debug("lockKey = {}, lockValue = {},rs = {}", lockKey, lockValue, rs);
            }
            if (rs > 0) {
                return true;
            } else {
                try {
                    Thread.sleep(500);
                    timeout -= 500;
                } catch (InterruptedException e) {
                    break;
                }
            }
        }

        return false;
    }

    @Override
    public boolean waitUntilLocked(String lockKey, String lockValue) {
        return waitUntilLocked(lockKey, lockValue, 30, 30, TimeUnit.SECONDS);
    }

    @Override
    public boolean unLock(String lockKey) {
        return false;
    }

    @Override
    public boolean unLock(String lockKey, String lockValue) {
        List<String> keyList = new ArrayList<String>();
        keyList.add(lockKey);
        keyList.add(lockValue);
        Long rs = stringRedisTemplate.execute(unlockScript, keyList);
        return rs == 1 ? true : false;
    }


    private class WatchDog extends Thread {

        private Map<String, Long> map = new HashMap<>();

        public WatchDog(String name) {
            super(name);
        }

        public boolean add(String lockKey, Long milliSeconds) {

            synchronized (map) {
                Long preValue = map.putIfAbsent(lockKey, milliSeconds);
                if (!Objects.isNull(preValue)) {
                    return false;
                }
            }
            return false;
        }

        @Override
        public void run() {
            super.run();
        }
    }
}
