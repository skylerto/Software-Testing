# Slices

Below we see the method under investigation, in this case `getNValue(String f)` from the `Repeat.java` class.

```java
/**
 * Gets the "N" multiplier value from the encoded appointment string
 *
 * @param f
 *            the encoded appointment string
 *
 * @return the "N" multiplier value
 */
static public int getNValue(String f) {
  if (f == null)
    return 0;

  String freq = Repeat.getFreq(f);

  if (!freq.equals(NDAYS) && !freq.equals(NWEEKS) && !freq.equals(NMONTHS) && !freq.equals(NYEARS))
    return (0);

  int i2 = f.indexOf(',', freq.length() + 1);
  if (i2 != -1)
    return (Integer.parseInt(f.substring(freq.length() + 1, i2)));

  return (Integer.parseInt(f.substring(freq.length() + 1)));

}
```

For this particular investigation we will statically and dynamically slice all A-defs and P-uses. **NOTE** For the duration of this section we will omit code comments and the method's declaration and outermost brackets for the brevity's sake.

## A-defs

A-defs, or assignment definitions are variable definitions created through assignments (e.g. `variable = value`). Within the `getNValue` method we find two assignment definitions, one for `String freq`.

### String Freq

We will begin by statically and dynamically slicing `String Freq`, which is defined by assignment in the method as follows:

```java
String freq = Repeat.getFreq(f);
```
#### Static Slicing

Forward slicing on `String freq` is straight-forward and results in the following slice:

```java
String freq = Repeat.getFreq(f);

if (!freq.equals(NDAYS) && !freq.equals(NWEEKS) && !freq.equals(NMONTHS) && !freq.equals(NYEARS))
  return (0);

int i2 = f.indexOf(',', freq.length() + 1);
if (i2 != -1)
  return (Integer.parseInt(f.substring(freq.length() + 1, i2)));

return (Integer.parseInt(f.substring(freq.length() + 1)));
```

Backward-slicing on the same assignment results in a much smaller slice:

```java
if (f == null)
  return 0;

String freq = Repeat.getFreq(f);
```

#### Dynamic Slicing
Dynamically slicing on `String freq`, we see three possibilities for the input. The input String `f` is `null`, or it is not. This second case can then be divided further; if the value of `f` is such that the result of the assignment is not one of `NDAYS, NWEEKS, NMONTHS` or `NYEARS` we see that the method will return `0`, otherwise, it will attempt to parse the string for an integer following this expression. See below for the respective Dynamic Slices:


Dynamic slicing on `f` equal to `null`

```java
return 0;
```

Dynamically slicing on `f` such that `Repeat.getFreq(f)` not equal to any of `NDAYS`, `NWEEKS`, `NMONTHS`, `NYEARS`

```java
String freq = Repeat.getFreq(f);

if (!freq.equals(NDAYS) && !freq.equals(NWEEKS) && !freq.equals(NMONTHS) && !freq.equals(NYEARS))
  return (0);
```

Dynamically slicing on `f` such that `Repeat.getFreq(f)` equal to one of `NDAYS`, `NWEEKS`, `NMONTHS` or `NYEARS`

```java
String freq = Repeat.getFreq(f);

int i2 = f.indexOf(',', freq.length() + 1);
if (i2 != -1)
  return (Integer.parseInt(f.substring(freq.length() + 1, i2)));

return (Integer.parseInt(f.substring(freq.length() + 1)));
```

### int i2

Moving now to `int i2`, we statically and dynamically slice the int as defined by assignment in the method as follows:

```java
int i2 = f.indexOf(',', freq.length() + 1);
```

#### Static Slicing

Forward slicing on `int i2` we get the following slice:

```java
int i2 = f.indexOf(',', freq.length() + 1);
if (i2 != -1)
  return (Integer.parseInt(f.substring(freq.length() + 1, i2)));

return (Integer.parseInt(f.substring(freq.length() + 1)));
```
#### Dynamic Slicing

## P-uses

### String f
#### Static Slicing
#### Dynamic Slicing

### int i2
#### Static Slicing
#### Dynamic Slicing
