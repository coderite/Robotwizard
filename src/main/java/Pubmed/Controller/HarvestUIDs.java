package Pubmed.Controller;

import Carters.Helpers;
import Pubmed.Model.UIDPage;
import com.google.gson.Gson;

import java.io.*;
import java.net.URL;
import java.util.*;

/**
 * Created by zenbox on 8/2/2016.
 */
public class HarvestUIDs {
    private String searchQuery = "breast cancer";
    private int retmax = 10000;
    private String mindate = "2011/08/05";
    private String maxdate = "2016/08/02";
    private int retstart = 0;

    private TreeSet<String> uids = new TreeSet<>();
    private static final double MEG = (Math.pow(1024, 2));

    public static void main(String[] args) throws Exception {
        HarvestUIDs harvester = new HarvestUIDs();
        harvester.start();
    }

    public void start() throws Exception {
        System.out.println("SEARCH QUERY=" + searchQuery);
        System.out.println("DATE RANGE=" + mindate + "-" + maxdate);
        System.out.println("TOTAL COUNT=" + getCount());
        System.out.println(getPubMedSearchQuery());

        int count = getCount();

        for(int i=retstart;i<count;i += retmax) {
            System.out.println("\tretstart index=" + i);
            String source = getUrlContent(getPubMedSearchQuery());
            retstart += retmax;

            Gson gson = new Gson();
            UIDPage page = gson.fromJson(source, UIDPage.class);
            for(String str : page.getIdList()) {
                uids.add(str + "\n");
            }

        }
        System.out.println(uids.size() + " unique IDs found");

        System.out.println("printing to file");
        Helpers helper = new Helpers();
        helper.setOutputFile("Results/PubMed/uids.txt");

        List<String> listUIDS = new ArrayList<>(uids);

        writeRaw(listUIDS);
        //writeBuffered(listUIDS, 8192);
        //writeBuffered(listUIDS, (int) MEG);
        //writeBuffered(listUIDS, 4 * (int) MEG);
        System.out.println("done");
    }

    private static void writeRaw(List<String> records) throws IOException {
        File file = new File("Results/PubMed/foo.txt");
        try {
            FileWriter writer = new FileWriter(file);
            System.out.print("Writing raw... ");
            write(records, writer);
        } finally {
            // comment this out if you want to inspect the files afterward
            //file.delete();
        }
    }

    private static void writeBuffered(List<String> records, int bufSize) throws IOException {
        File file = File.createTempFile("Results/PubMed/foo", ".txt");
        try {
            FileWriter writer = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(writer, bufSize);

            System.out.print("Writing buffered (buffer size: " + bufSize + ")... ");
            write(records, bufferedWriter);
        } finally {
            // comment this out if you want to inspect the files afterward
            file.delete();
        }
    }

    private static void write(List<String> records, Writer writer) throws IOException {
        long start = System.currentTimeMillis();
        for (String record: records) {
            writer.write(record);
        }
        writer.flush();
        writer.close();
        long end = System.currentTimeMillis();
        System.out.println((end - start) / 1000f + " seconds");
    }

    public String getPubMedSearchQuery() {
        searchQuery = searchQuery.replaceAll(" ", "+");
        return "https://eutils.ncbi.nlm.nih.gov/entrez/eutils/esearch.fcgi?db=pubmed&term=" + searchQuery + "&retmax=" + retmax + "&retstart=" + retstart + "&retype=uilist&datetype=pdat&mindate=" + mindate + "&maxdate=" + maxdate + "&retmode=json";
    }

    public int getCount() throws Exception {
        int oldRetmax = retmax;
        retmax = 10;
        String source = getUrlContent(getPubMedSearchQuery());
        retmax = oldRetmax;

        Gson gson = new Gson();
        UIDPage page = gson.fromJson(source, UIDPage.class);

        return page.getCount();
    }

    public String getUrlContent(String urlString) throws Exception {
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
