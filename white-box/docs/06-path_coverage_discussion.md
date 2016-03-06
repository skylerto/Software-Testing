# 5. Path Coverage Discussion

## Paths

The execution paths through the control flow graph presented in section 4 are as follows:  

  - A(return G);  
  - ABC(return G);  
  - ABCDE(return H);  
  - ABCDE(return I);  

## Test coverage

As all of these test cases are based on branching, when running the coverage reports from EclEmma we are notified of these as potential test paths in the report. This is important because our test suite checks all possible branches in the `getNValue(Strin f)` method, 100% code coverage . Thereby checking all paths through the control flow graph. Test cases in our suite that do these checks are:

``` java
/**
 *  Test null input
 */
@Test
public void testGetNValueNull() {
	String repeat = null;
	assertEquals(Repeat.getNValue(repeat), 0);
}

```

``` java
// Test Constant as input
assertEquals(Repeat.getNValue("notavalidconstant" + "," + "5"), 0);

```

``` java
// Test contains only 1 constant and 1 multiplier
assertEquals(Repeat.getNValue(Repeat.NDAYS + "," + "5"), 5);
```

``` java
/**
 *  Test contains more than one constant and/or multiplier
 */
@Test
public void testGetNValueMultiple() {
	String f = Repeat.NDAYS + ",1," + Repeat.NDAYS;
	assertEquals(Repeat.getNValue(f), 1);
}
```
