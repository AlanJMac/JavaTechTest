# 3. Task: Swap Numbers Without Third Variable

## Task Description

Write a Java program to swap two numbers without using the third variable.

## Basic Code Solution

```
double first = 1.0;
double second = 2.0<>

first = first + second;
second = first - second;
first = first - second;

```

## Run the code

### Javac

> javac NumberSwap.java
> java ajmac.interview.java.NumberSwap 5 10

Result:

```
INFO: Before swap, First: [5.0], Second: [10.0].
INFO: After swap, First: [10.0], Second: [5.0].
```

### Maven (run tests)

Run tests:

> mvn clean install

Execute:

> mvn clean compile exec:java -Dexec.args="5 10"

Result:

```
INFO: Before swap, First: [5.0], Second: [10.0].
INFO: After swap, First: [10.0], Second: [5.0].
```
