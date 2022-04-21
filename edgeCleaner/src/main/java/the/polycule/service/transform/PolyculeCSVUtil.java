package the.polycule.service.transform;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import the.polycule.model.Edge;
import the.polycule.model.Node;
import the.polycule.model.RawGraph;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class PolyculeCSVUtil {
    public RawGraph loadCSV(String csvSample) throws IOException {
        return loadCSV(new StringReader(csvSample));
    }

    public RawGraph loadCSV(Reader in) throws IOException {
        RawGraph graph = new RawGraph();
        Iterable<CSVRecord> records = CSVFormat.EXCEL.withCommentMarker('#').withHeader("name", "id", "partners", "image").parse(in);
        boolean first = false;
        for (CSVRecord record : records) {
            if (!first) {
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
                for (String partner : splits) {
                    graph.getEdges().add(
                            new Edge(id, partner)
                    );
                }
            }
        }
        return graph;
    }
}
