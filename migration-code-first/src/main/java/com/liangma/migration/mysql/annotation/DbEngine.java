package com.liangma.migration.mysql.annotation;

import com.liangma.migration.mysql.MysqlEngine;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Inherited
public @interface DbEngine {
    MysqlEngine value() default MysqlEngine.InnoDB;
}
