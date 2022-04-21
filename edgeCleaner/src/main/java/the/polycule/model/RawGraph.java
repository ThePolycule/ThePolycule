package the.polycule.model;

import java.util.LinkedList;
import java.util.List;

public class RawGraph {
    List<Node> nodes;
    List<Edge> edges;

    public RawGraph() {
        this.nodes = new LinkedList<>();
        this.edges = new LinkedList<>();
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }
}
