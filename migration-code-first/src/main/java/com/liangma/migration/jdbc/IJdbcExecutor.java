package com.liangma.migration.jdbc;

import com.liangma.migration.descriptor.ColumnDescriptor;
import com.liangma.migration.descriptor.TableDescriptor;

import java.util.List;

public interface IJdbcExecutor<Table extends TableDescriptor, Column extends ColumnDescriptor> {

    List<Table> getTables();

    List<Column> getColumns(String table);

    void execute(String sql);
}
