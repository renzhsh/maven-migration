package com.liangma.migration.annotation;

import java.lang.annotation.*;

/**
 * 不建立数据库字段映射
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Inherited
public @interface NotMapped {
}
