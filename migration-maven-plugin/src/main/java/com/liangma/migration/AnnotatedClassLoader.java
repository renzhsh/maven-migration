package com.liangma.migration;

import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

public class AnnotatedClassLoader {

    private final MavenProject project;
    private final String classDirectory;
    private final Log log;

    public AnnotatedClassLoader(MavenProject project, String classDirectory, Log log) {
        this.project = project;
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

        URLClassLoader loader = getClassLoader(project);

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

    private URLClassLoader getClassLoader(MavenProject project) {
        try {
            // 所有的类路径环境，也可以直接用 compilePath
            List<String> classpathElements = project.getCompileClasspathElements();

            classpathElements.add(project.getBuild().getOutputDirectory());
            classpathElements.add(project.getBuild().getTestOutputDirectory());
            // 转为 URL 数组
            URL[] urls = new URL[classpathElements.size()];
            for (int i = 0; i < classpathElements.size(); ++i) {
                urls[i] = new File((String) classpathElements.get(i)).toURL();
            }
            // 自定义类加载器
            return new URLClassLoader(urls, this.getClass().getClassLoader());
        } catch (Exception e) {
            log.debug("Couldn't get the classloader.");
            return (URLClassLoader) this.getClass().getClassLoader();
        }
    }
}
