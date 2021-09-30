package com.liangma.migration.annotations;

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
