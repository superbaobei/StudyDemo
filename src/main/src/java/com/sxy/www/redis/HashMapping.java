package com.sxy.www.redis;

import com.sxy.www.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.hash.HashMapper;
import org.springframework.data.redis.hash.ObjectHashMapper;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by xiangyusun on 2019/4/1.
 */
@Component
public class HashMapping {

    @Autowired
    RedisTemplate redisTemplate;

    HashMapper<Object, byte[], byte[]> mapper = new ObjectHashMapper();

    public void writeHash(String key, Person person) {
        HashOperations<String, byte[], byte[]> hashOperations = redisTemplate.opsForHash();

        Map<byte[], byte[]> mappedHash = mapper.toHash(person);
        hashOperations.putAll(key, mappedHash);
    }

    public Person loadHash(String key) {
        HashOperations<String, byte[], byte[]> hashOperations = redisTemplate.opsForHash();
        Map<byte[], byte[]> loadedHash = hashOperations.entries("sun");
        return (Person) mapper.fromHash(loadedHash);
    }
}
