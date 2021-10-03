package com.liangma.migration.descriptor;

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
    public String toString() {
        return "TableDescriptor{" +
                "name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
