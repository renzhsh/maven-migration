package com.liangma.migration.provider;

import com.liangma.migration.descriptor.ChangeDescriptor;
import com.liangma.migration.descriptor.ColumnDescriptor;
import com.liangma.migration.descriptor.ConstraintDescriptor;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class BaseMigrationProvider implements IMigrationProvider {

    public BaseMigrationProvider(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    protected final JdbcTemplate jdbcTemplate;


    /**
     * 表重命名
     *
     * @param oldTable
     * @param newTable
     */
    @Override
    public void RenameTable(@NotNull String oldTable, @NotNull String newTable) {
        String sql = "alter table " + oldTable + " rename to " + newTable;
        jdbcTemplate.execute(sql);
    }

    /**
     * 删除表
     *
     * @param name
     */
    @Override
    public void DropTable(@NotNull String name) {
        String sql = "drop table " + name;
        jdbcTemplate.execute(sql);
    }

    /**
     * 添加新字段
     *
     * @param table
     * @param column
     */
    @Override
    public void AddColumn(@NotNull String table, @NotNull ColumnDescriptor column) {
        String sql = "ALTER TABLE " + table + " ADD " + generateColumnSql(column);
        jdbcTemplate.execute(sql);
    }

    /**
     * 修改字段
     *
     * @param table
     * @param change
     */
    @Override
    public void AlterColumn(@NotNull String table, @NotNull ChangeDescriptor<ColumnDescriptor> change) {
        //TODO: AlterColumn

        String sql = "ALTER TABLE " + table + " ALTER COLUMN " + generateColumnSql(change.current);
        jdbcTemplate.execute(sql);
    }

    /**
     * 删除字段
     *
     * @param table
     * @param name
     */
    @Override
    public void DropColumn(@NotNull String table, @NotNull String name) {
        String sql = "alter table " + table + " drop column " + name;
        jdbcTemplate.execute(sql);
    }

    /**
     * 创建约束
     *
     * @param table
     * @param constraint
     */
    @Override
    public void CreateConstraint(@NotNull String table, @NotNull ConstraintDescriptor constraint) {

        // TODO: CreateConstraint

//        string sql = $"alter table {table} add constraint {constraint.Name} "
//                + (constraint.Type == ConstraintType.PrimaryKey ? "primary key " : "unique ")
//                + $"({string.Join(",", constraint.ColumnNames)})";
    }

    /**
     * 删除约束
     *
     * @param table
     * @param name
     */
    @Override
    public void DropConstraint(@NotNull String table, @NotNull String name) {
        String sql = "alter table " + table + " drop constraint " + name;
        jdbcTemplate.execute(sql);
    }

    /**
     * 生成 Column 语句
     *
     * @param columnDescriptor
     * @return
     */
    protected abstract String generateColumnSql(ColumnDescriptor columnDescriptor);
}
