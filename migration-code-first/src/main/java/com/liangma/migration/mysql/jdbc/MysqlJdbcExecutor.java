package com.liangma.migration.mysql.jdbc;

import com.liangma.migration.descriptor.ColumnDescriptor;
import com.liangma.migration.jdbc.IJdbcExecutor;
import com.liangma.migration.mysql.descriptor.MysqlColumnDescriptor;
import com.liangma.migration.mysql.descriptor.MysqlTableDescriptor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MysqlJdbcExecutor implements IJdbcExecutor<MysqlTableDescriptor, MysqlColumnDescriptor> {

    //用于访问数据库的组件
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<MysqlTableDescriptor> getTables() {
        // 获取当前数据库
        String schema = jdbcTemplate.<String>queryForObject("select database();", String.class);

        String sql = "select TABLE_NAME,Engine,TABLE_COMMENT from information_schema.tables where table_schema='" + schema + "' and table_type='base table';";


        List<MysqlTableDescriptor> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TableSchema.class))
                .stream().map(TableSchema::toDescriptor).collect(Collectors.toList());

        for (MysqlTableDescriptor table : list) {
            table.setColumns(getColumns(table.getName()).toArray(new ColumnDescriptor[0]));
        }

        return list;
    }

    @Override
    public List<MysqlColumnDescriptor> getColumns(@NotNull String table) {
        // 获取当前数据库
        String schema = jdbcTemplate.<String>queryForObject("select database();", String.class);

        String sql = "select * from information_schema.columns where table_schema='" + schema + "' and table_name='" + table + "'";

        List<ColumnSchema> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ColumnSchema.class));

        return list.stream().map(ColumnSchema::toDescriptor).collect(Collectors.toList());
    }

    @Override
    public void execute(String sql) {
        jdbcTemplate.execute(sql);
    }
}
