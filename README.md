# demo to reproduce openrewrite issue with groovy transform and enum

 ./gradlew  clean rewriteRun --init-script init.gradle


> Task :rewriteRun
Validating active recipes
Scanning sources in project :
Using active styles []
There were problems parsing some source files, run with --info to see full stack traces
There were problems parsing src/main/groovy/com/example/demo/Test2.groovy
There were problems parsing src/main/groovy/com/example/demo/Test.groovy
There were problems parsing src/main/groovy/com/example/demo/Status.groovy
All sources parsed, running active recipes: org.openrewrite.groovy.format.AutoFormat





### Details of error


 ./gradlew  clean rewriteRun --init-script init.gradle --info 


Transforms 
```
Error during rewrite run
org.openrewrite.groovy.GroovyParsingException: Failed to parse src/main/groovy/com/example/demo/Test.groovy, cursor position likely inaccurate.
        at org.openrewrite.groovy.GroovyParserVisitor.visit(GroovyParserVisitor.java:170)
        at org.openrewrite.groovy.GroovyParser.lambda$parseInputs$4(GroovyParser.java:154)
        at java.base@17.0.9/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        at java.base@17.0.9/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1625)
 ...
Caused by: java.lang.StringIndexOutOfBoundsException: begin 137, end 137, length 134
        at java.base/java.lang.String.checkBoundsBeginEnd(String.java:4606)
        at java.base/java.lang.String.substring(String.java:2709)
        at org.openrewrite.groovy.GroovyParserVisitor.whitespace(GroovyParserVisitor.java:2171)
        at org.openrewrite.groovy.GroovyParserVisitor.access$000(GroovyParserVisitor.java:66)
        at org.openrewrite.groovy.GroovyParserVisitor$RewriteGroovyVisitor.visitVariableExpressionType(GroovyParserVisitor.java:1955)
        at org.openrewrite.groovy.GroovyParserVisitor$RewriteGroovyVisitor.visitDeclarationExpression(GroovyParserVisitor.java:1262)
        at org.codehaus.groovy.ast.expr.DeclarationExpression.visit(DeclarationExpression.java:89)
```

 

ENUM:
```
Error during rewrite run
org.openrewrite.groovy.GroovyParsingException: Failed to parse src/main/groovy/com/example/demo/Status.groovy at cursor position 39. The next 10 characters in the original source are ` //enum al`
        at org.openrewrite.groovy.GroovyParserVisitor.visit(GroovyParserVisitor.java:175)
 ....
  Caused by: java.lang.UnsupportedOperationException: enum fields are not implemented.
        at org.openrewrite.groovy.GroovyParserVisitor$RewriteGroovyClassVisitor.visitEnumField(GroovyParserVisitor.java:357)
        at org.openrewrite.groovy.GroovyParserVisitor$RewriteGroovyClassVisitor.visitField(GroovyParserVisitor.java:348)
        at org.openrewrite.groovy.GroovyParserVisitor$RewriteGroovyClassVisitor.lambda$visitClassBlock$3(GroovyParserVisitor.java:332)
        at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1625)
```

