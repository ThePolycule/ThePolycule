package the.polycule.service;

import org.apache.commons.lang3.tuple.Pair;
import the.polycule.model.Edge;
import the.polycule.model.Node;
import the.polycule.model.RawGraph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class NodeService {
    public List<Pair<Node, Node>> transformNodes(RawGraph rawGraph) {
        List<Pair<Node, Node>> results = new LinkedList<>();
        if (rawGraph.getNodes().isEmpty()) {
            //boring graphs without a single node are not allowed
            throw new IllegalStateException("No nodes");
        }
        Map<String, Node> byId = new HashMap<>();
        for (Node node : rawGraph.getNodes()) {
            Node old = byId.put(node.getId(), node);
            if (old != null) {
                throw new IllegalStateException("Multiple nodes with id [" + node.getId() + "]");
            }
        }
        for (Edge edge : rawGraph.getEdges()) {
            Node from = byId.get(edge.getFromId());
            if (from == null) {
                throw new IllegalStateException("Unable to find Node for [from] for edge [" + edge.getFromId() + "]->[" + edge.getToId() + "]");
            }
            Node to = byId.get(edge.getToId());
            if (to == null) {
                throw new IllegalStateException("Unable to find Node for [to] for edge [" + edge.getFromId() + "]->[" + edge.getToId() + "]");
            }
            results.add(Pair.of(from, to));
        }
        return results;
    }
}
