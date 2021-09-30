package com.liangma.migration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
public class MigrationApplication implements CommandLineRunner {

    @Autowired
    private MigrationContext context;

    @Override
    public void run(String... args) throws Exception {
    }

    public static void main(String[] args) {
        SpringApplication.run(MigrationApplication.class, args);

    }
}
