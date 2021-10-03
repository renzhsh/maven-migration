package com.liangma.migration.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.liangma.migration.MigrationContext;
import com.liangma.migration.convert.MapperExpression;
import com.liangma.migration.exception.InvalidExpressionException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Configuration
public class MigrationConfig {

    @Bean
    public ClassLoader classLoader() {
        return this.getClass().getClassLoader();
    }

    @Bean("classDirectory")
    public String classDirectory(ClassLoader loader) {
        String result = loader.getResource("").getPath();

        if (result.startsWith("/")) {
            result = result.substring(1);
        }

        if (!result.endsWith("/")) {
            result = result + "/";
        }

        return result;
    }

    @Bean("mapper")
    public Map<String, MapperExpression> mapperSet() throws InvalidExpressionException {
        Map<String, MapperExpression> mapper = new HashMap<>();

//        int（4字节）	Integer
//        byte（1字节）	Byte
//        short（2字节）	Short
//        long（8字节）	Long
//        float（4字节）	Float
//        double（8字节）	Double
//        char（2字节）	Character
//        boolean（未定）	Boolean
//        enum
//        date
        mapper.put("int", new MapperExpression("int,11"));
        mapper.put("byte", new MapperExpression("int,2"));
        mapper.put("short", new MapperExpression("int,8"));
        mapper.put("long", new MapperExpression("bigint"));
        mapper.put("float", new MapperExpression("float"));
        mapper.put("double", new MapperExpression("double,10,4"));
        mapper.put("char", new MapperExpression("varchar"));
        mapper.put("string", new MapperExpression("varchar,50"));
        mapper.put("boolean", new MapperExpression("int,1"));
        mapper.put("enum", new MapperExpression("int,1"));
        mapper.put("date", new MapperExpression("datetime"));
        return mapper;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
