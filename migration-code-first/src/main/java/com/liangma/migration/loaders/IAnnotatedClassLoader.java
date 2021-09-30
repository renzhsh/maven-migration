package com.liangma.migration.loaders;

import java.util.List;

/**
 * 注解类加载器
 */
public interface IAnnotatedClassLoader {

    /**
     * 获取指定注解的类
     * @param clazz
     * @return
     */
    List<Class<?>> getTypesAnnotatedWith(Class clazz) throws ClassNotFoundException;
}
