package com.liangma.migration.mysql.descriptor;

import com.liangma.migration.descriptor.TableDescriptor;
import com.liangma.migration.mysql.MysqlEngine;
import org.jetbrains.annotations.NotNull;

/**
 *
 */
public class MysqlTableDescriptor extends TableDescriptor {

    private MysqlEngine engine = MysqlEngine.InnoDB;

    public MysqlTableDescriptor() {
    }

    public MysqlTableDescriptor(@NotNull TableDescriptor descriptor) {
        this.setName(descriptor.getName());
        this.setComment(descriptor.getComment());
        this.setColumns(descriptor.getColumns());
    }

    public MysqlEngine getEngine() {
        return engine;
    }

    public void setEngine(MysqlEngine engine) {
        this.engine = engine;
    }


    @Override
    public String toString() {
        return "MysqlTableDescriptor{" +
                "name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                ", engine=" + engine +
                "} " + super.toString();
    }
}
