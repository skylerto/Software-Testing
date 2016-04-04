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

For this particular investigation we will statically and dynamically slice all A-defs and P-uses. **NOTE:** For the duration of this section we will omit code comments and the method's declaration and outermost brackets for brevity's sake.

## A-defs

A-defs, or assignment definitions are variable definitions created through assignments (e.g. `variable = value`). Within the `getNValue` method we find two assignment definitions, one for `String freq`, and one for `int i2`.

**NOTE:** For each of the following dynamic slices as well as forward slices, we slice to the end of the method. For backwards-slices we slice from the assignment to the beginning of the method.

### String Freq

We will begin by statically and dynamically slicing `String Freq`, which is defined by assignment in the method as follows:

```java
String freq = Repeat.getFreq(f);
```

#### Static Slicing

Forward slicing on the assignment to the end of the method is straight forward and results in the following slice:

```java
String freq = Repeat.getFreq(f);

if (!freq.equals(NDAYS) && !freq.equals(NWEEKS) && !freq.equals(NMONTHS) && !freq.equals(NYEARS))
  return (0);

int i2 = f.indexOf(',', freq.length() + 1);
if (i2 != -1)
  return (Integer.parseInt(f.substring(freq.length() + 1, i2)));

return (Integer.parseInt(f.substring(freq.length() + 1)));
```

This displays the simplicity of the method; every line following the A-def for `freq` makes use of `freq`, or is dependent on something that does make use of `freq`.

Backward-slicing on the same assignment to the beginning of the method results in the following slice:

```java
if (f == null)
  return 0;

String freq = Repeat.getFreq(f);
```

Notice that this is the case as if `f` is null, the method returns and `freq` (nor any other variable) is ever assigned.

#### Dynamic Slicing
Dynamically slicing on the assignment of `String freq`, we see three possibilities for the input.

* `f` is null
* `f` is not null AND is such that `freq` becomes one of `NDAYS`, `NWEEKS`, `NMONTHS` or `NYEARS`
* `f` is not null AND is such that `freq` does not become one of `NDAYS`, `NWEEKS`, `NMONTHS` or `NYEARS`

If `f` is null or it is such that `freq` is not one of `NDAYS, NWEEKS, NMONTHS` or `NYEARS` we see that the method will return `0`, otherwise, it will attempt to parse the string for an integer following this expression. See below for the respective Dynamic Slices:

Dynamic slicing on `f` equal to `null` results in an empty slice as `freq` is never assigned and the method returns.

Dynamically slicing on `f` such that `Repeat.getFreq(f)` not equal to any of `NDAYS`, `NWEEKS`, `NMONTHS`, `NYEARS` results in the following slice:

```java
String freq = Repeat.getFreq(f);
```

In this case, the method collapses to the return statement and `freq` is assigned, used once

Dynamically slicing on `f` such that `Repeat.getFreq(f)` equal to one of `NDAYS`, `NWEEKS`, `NMONTHS` or `NYEARS`:

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

Notice here that the last return statement is included as it acts as the `else` statement to `if (i2 != -1)` due to the return statement in the `if` statement's scope.

Backward slicing on the same variable results in the following slice:

```java
if (f == null)
  return 0;

String freq = Repeat.getFreq(f);

if (!freq.equals(NDAYS) && !freq.equals(NWEEKS) && !freq.equals(NMONTHS) && !freq.equals(NYEARS))
  return (0);

int i2 = f.indexOf(',', freq.length() + 1);
```

If either of the `if` statements prior to the A-def result in `true`, then the method returns and `i2` is never assigned.

#### Dynamic Slicing

Dynamically slicing on the same variable, we see that the first few cases we consider are equivalent to dynamically slicing `String freq`. We do, however further divide the case where `freq` is not one of `NDAYS`, `NWEEKS`, `NMONTHS` or `NYEARS` to if `f` contains a comma or it does not. This is because `i2` directly affects the parsing in either of the last two return statements, therefore separating the cases makes sense here as opposed to with `freq` as it could help in locating possible bugs. We divide it such that the cases are:

* `f` is null
* `f` is not null AND `freq` is one of `NDAYS`, `NWEEKS`, `NMONTHS` or `NYEARS`
* `f` is not null AND `freq` is not one of `NDAYS`, `NWEEKS`, `NMONTHS` or `NYEARS` and `f` contains a comma
* `f` is not null AND `freq` is not one of `NDAYS`, `NWEEKS`, `NMONTHS` or `NYEARS` and `f` does not contain a comma

We omit the first two cases, as the slices are the same as in the `String freq` dynamic slicing section above.

Dynamically slicing on `f` such that `Repeat.getFreq(f)` equal to one of `NDAYS`, `NWEEKS`, `NMONTHS` or `NYEARS`, and `f` contains a comma:

```java
String freq = Repeat.getFreq(f);

int i2 = f.indexOf(',', freq.length() + 1);
return (Integer.parseInt(f.substring(freq.length() + 1, i2)));
```

Dynamically slicing on `f` such that `Repeat.getFreq(f)` equal to one of `NDAYS`, `NWEEKS`, `NMONTHS` or `NYEARS`, and `f` does not contain a comma:

```java
String freq = Repeat.getFreq(f);

return (Integer.parseInt(f.substring(freq.length() + 1)));
```

## P-uses

P-uses, or decision predicate uses, are uses of a variable as part of a predicate, e.g. determining the control flow of a method. Within the `getNValue` method we find three P-uses, one for `String f`, one for `String freq`, and one for `int i2`.

**NOTE:** As with the A-defs section, for each of the following dynamic slices as well as forward slices, we slice to the end of the method. For backwards-slices we slice from the assignment to the beginning of the method.

### String f

We will begin by statically and dynamically slicing `String f`, which is contains one P-use in the method:

```java
if (f == null)
```
#### Static Slicing

We do not backwards slice this line as it is the very first line in the method. Forward-slicing produces the entirety of the method which we leave out here for simplicity. This is due to the fact that the entire method consists of functions to determine the contents of `f`.

#### Dynamic Slicing

Dynamically slicing the very same variable, we find two possible cases to slice on:

* `f` is `null`
* `f` is not `null`

Slicing on `f` is `null` produces the following:

```java
return 0;
```

While slicing on `f` not being `null` produces:

```java
String freq = Repeat.getFreq(f);

if (!freq.equals(NDAYS) && !freq.equals(NWEEKS) && !freq.equals(NMONTHS) && !freq.equals(NYEARS))
  return (0);

int i2 = f.indexOf(',', freq.length() + 1);
if (i2 != -1)
  return (Integer.parseInt(f.substring(freq.length() + 1, i2)));

return (Integer.parseInt(f.substring(freq.length() + 1)));
```

### String freq

We now turn to the P-use of `freq`, which also occurs once:

```java
if (!freq.equals(NDAYS) && !freq.equals(NWEEKS) && !freq.equals(NMONTHS) && !freq.equals(NYEARS))
```

#### Static Slicing

Forward slicing this P-use we get:

```java

```

Backwards-slicing this variable results in the following slice:

```java
if (f == null)
  return 0;

String freq = Repeat.getFreq(f);
```

#### Dynamic Slicing

### int i2
#### Static Slicing
#### Dynamic Slicing
