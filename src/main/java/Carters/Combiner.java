package Carters;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by zenbox on 3/24/2016.
 */
public class Combiner {
    public static void main(String[] args) throws Exception{
        Set<String> finalList = new HashSet<>();
        File folder = new File("Results/Carter/");
        List<File> files = new ArrayList<>();
        for(File file: folder.listFiles()) {
            if(file.isFile() && !file.getName().equals("skipped.txt") && !file.getName().equals("combined.txt") && !file.getName().equals("skippedForInjection.txt") && !file.getName().contains("Links"))
                files.add(file);
        }

        Helpers helper = new Helpers();
        helper.setOutputFile("Results/Carter/combined.txt");

        System.out.println("Found " + files.size() + " files");
        for(File file : files) {
            System.out.println("\t loading " + file.getName());
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line;
            while((line = reader.readLine()) != null) {
                finalList.add(line);
            }
        }
        System.out.println(finalList.size() + " unique items loaded");
        System.out.print("wait a bit...");

        finalList.forEach(line -> helper.printItem(line));
        System.out.println("done");
    }
}
