package eecs4313a4;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import net.sf.borg.model.Repeat;

/**
 * This test class contains data flow test cases for the method getNValue.
 * 
 * @author Skyler Layne
 *
 */
public class TestDataFlow {

	/**
	 * This test case explores the path ABDEG
	 */
	@Test
	public void testAllDefs() {
		assertEquals(Repeat.getNValue("NWEEKS 5"), 0);
	}

	/**
	 * This test case explores the path ABDEGH
	 */
	@Test
	public void testAllUses() {
		assertEquals(Repeat.getNValue("NDAYS 5"), 0);
		assertEquals(Repeat.getNValue("NWEEKS 5, NWEEKS 8"), 0);
	}

	/**
	 * This test case explores the path ABDEGHI
	 */
	@Test
	public void testAllPSomeC() {
		assertEquals(Repeat.getNValue("NWEEKS 5"), 0);
	}

	/**
	 * This test case explores the path ABDEGHI and ABDEGHJ
	 */
	@Test
	public void testAllCSomeP() {
		assertEquals(Repeat.getNValue("NDAYS 5"), 0);
		assertEquals(Repeat.getNValue("NWEEKS 5, NWEEKS 8"), 0);
	}

}
