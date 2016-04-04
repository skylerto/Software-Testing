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
