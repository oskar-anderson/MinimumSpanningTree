import static org.junit.Assert.*;

import model.Arc;
import model.Graph;
import model.Vertex;
import org.junit.Test;

public class GraphTaskTest {

   @Test (timeout=20000, expected = RuntimeException.class)
   public void testParallelArcError() {
      Graph graph = new Graph("G");

      Vertex a = new Vertex("a").addToGraph(graph);
      Vertex b = new Vertex("b").addToGraph(graph);
      new Arc(3, b, a).addToGraph(graph);
      new Arc(1, b, a).addToGraph(graph);
   }

   @Test (expected = RuntimeException.class)
   public void testDuplicateArc() {
      Graph graph = new Graph("G");

      Vertex b = new Vertex("b").addToGraph(graph);
      Vertex c = new Vertex("c").addToGraph(graph);

      new Arc(6, c, b).addToGraph(graph);
      new Arc(4, b, c).addToGraph(graph);
   }

   @Test (timeout=1000000)
   public void testBigGraphA() {
      Graph g = new Graph("G");
      g.createRandomSimpleGraph(1000, 1000);
      System.out.println(GraphTask.kruskal(g));
   }

   @Test (timeout=1000000)
   public void testBigGraphB() {
      Graph g = new Graph("G");
      g.createRandomSimpleGraph(2000, 2000);
      System.out.println(GraphTask.kruskal(g));
   }

   @Test (timeout=1000000)
   public void testBigGraphC() {
      Graph g = new Graph("G");
      g.createRandomSimpleGraph(2500, 2500);
      System.out.println(GraphTask.kruskal(g));
   }

   @Test (timeout=1000000)
   public void testBigGraphD() {
      Graph g = new Graph("G");
      g.createRandomSimpleGraph(2000, 4000);
      System.out.println(GraphTask.kruskal(g));
   }

   @Test (timeout=1000000)
   public void testBigGraphE() {
      Graph g = new Graph("G");
      g.createRandomSimpleGraph(2000, 8000);
      System.out.println(GraphTask.kruskal(g));
   }

   @Test (timeout=1000000)
   public void testBigGraphF() {
      Graph g = new Graph("G");
      g.createRandomSimpleGraph(1000, 4000);
      System.out.println(GraphTask.kruskal(g));
   }


   @Test (timeout=1000000)
   public void testBigGraphG() {
      Graph g = new Graph("G");
      g.createRandomSimpleGraph(1000, 8000);
      System.out.println(GraphTask.kruskal(g));
   }

   @Test (timeout=100000)
   public void graph1() {
      // https://imgur.com/a/Q91csTd
      Graph graph = new Graph("G");

      Vertex a = new Vertex("a").addToGraph(graph);
      Vertex b = new Vertex("b").addToGraph(graph);
      Vertex c = new Vertex("c").addToGraph(graph);
      Vertex d = new Vertex("d").addToGraph(graph);
      Vertex e = new Vertex("e").addToGraph(graph);

      new Arc(4, e, b).addToGraph(graph);
      new Arc(3, c, b).addToGraph(graph);
      new Arc(2, d, b).addToGraph(graph);
      new Arc(1, b, a).addToGraph(graph);

      assertEquals(4, graph.getArcs().size());
      assertEquals(4, GraphTask.kruskal(graph).getArcs().size());
   }

   @Test (timeout=100000)
   public void graph2() {
      // https://imgur.com/FpjmQsS
      Graph graph = new Graph("G");

      Vertex a = new Vertex("a").addToGraph(graph);
      Vertex b = new Vertex("b").addToGraph(graph);
      Vertex c = new Vertex("c").addToGraph(graph);
      Vertex d = new Vertex("d").addToGraph(graph);

      new Arc(2, a, b).addToGraph(graph);
      new Arc(5, a, d).addToGraph(graph);
      new Arc(1, a, c).addToGraph(graph);
      new Arc(6, b, c).addToGraph(graph);
      new Arc(3, b, d).addToGraph(graph);
      new Arc(4, c, d).addToGraph(graph);

      assertEquals(6, graph.getArcs().size());
      Graph mst = GraphTask.kruskal(graph);
      assertEquals(3, mst.getArcs().size());
      assertEquals(6, mst.weightSum());
   }

   @Test (timeout=100000)
   public void graph3() {
      // https://imgur.com/a/yyzaSBt
      Graph graph = new Graph("G");

      Vertex a = new Vertex("a").addToGraph(graph);
      Vertex b = new Vertex("b").addToGraph(graph);
      Vertex c = new Vertex("c").addToGraph(graph);
      Vertex d = new Vertex("d").addToGraph(graph);
      Vertex e = new Vertex("e").addToGraph(graph);
      Vertex f = new Vertex("f").addToGraph(graph);
      Vertex g = new Vertex("g").addToGraph(graph);

      new Arc(1, a, c).addToGraph(graph);
      new Arc(2, g, f).addToGraph(graph);
      new Arc(3, f, e).addToGraph(graph);
      new Arc(4, e, c).addToGraph(graph);
      new Arc(5, g, d).addToGraph(graph);
      new Arc(6, d, e).addToGraph(graph);
      new Arc(7, d, c).addToGraph(graph);
      new Arc(8, a, b).addToGraph(graph);

      assertEquals(8, graph.getArcs().size());
      Graph mst = GraphTask.kruskal(graph);
      assertEquals(6, mst.getArcs().size());
      assertEquals(23, mst.weightSum());
   }


   @Test (timeout=100000)
   public void graph4() {
      // https://imgur.com/a/K4Hcp8Y
      Graph graph = new Graph("G");

      Vertex a = new Vertex("a").addToGraph(graph);
      Vertex b = new Vertex("b").addToGraph(graph);
      Vertex c = new Vertex("c").addToGraph(graph);
      Vertex d = new Vertex("d").addToGraph(graph);
      Vertex e = new Vertex("e").addToGraph(graph);
      Vertex f = new Vertex("f").addToGraph(graph);
      Vertex g = new Vertex("g").addToGraph(graph);

      new Arc(1, a, c).addToGraph(graph);
      new Arc(2, g, f).addToGraph(graph);
      new Arc(4, e, c).addToGraph(graph);
      new Arc(5, g, d).addToGraph(graph);
      new Arc(6, d, e).addToGraph(graph);
      new Arc(7, d, c).addToGraph(graph);
      new Arc(8, a, b).addToGraph(graph);

      assertEquals(7, graph.getArcs().size());
      Graph mst = GraphTask.kruskal(graph);
      assertEquals(6, mst.getArcs().size());
      assertEquals(26, mst.weightSum());
   }


   @Test (timeout=100000)
   public void graph5() {
      // https://imgur.com/a/z71Gqz4
      Graph graph = new Graph("G");

      Vertex a = new Vertex("a").addToGraph(graph);
      Vertex b = new Vertex("b").addToGraph(graph);
      Vertex c = new Vertex("c").addToGraph(graph);
      Vertex d = new Vertex("d").addToGraph(graph);
      Vertex e = new Vertex("e").addToGraph(graph);
      Vertex f = new Vertex("f").addToGraph(graph);
      Vertex g = new Vertex("g").addToGraph(graph);

      new Arc(8, a, b).addToGraph(graph);
      new Arc(1, a, c).addToGraph(graph);
      new Arc(6, d, e).addToGraph(graph);
      new Arc(7, d, c).addToGraph(graph);
      new Arc(2, g, f).addToGraph(graph);
      new Arc(5, g, d).addToGraph(graph);
      new Arc(3, a, g).addToGraph(graph);
      new Arc(4, e, c).addToGraph(graph);

      assertEquals(8, graph.getArcs().size());
      assertEquals(6, GraphTask.kruskal(graph).getArcs().size());
      assertEquals(23, GraphTask.kruskal(graph).weightSum());
   }
}

