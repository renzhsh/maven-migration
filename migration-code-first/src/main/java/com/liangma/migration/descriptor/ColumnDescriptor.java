package com.liangma.migration.descriptor;

import com.liangma.migration.convert.MapperExpression;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ColumnDescriptor {

    /**
     * 名称
     */
    protected String name;

    /**
     * 注解
     */
    protected String comment;

    /**
     * 字段类型
     */
    protected String dbType;

    /**
     * 最大长度
     */
    protected int maxLength;

    /**
     * 数据精度
     */
    protected int precise;

    /**
     * 是否允许为空
     */
    protected boolean allowNull = true;

    /**
     * 是否为主键
     */
    protected boolean isPrimaryKey;

    /**
     * 主键递增
     */
    protected boolean autoIncrement;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public int getPrecise() {
        return precise;
    }

    public void setPrecise(int precise) {
        this.precise = precise;
    }

    public boolean isAllowNull() {
        return allowNull;
    }

    public void setAllowNull(boolean allowNull) {
        this.allowNull = allowNull;
    }

    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        isPrimaryKey = primaryKey;
    }

    public boolean isAutoIncrement() {
        return autoIncrement;
    }

    public void setAutoIncrement(boolean autoIncrement) {
        this.autoIncrement = autoIncrement;
    }

    public void setMapperExpression(@NotNull MapperExpression expression) {
        setDbType(expression.getDbType());
        setMaxLength(expression.getMaxLength());
        setPrecise(expression.getPrecise());
        setAllowNull(expression.isAllowNull());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ColumnDescriptor)) return false;
        ColumnDescriptor that = (ColumnDescriptor) o;
        return maxLength == that.maxLength && precise == that.precise && allowNull == that.allowNull && isPrimaryKey == that.isPrimaryKey && autoIncrement == that.autoIncrement && name.equals(that.name) && comment.equals(that.comment) && dbType.equals(that.dbType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, comment, dbType, maxLength, precise, allowNull, isPrimaryKey, autoIncrement);
    }

    @Override
    public String toString() {
        return "ColumnDescriptor{" +
                "name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                ", dbType='" + dbType + '\'' +
                ", maxLength=" + maxLength +
                ", precise=" + precise +
                ", allowNull=" + allowNull +
                ", isPrimaryKey=" + isPrimaryKey +
                ", autoIncrement=" + autoIncrement +
                '}';
    }
}
