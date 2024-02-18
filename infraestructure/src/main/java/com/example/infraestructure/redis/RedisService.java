package com.example.infraestructure.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisService {
    private final StringRedisTemplate stringRedisTemplate;

    public void saveInRedis(String key, String value, int exp){
        stringRedisTemplate.opsForValue().set(key, value);
        stringRedisTemplate.expire(key, exp, TimeUnit.MINUTES);
    }

    public String getFromRedis(String key){
        return stringRedisTemplate.opsForValue().get(key);
    }

    public void deleteKey(String key){
        stringRedisTemplate.delete(key);
    }
}
