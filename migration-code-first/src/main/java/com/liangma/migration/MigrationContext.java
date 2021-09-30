package com.liangma.migration;

import com.liangma.migration.loaders.IAnnotatedClassLoader;
import com.liangma.migration.logs.ILogger;
import org.springframework.stereotype.Component;


/**
 *
 */
@Component
public class MigrationContext {

    private final IAnnotatedClassLoader annotatedClassLoader;

    private final ILogger log;

    /**
     *
     * @param annotatedClassLoader
     * @param log
     */
    public MigrationContext(IAnnotatedClassLoader annotatedClassLoader, ILogger log) {
        this.annotatedClassLoader = annotatedClassLoader;
        this.log = log;
    }


    public void LoadAllClass(){
        annotatedClassLoader.getClass();
    }
}
