package Carters;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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
            System.out.println("error : printing text");
        } finally {
            if(writer != null) {
                writer.close();
            }
        }
    }
}
