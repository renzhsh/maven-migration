package com.liangma.migration;

import com.liangma.migration.loaders.AnnotatedClassLoader;
import com.liangma.migration.test.logs.ConsoleLog;
import org.apache.maven.plugin.logging.Log;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Set;

public class MigrationApplication {
    public static void main(String[] args) throws Exception {
        ClassLoader loader = MigrationApplication.class.getClassLoader();
        String classDirectory = loader.getResource("").getPath();//.substring(1);
        Log log = new ConsoleLog();


        AnnotatedClassLoader annotatedClassLoader = new AnnotatedClassLoader(loader, classDirectory, log);

        Set<Class<?>> list = annotatedClassLoader.GetAllClasses();
        for (Class<?> clazz : list) {
            System.out.println(clazz);
        }
    }
}
