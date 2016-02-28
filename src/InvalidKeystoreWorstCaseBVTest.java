package net.sf.borg.common;

import java.security.InvalidKeyException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class InvalidKeystoreWorstCaseBVTest {
	protected static EncryptionHelper eh;

	private final String EMPTY_STRING = "";
	private final String ALMOST_EMPTY_STRING = " ";
	private final String NOMINAL_STRING = "a sample message";

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

	public static String generateLongString(long len) {
		final StringBuilder sb = new StringBuilder();

		for (long i = 0; i < len; i++) {
			sb.append("A");
		}

		return sb.toString();
	}

	@Test(expected = InvalidKeyException.class)
	public void testInvalidEmptyString() throws Exception {
		// Test smallest possible value, and ensure the encrypted form is
		// different than the plain form
		final String enc = InvalidKeystoreWorstCaseBVTest.eh.encrypt(this.EMPTY_STRING, "Test");
		Assert.assertNotEquals(enc, this.EMPTY_STRING);
		Assert.assertEquals(this.EMPTY_STRING,
				InvalidKeystoreWorstCaseBVTest.eh.decrypt(enc, EncryptionHelperTest.KEYNAME));
	}

	@Test(expected = InvalidKeyException.class)
	public void testValidSingleChar() throws Exception {
		// Test a single character encryption
		final String enc = InvalidKeystoreWorstCaseBVTest.eh.encrypt(this.ALMOST_EMPTY_STRING, "Test");
		Assert.assertNotEquals(enc, this.ALMOST_EMPTY_STRING);
		Assert.assertEquals(this.ALMOST_EMPTY_STRING,
				InvalidKeystoreWorstCaseBVTest.eh.decrypt(enc, EncryptionHelperTest.KEYNAME));
	}

	@Test(expected = InvalidKeyException.class)
	public void testValidNominal() throws Exception {
		// Test medium length encryption
		final String enc = InvalidKeystoreWorstCaseBVTest.eh.encrypt(this.NOMINAL_STRING, "Test");
		Assert.assertNotEquals(enc, this.NOMINAL_STRING);
		Assert.assertEquals(this.NOMINAL_STRING,
				InvalidKeystoreWorstCaseBVTest.eh.decrypt(enc, EncryptionHelperTest.KEYNAME));
	}

	@Test(expected = InvalidKeyException.class)
	public void testValidLarge() throws Exception {
		// Test large length encryption
		final String longString = InvalidKeystoreWorstCaseBVTest.generateLongString(4096);
		final String enc = InvalidKeystoreWorstCaseBVTest.eh.encrypt(longString, "Test");
		Assert.assertNotEquals(longString, enc);
		Assert.assertEquals(longString,
				InvalidKeystoreWorstCaseBVTest.eh.decrypt(enc, EncryptionHelperTest.KEYNAME));
	}
}
