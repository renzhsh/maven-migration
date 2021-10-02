package com.liangma.migration.util;

import java.util.Objects;

public class ObjectUtils {
    public static <T extends Object> T IfNull(T val, T val1) {
        return Objects.isNull(val) ? val1 : val;
    }
}
