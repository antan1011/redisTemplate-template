package com.example.system.config;

import com.example.system.utils.SnowflakeIdGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class BeanConfig {
    @Bean("imageName")
    public SnowflakeIdGenerator imageName() {
        return new SnowflakeIdGenerator(5);
    }
    @Bean("articleId")
    public SnowflakeIdGenerator articleId() {
        return new SnowflakeIdGenerator(4);
    }
    @Bean("recordId")
    public SnowflakeIdGenerator recordId() {
        return new SnowflakeIdGenerator(3);
    }
    @Bean
    public ExecutorService executorService() {
        return Executors.newSingleThreadExecutor();
    }
    @Bean
    public RedisTemplate<String,?> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, ?> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);

        GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer();

        // key 序列化
        template.setKeySerializer(RedisSerializer.string());
        template.setHashKeySerializer(RedisSerializer.string());

        // value 序列化
        template.setValueSerializer(serializer);
        template.setHashValueSerializer(serializer);

        return template;
    }
}
