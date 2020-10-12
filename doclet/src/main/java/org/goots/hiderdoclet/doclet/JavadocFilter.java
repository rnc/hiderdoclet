/*
 * Copyright (C) 2012 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.goots.hiderdoclet.doclet;

import ch.qos.logback.classic.Level;
import jdk.javadoc.doclet.DocletEnvironment;
import jdk.javadoc.doclet.StandardDoclet;

import javax.lang.model.SourceVersion;

/**
 * A <a href="http://java.sun.com/javase/6/docs/jdk/api/javadoc/doclet/">Doclet</a>
 * for excluding elements that are annotated with {@link JavadocExclude} to
 * decide what gets excluded from the Javadoc.
 * <br>
 */
public class JavadocFilter extends StandardDoclet
{

    public JavadocFilter()
    {
        String logLevel = System.getProperty( "org.goots.hiderdoclet.debug" );
        if ( "true".equalsIgnoreCase( logLevel ) )
        {
            ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) org.slf4j.LoggerFactory.getLogger(
                            ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME );
            root.setLevel( Level.DEBUG );
        }
    }

    @Override
    public String getName()
    {
        return getClass().getSimpleName();
    }

    @Override
    public SourceVersion getSupportedSourceVersion()
    {
        return SourceVersion.RELEASE_11;
    }

    @Override
    public boolean run( DocletEnvironment environment )
    {
        return super.run( new DocletEnvironmentProcessor( environment ));
    }
}
