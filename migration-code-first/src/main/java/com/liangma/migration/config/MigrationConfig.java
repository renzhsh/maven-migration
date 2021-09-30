package com.liangma.migration.config;

import com.liangma.migration.MigrationContext;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "migration")
public class MigrationConfig {

    @Bean
    public ClassLoader classLoader() {
        return this.getClass().getClassLoader();
    }
}
