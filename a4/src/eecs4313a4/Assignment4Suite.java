package eecs4313a4;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

@Suite.SuiteClasses({ RepeatTest.class, TestDataFlow.class})
@RunWith(Suite.class)
public class Assignment4Suite {
	public static void main(String args[]) {
		Result result = JUnitCore.runClasses(Assignment4Suite.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		System.out.println("All tests passed? " + result.wasSuccessful());
	}
}
