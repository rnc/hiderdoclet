
[![Actions Status](https://github.com/rnc/hiderdoclet/workflows/CI/badge.svg)](https://github.com/rnc/hiderdoclet/workflows/CI/actions)


# JavaDoc Doclet Extension

This is a JDK11 (and above) doclet that adds the capability to ignore arbitrary code if it has been annotated with `@JavadocExclude`.

For example, using the code from [here](https://github.com/rnc/hiderdoclet/blob/main/test/src/main/java/org/goots/hiderdoclet/doclet/test/TestDoc.java#L29):

```
    /**
     * Don't include me!
     */
    @JavadocExclude
    public void dummyMethod()
    {

    }

```

## Configuration

There is only a single configuration parameter currently: `org.goots.hiderdoclet.logLevel`. For example to enable debug
logging for the doclet pass as an additional option to the doclet configuration:
```
    <additionalJOption>-J-Dorg.goots.hiderdoclet.logLevel=debug</additionalJOption>
```
Other supported values are `info` (the default) and `warn`. The value is case insensitive.

## Exanple

A complete example is

```
         <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-javadoc-plugin</artifactId>
            <executions>
               <execution>
                  <id>attach-javadocs</id>
                  <configuration>
                     <source>11</source>
                     <doclet>org.goots.hiderdoclet.doclet.JavadocFilter</doclet>
                     <docletArtifact>
                        <groupId>org.goots.hiderdoclet</groupId>
                        <artifactId>doclet</artifactId>
                        <version>1.0</version>
                     </docletArtifact>
                     <additionalJOptions>
                        <additionalJOption>-J--add-exports=jdk.javadoc/jdk.javadoc.internal.tool=ALL-UNNAMED</additionalJOption>
                     </additionalJOptions>
                     <detectJavaApiLink>true</detectJavaApiLink>
                  </configuration>
               </execution>
            </executions>
         </plugin>

```
