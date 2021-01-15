package model;

import java.util.Objects;

public class DirectedArc {
    public Vertex from;
    public Vertex to;

    public DirectedArc(Vertex from, Vertex to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DirectedArc that = (DirectedArc) o;
        return Objects.equals(from, that.from) && Objects.equals(to, that.to);
    }

    @Override
    public String toString() {
        return from.getId() + "-->" + to.getId();
    }
}
