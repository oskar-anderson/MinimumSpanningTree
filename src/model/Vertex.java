package model;

import java.security.InvalidParameterException;

public class Vertex {

    private final String id;
    public String getId() { return id; }

    public Vertex(String s) {
        if (s == null) { throw new InvalidParameterException("String cannot be null"); }
        id = s;
    }

    public Vertex addToGraphUnsafe(Graph g) {
        if (g == null){ throw new InvalidParameterException("Graph cannot be null"); }
        g.addVertex(this);
        return this;
    }

    public Vertex addToGraph(Graph g) {
        if (g == null){ throw new InvalidParameterException("Graph cannot be null"); }

        // makes Vertex creation significantly slower
        if (g.getVertices().stream().anyMatch(x -> x.getId().equals(this.id))){
            throw new InvalidParameterException("id is already in use");
        }
        return addToGraphUnsafe(g);
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return id.equals(vertex.id);
    }
}