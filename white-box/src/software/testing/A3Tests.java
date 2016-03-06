package software.testing;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@Suite.SuiteClasses({ ValidKeystoreWorstCaseBVTest.class, InvalidKeystoreWorstCaseBVTest.class, RepeatTest.class })
@RunWith(Suite.class)
public class A3Tests {

}
