# Method Test #1:

The method under investigation, `calculateRepeatNumber` exists within `Repeat.java` in the `net.sf.borg.model` package, and has the following signature including java doc:

``` java
/**
 * Calculate the number of a repeat given the date and the appointment
 *
 * @param current
 *            the date
 * @param appt
 *            the appointment
 *
 * @return the number of the repeat (starting with 1)
 */
final static public int calculateRepeatNumber(Calendar current, Appointment appt)
```

The method calculates the instance number of the specified repeating event on a particular date. This means that a repeating event will have its instances numerated, and the instance number on the given date will be rturned.

- The first parameter **current**, of type `Calendar`, contains the date for which the instance number is to be determined.
- The second parameter **appt**, of type `Appointment`, contains the event specifics including the start date and repeat frequency.

If a date is given such that it does not exist in the repetitions (_i.e._ a weekend for a event repeating on weekdays, or a date before the start date), the value `0` is returned.

## Boundary Value Testing

Having observed the behavior and documentation of the function, it was determined that the distances between the start date of the `Appointment` and the `Calendar` date should be pushed to extreme Integer values. This was chosen after inspection of the code revealed that the repetition was calculated using a loop tracking an integer value, without proper checking for overflow.

Note that the `Appointment` parameter was not Boundary tested as its use in this function was limited to its start date and repeating frequency. The lowest frequency value in BORGCalendar was `DAILY`, therefore we could not test for multiple instances for a given date, and testing for different values would fall within the scope of testing `Calendar`. Testing for extreme values of a start date would not prove to be useful as we would be testing Java's `DATE` class, and not the method at hand.

Given a specific `Appointment`, the following cases were then chosen for the `Calendar`:
- The same day
- The next day
- The previous day
- Integer.MAX_VALUE - 1 days in the future
- Integer.MAX_VALUE days in the future
- Integer.MAX_VALUE + 1 days in the future
- Integer.MIN_VALUE + 1 days in the past
- Integer.MIN_VALUE days in the past
- Integer.MIN_VALUE - 1 days in the past

## Special Value Testing

Due to the nature of the `Appointment` parameter, in particular its use for start date and repeating frequency, two special cases were considered:
- Appointment without a start date
- Appointment with no frequency

These were tested to ensure the method was robust, as this custom class does not ensure that these two fields are set upon creation or modificaiton.
