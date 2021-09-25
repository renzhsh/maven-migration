package com.liangma.migration.codeFirst.annotations;

import java.lang.annotation.*;

/**
 * 主键
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.FIELD})
@Inherited
public @interface Key {
}
