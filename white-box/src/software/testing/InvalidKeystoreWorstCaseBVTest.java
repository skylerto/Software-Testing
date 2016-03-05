package software.testing;


import java.security.InvalidKeyException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import net.sf.borg.common.EncryptionHelper;

/**
 * Test cases to test invalid key being used in EncryptionHelper's encrypt(...)
 *
 * @author Drew Noel - cse23217 - 212513784
 */
public class InvalidKeystoreWorstCaseBVTest {
	protected static EncryptionHelper eh;

	private final String EMPTY_STRING = "";
	private final String ALMOST_EMPTY_STRING = " ";
	private final String NOMINAL_STRING = "a sample message";

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
}
