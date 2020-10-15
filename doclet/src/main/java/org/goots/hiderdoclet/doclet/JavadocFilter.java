/*
 * Copyright (C) 2020 Red Hat, Inc.
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
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import jdk.javadoc.doclet.DocletEnvironment;
import jdk.javadoc.doclet.StandardDoclet;
import org.slf4j.LoggerFactory;

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
        // Programmatically configure logback so we don't have to include a logback.xml file which can
        // confuse dependent projects.
        ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) org.slf4j.LoggerFactory.getLogger(
                        ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME );

        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        loggerContext.reset();

        PatternLayoutEncoder ple = new PatternLayoutEncoder();
        ple.setPattern( "%level %logger{36} - %msg%n" );
        ple.setContext( loggerContext );
        ple.start();

        ConsoleAppender<ILoggingEvent> consoleAppender = new ConsoleAppender<>();
        consoleAppender.setEncoder( ple );
        consoleAppender.setContext( loggerContext );
        consoleAppender.start();

        root.addAppender( consoleAppender );

        String logLevel = System.getProperty( "org.goots.hiderdoclet.debug" );
        if ( "true".equalsIgnoreCase( logLevel ) )
        {
            root.setLevel( Level.DEBUG );
        }
        else
        {
            root.setLevel( Level.INFO );
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
