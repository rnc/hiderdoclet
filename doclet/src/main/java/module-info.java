module hiderdoclet {
    requires java.compiler;
    requires jdk.javadoc;
    requires java.base;

    requires org.slf4j;
    requires logback.classic;

    exports org.goots.hiderdoclet.doclet;
}
