# Data Flow

## Data Flow Diagram

The Data Flow Diagram below shows the segments labelled by the type of node.

\begin{figure}[H]
\includegraphics[width=\textwidth]{./assets/data-flow-diagram.png} 
\caption{getNValue(String f) Data Flow Diagram} 
\label{df-diagram} 
\end{figure} 

## Program Segmented

The same program has been broken up into corresponding segments basked on the
data flow diagram above.

\begin{figure}[H]
\includegraphics[width=\textwidth]{./assets/program-segmented.png} 
\caption{getNValue(String f) Segmented} 
\label{Program Segmented Diagram} 
\end{figure}

## Program Graph

The following figure shows the program graph for the segmented code segment
above.

\begin{figure}[H]
\centering
\includegraphics[width=0.5\textwidth]{./assets/program-graph.png} 
\caption{getNValue(String f) Graph} 
\label{pg-diagram} 
\end{figure}

## Data Flow Paths

Below we define all the data flow paths. The labelling scheme used here follows
from the program graph defined above.

### All-Defs

The following graphs satisfy the All definitions criteria:

```
Each definition of each variable for at least one use of the definition: 
```

\begin{figure}[H]
\centering
\includegraphics[width=0.5\textwidth]{./assets/all-defs.png} 
\caption{All Definitions (ABDEG)} 
\label{all-def} 
\end{figure}


### All-Uses

The following graphs satisfy the All uses criteria:

```
At least one path of each variable to each c-use of the definition: 
```

\begin{figure}[H]
\centering
\includegraphics[width=0.5\textwidth]{./assets/all-uses.png} 
\caption{All Uses (ABDEGH)} 
\label{all-use} 
\end{figure}

### All P Uses and Some C Uses

The following graphs show the path for All P uses and Some C uses for the given
method. The criteria is:
```
At least one path of each variable definition to each p-use of the variable.
If any variable definitions are not covered by p-use, then use c-use: 
```

\begin{figure}[H]
\centering
\includegraphics[width=0.5\textwidth]{./assets/all-p-some-c.png} 
\caption{All P uses, Some C uses (ABDEGHI)} 
\label{all-p-some-c} 
\end{figure}


### All C Uses and Some P Uses

The following diagrams show the paths which satisfy the criteria for All-C-Uses
and Some-P-Uses:
```
At least one path of each variable definition to each c-use of the variable.
If any variable definitions are not covered, use p-use: 
```

\begin{figure}[H]
\centering
\includegraphics[width=0.5\textwidth]{./assets/all-c-some-p-1.png} 
\caption{All C uses, Some P uses (ABDEGHI)} 
\label{all-c-some-p-1} 
\end{figure}


\begin{figure}[H]
\centering
\includegraphics[width=0.5\textwidth]{./assets/all-c-some-p-2.png} 
\caption{All C uses, Some P uses (ABDEGHJ)} 
\label{all-c-some-p-2} 
\end{figure}

\newpage


