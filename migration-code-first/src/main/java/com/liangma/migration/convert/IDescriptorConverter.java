package com.liangma.migration.convert;

import com.liangma.migration.descriptor.ClassDescriptor;
import com.liangma.migration.descriptor.ColumnDescriptor;
import com.liangma.migration.descriptor.FieldDescriptor;
import com.liangma.migration.descriptor.TableDescriptor;

/**
 *
 */
public interface IDescriptorConverter {
    /**
     *
     * @param clazz
     * @return
     */
    TableDescriptor TableConvert(ClassDescriptor clazz);

    /**
     *
     * @param field
     * @return
     */
    ColumnDescriptor ColumnConvert(FieldDescriptor field);
}
