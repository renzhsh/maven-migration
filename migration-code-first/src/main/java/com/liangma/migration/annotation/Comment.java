package com.liangma.migration.annotation;

import java.lang.annotation.*;

/**
 * 注释
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
@Inherited
public @interface Comment {
    String value();
}
