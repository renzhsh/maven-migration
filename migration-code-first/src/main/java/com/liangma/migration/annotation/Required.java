package com.liangma.migration.annotation;

import java.lang.annotation.*;

/**
 * 不能为null
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Inherited
public @interface Required {
}
