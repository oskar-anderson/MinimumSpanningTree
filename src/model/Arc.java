package model;

import java.security.InvalidParameterException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Arc {

    private final String id;
    private final Vertex v2;
    private final Vertex v1;
    private final Integer weight;
    private Integer secondaryWeight;

    public Vertex getV2() { return v2; }
    public Vertex getV1() { return v1; }
    public Integer getWeight() { return weight; }
    public Integer getSecondaryWeight() { return secondaryWeight; }


    public Arc(int w, Vertex v1, Vertex v2) {
        if (v1 == v2) throw new RuntimeException("Arc to self is not allowed!");

        this.id = v1.getId() + "--" + v2.getId();
        this.weight = w;
        this.v1 = v1;
        this.v2 = v2;
    }

    private static int findUniqueSecWeight(Graph g, int w) {
        int sw = 0;
        if (g.getArcs().stream().anyMatch(x -> x.getWeight() == w)) {
            List<Arc> dupWeightArcs = g.getArcs().stream().filter(x -> x.weight == w).collect(Collectors.toList());
            Arc maxSwArc = Collections.max(dupWeightArcs, Comparator.comparing(x -> x.secondaryWeight));
            sw = maxSwArc.secondaryWeight + 1;
        }
        return sw;
    }

    public Arc addToGraphUnsafe(Graph g){
        if (g == null){ throw new InvalidParameterException("Graph cannot be null"); }
        this.secondaryWeight = findUniqueSecWeight(g, this.weight);
        g.addArc(this);
        return this;
    }

    public Arc addToGraph(Graph g){
        if (g == null){ throw new InvalidParameterException("Graph cannot be null"); }

        for (Arc arc : g.getArcs()) {
            if (arc.v1.equals(v1) && arc.v2.equals(v2)) {
                throw new RuntimeException("Duplicate/Parallel arc is not allowed!");
            }
            if (arc.v1.equals(v2) && arc.v2.equals(v1)) {
                throw new RuntimeException("Duplicate/Parallel arc is not allowed!");
            }
        }

        return addToGraphUnsafe(g);
    }

    @Override
    public String toString() {
        return id;
    }
}
