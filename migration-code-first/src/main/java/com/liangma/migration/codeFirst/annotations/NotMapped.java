package com.liangma.migration.codeFirst.annotations;

import java.lang.annotation.*;

/**
 * 不建立数据库字段映射
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.FIELD})
@Inherited
public @interface NotMapped {
}
