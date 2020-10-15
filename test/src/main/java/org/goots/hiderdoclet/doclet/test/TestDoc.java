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
package org.goots.hiderdoclet.doclet.test;

import org.goots.hiderdoclet.doclet.JavadocExclude;

/**
 * Just to check the annotation is detected and javadoc works.
 */
@SuppressWarnings( "unused" )
public class TestDoc
{
    /**
     * Don't include me!
     */
    @JavadocExclude
    public void dummyMethod()
    {

    }

    /**
     * I am a method
     */
    public void includeMe()
    {

    }

    public static class InnerClass
    {
        public void innerMethod()
        {

        }
    }


    @JavadocExclude
    public static class HiddenInnerClass
    {
        public void innerMethod()
        {

        }
    }
}
