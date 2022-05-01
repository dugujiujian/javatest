package com.dugu.test.service.redis;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.UnknownHostException;
import java.time.Duration;

/**
 * @author cihun
 * @date 2022-04-30 10:45 上午
 */
@Configuration//这个标签，通常与@bean结合使用，当@bean使用到该类的方法上，代表将该方法做为一个bean交给了spring的context
@EnableCaching//允许我们使用缓存
public class RedisConfig extends CachingConfigurerSupport {


    /**
     * jedis pool springboot2.x.x 获取pool的工具类
     *
     * @param redisProperties
     * @return
     */
//    @Bean//此时，将我们的redisTemplate加载到了我们的spring的上下文中，applicationContext
//    public JedisPoolConfig getGenericObjectJedisPoolConfig(RedisProperties redisProperties) {
//        //JedisPoolConfig 继承 GenericObjectPoolConfig类,并将testWhileIdle设置为true
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMaxIdle(redisProperties.getJedis().getPool().getMaxIdle());
//        jedisPoolConfig.setMinIdle(redisProperties.getJedis().getPool().getMinIdle());
//        jedisPoolConfig.setMaxTotal(redisProperties.getJedis().getPool().getMaxActive());
//        jedisPoolConfig.setMaxWaitMillis(redisProperties.getJedis().getPool().getMaxWait().toMillis());
//        //此处可以设置testOnBorrow,默认值为false,网络良好的情况下建议使用默认值
//        return jedisPoolConfig;
//    }

//    /**
//     * springboot2.1.x 使用 jedis 连接redis单实例或集群，需要添加以下的选项
//     * 目的:为当前应用添加连接redis的标识，方便多应用连接同一个redis的问题查找
//     */
//    @Bean
//    public RedisConnectionFactory connectionFactory(RedisProperties redisProperties) {
//
//        //添加额外属性
//        JedisClientConfiguration clientConfig = JedisClientConfiguration.builder()
//                //客户端名称需要再这里写死
//                .clientName("AppName")
//                .usePooling().poolConfig(getGenericObjectJedisPoolConfig(redisProperties)).and().readTimeout(redisProperties.getTimeout())
//                .build();
//
//        //单实例连接配置
////        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(redisProperties.getHost());
////        configuration.setPort(redisProperties.getPort());
////        configuration.setPassword(redisProperties.getPassword());
//
//        //集群连接配置
//        RedisClusterConfiguration configuration = new RedisClusterConfiguration(redisProperties.getCluster().getNodes());
//        configuration.setMaxRedirects(redisProperties.getCluster().getMaxRedirects());
//        configuration.setPassword(RedisPassword.of(redisProperties.getPassword()));
//
//        return new JedisConnectionFactory(configuration, clientConfig);
//    }


    /**
     * redis template相关配置
     *
     * @param redisConnectionFactory 链接工厂
     * @return redis template
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {

        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // 配置连接工厂
        template.setConnectionFactory(redisConnectionFactory);

        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
        Jackson2JsonRedisSerializer jacksonSerial = new Jackson2JsonRedisSerializer(Object.class);

        ObjectMapper om = new ObjectMapper();
        // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jacksonSerial.setObjectMapper(om);

        // 值采用json序列化
        template.setValueSerializer(jacksonSerial);
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());

        // 设置hash key 和value序列化模式
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(jacksonSerial);
        template.afterPropertiesSet();

        return template;
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        //1.序列话（一般用于key值）
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        //2.引入json串的转化类（一般用于value的处理）
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        //2.1设置objectMapper的访问权限
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        //2.2指定序列化输入类型,就是将数据库里的数据按照一定类型存储到redis缓存中。
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);//最近升级SpringBoot，发现enableDefaultTyping方法过期过期了。可以使用下面的方法代替
        //objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.WRAPPER_ARRAY);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        //3.序列话配置，乱码问题解决以及我们缓存的时效性
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig().
                entryTtl(Duration.ofSeconds(1000)).//缓存时效性设置
                serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer)).//key序列化
                serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer)).//value序列化
                disableCachingNullValues();//空值不存入缓存
        //4.创建cacheManager链接并设置属性
        RedisCacheManager cacheManager = RedisCacheManager.builder(factory).cacheDefaults(config).build();
        return cacheManager;
    }


    @Bean
    @ConditionalOnMissingBean(StringRedisTemplate.class)
    public StringRedisTemplate stringRedisTemplate(
            RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    /**
     * 对hash类型的数据操作
     *
     * @param redisTemplate redis访问模版
     * @return hash类型的数据
     */
    @Bean
    public HashOperations<String, String, Object> hashOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForHash();
    }

    /**
     * 对redis字符串类型数据操作
     *
     * @param redisTemplate redis访问模版
     * @return redis字符串类型数据
     */
    @Bean
    public ValueOperations<String, Object> valueOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForValue();
    }

    /**
     * 对链表类型的数据操作
     *
     * @param redisTemplate redis访问模版
     * @return 链表类型的数据
     */
    @Bean
    public ListOperations<String, Object> listOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForList();
    }

    /**
     * 对无序集合类型的数据操作
     *
     * @param redisTemplate redis访问模版
     * @return 无序集合类型的数据
     */
    @Bean
    public SetOperations<String, Object> setOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForSet();
    }

    /**
     * 对有序集合类型的数据操作
     *
     * @param redisTemplate redis访问模版
     * @return 有序集合类型的数据
     */
    @Bean
    public ZSetOperations<String, Object> zSetOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForZSet();
    }

}
