package com.liangma.migration.generator;

import com.liangma.migration.descriptor.ColumnDescriptor;
import com.liangma.migration.descriptor.TableDescriptor;

public interface ISQLGenerator {
    String generateTable(TableDescriptor descriptor);

    String generateColumn(ColumnDescriptor descriptor);
}
