package com.liangma.migration.generator;

import com.liangma.migration.descriptor.ChangeDescriptor;
import com.liangma.migration.descriptor.ClassDescriptor;
import com.liangma.migration.descriptor.ColumnDescriptor;
import com.liangma.migration.descriptor.TableDescriptor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface IDiffComparator {

    List<TableDescriptor> findCreatedTable(@NotNull List<TableDescriptor> origin, @NotNull List<TableDescriptor> current);

    List<TableDescriptor> findModifiedTable(@NotNull List<TableDescriptor> origin, @NotNull List<TableDescriptor> current);

    List<TableDescriptor> findDroppedTable(@NotNull List<TableDescriptor> origin, @NotNull List<TableDescriptor> current);

    List<ColumnDescriptor> findCreatedColumn(@NotNull List<ColumnDescriptor> origin, @NotNull List<ColumnDescriptor> current);

    List<ColumnDescriptor> findModifiedColumn(@NotNull List<ColumnDescriptor> origin, @NotNull List<ColumnDescriptor> current);

    List<ColumnDescriptor> findDroppedColumn(@NotNull List<ColumnDescriptor> origin, @NotNull List<ColumnDescriptor> current);

    ChangeDescriptor<TableDescriptor> diffTable(@NotNull TableDescriptor origin, @NotNull TableDescriptor current);

    ChangeDescriptor<ColumnDescriptor> diffColumn(@NotNull ColumnDescriptor origin, @NotNull ColumnDescriptor current);
}
