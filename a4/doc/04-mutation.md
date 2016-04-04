# Mutation Testing

## Assignment 2 Review

The mutation testing was limited to only tests written by this group, with all other tests forcibly removed. The testing was next limited to mutate only the class which contained the method being tested. As discussed in A2, some tests uncovered potential bugs in the BORGCalendar. PIT requires that all tests pass in order to run, so these tests were temporarily removed.

### `Repeat.java` Test Analysis

Two of the three methods tested belong to `net.sf.borg.model.Repeat`. The following is the overview of the class using only the tests submitted for A2:

\begin{figure}[H]
\includegraphics[width=0.5\textwidth]{./assets/PIT/A2RepeatOverviewBefore.png}
\caption{net.sf.borg.model.Repeat}
\end{figure}

This overview is not particularly useful since there are many unrelated methods in this class that were not the focus of the tests. The detailed mutation coverage of the two methods under test are as follows:

\begin{figure}[H]
\includegraphics[width=\textwidth]{./assets/PIT/A2getNValueSpecific.png}
\caption{net.sf.borg.model.Repeat getNValue(...)}
\end{figure}

\begin{figure}[H]
\includegraphics[width=\textwidth]{./assets/PIT/A2calcRepeatSpecific.png}
\caption{net.sf.borg.model.Repeat calculateRepeatNumber(...)}
\end{figure}

Since A2 was a black-box testing exercise, several of these are expected. For example, it was not clear from the documentation that `getNValue(...)` should behave differently if there were 2 commas in the input string (lines 325-337). Other mutation failures, such as ones in `calculateRepeatNumber(...)` indicate that the original testing was too minimal; some of those mutations are more trivial (lines 444, 451-452).

### `Repeat.java` Test Repair

In order to repair the missing mutation handling, four new tests were written:

1. Test to ensure that `calculateRepeatNumber(...)` sets the dates correctly (kills lines 444) by testing that on two separate days, the repeat number is different.
2. Test to ensure that `calculateRepeatNumber(...)` loop calculates the repeat number as expected, by creating a more detailed typical use-case test.
3. Test to ensure that `getNValue(...)` returns 0 when passed in a `null`.
4. Test to ensure that `getNValue(...)` returns the number between two commas.

The updated source code has been submitted in the submit package. The updated coverage results are below:

\begin{figure}[H]
\includegraphics[width=0.5\textwidth]{./assets/PIT/A2RepeatOverviewAfter.png}
\caption{net.sf.borg.model.Repeat Updated}
\end{figure}

\begin{figure}[H]
\includegraphics[width=\textwidth]{./assets/PIT/A2getNValueSpecificFixed.png}
\caption{net.sf.borg.model.Repeat getNValue(...) Updated}
\end{figure}

\begin{figure}[H]
\includegraphics[width=\textwidth]{./assets/PIT/A2calcRepeatSpecificFixed.png}
\caption{net.sf.borg.model.Repeat calculateRepeatNumber(...) Updated}
\end{figure}

### `EncryptionHelper` Test Analysis

After restricting the tests to show the class and test results of only the written tests, the following overview was produced:

\begin{figure}[H]
\includegraphics[width=0.5\textwidth]{./assets/PIT/A2EncryptionTestOverview.png}
\caption{net.sf.borg.common}
\end{figure}

There is only one method under test in this class, `encrypt(...)`. It passed all of the mutation tests with no modifications, as seen here:

\begin{figure}[H]
\includegraphics[width=\textwidth]{./assets/PIT/A2EncryptionTestSpecific.png}
\caption{net.sf.borg.common encryption(...)}
\end{figure}

This is likely because there were many tests written for this method, yet this method just communicates with built-in Java methods. Since the original tests correctly killed all the mutants, the test source code was not modified.

## Assignment 3 Review

### `Repeat.java` Test Analysis

After being permitted to view the source code, the tests written for `net.sf.borg.model.Repeat` significantly improved in quality. The overview:

\begin{figure}[H]
\includegraphics[width=0.5\textwidth]{./assets/PIT/A3RepeatOverviewBefore.png}
\caption{net.sf.borg.model.Repeat}
\end{figure}

Note that this is actually 1 fewer mutant kill than in the A2 analysis after adding tests. Viewing the detailed information confirmed this finding.

\begin{figure}[H]
\includegraphics[width=\textwidth]{./assets/PIT/A3getNValueSpecific.png}
\caption{net.sf.borg.model.Repeat getNValue(...)}
\end{figure}

\begin{figure}[H]
\includegraphics[width=\textwidth]{./assets/PIT/A3calcRepeatSpecific.png}
\caption{net.sf.borg.model.Repeat calculateRepeatNumber(...)}
\end{figure}

The original tests from A2 killed all the mutants successfully, and so did not require any modifications. Additionally, the `EncryptionHelper` tests were not changed between A2 and A3, and so they still kill all their mutants as well. Therefore, all tests written and submitted as part of A3 killed all the mutants created by PIT. No code modifications were made for A4.
