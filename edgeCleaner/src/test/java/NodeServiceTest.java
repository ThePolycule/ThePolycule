import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import the.polycule.model.Edge;
import the.polycule.model.Node;
import the.polycule.model.RawGraph;
import the.polycule.service.NodeService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class NodeServiceTest {
    private NodeService underTest = new NodeService();

    private final String aName = "aName", aId = "aId", bId = "bId", cId = "cId", bName = "bName", cName = "cName", URL = "https://image/png";

    @Test
    void happyPath() {

        RawGraph input = new RawGraph();
        input.setEdges(Arrays.asList(new Edge(aId, bId), new Edge(aId, cId), new Edge(bId, aId), new Edge(cId, aId)));
        Node a = new Node(aId, aName, null);
        Node b = new Node(bId, bName, null);
        Node c = new Node(cId, cName, URL);
        input.setNodes(Arrays.asList(a, b, c));

        List<Pair<Node, Node>> expected = Arrays.asList(Pair.of(a, b), Pair.of(a, c), Pair.of(b, a), Pair.of(c, a));

        List<Pair<Node, Node>> result = underTest.transformNodes(input);
        assertThat(result).containsAll(expected);
    }

    @Test
    void boringGraph() {
        IllegalStateException thrown = Assertions.assertThrows(IllegalStateException.class, () -> {
            RawGraph input = new RawGraph();
            underTest.transformNodes(input);

        });
        Assertions.assertEquals("No nodes", thrown.getMessage());

    }

    @Test
    void dupeId() {
        IllegalStateException thrown = Assertions.assertThrows(IllegalStateException.class, () -> {
            RawGraph input = new RawGraph();
            Node a = new Node(aId, aName, null);
            input.setNodes(Arrays.asList(a, a));

            underTest.transformNodes(input);

        });
        Assertions.assertEquals("Multiple nodes with id [aId]", thrown.getMessage());
    }

    @Test
    void noTo() {
        IllegalStateException thrown = Assertions.assertThrows(IllegalStateException.class, () -> {
            RawGraph input = new RawGraph();
            Node a = new Node(aId, aName, null);
            input.setNodes(Collections.singletonList(a));
            input.setEdges(Collections.singletonList(new Edge(aId, bId)));

            underTest.transformNodes(input);

        });
        Assertions.assertEquals("Unable to find Node for [to] for edge [aId]->[bId]", thrown.getMessage());
    }

    @Test
    void noFrom() {
        IllegalStateException thrown = Assertions.assertThrows(IllegalStateException.class, () -> {
            RawGraph input = new RawGraph();
            Node a = new Node(aId, aName, null);
            input.setNodes(Collections.singletonList(a));
            input.setEdges(Collections.singletonList(new Edge(bId, aId)));

            underTest.transformNodes(input);

        });
        Assertions.assertEquals("Unable to find Node for [from] for edge [bId]->[aId]", thrown.getMessage());
    }
}
