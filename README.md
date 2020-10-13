
# JavaDoc Doclet Extension

This doclet adds the capability to ignore arbitrary code if it has been annotated with `@JavadocExclude`.

## Configuration

There is only a single configuration parameter currently: `org.goots.hiderdoclet.debug`. For example to enable debug 
logging for the doclet pass as an additional option to the doclet configuration:
```
                        <additionalJOption>-J-Dorg.goots.hiderdoclet.debug=true</additionalJOption>
```

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
                        <version>${project.version}</version>
                     </docletArtifact>
                     <additionalJOptions>
                        <additionalJOption>--add-exports=jdk.javadoc/jdk.javadoc.internal.tool=hiderdoclet</additionalJOption>
                        <additionalJOption>--add-exports=jdk.compiler/com.sun.tools.javac.parser=hiderdoclet</additionalJOption>
                        <additionalJOption>--add-exports=jdk.compiler/com.sun.tools.javac.tree=hiderdoclet</additionalJOption>
                        <additionalJOption>--add-exports=jdk.compiler/com.sun.tools.javac.model=hiderdoclet</additionalJOption>
                        <additionalJOption>-J--add-exports=jdk.javadoc/jdk.javadoc.internal.tool=ALL-UNNAMED</additionalJOption>
                        <additionalJOption>-J--add-exports=jdk.compiler/com.sun.tools.javac.parser=ALL-UNNAMED</additionalJOption>
                        <additionalJOption>-J--add-exports=jdk.compiler/com.sun.tools.javac.tree=ALL-UNNAMED</additionalJOption>
                        <additionalJOption>-J--add-exports=jdk.compiler/com.sun.tools.javac.model=ALL-UNNAMED</additionalJOption>

                     </additionalJOptions>
                     <detectJavaApiLink>true</detectJavaApiLink>
                  </configuration>
               </execution>
            </executions>
         </plugin>

```
