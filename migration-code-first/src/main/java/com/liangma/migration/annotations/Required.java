package com.liangma.migration.annotations;

import java.lang.annotation.*;

/**
 * 不能为null
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.FIELD})
@Inherited
public @interface Required {
}
