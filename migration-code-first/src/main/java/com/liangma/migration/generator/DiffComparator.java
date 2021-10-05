package com.liangma.migration.generator;

import com.liangma.migration.descriptor.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class DiffComparator implements IDiffComparator {
    @Override
    public List<TableDescriptor> findCreatedTable(@NotNull List<TableDescriptor> origin, @NotNull List<TableDescriptor> current) {
        List<String> originNames = origin.stream().map(TableDescriptor::getName).collect(Collectors.toList());

        return current.stream().filter(item -> !originNames.contains(item.getName())).collect(Collectors.toList());
    }

    @Override
    public List<TableDescriptor> findModifiedTable(@NotNull List<TableDescriptor> origin, @NotNull List<TableDescriptor> current) {

        List<TableDescriptor> result = new ArrayList<>();
        List<String> originNames = origin.stream().map(TableDescriptor::getName).collect(Collectors.toList());

        for (TableDescriptor table : current) {
            if (!originNames.contains(table.getName())) {
                continue;
            }

            TableDescriptor that = origin.stream().filter(item -> Objects.equals(item.getName(), table.getName())).findFirst().orElseThrow(NullPointerException::new);

            if (!table.equals(that)) {
                result.add(table);
            }
        }

        return result;
    }

    @Override
    public List<TableDescriptor> findDroppedTable(@NotNull List<TableDescriptor> origin, @NotNull List<TableDescriptor> current) {
        List<String> currentNames = current.stream().map(TableDescriptor::getName).collect(Collectors.toList());

        return origin.stream().filter(item -> !currentNames.contains(item.getName())).collect(Collectors.toList());
    }

    @Override
    public List<ColumnDescriptor> findCreatedColumn(@NotNull List<ColumnDescriptor> origin, @NotNull List<ColumnDescriptor> current) {
        List<String> originNames = origin.stream().map(ColumnDescriptor::getName).collect(Collectors.toList());

        return current.stream().filter(item -> !originNames.contains(item.getName())).collect(Collectors.toList());
    }

    @Override
    public List<ColumnDescriptor> findModifiedColumn(@NotNull List<ColumnDescriptor> origin, @NotNull List<ColumnDescriptor> current) {
        List<ColumnDescriptor> result = new ArrayList<>();
        List<String> originNames = origin.stream().map(ColumnDescriptor::getName).collect(Collectors.toList());

        for (ColumnDescriptor column : current) {
            if (!originNames.contains(column.getName())) {
                continue;
            }

            ColumnDescriptor that = origin.stream().filter(item -> Objects.equals(item.getName(), column.getName())).findFirst().orElseThrow(NullPointerException::new);

            if (!column.equals(that)) {
                result.add(column);
            }
        }

        return result;
    }

    @Override
    public List<ColumnDescriptor> findDroppedColumn(@NotNull List<ColumnDescriptor> origin, @NotNull List<ColumnDescriptor> current) {
        List<String> currentNames = current.stream().map(ColumnDescriptor::getName).collect(Collectors.toList());

        return origin.stream().filter(item -> !currentNames.contains(item.getName())).collect(Collectors.toList());
    }

    @Override
    public ChangeDescriptor<TableDescriptor> diffTable(@NotNull TableDescriptor origin, @NotNull TableDescriptor current) {
        List<String> changedList = new ArrayList<>();

        if (changed(origin.getComment(), current.getComment())) {
            changedList.add(ChangeConstants.Comment);
        }

        return new ChangeDescriptor<>(origin, current, changedList);
    }

    @Override
    public ChangeDescriptor<ColumnDescriptor> diffColumn(@NotNull ColumnDescriptor origin, @NotNull ColumnDescriptor current) {
        List<String> changedList = new ArrayList<>();

        if (changed(origin.getComment(), current.getComment())) {
            changedList.add(ChangeConstants.Comment);
        }
        if (changed(origin.getDbType(), current.getDbType())) {
            changedList.add(ChangeConstants.DbType);
        }
        if (changed(origin.getMaxLength(), current.getMaxLength())) {
            changedList.add(ChangeConstants.MaxLength);
        }

        if (changed(origin.getPrecise(), current.getPrecise())) {
            changedList.add(ChangeConstants.Precise);
        }

        if (changed(origin.isAllowNull(), current.isAllowNull())) {
            changedList.add(ChangeConstants.AllowNull);
        }

        if (changed(origin.isAutoIncrement(), current.isAutoIncrement())) {
            changedList.add(ChangeConstants.AutoIncrement);
        }

        if (changed(origin.isPrimaryKey(), origin.isPrimaryKey())) {
            changedList.add(ChangeConstants.PrimaryKey);
        }

        return new ChangeDescriptor<>(origin, current, changedList);
    }

    protected boolean changed(Object left, Object right) {
        if (left == null && right == null) {
            return false;
        }

        if (left == null || right == null) {
            return true;
        }

        return !left.equals(right);
    }
}
