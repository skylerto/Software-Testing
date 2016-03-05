package net.sf.borg.common;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import junit.framework.TestCase;

@Suite.SuiteClasses({ ValidKeystoreWorstCaseBVTest.class, InvalidKeystoreWorstCaseBVTest.class })
@RunWith(Suite.class)

/**
 * Test Suite in order to trigger the two seperate test classes for EncryptionHelper
 *
 * @author Drew Noel - cse23217 - 212513784
 */
public class EncryptionHelperTest extends TestCase {

	/** Hold the store name */
	protected final static String STORE_NAME = "store";
	/** Hold the store password */
	protected final static String STORE_PASS = "test1234";
	/** Create some arbitrary key input */
	protected final static String KEY = "bL6bZauVL/9GqglfaR3Ecg==";
	/** Create some arbitrary name for the key */
	protected final static String KEYNAME = "junit";
}
