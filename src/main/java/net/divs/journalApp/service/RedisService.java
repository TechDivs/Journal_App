package net.divs.journalApp.service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
@Slf4j
public class RedisService {
    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;

    public RedisService(RedisTemplate<String, String> redisTemplate,ObjectMapper objectMapper) {
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
    }

    public <T> T get(String key, Class<T> entityClass) {
        try {
            String value = redisTemplate.opsForValue().get(key);
            if(value==null) return null;
            return objectMapper.readValue(value, entityClass);
        } 
        catch (Exception e) {
            log.error("Exception: ", e);
            return null;
        }
    }

    public void set(String key, Object o, Long ttl) {
        try {
            String value = objectMapper.writeValueAsString(o);
            redisTemplate.opsForValue().set(key,value,ttl,TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("Exception: ", e);
        }
    }
}
