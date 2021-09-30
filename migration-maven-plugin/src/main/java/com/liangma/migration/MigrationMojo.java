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

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;


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


    }

}
