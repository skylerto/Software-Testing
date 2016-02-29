## Bug Report #1

**Title**: Integer Overflow in `calculateRepeatNumber`.  
**Reported by**: Siraj Rauff.  
**Date Reported**: February 28, 2016.  
**Program name**: BORG Calendar.  
**Configuration**: OS X 10.11.3, Java version 1.8.0_71, Runtime build 1.8.0_71-b15.  
**Report type**: Bug.  
**Reproducibility**: Yes – consistently.  
**Priority**: Medium.  
**Problem Summary**:  
`calculateRepeatNumber` in `Repeat.java` does not account for integer overflow in its calculation.  
**Problem Description**:  
`calculateRepeatNumber` utilizes a for loop, keeping track of the number of occurrences of the repeating event using an integer variable. (`Repeat.java`, [446,0])

``` java
for (int i = 1;; i++) { ... }
```

This section of the method does not, however, check for integer overflow. This means that any event that occurs more than `Integer.MAX_VALUE` times, the method will simply wrap around to `Integer.MIN_VALUE` and continue counting. Depending on the implementation of infinitely repeating events, a event could quite reasonably have more than `Integer.MAX_VALUE` instances.

This can be reproduced with the following:
``` java
Calendar testCal = new GregorianCalendar(0,0,2,0,0);
Appointment testAppt = new Appointment();
testAppt.setFrequency("DAILY");
testAppt.setDate(testCal.getTime());

testCal.add(Calendar.DAY_OF_MONTH, Integer.MAX_VALUE);
assertEquals(greaterThan(Integer.MAX_VALUE), Repeat.calculateRepeatNumber(testCal, testAppt));
```
**New or old bug**: old.

## Bug Report #2

**Title**: Unchecked `NullPointerException` in `calculateRepeatNumber`.  
**Reported by**: Siraj Rauff.  
**Date Reported**: February 28, 2016.  
**Program name**: BORG Calendar.  
**Configuration**: OS X 10.11.3, Java version 1.8.0_71, Runtime build 1.8.0_71-b15.  
**Report type**: Bug.  
**Reproducibility**: Yes – consistently.  
**Priority**: Low.  
**Problem Summary**:  
`calculateRepeatNumber` in `Repeat.java` does not check if the `Appointment` contains a valid start date before using it.
**Problem Description**:  
`calculateRepeatNumber` utilizes `Appointment` parameter to get the start date and repeat frequency of an event. It does not, however, check if either of these exist. A `null` frequency is correctly handled in the creation of a repeating event, however a `null` start date will cause a `NullPointerException`.

This can be reproduced with the following:
``` java
Calendar testCal = new GregorianCalendar(0,0,2,0,0);
Appointment testAppt = new Appointment();

assertEquals(0, Repeat.calculateRepeatNumber(testCal, testAppt));
```
**New or old bug**: old.

## Bug Report #3

**Title**: Unchecked NumberFormatException.  
**Reported by**: Skyler Layne.  
**Date Reported**: February 28, 2016.  
**Program name**: BORG Calendar.  
**Configuration**: OS X 10.11.3, Java version 1.8.0_60, Runtime build 1.8.0_60-b27.  
**Report type**: Bug.  
**Reproducibility**: Yes – consistently.  
**Priority**: Low.  
**Problem Summary**:

Unit testing surfaced an uncaught error in String to Integer conversion.

When setting a repeat event, if the String code passed in takes space, or if it is not an integer, then an uncaught/unhandled error occurs `NumberFormatException` error occurs.

Example inputs:

``` java
Repeat.getNValue("nweeks, ");
Repeat.getNValue("nweeks, 14");
Repeat.getNValue("nweeks, FOO");
Repeat.getNValue("nweeks,FOO");
```

**New or old bug**: New.
