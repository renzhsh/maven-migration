package com.liangma.migration.annotation;

import java.lang.annotation.*;

/**
 * 主键
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Inherited
public @interface Key {
    /**
     * 自动递增
     * @return
     */
    boolean autoIncrement() default false;
}
