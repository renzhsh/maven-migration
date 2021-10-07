package com.liangma.migration.provider;

import com.liangma.migration.descriptor.ChangeDescriptor;
import com.liangma.migration.descriptor.ColumnDescriptor;
import com.liangma.migration.descriptor.ConstraintDescriptor;
import com.liangma.migration.descriptor.TableDescriptor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface IMigrationProvider {


    /**
     * 获取数据库中存在的表
     * @param name
     * @return
     */
    TableDescriptor GetTable(@NotNull String name);

    /**
     * 创建表
     * @param table
     */
    void CreateTable(@NotNull TableDescriptor table);

    /**
     * 表重命名
     * @param oldTable
     * @param newTable
     */
    void RenameTable(@NotNull String oldTable, @NotNull String newTable);

    /**
     * 删除表
     * @param name
     */
    void DropTable(@NotNull String name);

    /**
     * 添加新字段
     * @param table
     * @param column
     */
    void AddColumn(@NotNull String table, @NotNull ColumnDescriptor column);

    /**
     * 修改字段
     * @param table
     * @param change
     */
    void AlterColumn(@NotNull String table, @NotNull ChangeDescriptor<ColumnDescriptor> change);

    /**
     * 删除字段
     * @param table
     * @param name
     */
    void DropColumn(@NotNull String table, @NotNull String name);

    /**
     * 获取表的约束
     * @param table
     * @return
     */
    List<ConstraintDescriptor> GetConstraints(@NotNull String table);

    /**
     * 创建约束
     * @param table
     * @param constraint
     */
    void CreateConstraint(@NotNull String table, @NotNull ConstraintDescriptor constraint);

    /**
     * 删除约束
     * @param table
     * @param name
     */
    void DropConstraint(@NotNull String table, @NotNull String name);
}
