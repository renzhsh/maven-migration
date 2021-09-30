package com.liangma.migration.annotations;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.CLASS)
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
