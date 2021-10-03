package com.liangma.migration.descriptor;

import com.liangma.migration.annotation.Comment;
import com.liangma.migration.util.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class ClassDescriptor {

    private Class<?> target;

    private String name;

    private String fullName;

    private String comment;

    private FieldDescriptor[] fields;

    public ClassDescriptor(@NotNull Class<?> clazz) {
        this.target = clazz;
    }

    public String getName() {
        if (StringUtils.isEmpty(name)) {
            String[] arrs = getFullName().split("[.]");
            name = arrs[arrs.length - 1];
        }

        return name;
    }

    public String getFullName() {
        if (StringUtils.isEmpty(fullName)) {
            this.fullName = this.target.getName();
        }
        return fullName;
    }

    public String getComment() {
        if (StringUtils.isNull(comment)) {
            Comment anno = target.getAnnotation(Comment.class);
            if (anno == null) {
                comment = "";
            } else {
                comment = anno.value();
            }
        }

        return comment;
    }

    public <T extends Annotation> T getAnnotation(@NotNull Class<T> annotationClass) {
        return target.getAnnotation(annotationClass);
    }

    public FieldDescriptor[] getFields() {
        if (fields == null) {
            List<FieldDescriptor> list = Arrays.stream(target.getDeclaredFields())
                    .map(FieldDescriptor::new)
                    .filter(item -> !item.isIgnore())
                    .collect(Collectors.toList());

            fields = list.toArray(new FieldDescriptor[]{});
        }

        return fields;
    }

    @Override
    public String toString() {
        return "ClassDescriptor{" +
                "name='" + getName() + '\'' +
                ", fullName='" + getFullName() + '\'' +
                ", comment='" + getComment() + '\'' +
                ", fields=" + Arrays.toString(getFields()) +
                '}';
    }
}
