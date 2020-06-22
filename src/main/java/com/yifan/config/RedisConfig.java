package com.yifan.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
public class RedisConfig {
/*
*    编写我们自己的  RedisTemplate
*    固定的模板
* */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        // Json序列化配置
        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
        serializer.setObjectMapper(mapper);
        // String 的序列化
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        //  设置具体的序列号方式~
           //------------- key 采用string的序列化方式
        template.setKeySerializer(stringRedisSerializer);
          // -------------hash 的key 采用 string的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
          //------------- value 采用 json的序列化方式
        template.setValueSerializer(serializer);
          // -------------hash 的 value 采用json的序列化方式
        template.setHashValueSerializer(serializer);
        template.afterPropertiesSet();
        return template;
    }
}
