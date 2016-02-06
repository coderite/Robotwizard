package SalesRabbit;

import SalesRabbit.Indeed.Result;
import SalesRabbit.Indeed.SearchResults;
import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by zenbox on 2/3/2016.
 */
public class Searcher {
    private SearchQuery query;
    private ArrayList<Result> jobs;
    private ArrayList<String> states;

    public Searcher(SearchQuery query) {
        this.query = query;
    }

    public void search() {
        System.out.println("URL " + query.getUrl());


        String set = connect(query.getUrl());
        SearchResults results = getResults(set);

        // loop through each state


        int totalResults = results.getTotalResults();
        int pages = totalResults / 25;
        if(pages>40)
            pages = 40;

        System.out.println("total results: " + totalResults + " " + pages + " pages");
        for(int i=0;i<pages;i++) {
            query.setStart(query.getStart() + 25);
            System.out.println(i + " " + query.getUrl());
        }
    }

    public String connect(String url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            InputStream stream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

            StringBuilder sb = new StringBuilder();
            String line;
            while((line=reader.readLine()) != null) {
                sb.append(line);
            }
            connection.disconnect();
            reader.close();
            return sb.toString();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public SearchResults getResults(String source) {
        Gson gson = new Gson();
        return gson.fromJson(source, SearchResults.class);
    }


}
