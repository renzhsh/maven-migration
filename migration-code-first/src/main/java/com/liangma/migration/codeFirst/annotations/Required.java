package com.liangma.migration.codeFirst.annotations;

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
