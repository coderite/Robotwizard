package Carters;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by zenbox on 2/1/2016.
 */
public class Helpers {
    private String outputFile;

    public void setOutputFile(String filename) {
        this.outputFile = filename;
    }

    public void printItem(String text) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileWriter(outputFile, true));
            writer.println(text);
        } catch (IOException ex) {
            System.out.println("error : printing text: " + ex.getMessage());
        } finally {
            if(writer != null) {
                writer.close();
            }
        }
    }

    public ArrayList<String> loadLinks(String filePath) {
        InputStream stream = getClass().getClassLoader().getResourceAsStream(filePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        ArrayList<String> list = new ArrayList<String>();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                if(!line.startsWith("#")) {
                   // String[] tokens = line.split("html");
                   // line = tokens[0] + "html";

                    if(!list.contains(line))
                        list.add(line.trim());
                }
            }
        }catch (IOException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
