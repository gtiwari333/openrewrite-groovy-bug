## DEMO project to showcase errors during migrating groovy project using openrewrite


## How to generate error

Run it using following, see the logs

`./gradlew  clean rewriteRun --init-script init.gradle --info`

# Error 3. Can't parse conditional statements from generated code eg: @Slf4j

Github Issue:  TBD

PR: TBD

Issue:

Getting `Caused by: java.lang.ArrayIndexOutOfBoundsException: Index -2 out of bounds for length 11` error when logging
with parameters eg:   `log.info("Headers ${a}")` and  `log.info("Headers " + a)`

Full Error:

``` 
There were problems parsing src/main/groovy/com/example/demo/LogTestWithConcatenatedArg.groovy
Error during rewrite run
org.openrewrite.groovy.GroovyParsingException: Failed to parse src/main/groovy/com/example/demo/LogTestWithStringTemplate.groovy at cursor position 128. The surrounding characters in the original source are:
package com.example.demo

import groovy.util.logging.Slf4j

@Slf4j
class LogTestWithStringTemplate {

    void test() {
        ~cursor~>log.info("Headers ${a}")
    }
}

        at org.openrewrite.groovy.GroovyParserVisitor.visit(GroovyParserVisitor.java:248)
        at org.openrewrite.groovy.GroovyParser.lambda$parseInputs$3(GroovyParser.java:155)
        ...
        at org.gradle.execution.plan.DefaultPlanExecutor$ExecutorWorker.run(DefaultPlanExecutor.java:380)
        at org.gradle.internal.concurrent.ExecutorPolicy$CatchAndRecordFailures.onExecute(ExecutorPolicy.java:64)
        at org.gradle.internal.concurrent.AbstractManagedExecutor$1.run(AbstractManagedExecutor.java:47)
        at java.base@21.0.1/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1144)
        at java.base@21.0.1/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:642)
        at java.base@21.0.1/java.lang.Thread.run(Thread.java:1583)
Caused by: java.lang.ArrayIndexOutOfBoundsException: Index -2 out of bounds for length 11
        at org.openrewrite.groovy.GroovyParserVisitor.determineParenthesisLevel(GroovyParserVisitor.java:3991)
        at org.openrewrite.groovy.GroovyParserVisitor.getInsideParenthesesLevel(GroovyParserVisitor.java:3946)
        at org.openrewrite.groovy.GroovyParserVisitor.access$2600(GroovyParserVisitor.java:82)
        at org.openrewrite.groovy.GroovyParserVisitor$RewriteGroovyVisitor.insideParentheses(GroovyParserVisitor.java:1280)
        at org.openrewrite.groovy.GroovyParserVisitor$RewriteGroovyVisitor.visitMethodCallExpression(GroovyParserVisitor.java:2643)
        at org.codehaus.groovy.ast.expr.MethodCallExpression.visit(MethodCallExpression.java:79)
        ....
        at org.openrewrite.groovy.GroovyParserVisitor$RewriteGroovyClassVisitor.visitClassBlock(GroovyParserVisitor.java:822)
        at org.openrewrite.groovy.GroovyParserVisitor$RewriteGroovyClassVisitor.visitClass(GroovyParserVisitor.java:408)
        at org.openrewrite.groovy.GroovyParserVisitor.convertTopLevelStatement(GroovyParserVisitor.java:3436)
        at org.openrewrite.groovy.GroovyParserVisitor.visit(GroovyParserVisitor.java:234)
```

# FIXED: Error 2. @EqualsAndHashCode and other groovy groovy transform fails

GitHub Issue: https://github.com/openrewrite/rewrite/issues/4254

PRs:

- Groovy Parser supports basic Enum classes #5781 - https://github.com/openrewrite/rewrite/pull/5781
- Groovy Parser supports constructor method invocations for Enum classes
  #5802 - https://github.com/openrewrite/rewrite/pull/5802

<details>

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

</details> 

### FIXED: Error 1. Groovy Enum is throwing "Failed to parse" "UnsupportedOperationException: enum fields are not implemented."

Issue: https://github.com/openrewrite/rewrite/issues/4252

PR: "Skip parsing groovy generated transform methods" #4848 https://github.com/openrewrite/rewrite/pull/4848



<details> 

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

</details>
