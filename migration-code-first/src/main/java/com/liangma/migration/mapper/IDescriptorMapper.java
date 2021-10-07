package com.liangma.migration.mapper;

import com.liangma.migration.descriptor.ClassDescriptor;
import com.liangma.migration.descriptor.ColumnDescriptor;
import com.liangma.migration.descriptor.FieldDescriptor;
import com.liangma.migration.descriptor.TableDescriptor;
import com.liangma.migration.exception.*;

/**
 *
 */
public interface IDescriptorMapper {
    /**
     * @param clazz
     * @return
     */
    TableDescriptor mapTable(ClassDescriptor clazz) throws MigrationException;

    /**
     * @param field
     * @return
     */
    ColumnDescriptor mapColumn(FieldDescriptor field) throws MigrationException;
}
