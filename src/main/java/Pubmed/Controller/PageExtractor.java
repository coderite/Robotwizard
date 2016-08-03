package Pubmed.Controller;

import java.io.*;
import java.net.URL;
import java.util.List;

/**
 * Created by zenbox on 8/2/2016.
 */
public class PageExtractor {
    private String id;
    private int numberOfIds;
    private String source;

    public void setNumberOfIds(int number) {
        this.numberOfIds = number;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void run() throws Exception {
        String source = getSource(getPubMedFetchQuery());
        writeRaw(source);
    }

    private void writeRaw(String source) throws IOException {
        long start = System.currentTimeMillis();
        File file = new File("Results/PubMed/xml/" + id.substring(0,8) + ".txt");
        try {
            FileWriter writer = new FileWriter(file);
            System.out.print("\tWriting xml... ");
            write(source, writer);
        } finally {
            // comment this out if you want to inspect the files afterward
            //file.delete();
        }

        long end = System.currentTimeMillis();
        System.out.println((end - start) / 1000f + " seconds");
    }

    private static void write(String source, Writer writer) throws IOException {
        long start = System.currentTimeMillis();
        writer.write(source);
        writer.flush();
        writer.close();
        long end = System.currentTimeMillis();
        System.out.println((end - start) / 1000f + " seconds");
    }

    public String getPubMedFetchQuery() {
        if(numberOfIds == 1)
            return "https://eutils.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi?db=pubmed&id=" + id + "&retmode=xml";
        else {

            return "https://eutils.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi?db=pubmed&id=" + id + "&retmode=xml";
        }
    }

    public String getSource(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }
}
