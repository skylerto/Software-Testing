# Testing Strategy

## Method Stub

The desired behaviour of this method is such that it takes in a string of the following form:

*constant + "," + a number*

E.g. "nyears,5", "ndays,20", etc.

hmm, this can be seen as a *code* comma seperated by a *value*


```java
/**
* Gets the "N" multiplier value from the encoded appointment string
* @param f - the encoded appointment string
* @return the "N" multiplier value
*/
static public int getNValue(String f) {}
```

Desired Input:

Desired output:

```java
 Repeat.getNValue("Some String");
```

For anything which is:

frew == one of the contants + "," + some number of reasonable repetitions.

```java
if (!freq.equals(NDAYS) && !freq.equals(NWEEKS)
    && !freq.equals(NMONTHS) && !freq.equals(NYEARS))
  return (0);
```
The number of occurs should be 0. Where the constants are defined as:
```java
public static final String NDAYS = "ndays";
public static final String NWEEKS = "nweeks";
public static final String NMONTHS = "nmonths";
public static final String NYEARS = "nyears";
```

**A parseInt is used without checking for a NumberFormatException !!**
-- make use of this. *:D*

## equivalence class testing


## boundary value testing


## decision table testing


## FULL METHOD

```java
  3     /**
  2      * Gets the "N" multiplier value from the encoded appointment string
  1      *
  0      * @param f
  1      *            the encoded appointment string
  2      *
  3      * @return the "N" multiplier value
  4      */
  5     static public int getNValue(String f) {
  6         if (f == null)
  7             return 0;
  8
  9         String freq = Repeat.getFreq(f);
 10
 11         if (!freq.equals(NDAYS) && !freq.equals(NWEEKS)
 12                 && !freq.equals(NMONTHS) && !freq.equals(NYEARS))
 13             return (0);
 14
 15         int i2 = f.indexOf(',', freq.length() + 1);
 16         if (i2 != -1)
 17             return (Integer.parseInt(f.substring(freq.length() + 1, i2)));
 18
 19         return (Integer.parseInt(f.substring(freq.length() + 1)));
 20
 21     }
 22
 ```
