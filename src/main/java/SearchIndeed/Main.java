package SearchIndeed;

import Carters.Helpers;

import java.util.List;

/**
 * Created by zenbox on 2/3/2016.
 */
public class Main {
    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    public void start() {
        SearchQuery query = new SearchQuery();
        query.setBaseUri("http://api.indeed.com/ads/apisearch?");
        query.setPublisher("7984394500788683");
        query.setQuery("");
        query.setLocation("Virginia");
        query.setCountry("");
        query.setFormat("json");
        query.setStart(0);
        query.setLimit(25);
        query.setRadius("0");
        query.setUserip("94.112.146.60");
        query.setUseragent("Mozilla/%2F4.0%28Firefox%29");
        query.setVersion(2);

        Helpers help = new Helpers();
        List<String> list = help.loadLinks("Indeed/zips");
        //for(int i=0;i<list.size();i++) {
          //  query.setLocation(list.get(i));

            Searcher search = new Searcher(query);
            search.search();
        //}



        // ArrayList<Job> jobs = searcher.search();


    }

}
