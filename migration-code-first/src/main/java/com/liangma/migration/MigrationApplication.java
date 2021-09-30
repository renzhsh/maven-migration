package com.liangma.migration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableConfigurationProperties
public class MigrationApplication implements CommandLineRunner {

    @Autowired
    private MigrationContext context;

    @Override
    public void run(String... args) throws Exception {
        context.execute();
    }

    public static void main(String[] args) {
        SpringApplication.run(MigrationApplication.class, args);
    }
}
