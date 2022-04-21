package the.polycule;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import the.polycule.model.Node;
import the.polycule.model.RawGraph;
import the.polycule.service.GraphService;
import the.polycule.service.NodeService;
import the.polycule.service.transform.PolyculeCSVUtil;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Main {
    public static void main(String... args) throws IOException {
        String finPath;
        if (args == null || args.length == 0) {
            finPath = "polycule.csv";
        } else {
            finPath = args[0];
        }

        File fin = new File(finPath);

        if (!fin.exists()) {
            throw new IllegalStateException("File [" + fin.getAbsolutePath() + "] is not valid");
        } else {
            System.out.println("Loading [" + fin.getAbsolutePath() + "]");
        }

        Reader in = new FileReader(fin);

        PolyculeCSVUtil csvUtil = new PolyculeCSVUtil();
        RawGraph graph = csvUtil.loadCSV(in);

        NodeService nodeService = new NodeService();
        GraphService graphService = new GraphService(nodeService);
        Graph<Node, DefaultEdge> jGraph = graphService.transformJGraph(graph);
    }
}
