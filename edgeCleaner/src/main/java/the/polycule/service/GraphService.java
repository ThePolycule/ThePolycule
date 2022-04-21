package the.polycule.service;

import org.apache.commons.lang3.tuple.Pair;
import org.jgrapht.*;

import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import the.polycule.model.Edge;
import the.polycule.model.Node;
import the.polycule.model.RawGraph;

import java.util.List;

public class GraphService {
    final NodeService nodeService;

    public GraphService(NodeService nodeService) {
        this.nodeService = nodeService;
    }

    public Graph<Node, DefaultEdge> transformJGraph(RawGraph rawGraph){
        List<Pair<Node, Node>> edges = nodeService.transformNodes(rawGraph);
        Graph<Node, DefaultEdge> result = new DefaultDirectedGraph<>(DefaultEdge.class);

        for (Node node : rawGraph.getNodes()) {
            result.addVertex(node);
        }

        for (Pair<Node, Node> edge : edges) {
            result.addEdge(edge.getKey(), edge.getValue());
        }

        return result;
    }
}
