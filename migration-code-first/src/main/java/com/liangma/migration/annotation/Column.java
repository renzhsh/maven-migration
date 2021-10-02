package com.liangma.migration.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Inherited
public @interface Column {
    /**
     * 名称
     * @return String
     */
    String value() default "";

    /**
     * 字段类型
     * @return
     */
    String dbType() default "";

}
