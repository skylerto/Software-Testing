package eecs4313a2;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

@Suite.SuiteClasses({ ValidKeystoreWorstCaseBVTest.class, InvalidKeystoreWorstCaseBVTest.class, RepeatTest.class })
@RunWith(Suite.class)
public class A2Tests {
	public static void main(String args[]) {
		Result result = JUnitCore.runClasses(A2Tests.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		System.out.println("All tests passed? " + result.wasSuccessful());
	}
}
