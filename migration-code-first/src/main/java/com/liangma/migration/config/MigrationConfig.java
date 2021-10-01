package com.liangma.migration.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.liangma.migration.MigrationContext;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class MigrationConfig {

    @Bean
    public ClassLoader classLoader() {
        return this.getClass().getClassLoader();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }

    @Bean
    public JdbcTemplate jdbdTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
