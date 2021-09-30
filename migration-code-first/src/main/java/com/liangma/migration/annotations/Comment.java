package com.liangma.migration.annotations;

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
