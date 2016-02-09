package Burton;

import Carters.Helpers;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by zenbox on 2/7/16.
 */
public class FailChecker {
    String fileString = "burtonProductLinksReal.txt";

    public static void main(String[] args) {
        FailChecker app = new FailChecker();
        app.start();
        // app.checkPermutation();
    }

    public void checkPermutation() {
        ArrayList<String> list = new Helpers().loadLinks("Burton/rawLinks");
        Set<String> finalList = new HashSet<String>();

        for(int i=0;i<list.size();i++) {
            String com = list.get(i).replaceAll("http://www.burtonandburton.com/", "");

            com = com.substring(0, com.indexOf("---"));

            finalList.add(list.get(i));
            System.out.println(com);
        }

        for(String n : list)
            System.out.println(n);

        System.out.println("original list size " + list.size());
        System.out.println("pruned list size " + finalList.size());

    }

    public void start() {
        File file = new File(fileString);
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));

            String previous = "start";
            String current;
            while((current = reader.readLine()) != null) {
                if(current.contains("Opening") && previous.contains("Opening")) {
                    System.out.println(previous.replaceAll("Opening page:", "").trim());
                }
                previous = current;
            }
            reader.close();
        } catch(FileNotFoundException ex) {

        } catch(IOException ex) {

        }
    }
}
