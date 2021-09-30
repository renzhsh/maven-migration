package com.liangma.migration.loaders;

import com.liangma.migration.logs.ILogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.*;

@Component
public class AnnotatedClassLoader implements IAnnotatedClassLoader {

    @Autowired
    private ILogger logger;
    private String classDirectory;

    private ClassLoader loader;

    public AnnotatedClassLoader(){

    }


    @Override
    public List<Class<?>> getTypesAnnotatedWith(Class clazz) {
        return new ArrayList<>();
    }


    public Set<Class<?>> GetAllClasses() {
        Set<Class<?>> classes = new HashSet<>();

        File[] files = new File(classDirectory).listFiles();
        ArrayList<String> classNames = GetClassNames(files);

        for (String clazz : classNames) {
            logger.info(clazz);
        }

        for (String name : classNames) {

            try {
                classes.add(loader.loadClass(name));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return classes;
    }

    private ArrayList<String> GetClassNames(File[] files) {
        ArrayList<String> list = new ArrayList<>();

        for (File file : files) {
            if (file.isFile()) {
                String path = file.getPath();

                if (path.endsWith(".class")) {
                    String className = path.substring(classDirectory.length() + 1, path.length() - ".class".length()).replace("\\", ".");

                    list.add(className);
                }

            } else if (file.isDirectory()) {
                list.addAll(GetClassNames(Objects.requireNonNull(file.listFiles())));
            }
        }

        return list;
    }
}
