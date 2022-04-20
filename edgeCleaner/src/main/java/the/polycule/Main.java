package the.polycule;

import the.polycule.model.Graph;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Main {
    public static void main(String... args) throws IOException {
        String finPath;
        if (args != null || args.length == 0) {
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
        Graph graph = csvUtil.loadCSV(in);

    }
}
