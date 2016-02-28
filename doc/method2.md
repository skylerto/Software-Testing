# Method Test #2:

The method under test belongs to the `EncryptionHelper` class, located in the package `net.sf.borg.common`. The method has the following signature, including java doc:

```java
/**
* encrypt a String using a key from the key store
*
* @param clearText
*            - the string to encrypt
* @param keyAlias
*            - the encryption key alias
* @return the encrypted string
* @throws Exception
*/
public String encrypt(String clearText, String keyAlias);
```

This method accepts two arguments: a string which should be encrypted, and a string which informs the method which key from the `Keystore` to use. The `Keystore` is created through static methods in the class ahead of time. This method throws an `Exception` if there is any type of exception thrown during its execution.

The technique selected to test this method is the Boundary-Value Test strategy. Through examining the source code, it is clear that `keyAlias` is only manipulated by core Java methods and classes, and so testing values of `keyAlias` was given a lower priority. The argument `clearText` accepts any `String` as input. By treating the length of the string as the component to test, the following value breakdown was created:

- A string with zero length
- A string with length of one
- A string with a nominal length
- A string which is exceptionally large
- A string comprised of binary data

It is not possible to perform a Strong Boundary-Value Test because a String cannot have length that is negative, and String has no practical upper limit. Note that it would be possible to design a test which forcibly exhausts the memory of the JVM by creating an enormous string as input. However this would be a failure point on the JVM, which the application is not expected to be able to handle.

The final test value was created after inspecting the source code. In the method, there is a call to the `String` method `getBytes()`. This method uses the default encoding, which may or may not fundamentally change the data it receives.

By changing the value of `keyAlias` to be either a valid key or an invalid key (similar to a boolean), a second breakdown was created:

- A string which describes a valid key
- A string which does not describe a valid key

The tests were written to attempt every possible combination of these test values, creating a Worst-Case Boundary-Value Test suite. Examination of the source code indicated that the result of an invalid key description will always be a thrown `Exception`. Specifically the type of the exception will be an `InvalidKeyException`. There were several other types of exceptions that this method might throw, but since the signature indicates it may throw any `Exception`, this was not tested.

All the tests described above passed when execute. It is worthwhile to observe that this method, like most methods in the `net.sf.borg.common` package, gives no information about the exception types that it might throw. This significantly increases the amount of code that the client classes will require in order to properly determine why an exception was thrown at all.
