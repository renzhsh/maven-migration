package com.liangma.migration;

import com.liangma.migration.annotations.Table;
import com.liangma.migration.loaders.IAnnotatedClassLoader;
import com.liangma.migration.logs.ILogger;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.List;


/**
 *
 */
@Component
public class MigrationContext {

    private final IAnnotatedClassLoader annotatedClassLoader;

    private final ILogger log;

    /**
     * @param annotatedClassLoader
     * @param log
     */
    public MigrationContext(IAnnotatedClassLoader annotatedClassLoader, ILogger log) {
        this.annotatedClassLoader = annotatedClassLoader;
        this.log = log;
    }

    public void execute() {
        List<Class<?>> list = annotatedClassLoader.getTypesAnnotatedWith(Table.class);

        for (Class<?> item : list) {
            System.out.println(item);
        }
    }
}
