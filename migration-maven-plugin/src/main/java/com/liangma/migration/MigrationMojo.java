package com.liangma.migration;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.liangma.migration.loaders.AnnotatedClassLoader;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;


/**
 * Goal which touches a timestamp file.
 *
 * @requiresDependencyResolution compile
 */
@Mojo(name = "migration")
public class MigrationMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project}")
    public MavenProject project;

    /**
     *
     */
    @Parameter(defaultValue = "${project.build.outputDirectory}")
    public String classDirectory;

    /**
     * 扫描Jar包的前缀
     */
    @Parameter(defaultValue = "")
    public String packagePrefix;

    private final Log log = getLog();

    public void execute() throws MojoExecutionException {

        AnnotatedClassLoader loader = new AnnotatedClassLoader(getClassLoader(project), classDirectory, log);

        try {
            loader.GetAllClasses();
        } catch (Exception e) {
            e.printStackTrace();
        }

//        loader.getTypesAnnotatedWith(Table.class);

        System.out.println(classDirectory);
//        System.out.println(packagePrefix);
        System.out.println("hello, maven plugin");
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
