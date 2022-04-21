import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.junit.jupiter.api.Test;
import the.polycule.model.Edge;
import the.polycule.model.Node;
import the.polycule.model.RawGraph;
import the.polycule.service.GraphService;
import the.polycule.service.NodeService;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class GraphServiceTest {
    private NodeService nodeService = new NodeService();
    GraphService underTest = new GraphService(nodeService);

    private final String aName = "aName", aId = "aId", bId = "bId", cId = "cId", bName = "bName", cName = "cName", URL = "https://image/png";

    @Test
    void name() {
        RawGraph input = new RawGraph();
        input.setEdges(Arrays.asList(new Edge(aId, bId), new Edge(aId, cId), new Edge(bId, aId), new Edge(cId, aId)));
        Node a = new Node(aId, aName, null);
        Node b = new Node(bId, bName, null);
        Node c = new Node(cId, cName, URL);
        input.setNodes(Arrays.asList(a, b, c));


        Graph<Node, DefaultEdge> result = underTest.transformJGraph(input);

        assertThat(result.getAllEdges(a,b)).hasSize(1);
        assertThat(result.getAllEdges(b,a)).hasSize(1);
        assertThat(result.getAllEdges(a,b)).hasSize(1);
        assertThat(result.getAllEdges(b,a)).hasSize(1);
        assertThat(result.getAllEdges(b,c)).hasSize(0);

        assertThat(result.getAllEdges(a,a)).hasSize(0);
        assertThat(result.getAllEdges(b,b)).hasSize(0);
        assertThat(result.getAllEdges(c,c)).hasSize(0);

    }
}
