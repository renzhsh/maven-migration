package com.liangma;

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

import com.liangma.migration.AnnotatedClassLoader;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.IOException;


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

    public void execute() throws MojoExecutionException {

        AnnotatedClassLoader loader = new AnnotatedClassLoader(project, classDirectory, getLog());

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

}
