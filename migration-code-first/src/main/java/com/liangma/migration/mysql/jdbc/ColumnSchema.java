package com.liangma.migration.mysql.jdbc;

import com.liangma.migration.mysql.descriptor.MysqlColumnDescriptor;
import com.liangma.migration.util.StringUtils;

public class ColumnSchema {
    private String tableName;

    private String columnName;

    private String isNullable;

    private String columnType;

    private String columnKey;

    private String extra;

    private String columnComment;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getIsNullable() {
        return isNullable;
    }

    public void setIsNullable(String isNullable) {
        this.isNullable = isNullable;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    @Override
    public String toString() {
        return "ColumnSchema{" +
                "tableName='" + tableName + '\'' +
                ", columnName='" + columnName + '\'' +
                ", isNullable='" + isNullable + '\'' +
                ", columnType='" + columnType + '\'' +
                ", columnKey='" + columnKey + '\'' +
                ", extra='" + extra + '\'' +
                ", columnComment='" + columnComment + '\'' +
                '}';
    }

    public MysqlColumnDescriptor toDescriptor() {
        MysqlColumnDescriptor result = new MysqlColumnDescriptor();

        result.setName(getColumnName());
        result.setComment(getColumnComment());

        String[] segs = getColumnType().replace("(", ",").replace(")", ",").split(",");
        if (segs.length > 0) {
            result.setDbType(segs[0]);
        }
        if (segs.length > 1) {
            result.setMaxLength(Integer.parseInt(segs[1]));
        }
        if (segs.length > 2) {
            result.setPrecise(Integer.parseInt(segs[2]));
        }

        result.setAllowNull(getIsNullable().equals("YES"));
        result.setPrimaryKey(getColumnKey().equals("PRI"));

        String extra = getExtra();
        if (StringUtils.isEmpty(extra) && extra.equals("auto_increment")) {
            result.setAutoIncrement(true);
        }

        return result;
    }
}
