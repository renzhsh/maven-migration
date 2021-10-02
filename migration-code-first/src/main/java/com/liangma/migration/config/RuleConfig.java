package com.liangma.migration.config;

import com.liangma.migration.util.ObjectUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 规则配置
 */
@Component
@ConfigurationProperties("migration.rules")
public class RuleConfig {
    /**
     * Table 命名规则
     */
    private NamingConvention tableNaming;

    /**
     * Column 命名规则
     */
    private NamingConvention columnNaming;

    private final NamingConvention defaultNaming;

    public RuleConfig() {
        defaultNaming = new NamingConvention();
        defaultNaming.pascal = true;
    }

    /**
     * @return
     */
    @Bean("tableNaming")
    public NamingConvention getTableNaming() {
        return ObjectUtils.IfNull(tableNaming, defaultNaming);
    }

    /**
     * @return
     */
    @Bean("columnNaming")
    public NamingConvention getColumnNaming() {
        return ObjectUtils.IfNull(columnNaming, defaultNaming);
    }


    @Override
    public String toString() {
        return "RuleConfig{" +
                "tableNaming=" + tableNaming +
                ", columnNaming=" + columnNaming +
                '}';
    }
}



