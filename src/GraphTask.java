import model.Arc;
import model.DirectedArc;
import model.Graph;
import model.Vertex;

import java.util.*;
import java.util.stream.Collectors;


public class GraphTask {

   public static void main (String[] args) {
      GraphTask a = new GraphTask();
      a.run();
   }

   public void run() {
      if (true)
      {
         // https://imgur.com/a/Q91csTd

         Graph graph1 = new Graph("G");

         Vertex a = new Vertex("a").addToGraph(graph1);
         Vertex b = new Vertex("b").addToGraph(graph1);
         Vertex c = new Vertex("c").addToGraph(graph1);
         Vertex d = new Vertex("d").addToGraph(graph1);
         Vertex e = new Vertex("e").addToGraph(graph1);


         new Arc(4, e, b).addToGraph(graph1);
         new Arc(3, c, b).addToGraph(graph1);
         new Arc(2, d, b).addToGraph(graph1);
         new Arc(1, b, a).addToGraph(graph1);

         System.out.println(graph1);
         System.out.println(kruskal(graph1));
      }
   }


   /**
    * Main method for creating Minimum Spanning Tree.
    * @param g Original graph that MST will be taken from
    * @return Minimum Spanning Tree.
    */
   public static Graph kruskal(Graph g) {
      Graph result = g.deepCopy(false);
      result.name = "MST of graph " + g.name;
      for (Arc arc : g.getArcs()) {
         arc.addToGraphUnsafe(result);
         boolean createsCycle = createsCycle(arc.getV1(), result);
         if (createsCycle) {
            result.removeArc(arc);
         }
      }
      return result;
   }


   private static boolean createsCycle(Vertex expandFrom, Graph g) {
      Deque<DirectedArc> expand = g.getVertexArcs(expandFrom)
              .stream()
              .map(x -> new DirectedArc(
                      expandFrom,
                      expandFrom.equals(x.getV1()) ? x.getV2() : x.getV1()
              ))
              .collect(Collectors.toCollection(ArrayDeque::new));
      Set<Vertex> visited = new HashSet<>();
      while (! expand.isEmpty()) {
         DirectedArc bfe = expand.pop();
         visited.add(bfe.from);
         if (visited.contains(bfe.to)) {
            return true;
         }
         DirectedArc opposite = new DirectedArc(bfe.to, bfe.from);
         expand.addAll(g.getVertexArcs(bfe.to)
                 .stream()
                 .map(x -> new DirectedArc(
                         bfe.to,
                         bfe.to.equals(x.getV1()) ? x.getV2() : x.getV1()
                         ))
                 .filter(x -> ! x.equals(opposite) )
                 .collect(Collectors.toList())
         );
      }
      return false;
   }
}