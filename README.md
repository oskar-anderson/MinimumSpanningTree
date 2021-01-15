# Minimum Spanning Tree #

Find the minimum spanning tree (MST) of an undirected weighted graph using Kruskal's algorithm.
Done for TalTech's Algorithms and Data Structures (ICD0001) course.

Based on a modified [template](https://bitbucket.org/itc_algorithms/k6/src/master/) by Jaanus Pöial.

## About ##

1. Create a copy of a graph without arcs.
2. Loop over a list of sorted (by weight) arcs.
3. Try to place the arc to the copy.
4. When a cycle forms, take back the arc placement.

Code:
```
public static Graph kruskal(Graph g) {
  Graph result = g.deepCopy(false);
  for (Arc arc : g.getArcs()) {
     arc.addToGraphUnsafe(result);
     boolean createsCycle = createsCycle(arc.getSource(), result);
     if (createsCycle) { result.removeArc(arc); }
  }
  return result;
}
```

Cycle detection uses a set and a deque.
Visited vertices are stored in the set and directed arcs to visit are stored in the deque.

Sample of solution times from my machine.

| Vertices    | Arcs        | Time(s)     |
| ----------- | ----------- |-------------|
| 1000        | 1000        | 1           |
| 1000        | 2000        | 5           |
| 1000        | 4000        | 10          |
| 1000        | 8000        | 27          |
| 1000        | 16000       | 45          |
| 2000        | 2000        | 9           |
| 2000        | 4000        | 54          |
| 2000        | 8000        | 121         |
| 2000        | 16000       | 301         |

