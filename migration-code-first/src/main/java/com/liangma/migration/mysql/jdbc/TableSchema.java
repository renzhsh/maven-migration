package com.liangma.migration.mysql.jdbc;

import com.liangma.migration.mysql.MysqlEngine;
import com.liangma.migration.mysql.descriptor.MysqlTableDescriptor;

public class TableSchema {
    private String tableName;
    private String engine;
    private String tableComment;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    @Override
    public String toString() {
        return "TableSchema{" +
                "tableName='" + tableName + '\'' +
                ", engine='" + engine + '\'' +
                ", tableComment='" + tableComment + '\'' +
                '}';
    }

    public MysqlTableDescriptor toDescriptor() {
        MysqlTableDescriptor result = new MysqlTableDescriptor();

        result.setName(getTableName());
        result.setComment(getTableComment());
        result.setEngine(MysqlEngine.valueOf(getEngine()));

        return result;
    }
}
