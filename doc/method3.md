# Method Test #3:

The method under test has the following signature, including java doc:

```java
/**
 * Gets the "N" multiplier value from the encoded appointment string
 *
 * @param f
 *            the encoded appointment string
 *
 * @return the "N" multiplier value
 */
static public int getNValue(String f);
```

Using the testing techniques which we have discussed in class we have devised the following strategy for testing the above stubbed method. Upon inspection, the method under test takes as an argument a `String`. This was troublesome at first however, looking further into this we were able to deduce what, in this case, a valid `String` is. In this context a valid input string is one which contains some word, *Constant*, and a comma separated integer, *Number* such that it has the form `"Constant,Number"`. In the context of testing we can break this up into essentially *2* inputs, the Constant and the Number. This is important to note from the start as we use this context through the entire test.

## Boundary Value Testing

As the input is a `String` and the output is an `int`, we can carefully consider the edge cases given that the input is of the form `Constant + "," + Number` (See Appendix A for the JUnit test cases for this strategy):

**Constant**:  
  - The empty `String`.  
  - A very long `String`.  
  - A desirable `String`.  
  - Without a comma separator.  

**Number**:  
  - The empty `String`.  
  - A non-numeric `String`.  
  - A very large negative `int` and `float`.  
  - Zero.  
  - A very large positive `int` and `float`.  


## Equivalence Class Testing

Using the following strategy we attempt to cover some of the missing gaps, and remove some of the redundancy from the *Boundary Value Tests*. The following diagrams present this testing strategy, each of the possible Number inputs are on the Y axis, while each of the possible Constant inputs are on the X axis. Each of the filled in boxes presents valid input, while the X represents a corresponding test case (See Appendix A for the JUnit test cases for this strategy).

![Weak Normal Test Cases](../assets/weak-normal.png)  

![Strong Normal Test Cases](../assets/strong-normal.png)  

![Weak Robust Test Cases](../assets/weak-robust.png)  

![Strong Robust Test Cases](../assets/strong-robust.png)

\newpage

## Decision Table Testing

In the following decision table we show the desired behaviour of the system; under the condition what the desired action should be (See Appendix A for the JUnit test cases for this strategy).

![Decision Table](../assets/decision-table.png)
