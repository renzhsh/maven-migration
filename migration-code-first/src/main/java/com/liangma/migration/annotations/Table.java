package com.liangma.migration.annotations;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE})
@Inherited
public @interface Table {
    String value()  default "";
}
