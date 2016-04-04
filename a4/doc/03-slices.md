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


```java
```



```java
String freq = Repeat.getFreq(f);

if (!freq.equals(NDAYS) && !freq.equals(NWEEKS) && !freq.equals(NMONTHS) && !freq.equals(NYEARS))
  return (0);

int i2 = f.indexOf(',', freq.length() + 1);
if (i2 != -1)
  return (Integer.parseInt(f.substring(freq.length() + 1, i2)));

return (Integer.parseInt(f.substring(freq.length() + 1)));
```


```java

```







```java

String freq = Repeat.getFreq(f);
```






```java
if (f == null)
  return 0;

String freq = Repeat.getFreq(f);

if (!freq.equals(NDAYS) && !freq.equals(NWEEKS) && !freq.equals(NMONTHS) && !freq.equals(NYEARS))
  return (0);

int i2 = f.indexOf(',', freq.length() + 1);
```







```java
```


```java
String freq = Repeat.getFreq(f);

```


```java
```


```java
```


```java
String freq = Repeat.getFreq(f);

int i2 = f.indexOf(',', freq.length() + 1);

```



```java


```


