import org.junit.jupiter.api.Test;
import the.polycule.service.transform.PolyculeCSVUtil;
import the.polycule.model.Edge;
import the.polycule.model.RawGraph;
import the.polycule.model.Node;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class CSVTest {
    PolyculeCSVUtil underTest = new PolyculeCSVUtil();

    @Test
    void singleElement() throws IOException {
        String IMG = "https://image/png";
        String ID = "aId";
        String NAME = "aName";
        String csvSample = "name,id,partners,image\n" + NAME + "," + ID + ",," + IMG;
        RawGraph expected = new RawGraph();
        expected.setEdges(Collections.emptyList());
        expected.setNodes(Collections.singletonList(new Node(ID, NAME, IMG)));

        RawGraph result = underTest.loadCSV(csvSample);

        assertThat(result).isNotNull();
        assertThat(result.getEdges()).isEqualTo(expected.getEdges());
        assertThat(result.getNodes()).isEqualTo(expected.getNodes());
    }

    @Test
    void simple() throws IOException {
        String aName = "aName";
        String aId = "aId";
        String bId = "bId";
        String cId = "cId";
        String bName = "bName";
        String cName = "cName";
        String URL = "https://image/png";
        String csvSample = "name,id,partners,image\n"
                + aName + "," + aId + ",\"" + bId + "," + cId + "\",\n"
                + bName + "," + bId + "," + aId + ",\n"
                + cName + "," + cId + ",\"" + aId + "\"," + URL;

        System.out.println(csvSample);

        RawGraph expected = new RawGraph();
        expected.setEdges(Arrays.asList(new Edge(aId, bId), new Edge(aId, cId), new Edge(bId, aId), new Edge(cId, aId)));
        expected.setNodes(Arrays.asList(new Node(aId, aName, null), new Node(bId, bName, null), new Node(cId, cName, URL)));

        RawGraph result = underTest.loadCSV(csvSample);

        assertThat(result).isNotNull();
        assertThat(result.getEdges()).isEqualTo(expected.getEdges());
        assertThat(result.getNodes()).isEqualTo(expected.getNodes());
    }
}
