package com.liangma.migration.convert;

import com.liangma.migration.descriptor.ClassDescriptor;
import com.liangma.migration.descriptor.ColumnDescriptor;
import com.liangma.migration.descriptor.FieldDescriptor;
import com.liangma.migration.descriptor.TableDescriptor;
import com.liangma.migration.exception.*;

/**
 *
 */
public interface IDescriptorConverter {
    /**
     * @param clazz
     * @return
     */
    TableDescriptor TableConvert(ClassDescriptor clazz) throws MigrationException;

    /**
     * @param field
     * @return
     */
    ColumnDescriptor ColumnConvert(FieldDescriptor field) throws MigrationException;
}
