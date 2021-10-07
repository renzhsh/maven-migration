package com.liangma.migration.mysql;

import com.liangma.migration.descriptor.ColumnDescriptor;
import com.liangma.migration.descriptor.ConstraintDescriptor;
import com.liangma.migration.descriptor.TableDescriptor;
import com.liangma.migration.provider.BaseMigrationProvider;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class MysqlMigrationProvider extends BaseMigrationProvider {

    public MysqlMigrationProvider(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    /**
     * 生成 Column 语句
     *
     * @param columnDescriptor
     * @return
     */
    @Override
    protected String generateColumnSql(ColumnDescriptor columnDescriptor) {
        return null;
    }

    /**
     * 获取数据库中存在的表
     *
     * @param name
     * @return
     */
    @Override
    public TableDescriptor GetTable(@NotNull String name) {
        return null;
    }

    /**
     * 创建表
     *
     * @param table
     */
    @Override
    public void CreateTable(@NotNull TableDescriptor table) {

    }

    /**
     * 获取表的约束
     *
     * @param table
     * @return
     */
    @Override
    public List<ConstraintDescriptor> GetConstraints(@NotNull String table) {
        return null;
    }
}
