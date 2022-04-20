package the.polycule;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import the.polycule.model.Edge;
import the.polycule.model.Graph;
import the.polycule.model.Node;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.LinkedList;

public class PolyculeCSVUtil {
    public Graph loadCSV(String csvSample) throws IOException {
        return loadCSV(new StringReader(csvSample));
    }

    public Graph loadCSV(Reader in) throws IOException {
        Graph graph = new Graph();
        graph.setNodes(new LinkedList<>());
        graph.setEdges(new LinkedList<>());
        Iterable<CSVRecord> records = CSVFormat.EXCEL.withCommentMarker('#').withHeader("name", "id", "partners", "image").parse(in);
        boolean first = false;
        for (CSVRecord record : records) {
            if(!first){
                first = true;
                continue;
            }
            //name,id,partners,image
            String name = record.get("name");
            String id = record.get("id");
            String img = record.get("image");

            Node node = new Node(id, name, StringUtils.trimToNull(img));
            graph.getNodes().add(node);

            //partners
            String csvPartners = record.get("partners");
            if (StringUtils.isNotEmpty(csvPartners)) {
                String[] splits = csvPartners.split(",");
                int i = 0;
                for (String partner : splits) {
                    i++;
                    graph.getEdges().add(
                            new Edge(id, partner)
                    );
                }
            }
        }
        return graph;
    }
}
