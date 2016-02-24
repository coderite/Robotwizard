package KSBLaw;

import Carters.Helpers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by zenbox on 2/9/2016.
 */
public class TextCombine {
    public static void main(String[] args) {
        TextCombine app = new TextCombine();
        //app.start();
        //app.extractDocketNumbers();
        //app.findMissing();
        //app.totalCount();
        app.cleanRemoved();
    }

    public void cleanRemoved() {
        File completedFile = new File("Results/Ksblaw/removedDockets.txt");
        System.out.println("reading large file " + completedFile.exists());
        Helpers helper = new Helpers();
        helper.setOutputFile("Results/Ksblaw/cleanedRemoved.txt");

        int lineCount = 1;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(completedFile));
            String line;
            while((line = reader.readLine())!= null) {
                String[] tokens = line.trim().split(" ");
                String[] dockets = tokens[1].split("id=");
                String name = "N/A";
                String docketNumber = dockets[1];
                String bookingDate = "N/A";
                String releasedDate = "N/A";

                helper.printItem(name + ";" + docketNumber + ";" + bookingDate + ";" + releasedDate);
                ++lineCount;
            }
            reader.close();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
        System.out.println(lineCount);
    }

    public void totalCount() {
        File completedFile = new File("Results/Ksblaw/combined.txt");
        System.out.println("reading large file " + completedFile.exists());

        int lineCount = 1;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(completedFile));
            String line;
            while((line = reader.readLine())!= null) {
                ++lineCount;
            }
            reader.close();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
        System.out.println(lineCount);
    }

    public void extractDocketNumbers() {
        File completedFile = new File("Results/Ksblaw/combined.txt");

        Helpers helper = new Helpers();
        helper.setOutputFile("Results/Ksblaw/foundIDs.txt");

        System.out.println("reading large file " + completedFile.exists());

        int lineCount = 1;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(completedFile));
            String line;
            while((line = reader.readLine())!= null) {
                String[] tokens = line.split(";");
                if(tokens[1].contains("com")) {
                    String[] takens = tokens[1].split("id=");
                    helper.printItem(takens[1]);
                } else {
                    helper.printItem(tokens[1]);
                }
                ++lineCount;
            }
            reader.close();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
        System.out.println(lineCount);
    }

    public void findMissing() {
        File completedFile = new File("Results/Ksblaw/foundIDs.txt");

        System.out.println("reading large file " + completedFile.exists());
        ArrayList<String> completed = new ArrayList<String>();
        int lineCount = 1;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(completedFile));
            String line;
            while((line = reader.readLine())!= null) {
                completed.add(line);
                ++lineCount;
            }
            reader.close();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
        System.out.println(lineCount);

        Helpers helper = new Helpers();
        helper.setOutputFile("Results/Ksblaw/missing.txt");

        ArrayList<String> total = new ArrayList<String>();
        for (int i = 1283009; i <= 1576543; i++) {
            total.add(String.valueOf(i));
        }
        System.out.println("initiated array");

        Collection result = new ArrayList(total);
        result.removeAll(completed);
        List<String> test = new ArrayList<String>(result);
        System.out.println(test.size());

        for(String line : test) {
            helper.printItem(line);
        }
        System.out.println("done");


/*
        int count = 1;
        for(int k=0;k<completed.size();k++) { // for each entry we have
            String found = completed.get(k);

            boolean pass = false;
            for (int i = 1283009; i <= 1576543; i++) {
                String real = String.valueOf(i);

                if(found.equals(real)) {
                    pass = true;
                    break;
                }
            }

            if(!pass) {
                String missingLink = completed.get(i);
                System.out.println("missing: " + missingLink);
                helper.printItem(missingLink);
                ++count;
            }
        }

        if(completed.size() + count == 1576543) {
            System.out.println("found them all!");
        }
        System.out.println("all done");*/
    }

    public void start() {
        File folder = new File("Results/Ksblaw/");
        File[] files = folder.listFiles();
        System.out.println("there are " + files.length + " files");

        Helpers helper = new Helpers();
        helper.setOutputFile("Results/Ksblaw/combined.txt");

        int count = 1;
        for(File file : files) {
            if(file.isFile()) {
                System.out.println("Reading " + file.getName());

                try {
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    String line;
                    while((line = reader.readLine())!= null) {
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
