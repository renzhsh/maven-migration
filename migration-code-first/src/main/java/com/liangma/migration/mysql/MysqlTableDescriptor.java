package com.liangma.migration.mysql;

import com.liangma.migration.descriptor.TableDescriptor;
import org.jetbrains.annotations.NotNull;

/**
 *
 */
public class MysqlTableDescriptor extends TableDescriptor {

    private MysqlEngine engine;

    public MysqlTableDescriptor() {
    }

    public MysqlTableDescriptor(@NotNull TableDescriptor descriptor) {
        this.setName(descriptor.getName());
        this.setComment(descriptor.getComment());
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
