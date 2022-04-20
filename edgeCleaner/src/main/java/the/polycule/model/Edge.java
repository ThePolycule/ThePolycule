package the.polycule.model;

import java.util.Objects;

//BIDI Edge
public class Edge {
    final String fromId, toId;

    public Edge(String fromId, String toId) {
        this.fromId = fromId;
        this.toId = toId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return (Objects.equals(fromId, edge.fromId) &&
                Objects.equals(toId, edge.toId));
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromId, toId);
    }

    @Override
    public String toString() {
        return "Edge{" +
                "fromId='" + fromId + '\'' +
                ", toId='" + toId + '\'' +
                '}';
    }
}
