# 2. Test Case Analysis

## Method Test #3:

The method under test, `getNValue` exists within `Repeat.java` in the `net.sf.borg.model` package, and has the following signature including java doc:

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

The coverage metrics before looking into the code (black-box tests) can be seen in the coverage portion, with coverage percent 79.2%. When expanding the code, 2 additional test cases were added. One to check a `null` input, and another to check for repeated input.

This test case checks for when a `null` string has been passed in. Upon inspection of the code and coverage metrics, it was determined that this branch was not tested for in the black-box testing. When looking into the code, it became clear that a null string should return a 0 value multiplier.  

``` java
@Test
public void testGetNValueNull() {
  String repeat = null;
  assertEquals(0, Repeat.getNValue(repeat));
}
```

However odd, this test was written after the inspection of the code and coverage metrics. Specifically when looking into the `Repeat` class, the branch checks when more than one comma separated, encoded strings. When this occurs, only the first encoded string and a multiplier are considered.  

``` java
@Test
public void testGetNValueMultiple() {
  String f = Repeat.NDAYS + ",1," + Repeat.NDAYS;
  assertEquals(1, Repeat.getNValue(f));
}
```
