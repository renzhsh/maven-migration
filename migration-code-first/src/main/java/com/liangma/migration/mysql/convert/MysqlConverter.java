package com.liangma.migration.mysql.convert;

import com.liangma.migration.mapper.DescriptorMapper;
import com.liangma.migration.mapper.IDescriptorMapper;
import com.liangma.migration.descriptor.ClassDescriptor;
import com.liangma.migration.descriptor.ColumnDescriptor;
import com.liangma.migration.descriptor.FieldDescriptor;
import com.liangma.migration.descriptor.TableDescriptor;
import com.liangma.migration.exception.MigrationException;
import com.liangma.migration.mysql.annotation.DbEngine;
import com.liangma.migration.mysql.descriptor.MysqlColumnDescriptor;
import com.liangma.migration.mysql.descriptor.MysqlTableDescriptor;
import com.liangma.migration.util.ObjectUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 *
 */
@Primary
@Component
public class MysqlConverter extends DescriptorMapper implements IDescriptorMapper {

    @Override
    public TableDescriptor mapTable(ClassDescriptor clazz) throws MigrationException {
        TableDescriptor superResult = super.mapTable(clazz);

        MysqlTableDescriptor result = new MysqlTableDescriptor(superResult);

        DbEngine engine = clazz.getAnnotation(DbEngine.class);
        if (engine != null) {
            result.setEngine(engine.value());
        }

        return result;
    }

    @Override
    public ColumnDescriptor mapColumn(FieldDescriptor field) throws MigrationException {
        ColumnDescriptor result = super.mapColumn(field);

        return ObjectUtils.<MysqlColumnDescriptor>MapTo(result, MysqlColumnDescriptor.class);

    }
}
