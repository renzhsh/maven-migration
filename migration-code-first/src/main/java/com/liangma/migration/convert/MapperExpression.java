package com.liangma.migration.convert;

import com.liangma.migration.exception.InvalidCharacterException;
import com.liangma.migration.exception.InvalidExpressionException;
import com.liangma.migration.util.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Mapper表达式
 * <p>
 * 标准格式：varchar,1000,0,true
 * 解释： type,maxLength,precise,allowNull
 */
public class MapperExpression {
    private final String dbType;
    private int maxLength = 0;
    private int precise = 0;
    private boolean allowNull = true;

    public MapperExpression(@NotNull String expression) throws InvalidExpressionException {
        if (StringUtils.isEmpty(expression)) {
            throw new InvalidExpressionException(expression);
        }

        String[] items = expression.split(",");

        dbType = items[0];
        if (items.length > 1) {
            // 只能是数字
            if (!Pattern.matches("^[0-9]+$", items[1])) {
                throw new InvalidExpressionException(expression);
            }

            maxLength = Integer.parseInt(items[1]);
        }

        if (items.length > 2) {
            // 只能是数字
            if (!Pattern.matches("^[0-9]+$", items[2])) {
                throw new InvalidExpressionException(expression);
            }

            precise = Integer.parseInt(items[2]);
        }

        if (items.length > 3) {
            List<String> booleans = Arrays.asList("true", "false", "TRUE", "FALSE");

            if (!booleans.contains(items[3])) {
                throw new InvalidExpressionException(expression);
            }

            allowNull = Boolean.parseBoolean(items[3]);
        }
    }

    public String getDbType() {
        return dbType;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public int getPrecise() {
        return precise;
    }

    public boolean isAllowNull() {
        return allowNull;
    }
}
