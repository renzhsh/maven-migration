package com.liangma.migration.loaders;

import org.apache.maven.plugin.logging.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class AnnotatedClassLoader {


    private final Log log;
    private final ClassLoader loader;
    private final String classDirectory;

    public AnnotatedClassLoader(ClassLoader loader, String classDirectory, Log log) {
        this.loader = loader;
        this.classDirectory = classDirectory;
        this.log = log;
    }

    public ArrayList<Class<?>> getTypesAnnotatedWith(Class clazz) {

//        try {
//            ClassLoader loader = getClassLoader(this.project);
//
//
//            Class<?> clazz = loader.loadClass("com.liangma.migration.pluginTest.domain.Student");
//
//            System.out.println("Class:" + clazz.getCanonicalName());
//
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

        return new ArrayList<Class<?>>();

    }

    public Set<Class<?>> GetAllClasses() throws Exception {
        Set<Class<?>> classes = new HashSet<>();

        File[] files = new File(classDirectory).listFiles();
        ArrayList<String> classNames = GetClassNames(files);

        for (String name : classNames) {
            classes.add(loader.loadClass(name));
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
