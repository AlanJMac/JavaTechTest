# 2. Task: Remove White Space

## Task Description

Write a Java program to remove all white spaces from a string without using replace().

## Basic Code Solution

```
String[] splitOnWhiteSpace = inputString.split("\\s+");
return String.join("", splitOnWhiteSpace);
```

## Run the code

### Javac

> javac RemoveWhiteSpace.java
> java ajmac.interview.java.RemoveWhiteSpace "A string with white space."

Result:

```
INFO: Result: Astringwithwhitespace.
```

### Maven (run tests)

Run tests:

> mvn clean install

Execute:

> mvn clean compile exec:java -Dexec.args="'A string with white space.'"

Result:

```
INFO: Result: Astringwithwhitespace.
```
