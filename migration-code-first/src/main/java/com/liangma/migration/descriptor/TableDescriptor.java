package com.liangma.migration.descriptor;

import java.util.Arrays;
import java.util.Objects;

public class TableDescriptor {
    protected String name;

    protected String comment;

    protected ColumnDescriptor[] columns;

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

    public ColumnDescriptor[] getColumns() {
        return columns;
    }

    public void setColumns(ColumnDescriptor[] columns) {
        this.columns = columns;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TableDescriptor)) return false;
        TableDescriptor that = (TableDescriptor) o;
        return name.equals(that.name) && comment.equals(that.comment) && Arrays.equals(columns, that.columns);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, comment);
        result = 31 * result + Arrays.hashCode(columns);
        return result;
    }

    @Override
    public String toString() {
        return "TableDescriptor{" +
                "name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
