package com.liangma.migration.descriptor;

import com.liangma.migration.annotations.Comment;
import com.liangma.migration.annotations.NotMapped;
import com.liangma.migration.util.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public final class FieldDescriptor {

    private Field target;

    private String name;

    private Class<?> type;

    private String comment;

    public FieldDescriptor(@NotNull Field field) {
        target = field;
    }

    public String getName() {
        if (StringUtils.isEmpty(name)) {
            name = target.getName();
        }
        return name;
    }

    public Class<?> getType() {
        if (type == null) {
            type = target.getType();
        }
        return type;
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

    public boolean isIgnore() {
        return target.isAnnotationPresent(NotMapped.class);
    }

    public <T extends Annotation> T getAnnotation(@NotNull Class<T> annotationClass) {
        return target.getAnnotation(annotationClass);
    }

    @Override
    public String toString() {
        return "FieldDescriptor{" +
                "name='" + getName() + '\'' +
                ", isIgnore=" + isIgnore() +
                ", type=" + getType() +
                ", comment='" + getComment() + '\'' +
                '}';
    }
}
