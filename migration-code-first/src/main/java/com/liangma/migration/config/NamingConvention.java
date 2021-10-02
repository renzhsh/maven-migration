package com.liangma.migration.config;

import com.liangma.migration.util.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * 命名规范
 */
public class NamingConvention {

    /**
     * 骆驼式命名法
     * example: userName
     */
    public boolean camel;

    /**
     * 统一前缀
     */
    public String prefix;

    /**
     * 帕斯卡命名法
     * example: UserName
     */
    public boolean pascal;

    /**
     * 小写_下划线法
     * example: user_name
     */
    public boolean lowercaseWithUnderline;

    /**
     * 大写_下划线法
     * example: USER_NAME
     */
    public boolean uppercaseWithUnderline;

    /**
     * @param origin
     * @return
     */
    public String caseTransform(@NotNull String origin) {
        // 允许的字符：[a-z], [A-Z], [0-9], _

        ArrayList<String> segment = new ArrayList<>();

        String originName = origin;

        if (!StringUtils.isEmpty(prefix)) {
            originName = prefix + originName;
        }

        String item = "";

        for (int i = 0; i < originName.length(); i++) {
            char letter = originName.charAt(i);

            // 不是字母，数字 或 下划线_
            if (!Character.isLetter(letter) && !Character.isDigit(letter) && letter != '_') {
                if (!StringUtils.isEmpty(item)) {
                    segment.add(item);
                    item = "";
                }
                continue;
            }

            if (letter == '_') {
                if (!StringUtils.isEmpty(item)) {
                    segment.add(item);
                    item = "";
                }
                continue;
            }

            if (Character.isUpperCase(letter)) {
                if (!StringUtils.isEmpty(item)) {
                    segment.add(item);
                    item = "";
                }

                item += letter;
                continue;
            }

            if (Character.isLowerCase(letter) || Character.isDigit(letter)) {
                item += letter;
                continue;
            }
        }

        if (!StringUtils.isEmpty(item)) {
            segment.add(item);
        }

        StringBuilder result = new StringBuilder();

        /**
         * 骆驼式命名法
         * example: userName
         */
        if (camel) {
            for (int i = 0; i < segment.size(); i++) {
                String seg = segment.get(i);
                if (i == 0) {
                    result.append(seg.toLowerCase());
                } else {
                    result.append(Character.toUpperCase(seg.charAt(0))).append(seg.substring(1));
                }
            }
        }

        /**
         * 帕斯卡命名法
         * example: UserName
         */
        if (pascal) {
            for (String seg : segment) {
                result.append(Character.toUpperCase(seg.charAt(0))).append(seg.substring(1));
            }
        }

        /**
         * 小写_下划线法
         * example: user_name
         */
        if (lowercaseWithUnderline) {
            result = new StringBuilder(segment.stream().map(String::toLowerCase).collect(Collectors.joining("_")));
        }

        /**
         * 大写_下划线法
         * example: USER_NAME
         */
        if (uppercaseWithUnderline) {
            result = new StringBuilder(segment.stream().map(String::toUpperCase).collect(Collectors.joining("_")));
        }

        return result.toString();

    }

    @Override
    public String toString() {
        return "NamingConvention{" +
                "camel=" + camel +
                ", prefix='" + prefix + '\'' +
                ", pascal=" + pascal +
                ", lowercaseWithUnderline=" + lowercaseWithUnderline +
                ", uppercaseWithUnderline=" + uppercaseWithUnderline +
                '}';
    }
}