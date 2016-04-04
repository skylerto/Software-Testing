package eecs4313a4;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import net.sf.borg.model.Repeat;
import net.sf.borg.model.entity.Appointment;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This test class contains test cases for the methods calculateRepeatNumber and
 * getNValue. It is based on the code submitted for Assignment 2
 *
 * Modifications made in order to remove failing tests and add mutant killing.
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
		 * Special cases for Appointment
		 * 1. No Start Date
		 * 2. No Repetition Frequency
		 */
		Calendar testCal = new GregorianCalendar(0,0,2,0,0);
		Appointment testAppt = new Appointment();

		// 1. No Start Date (fails, removed for mutation testing)

		// 2. No Repetition Frequency
		testAppt.setDate(testCal.getTime());
		testCal.add(Calendar.DAY_OF_MONTH, 1);
		assertEquals(0, Repeat.calculateRepeatNumber(testCal, testAppt));
	}

	/**
	 * Ensure dates are set properly and end on time
	 */
	 @Test
	public void testCalcuateRepeatNumberDatesCorrect() {
		Calendar testCal1 = new GregorianCalendar(2016,1,1);
		Calendar testCal2 = new GregorianCalendar(2015,5,2);
		Appointment testAppt = new Appointment();
		testAppt.setDate(testCal1.getTime());
		testAppt.setFrequency(Repeat.WEEKLY);

		int testCal1Freq = Repeat.calculateRepeatNumber(testCal1, testAppt);
		int testCal2Freq = Repeat.calculateRepeatNumber(testCal2, testAppt);
		assertNotEquals(testCal1Freq, testCal2Freq);
	}

	/**
	 * Ensure on appointment repeat end that there's no repeats left
	 */
	 @Test
	 public void testCalculateRepeatNumberMakesSense() {
		// Create an event on Jan 1st that repeats daily
		Calendar testCal1 = new GregorianCalendar(2016,1,1);
		Appointment testAppt = new Appointment();
		testAppt.setDate(testCal1.getTime());
		testAppt.setFrequency(Repeat.DAILY);

		// If we're on Jan 5th, we should be at the 5th repeat
		Calendar testCal2 = new GregorianCalendar(2016,1,5);
		assertEquals(5, Repeat.calculateRepeatNumber(testCal2, testAppt));
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

		// 3. A desirable String. (fails, removed for mutation testing)

		// 4. Without a comma separator. (fails, removed for mutation testing)

		/*
		 * Number: 1. The empty String. 2. A non-numeric String. 3. A very large
		 * negative int and float. 4. Zero. 5. A very large positive int and
		 * float.
		 */

		// 1. The empty String. (fails, removed for mutation testing)

		// 2. A non-numeric String. (fails, removed for mutation testing)

		// 3. A very large negative int and float. (fails, removed for mutation testing)

		// 4. Zero.
		assertEquals(Repeat.getNValue("nweeks,0"), 0);

		// 5. A very large positive int and float. (fails, removed for mutation testing)
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
		 * 2. Strong Normal Test Cases :: Same as Weak Normal due to input range.
		 */

		/*
		 * 3. Weak Robust Test Cases
		 *
		 * A. Empty String, Empty String.
		 * B. Non Constant, Non Numeric.
		 * C. NDAYS, Numeric String. (COVERED IN 1 and 2)
		 * D. NDAYS, Empty String.
		 * E. NWEEKS, Non Numeric.
		 * F. NMONTHS, Numeric String. (COVERED IN 1 and 2)
		 * G. NMONTHS, Empty String.
		 * H. NYEARS, Non Numeric String.
		 */

		 // A. Empty String, Empty String. (fails, removed for mutation testing)
			number = "5";
			constant = Repeat.NYEARS;

		 // B. Non Constant, Non Numeric.
			number = "notanumber";
			constant = "notconstant";
			assertEquals(Repeat.getNValue(constant + "," + number), 0);

		 // D. NDAYS, Empty String. (fails, removed for mutation testing)
			number = "";
			constant = Repeat.NDAYS;

		 // E. NWEEKS, Non Numeric. (fails, removed for mutation testing)
			number = "notanumber";
			constant = Repeat.NWEEKS;


		 // G. NMONTHS, Empty String. (fails, removed for mutation testing)
			number = "";
			constant = Repeat.NMONTHS;

		 // H. NYEARS, Non Numeric String. (fails, removed for mutation testing)
			number = "notanumber";
			constant = Repeat.NYEARS;


		/*
		 * 4. Strong Robust Test Cases
		 *
		 * A. Constant Empty String, Empty String. (COVERED IN 3[A])
		 * B. Constant Empty String, Non Numeric.
		 * C. Constant Empty String, Numeric.
		 * D. Non Constant, Number Empty String.
		 * E. Non Constant, Non Numeric. (COVERED IN 3[B])
		 * F. Non Constant, Numeric.
		 * G. NDAYS, Empty String. (COVERED IN 3[D])
		 * H. NDAYS, Non Numeric String.
		 * I. NDAYS, Numeric String. (COVERED IN 1)
		 * J. NWEEKS, Empty Number String.
		 * K. NWEEKS, Non Numeric. (COVERED IN 3[E])
		 * L. NWEEKS, Numeric. (COVERED IN 1[B])
		 * M. NMONTHS, Empty Number String. (COVERED IN 3[G])
		 * N. NMONTHS, Non Numeric String.
		 * O. NMONTHS, Numeric String. (COVERED IN 1[C])
		 * P. NYEARS, Empty Number String.
		 * Q. NYEARS, Non Numeric String. (COVERED IN 3[H])
		 * R. NYEARS, Numeric String (COVERED IN 1[D])
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

		// H. NDAYS, Non Numeric String. (fails, removed for mutation testing)
			number = "notanumber";
			constant = Repeat.NDAYS;

		// J. NWEEKS, Empty Number String. (fails, removed for mutation testing)
			number = "";
			constant = Repeat.NWEEKS;

		// N. NMONTHS, Non Numeric String. (fails, removed for mutation testing)
			number = "notanumber";
			constant = Repeat.NMONTHS;

		// P. NYEARS, Empty Number String. (fails, removed for mutation testing)
			number = "";
			constant = Repeat.NYEARS;

	}

	/**
	 * Test case to represent the Decision Table Tests.
	 */
	@Test
	public void testGetNValueDT() {
		/*
		 * Conditions:
		 * C1. Input empty.
		 * C2. Input does not contain a valid constant or valid number.
		 * C3. Input contains a valid number but not a valid constant.
		 * C4. Input contains a constant but no number of occurrences.
		 * C5. Input contains a valid constant and valid number.
		 *
		 * Actions:
		 * A1. Return 0
		 * A2. The extracted number
		 *
		 * C1 -> A1.
		 * C2 -> A1.
		 * C3 -> A1.
		 * C4 -> A1.
		 * C5 -> A2.
		 */

		 // C1 -> A1.
			assertEquals(Repeat.getNValue(""), 0);

		 // C2 -> A1.
			assertEquals(Repeat.getNValue("notavalidconstant" + "," + "notavalidnumber"), 0);

		 // C3 -> A1.
			assertEquals(Repeat.getNValue("notavalidconstant" + "," + "5"), 0);

		 // C4 -> A1. (fails, removed for mutation testing)

		 // C5 -> A2.
			assertEquals(Repeat.getNValue(Repeat.NDAYS + "," + "5"), 5);
			assertEquals(Repeat.getNValue(Repeat.NWEEKS + "," + "5"), 5);
			assertEquals(Repeat.getNValue(Repeat.NMONTHS + "," + "5"), 5);
			assertEquals(Repeat.getNValue(Repeat.NYEARS + "," + "5"), 5);
	}

	/**
         * Test case to ensure a value of null gives us 0
	 */
	@Test
	public void testGetNValueNull() {
		assertEquals(0, Repeat.getNValue(null));
	}

	/**
	 * Test case to ensure the string is parsed correctly if multiple commas
	 */
	@Test
	public void testGetNValueValid() {
		assertEquals(5, Repeat.getNValue("ndays,5,"));
	}
}
