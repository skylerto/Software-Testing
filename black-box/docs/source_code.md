# Appendix B

## Method #1

### Boundary Value Tests

``` java
/*
 * Testing Boundry Values of Calculate Repeat Number
 */
@Test
public void testCalculateRepeatNumberBVT() {

  /*
   * Boundary Values for Calendar:
   * 1. The same day
   * 2. The next day
   * 3. The previous day
   * 4. Integer.MAX_VALUE - 1 days in the future
   * 5. Integer.MAX_VALUE days in the future
   * 6. Integer.MAX_VALUE + 1 days in the future
   * 7. Integer.MIN_VALUE + 1 days in the past
   * 8. Integer.MIN_VALUE days in the past
   * 9. Integer.MIN_VALUE - 1 days in the past
   */

  Calendar prevDay = new GregorianCalendar(0,0,1,0,0);
  Calendar sameDay = new GregorianCalendar(0,0,2,0,0);
  Calendar nextDay = new GregorianCalendar(0,0,3,0,0);
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
  overMaxDays.add(Calendar.DAY_OF_MONTH, Integer.MAX_VALUE - 1);
  assertEquals(Integer.MAX_VALUE, Repeat.calculateRepeatNumber(overMaxDays, sampleAppt));

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
```

### Special Value Tests

``` java
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

  // 1. No Start Date
  assertEquals(0, Repeat.calculateRepeatNumber(testCal, testAppt));

  // 2. No Repetition Frequency
  testAppt.setDate(testCal.getTime());
  testCal.add(Calendar.DAY_OF_MONTH, 1);
  assertEquals(0, Repeat.calculateRepeatNumber(testCal, testAppt));
}
```
## Method #2
### Boundary Value Tests
```java
/**
 * Setup class to populate the store with a key
 */
@BeforeClass
public static void setup() throws Exception {
  // Create a new store
  EncryptionHelper.createStore(EncryptionHelperTest.STORE_NAME,
      EncryptionHelperTest.STORE_PASS);

  // Add a key, manually
  EncryptionHelper.importKey(EncryptionHelperTest.STORE_NAME, EncryptionHelperTest.KEY,
      EncryptionHelperTest.KEYNAME, EncryptionHelperTest.STORE_PASS);

  // Set up the encryption helper
  InvalidKeystoreWorstCaseBVTest.eh = new EncryptionHelper(EncryptionHelperTest.STORE_NAME,
      EncryptionHelperTest.STORE_PASS);

}

/**
 * Method which generates a long string for use in tests
 * @param  len Maximum length of the string
 * @return     String of length `len`
 */
public static String generateLongString(long len) {
  final StringBuilder sb = new StringBuilder();

  for (long i = 0; i < len; i++) {
    sb.append("A");
  }

  return sb.toString();
}

/**
 * Test an invalid key against an empty string
 */
@Test(expected = InvalidKeyException.class)
public void testInvalidEmptyString() throws Exception {
  // Test smallest possible value, and ensure the encrypted form is
  // different than the plain form
  final String enc = InvalidKeystoreWorstCaseBVTest.eh.encrypt(this.EMPTY_STRING, "Test");
  Assert.assertNotEquals(enc, this.EMPTY_STRING);
  Assert.assertEquals(this.EMPTY_STRING,
      InvalidKeystoreWorstCaseBVTest.eh.decrypt(enc, EncryptionHelperTest.KEYNAME));
}

/**
 * Test an invalid key against a single-char string
 */
@Test(expected = InvalidKeyException.class)
public void testValidSingleChar() throws Exception {
  // Test a single character encryption
  final String enc = InvalidKeystoreWorstCaseBVTest.eh.encrypt(this.ALMOST_EMPTY_STRING, "Test");
  Assert.assertNotEquals(enc, this.ALMOST_EMPTY_STRING);
  Assert.assertEquals(this.ALMOST_EMPTY_STRING,
      InvalidKeystoreWorstCaseBVTest.eh.decrypt(enc, EncryptionHelperTest.KEYNAME));
}

/**
 * Test an invalid key against a typical string
 */
@Test(expected = InvalidKeyException.class)
public void testValidNominal() throws Exception {
  // Test medium length encryption
  final String enc = InvalidKeystoreWorstCaseBVTest.eh.encrypt(this.NOMINAL_STRING, "Test");
  Assert.assertNotEquals(enc, this.NOMINAL_STRING);
  Assert.assertEquals(this.NOMINAL_STRING,
      InvalidKeystoreWorstCaseBVTest.eh.decrypt(enc, EncryptionHelperTest.KEYNAME));
}

/**
 * Test an invalid key against a very large string
 */
@Test(expected = InvalidKeyException.class)
public void testValidLarge() throws Exception {
  // Test large length encryption
  final String longString = InvalidKeystoreWorstCaseBVTest.generateLongString(4096);
  final String enc = InvalidKeystoreWorstCaseBVTest.eh.encrypt(longString, "Test");
  Assert.assertNotEquals(longString, enc);
  Assert.assertEquals(longString,
      InvalidKeystoreWorstCaseBVTest.eh.decrypt(enc, EncryptionHelperTest.KEYNAME));
}

/**
 * Test a invalid key using a binary string
 */
@Test(expected = InvalidKeyException.class)
public void testValidRawBytes() throws Exception {
  // Test some bytes that are less than 0x80 (String overflows there)
  final byte[] inp = { (byte) 0x00, (byte) 0x0c, (byte) 0x75, (byte) 0x00, (byte) 0x13 };
  final String s = new String(inp);
  final String enc = ValidKeystoreWorstCaseBVTest.eh.encrypt(s, "Test");
  final String dec = ValidKeystoreWorstCaseBVTest.eh.decrypt(enc, EncryptionHelperTest.KEYNAME);
  final byte[] out = dec.getBytes();

  boolean bytesMatch = true;
  for (int i = 0; i < inp.length; i++) {
    bytesMatch = bytesMatch & (inp[i] == out[i]);
  }

  Assert.assertTrue(bytesMatch);

}
/**
 * Test a valid key using an empty string
 */
@Test
public void testValidEmptyString() throws Exception {
  // Test smallest possible value, and ensure the encrypted
  // form is different than the plain form
  final String enc = ValidKeystoreWorstCaseBVTest.eh.encrypt(this.EMPTY_STRING,
      EncryptionHelperTest.KEYNAME);
  Assert.assertNotEquals(enc, this.EMPTY_STRING);
  Assert.assertEquals(this.EMPTY_STRING,
      ValidKeystoreWorstCaseBVTest.eh.decrypt(enc, EncryptionHelperTest.KEYNAME));
}

/**
 * Test a valid key using a very small string
 */
@Test
public void testValidSingleChar() throws Exception {
  // Test a single character encryption
  final String enc = ValidKeystoreWorstCaseBVTest.eh.encrypt(this.ALMOST_EMPTY_STRING,
      EncryptionHelperTest.KEYNAME);
  Assert.assertNotEquals(enc, this.ALMOST_EMPTY_STRING);
  Assert.assertEquals(this.ALMOST_EMPTY_STRING,
      ValidKeystoreWorstCaseBVTest.eh.decrypt(enc, EncryptionHelperTest.KEYNAME));
}

/**
 * Test a valid key using a standard-length string
 */
@Test
public void testValidNominal() throws Exception {
  // Test medium length encryption
  final String enc = ValidKeystoreWorstCaseBVTest.eh.encrypt(this.NOMINAL_STRING,
      EncryptionHelperTest.KEYNAME);
  Assert.assertNotEquals(enc, this.NOMINAL_STRING);
  Assert.assertEquals(this.NOMINAL_STRING,
      ValidKeystoreWorstCaseBVTest.eh.decrypt(enc, EncryptionHelperTest.KEYNAME));
}

/**
 * Test a valid key against a large 4KB string
 */
@Test
public void testValidLarge() throws Exception {
  // Test large length encryption
  final String longString = ValidKeystoreWorstCaseBVTest.generateLongString(4096);
  final String enc = ValidKeystoreWorstCaseBVTest.eh.encrypt(longString, EncryptionHelperTest.KEYNAME);
  Assert.assertNotEquals(longString, enc);
  Assert.assertEquals(longString,
      ValidKeystoreWorstCaseBVTest.eh.decrypt(enc, EncryptionHelperTest.KEYNAME));
}

/**
 * Test a valid key using a binary string
 */
@Test
public void testValidRawBytes() throws Exception {
  // Test some bytes that are less than 0x80 (String overflows there)
  final byte[] inp = { (byte) 0x00, (byte) 0x0c, (byte) 0x75, (byte) 0x00, (byte) 0x13 };
  final String s = new String(inp);
  final String enc = ValidKeystoreWorstCaseBVTest.eh.encrypt(s, EncryptionHelperTest.KEYNAME);
  final String dec = ValidKeystoreWorstCaseBVTest.eh.decrypt(enc, EncryptionHelperTest.KEYNAME);
  final byte[] out = dec.getBytes();

  boolean bytesMatch = true;
  for (int i = 0; i < inp.length; i++) {
    bytesMatch = bytesMatch & (inp[i] == out[i]);
  }

  Assert.assertTrue(bytesMatch);

}
```
## Method #3

Using the outlined testing strategies above we have developed the following test cases for the method under test:

### Boundary Value Tests

```java
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
  assertEquals(Repeat.getNValue("nweeks"), 0);

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
```
### Equivalence Class Tests

```java
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
```

### Decision Table Tests
```java
/**
 * Test case to represent the Decision Table Tests.
 */
@Test
public void testGetNValueDT() {
  fail("Not yet implemented");
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

   // C4 -> A1.
    assertEquals(Repeat.getNValue(Repeat.NDAYS + "," + "notavalidnumber"), 0);
    assertEquals(Repeat.getNValue(Repeat.NDAYS + "," + "-1"), 0);
    assertEquals(Repeat.getNValue(Repeat.NDAYS + "," + "2.5"), 0);
    assertEquals(Repeat.getNValue(Repeat.NDAYS + "," + "0"), 0);

   // C5 -> A2.
    assertEquals(Repeat.getNValue(Repeat.NDAYS + "," + "5"), 5);
    assertEquals(Repeat.getNValue(Repeat.NWEEKS + "," + "5"), 5);
    assertEquals(Repeat.getNValue(Repeat.NMONTHS + "," + "5"), 5);
    assertEquals(Repeat.getNValue(Repeat.NYEARS + "," + "5"), 5);
}
```
