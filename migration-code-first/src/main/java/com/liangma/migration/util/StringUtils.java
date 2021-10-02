package com.liangma.migration.util;

public class StringUtils {
    public static boolean isEmpty(String value) {
        return isEmpty((CharSequence) value);
    }

    public static boolean isEmpty(CharSequence value) {
        return value == null || value.length() == 0;
    }

    public static boolean isNull(CharSequence value) {
        return value == null;
    }

    public static boolean notNull(CharSequence value) {
        return !isNull(value);
    }
}
