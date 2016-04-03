\newpage

# 6. Appendix A

This appendix contains each of our method specifications from Assignment 2.

## Method 1

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
final static public int calculateRepeatNumber(Calendar current,
    Appointment appt) {
  Calendar start = new GregorianCalendar();
  Calendar c = start;
  start.setTime(appt.getDate());
  Repeat r = new Repeat(start, appt.getFrequency());
  for (int i = 1;; i++) {
    if ((c.get(Calendar.YEAR) == current.get(Calendar.YEAR))
        && (c.get(Calendar.DAY_OF_YEAR) == current
            .get(Calendar.DAY_OF_YEAR)))
      return (i);
    if (c.after(current))
      return (0);
    c = r.next();
    if (c == null)
      return (0);
  }

}
```

## Method 2

``` java
	/**
	 * encrypt a String using a key from the key store
	 * @param clearText - the string to encrypt
	 * @param keyAlias - the encryption key alias
	 * @return the encrypted string
	 * @throws Exception
	 */
	public String encrypt(String clearText, String keyAlias)
			throws Exception {

		/*
		 * get the key and create the Cipher
		 */
		Key key = this.keyStore.getKey(keyAlias, this.password.toCharArray());
		Cipher enc = Cipher.getInstance("AES");
		enc.init(Cipher.ENCRYPT_MODE, key);

		/*
		 * encrypt the clear text
		 */
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		OutputStream os = new CipherOutputStream(baos, enc);
		os.write(clearText.getBytes());
		os.close();

		/*
		 * get the encrypted bytes and encode to a string
		 */
		byte[] ba = baos.toByteArray();
		return new String(Base64Coder.encode(ba));

	}
```

## Method 3

``` java
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

  if (!freq.equals(NDAYS) && !freq.equals(NWEEKS)
      && !freq.equals(NMONTHS) && !freq.equals(NYEARS))
    return (0);

  int i2 = f.indexOf(',', freq.length() + 1);
  if (i2 != -1)
    return (Integer.parseInt(f.substring(freq.length() + 1, i2)));

  return (Integer.parseInt(f.substring(freq.length() + 1)));

}
```
