package com.nocdy.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

/**
 * @author Nocdy
 * @date 2021年 09月06日 16:49:04
 */
@Configuration
public class RedisTemplateConfiguration {

    @Bean
    public RedisTemplate<Object,Object> redisTemplate(RedisConnectionFactory rcf){
        RedisTemplate<Object,Object> template=new RedisTemplate<>();
        template.setConnectionFactory(rcf);
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer=new Jackson2JsonRedisSerializer<>(Object.class);
        template.setDefaultSerializer(jackson2JsonRedisSerializer);
        return template;
    }


}
