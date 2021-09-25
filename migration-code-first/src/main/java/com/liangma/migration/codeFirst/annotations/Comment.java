package com.liangma.migration.codeFirst.annotations;

import java.lang.annotation.*;

/**
 * 注释
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.FIELD, ElementType.TYPE})
@Inherited
public @interface Comment {
    String value();
}
