package eecs4313a3;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

import net.sf.borg.model.Repeat;
import net.sf.borg.model.entity.Appointment;

/**
 * This test class contains test cases for the methods calculateRepeatNumber and
 * getNValue.
 * 
 * Modifications needed to cover tests.
 * 
 * @author Skyler Layne & Siraj Rauff
 *
 */
public class RepeatTest {

	/*
	 * Special Value cases of Calculate Repeat Number
	 */
	@Test
	public void testCalculateRepeatNumberSpecialValue() {

		/*
		 * Special cases for Appointment 1. No Start Date 2. No Repetition
		 * Frequency
		 */
		Calendar testCal = new GregorianCalendar(0, 0, 2, 0, 0);
		Appointment testAppt = new Appointment();

		// 1. No Start Date
		try {
			assertEquals(0, Repeat.calculateRepeatNumber(testCal, testAppt));
		} catch (Exception e) {
			// TODO Auto-generated catch block

		}

		// 2. No Repetition Frequency
		testAppt.setDate(testCal.getTime());
		testCal.add(Calendar.DAY_OF_MONTH, 1);
		assertEquals(0, Repeat.calculateRepeatNumber(testCal, testAppt));
	}

	/**
	 * Test Case to represent the Boundary Value Tests.
	 */
	@Test
	public void testGetNValueBVT() {

		/*
		 * Constant: 1. The empty String. 2. A very long String. 3. A desirable
		 * String. 4. Without a comma separator.
		 */

		// 1. The empty String.
		assertEquals(Repeat.getNValue(""), 0);

		// 2. A very long String.
		assertEquals(Repeat.getNValue("THISORANGEJUICETASTESLIKECOFFEE"), 0);

		// 3. A desirable String.
		try {
			assertEquals(Repeat.getNValue("nweeks"), 0);
		} catch (Exception e) {
			// TODO Auto-generated catch block

		}

		// 4. Without a comma separator.
		assertEquals(Repeat.getNValue("nweeks 5"), 5);

		/*
		 * Number: 1. The empty String. 2. A non-numeric String. 3. A very large
		 * negative int and float. 4. Zero. 5. A very large positive int and
		 * float.
		 */

		// 1. The empty String.
		assertEquals(Repeat.getNValue("nweeks, "), 0);

		// 2. A non-numeric String.
		assertEquals(Repeat.getNValue("nweeks,SEVEN"), 0);

		// 3. A very large negative int and float.
		assertEquals(Repeat.getNValue("nweeks," + Integer.MIN_VALUE), 0);
		assertEquals(Repeat.getNValue("nweeks," + Float.MIN_VALUE), 0);

		// 4. Zero.
		assertEquals(Repeat.getNValue("nweeks,0"), 0);

		// 5. A very large positive int and float.
		assertEquals(Repeat.getNValue("nweeks," + Integer.MAX_VALUE), 0);
		assertEquals(Repeat.getNValue("nweeks," + Float.MAX_VALUE), 0);
	}

	/**
	 * Test case to represent the Equivalence Class Tests.
	 */
	@Test
	public void testGetNValueECT() {
		/*
		 * 1. Weak Normal Test Cases. 2. Strong Normal Test Cases. 3. Weak
		 * Robust Test Cases. 4. Strong Robust Test Cases.
		 */

		String number = "";
		String constant = "";

		/*
		 * Weak Normal Test Cases A. NDAYS, Numeric String B. NWEEKS, Numeric
		 * String C. NMONTHS, Numeric String D. NYEARS, Numeric String
		 */
		// A. NDAYS, Numeric String
		number = "5";
		constant = Repeat.NDAYS;
		assertEquals(Repeat.getNValue(constant + "," + number), 5);

		// B. NWEEKS, Numeric String
		number = "5";
		constant = Repeat.NWEEKS;
		assertEquals(Repeat.getNValue(constant + "," + number), 5);

		// C. NMONTHS, Numeric String
		number = "5";
		constant = Repeat.NMONTHS;
		assertEquals(Repeat.getNValue(constant + "," + number), 5);

		// D. NYEARS, Numeric String
		number = "5";
		constant = Repeat.NYEARS;
		assertEquals(Repeat.getNValue(constant + "," + number), 5);

		/*
		 * 2. Strong Normal Test Cases :: Same as Weak Normal due to input
		 * range.
		 */

		/*
		 * 3. Weak Robust Test Cases
		 *
		 * A. Empty String, Empty String. B. Non Constant, Non Numeric. C.
		 * NDAYS, Numeric String. (COVERED IN 1 and 2) D. NDAYS, Empty String.
		 * E. NWEEKS, Non Numeric. F. NMONTHS, Numeric String. (COVERED IN 1 and
		 * 2) G. NMONTHS, Empty String. H. NYEARS, Non Numeric String.
		 */

		// A. Empty String, Empty String.
		number = "5";
		constant = Repeat.NYEARS;

		assertEquals(Repeat.getNValue(constant + "," + number), 0);

		// B. Non Constant, Non Numeric.
		number = "notanumber";
		constant = "notconstant";
		assertEquals(Repeat.getNValue(constant + "," + number), 0);

		// D. NDAYS, Empty String.
		number = "";
		constant = Repeat.NDAYS;
		assertEquals(Repeat.getNValue(constant + "," + number), 0);

		// E. NWEEKS, Non Numeric.
		number = "notanumber";
		constant = Repeat.NWEEKS;
		assertEquals(Repeat.getNValue(constant + "," + number), 0);

		// G. NMONTHS, Empty String.
		number = "";
		constant = Repeat.NMONTHS;
		assertEquals(Repeat.getNValue(constant + "," + number), 0);

		// H. NYEARS, Non Numeric String.
		number = "notanumber";
		constant = Repeat.NYEARS;
		assertEquals(Repeat.getNValue(constant + "," + number), 0);

		/*
		 * 4. Strong Robust Test Cases
		 *
		 * A. Constant Empty String, Empty String. (COVERED IN 3[A]) B. Constant
		 * Empty String, Non Numeric. C. Constant Empty String, Numeric. D. Non
		 * Constant, Number Empty String. E. Non Constant, Non Numeric. (COVERED
		 * IN 3[B]) F. Non Constant, Numeric. G. NDAYS, Empty String. (COVERED
		 * IN 3[D]) H. NDAYS, Non Numeric String. I. NDAYS, Numeric String.
		 * (COVERED IN 1) J. NWEEKS, Empty Number String. K. NWEEKS, Non
		 * Numeric. (COVERED IN 3[E]) L. NWEEKS, Numeric. (COVERED IN 1[B]) M.
		 * NMONTHS, Empty Number String. (COVERED IN 3[G]) N. NMONTHS, Non
		 * Numeric String. O. NMONTHS, Numeric String. (COVERED IN 1[C]) P.
		 * NYEARS, Empty Number String. Q. NYEARS, Non Numeric String. (COVERED
		 * IN 3[H]) R. NYEARS, Numeric String (COVERED IN 1[D])
		 */

		// B. Constant Empty String, Non Numeric.
		number = "notanumber";
		constant = "";
		assertEquals(Repeat.getNValue(constant + "," + number), 0);

		// C. Constant Empty String, Numeric.
		number = "5";
		constant = "";
		assertEquals(Repeat.getNValue(constant + "," + number), 0);

		// D. Non Constant, Number Empty String.
		number = "";
		constant = "notaconstant";
		assertEquals(Repeat.getNValue(constant + "," + number), 0);

		// F. Non Constant, Numeric.
		number = "5";
		constant = "notaconstant";
		assertEquals(Repeat.getNValue(constant + "," + number), 0);

		// H. NDAYS, Non Numeric String.
		number = "notanumber";
		constant = Repeat.NDAYS;
		assertEquals(Repeat.getNValue(constant + "," + number), 0);

		// J. NWEEKS, Empty Number String.
		number = "";
		constant = Repeat.NWEEKS;
		assertEquals(Repeat.getNValue(constant + "," + number), 0);

		// N. NMONTHS, Non Numeric String.
		number = "notanumber";
		constant = Repeat.NMONTHS;
		assertEquals(Repeat.getNValue(constant + "," + number), 0);

		// P. NYEARS, Empty Number String.
		number = "";
		constant = Repeat.NYEARS;
		assertEquals(Repeat.getNValue(constant + "," + number), 0);

	}

	/**
	 * Test case to represent the Decision Table Tests.
	 */
	@Test
	public void testGetNValueDT() {
		/*
		 * Conditions: C1. Input empty. C2. Input does not contain a valid
		 * constant or valid number. C3. Input contains a valid number but not a
		 * valid constant. C4. Input contains a constant but no number of
		 * occurrences. C5. Input contains a valid constant and valid number.
		 *
		 * Actions: A1. Return 0 A2. The extracted number
		 *
		 * C1 -> A1. C2 -> A1. C3 -> A1. C4 -> A1. C5 -> A2.
		 */

		// C1 -> A1.
		assertEquals(Repeat.getNValue(""), 0);

		// C2 -> A1.
		try {
			assertEquals(Repeat.getNValue("notavalidconstant" + "," + "notavalidnumber"), 0);
		} catch (Exception e) {
			// TODO Auto-generated catch block

		}

		// C3 -> A1.
		assertEquals(Repeat.getNValue("notavalidconstant" + "," + "5"), 0);

		// C4 -> A1.
		try {
			assertEquals(Repeat.getNValue(Repeat.NDAYS + "," + "notavalidnumber"), 0);
		} catch (Exception e) {
			// TODO Auto-generated catch block

		}
		assertEquals(Repeat.getNValue(Repeat.NDAYS + "," + "-1"), 0);
		assertEquals(Repeat.getNValue(Repeat.NDAYS + "," + "2.5"), 0);
		assertEquals(Repeat.getNValue(Repeat.NDAYS + "," + "0"), 0);

		// C5 -> A2.
		assertEquals(Repeat.getNValue(Repeat.NDAYS + "," + "5"), 5);
		assertEquals(Repeat.getNValue(Repeat.NWEEKS + "," + "5"), 5);
		assertEquals(Repeat.getNValue(Repeat.NMONTHS + "," + "5"), 5);
		assertEquals(Repeat.getNValue(Repeat.NYEARS + "," + "5"), 5);

		/**/
	}

	@Test
	public void testGetNValueNull() {
		String repeat = null;
		assertEquals(Repeat.getNValue(repeat), 0);
	}

	@Test
	public void testGetNValueMultiple() {
		String f = Repeat.NDAYS + ",1," + Repeat.NDAYS;
		assertEquals(Repeat.getNValue(f), 1);
	}

	/*
	 * Testing Boundry Values of Calculate Repeat Number
	 */
	@Test
	public void testCalculateRepeatNumberBVT() {

		/*
		 * Boundary Values for Calendar: 1. The same day 2. The next day 3. The
		 * previous day 4. Integer.MAX_VALUE - 1 days in the future 5.
		 * Integer.MAX_VALUE days in the future 6. Integer.MAX_VALUE + 1 days in
		 * the future 7. Integer.MIN_VALUE + 1 days in the past 8.
		 * Integer.MIN_VALUE days in the past 9. Integer.MIN_VALUE - 1 days in
		 * the past
		 */

		Calendar prevDay = new GregorianCalendar(0, 0, 1, 0, 0);
		Calendar sameDay = new GregorianCalendar(0, 0, 2, 0, 0);
		Calendar nextDay = new GregorianCalendar(0, 0, 3, 0, 0);
		Appointment sampleAppt = new Appointment();
		sampleAppt.setFrequency("DAILY");

		// 1. The same day
		sampleAppt.setDate(sameDay.getTime());
		assertEquals(1, Repeat.calculateRepeatNumber(sameDay, sampleAppt));

		// 2. The next day
		assertEquals(2, Repeat.calculateRepeatNumber(nextDay, sampleAppt));

		// 3. The previous day
		assertEquals(0, Repeat.calculateRepeatNumber(prevDay, sampleAppt));

		// 4. Integer.MAX_VALUE - 1 days in the future
		Calendar overMaxDays = sameDay;

		/*
		 * LOOP FOREVER ? overMaxDays.add(Calendar.DAY_OF_MONTH,
		 * Integer.MAX_VALUE - 1); assertEquals(Integer.MAX_VALUE,
		 * Repeat.calculateRepeatNumber(overMaxDays, sampleAppt));
		 */

		// 5. Integer.MAX_VALUE days in the future
		overMaxDays.add(Calendar.DAY_OF_MONTH, 1);
		assertEquals(greaterThan(Integer.MAX_VALUE), Repeat.calculateRepeatNumber(overMaxDays, sampleAppt));

		// 6. Integer.MAX_VALUE days in the future
		overMaxDays.add(Calendar.DAY_OF_MONTH, 1);
		assertEquals(greaterThan(Integer.MAX_VALUE), Repeat.calculateRepeatNumber(overMaxDays, sampleAppt));

		// 7. Integer.MIN_VALUE + 1 days in the past
		Calendar underMaxDays = sameDay;
		underMaxDays.add(Calendar.DAY_OF_MONTH, Integer.MIN_VALUE + 1);
		assertEquals(0, Repeat.calculateRepeatNumber(underMaxDays, sampleAppt));

		// 8. Integer.MIN_VALUE days in the past
		underMaxDays.add(Calendar.DAY_OF_MONTH, -1);
		assertEquals(0, Repeat.calculateRepeatNumber(underMaxDays, sampleAppt));

		// 9. Integer.MIN_VALUE - 1 days in the past
		underMaxDays.add(Calendar.DAY_OF_MONTH, -1);
		assertEquals(0, Repeat.calculateRepeatNumber(underMaxDays, sampleAppt));

	}

}
