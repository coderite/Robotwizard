package Burton;

import Carters.Helpers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by zenbox on 2/10/2016.
 */
public class Combiner {
    public static void main(String[] args) {
        Combiner app = new Combiner();
        app.start();
    }

    public void start() {
        File folder = new File("Results/Burton/");
        File[] files = folder.listFiles();
        System.out.println("there are " + files.length + " files");

        Helpers helper = new Helpers();
        helper.setOutputFile("Results/Burton/combined.txt");

        int count = 1;
        for (File file : files) {
            if (file.isFile()) {
                System.out.println("Reading " + file.getName());

                try {
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        helper.printItem(line);
                        ++count;
                    }
                    reader.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        System.out.println("count: " + count);
    }
}
