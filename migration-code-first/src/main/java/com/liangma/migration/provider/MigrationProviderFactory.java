package com.liangma.migration.provider;

import com.liangma.migration.mysql.MysqlMigrationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@Deprecated
public class MigrationProviderFactory implements IProviderFactory {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public IMigrationProvider getProvider() {
        return new MysqlMigrationProvider(jdbcTemplate);
    }
}
