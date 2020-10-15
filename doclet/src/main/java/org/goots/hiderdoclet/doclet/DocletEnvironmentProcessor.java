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

import com.sun.source.util.DocTrees;
import jdk.javadoc.doclet.DocletEnvironment;
import jdk.javadoc.internal.tool.DocEnvImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class DocletEnvironmentProcessor extends DocEnvImpl implements DocletEnvironment
{
    private final Logger logger = LoggerFactory.getLogger( DocletEnvironmentProcessor.class );
    private final DocletEnvironment delegate;

    public DocletEnvironmentProcessor( DocletEnvironment environment )
    {
        super((( DocEnvImpl)environment).toolEnv, (( DocEnvImpl)environment).etable );

        this.delegate = environment;
    }

    /**
     * Returns the elements <a href="package-summary.html#specified">specified</a>
     * when the tool is invoked.
     *
     * @return the set of specified elements
     */
    @Override
    public Set<? extends Element> getSpecifiedElements()
    {
        return delegate.getSpecifiedElements();
    }

    /**
     * Returns the module, package and type elements that should be
     * <a href="package-summary.html#included">included</a> in the
     * documentation.
     *
     * @return the set of included elements
     */
    @Override
    public Set<? extends Element> getIncludedElements()
    {
        Set<? extends Element> result = new HashSet<>(delegate.getIncludedElements());

        Iterator<? extends Element> iterator = result.iterator();
        while (iterator.hasNext())
        {
            Element e = iterator.next();
            if (checkForAnnotation( e ))
            {
                logger.info( "Removing included element {}", e.getSimpleName() );
                iterator.remove();
            }
        }

        return result;
    }

    /**
     * Returns an instance of the {@code DocTrees} utility class.
     * This class provides methods to access {@code TreePath}s, {@code DocCommentTree}s
     * and so on.
     *
     * @return a utility class to operate on doc trees
     */
    @Override
    public DocTrees getDocTrees()
    {
        return delegate.getDocTrees();
    }

    /**
     * Returns an instance of the {@code Elements} utility class.
     * This class provides methods for operating on
     * {@link Element elements}.
     *
     * @return a utility class to operate on elements
     */
    @Override
    public Elements getElementUtils()
    {
        return delegate.getElementUtils();
    }

    /**
     * Returns an instance of the {@code Types} utility class.
     * This class provides methods for operating on
     * {@link TypeMirror type mirrors}.
     *
     * @return a utility class to operate on type mirrors
     */
    @Override
    public Types getTypeUtils()
    {
        return delegate.getTypeUtils();
    }

    /**
     * Returns true if an element should be
     * <a href="package-summary.html#included">included</a> in the
     * documentation.
     *
     * @param e the element
     * @return true if included, false otherwise
     */
    @Override
    public boolean isIncluded( Element e )
    {
        if (checkForAnnotation( e ))
        {
            logger.info( "Ignoring element {}", e.getSimpleName() );
            return false;
        }
        return delegate.isIncluded( e );
    }

    /**
     * Returns true if the element is <a href="package-summary.html#selected">selected</a>.
     *
     * @param e the element
     * @return true if selected, false otherwise
     */
    @Override
    public boolean isSelected( Element e )
    {
        if (checkForAnnotation( e ))
        {
            logger.info( "Not selecting element {}", e.getSimpleName() );
            return false;
        }
        return delegate.isSelected( e );
    }

    /**
     * Returns the file manager used to read and write files.
     *
     * @return the file manager used to read and write files
     */
    @Override
    public JavaFileManager getJavaFileManager()
    {
        return delegate.getJavaFileManager();
    }

    /**
     * Returns the source version of the source files that were read.
     *
     * @return the source version
     */
    @Override
    public SourceVersion getSourceVersion()
    {
        return delegate.getSourceVersion();
    }

    /**
     * Returns the required level of module documentation.
     *
     * @return the required level of module documentation
     */
    @Override
    public ModuleMode getModuleMode()
    {
        return delegate.getModuleMode();
    }

    /**
     * Returns the file kind of a type element.
     *
     * @param type the type element
     * @return the file kind
     */
    @Override
    public JavaFileObject.Kind getFileKind( TypeElement type )
    {
        return delegate.getFileKind( type );
    }

    /**
     * Return true if we find the Exclude annotation
     * @param e the element to check
     * @return true if the annotation is found
     */
    private boolean checkForAnnotation(Element e)
    {
        List<? extends AnnotationMirror> annotationMirrors = e.getAnnotationMirrors();
        boolean result = false;

        if ( annotationMirrors.stream().anyMatch( a -> a.getAnnotationType().toString().equals( "org.goots.hiderdoclet.doclet.JavadocExclude" ) ) )
        {
            result = true;
        }
        logger.debug( "Looking for annotation in element {} and found {}", e.getSimpleName(), result );

        return result;
    }
}
