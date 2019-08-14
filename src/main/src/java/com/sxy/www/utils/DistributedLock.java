package com.sxy.www.utils;

import java.util.concurrent.TimeUnit;

public interface DistributedLock {

    boolean lock(String lockKey);

    boolean lock(String lockKey, String lockValue);

    boolean waitUntilLocked(String lockKey, String lockValue, long lockExpireTime, long timeout, TimeUnit unit);

    boolean waitUntilLocked(String lockKey, String lockValue);

    boolean unLock(String lockKey);

    boolean unLock(String lockKey, String lockValue);
}
