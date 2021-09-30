package com.liangma.migration.annotations;

import java.lang.annotation.*;

/**
 * 最大长度
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.FIELD})
@Inherited
public @interface MaxLength {
    int value();

    /**
     * 数字精度
     * @return int
     */
    int precise() default 0;
}
