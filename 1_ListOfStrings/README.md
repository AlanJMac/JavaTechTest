# 1. Task: List of Strings

## Task Description

Given a list of strings, write a method that returns a list of all strings that start with the letter ‘a’
(lower case) and have exactly 3 letters. TIP: Use Java Lambdas and Streams API’s.

## Basic Code Solution

```
public List<String> filterList(List<String> inputStrings) {
    return inputStrings.stream().filter(s -> s.startsWith("a") && s.length() == 3).collect(Collectors.toList());
}
```

## Run the code

### Javac

> javac StringFilter.java
> java ajmac.interview.java.StringFilter abc def

Result:

```
INFO: Filtered Result: [abc]
```

### Maven (run tests)

Run tests:

> mvn clean install

Execute:

> mvn clean compile exec:java -Dexec.args="abc def"

Result:

```
INFO: Filtered Result: [abc]
```
