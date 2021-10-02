package com.liangma.migration.loaders;

import com.liangma.migration.logs.ILogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class AnnotatedClassLoader implements IAnnotatedClassLoader {

    @Autowired
    private ILogger logger;

    @Autowired
    private ClassLoader loader;

    @Qualifier("classDirectory")
    @Autowired
    private String classDirectory;



    @Override
    public List<Class<?>> getTypesAnnotatedWith(Class annotationClass) {
        List<Class<?>> list = getAllClasses();

        return (List<Class<?>>) list.stream().filter(item -> item.isAnnotationPresent(annotationClass)).collect(Collectors.toList());
    }


    private List<Class<?>> getAllClasses() {
        List<Class<?>> classes = new ArrayList<>();

        File[] files = new File(classDirectory).listFiles();
        ArrayList<String> classNames = getClassNames(files);

        for (String name : classNames) {
            try {
                classes.add(loader.loadClass(name));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return classes;
    }

    private ArrayList<String> getClassNames(File[] files) {
        ArrayList<String> list = new ArrayList<>();

        for (File file : files) {
            if (file.isFile()) {
                String path = file.getPath();

                if (path.endsWith(".class")) {
                    String className = path.substring(classDirectory.length(), path.length() - ".class".length()).replace("\\", ".");

                    list.add(className);
                }

            } else if (file.isDirectory()) {
                list.addAll(getClassNames(Objects.requireNonNull(file.listFiles())));
            }
        }

        return list;
    }
}
