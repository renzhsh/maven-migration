package com.liangma.migration.util;

import com.alibaba.fastjson.JSONObject;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ObjectUtils {
    public static <T extends Object> T IfNull(T val, T val1) {
        return Objects.isNull(val) ? val1 : val;
    }

    public static <T extends Object> T MapTo(@NotNull Object src, Class<?> clazz) {
        String json = JSONObject.toJSONString(src);

        // TODO: 1. 嵌入式对象解析
        // TODO: 2. cast失败处理

        return (T) JSONObject.parseObject(json, clazz);
    }
}
