package org.goots.hiderdoclet.doclet.test;

import org.goots.hiderdoclet.doclet.JavadocExclude;


// TODO: Replace with a proper test rather than manual verification.

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
