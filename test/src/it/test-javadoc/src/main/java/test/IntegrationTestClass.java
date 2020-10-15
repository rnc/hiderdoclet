package test;

import org.goots.hiderdoclet.doclet.JavadocExclude;

public class IntegrationTestClass
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
        public void innerMethodThatIsHidden()
        {

        }
    }
}
