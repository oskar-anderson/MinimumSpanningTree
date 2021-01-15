package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Graph {

    public String name;

    /**
     * Stores all arcs in non-descending order based on weight and secondaryWeight
     * https://stackoverflow.com/questions/16764007/insert-into-an-already-sorted-list
     * https://stackoverflow.com/questions/369512/how-to-compare-objects-by-multiple-fields
     */
    private final ArrayList<Arc> arcs = new ArrayList<>() {
        public boolean add(Arc arc) {
            int index = Collections.binarySearch(arcs, arc, Comparator
                    .comparing(Arc::getWeight)
                    .thenComparing(Arc::getSecondaryWeight));
            if (index < 0) {
                index = -index - 1;
            }
            arcs.add(index, arc);
            return true;
        }
    };
    public List<Arc> getArcs() { return Collections.unmodifiableList(arcs); }
    public void addArc(Arc arc) { arcs.add(arc); }


    private final ArrayList<Vertex> vertices = new ArrayList<>();
    public List<Vertex> getVertices() { return Collections.unmodifiableList(vertices); }
    public void addVertex(Vertex v) { vertices.add(v); }



    public Graph(String s) {
        name = s;
    }


    public ArrayList<Arc> getVertexArcs(Vertex vertex) {
        Stream<Arc> arcs = this.arcs.stream().filter(x -> x.getV1().equals(vertex) || x.getV2().equals(vertex));
        return (ArrayList<Arc>) arcs.collect(Collectors.toList());
    }


    public Graph deepCopy(boolean arcsToo) {
        Graph result = new Graph(this.name);
        for (Vertex vertex : this.vertices) {
            new Vertex(vertex.getId()).addToGraphUnsafe(result);
        }
        if (! arcsToo) return result;
        for (Arc arc : this.arcs) {
            Vertex v1 = result.vertices.stream().filter(x -> x.getId().equals(arc.getV1().getId())).findFirst().orElseThrow();
            Vertex v2 = result.vertices.stream().filter(x -> x.getId().equals(arc.getV2().getId())).findFirst().orElseThrow();
            new Arc(arc.getWeight(), v1, v2).addToGraphUnsafe(result);
        }
        return result;
    }


    public void removeArc(Arc arc) {
        Arc removeMe = this.arcs
                .stream()
                .filter(x ->
                        x.getV1().equals(arc.getV1()) && x.getV2().equals(arc.getV2()) ||
                        x.getV2().equals(arc.getV1()) && x.getV1().equals(arc.getV2()))
                .findFirst()
                .orElseThrow();
        this.arcs.remove(removeMe);
    }


    public int weightSum() {
        return arcs.stream().mapToInt(x -> x.getWeight()).sum();
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\n");
        sb.append(name);
        sb.append("\n");
        for (Vertex vertex : vertices) {
            sb.append(vertex.toString());
            sb.append(":");
            ArrayList<Arc> arcs = this.getVertexArcs(vertex);
            for (Arc arc : arcs) {
                sb.append(" ");
                sb.append(arc.toString());
            }
            sb.append("\n");
        }
        return sb.toString();
    }


    /**
     * Create a connected undirected random tree with n vertices.
     * Each new vertex is connected to a random existing vertex.
     * @param n number of vertices added to this graph
     */
    private void createRandomTree(int n) {
        Vertex[] varray = new Vertex[n];
        for (int i = 0; i < n; i++) {
            Vertex v1 = new Vertex("v" + i).addToGraphUnsafe(this);
            varray[i] = v1;
            if (i < 1)
                continue;
            int vnr = (int) (Math.random() * i);
            Vertex v2 = varray[vnr];
            new Arc((int) (Math.random() * Integer.MAX_VALUE), v2, v1).addToGraphUnsafe(this);
        }
    }

    private boolean[][] createAdjMatrix() {
        boolean[][] res = new boolean[this.vertices.size()][this.vertices.size()];

        for (Vertex v : this.vertices) {
            int i = this.vertices.indexOf(v);
            ArrayList<Arc> vertexArcs = this.getVertexArcs(v);
            for (Arc arc : vertexArcs) {
                int j =  this.vertices.indexOf(arc.getV2());
                res[i][j] = true;
            }
        }
        return res;
    }

    public void createRandomSimpleGraph(int vertexCount, int arcCount) {
        if (vertexCount <= 0)
            return;
        if (vertexCount > 3000)
            throw new IllegalArgumentException("Too many vertices: " + vertexCount);
        if (arcCount < vertexCount-1 || arcCount > vertexCount*(vertexCount-1)/2)
            throw new IllegalArgumentException("Impossible number of edges: " + arcCount);
        createRandomTree(vertexCount);       // vertexCount-1 edges created here

        boolean[][] connected = createAdjMatrix();
        int edgeCount = arcCount - vertexCount + 1;
        for (int ignore = 0; ignore < edgeCount; ignore++) {
            int sourceIdx = (int)(Math.random()*vertexCount);
            int targetIdx = (int)(Math.random()*vertexCount);
            if (sourceIdx == targetIdx)
                continue;  // no loops
            if (connected[sourceIdx][targetIdx] || connected[targetIdx][sourceIdx])
                continue;  // no multiple edges
            Vertex vSource = this.vertices.get(sourceIdx);
            Vertex vTarget = this.vertices.get(targetIdx);
            new Arc((int) (Math.random() * Integer.MAX_VALUE), vSource, vTarget).addToGraphUnsafe(this);
            connected[sourceIdx][targetIdx] = true;
        }
    }
}