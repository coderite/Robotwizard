package ProxyFactory;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zenbox on 3/2/2016.
 */
public class UsProxyOrg implements ProxySite {
    private String link = "http://www.us-proxy.org/";
    private List<Record> anonymousProxies;
    private List<Record> transparentProxies;

    public static void main(String[] args) {
        UsProxyOrg app = new UsProxyOrg();
    }

    public UsProxyOrg() {
        parse(downloadList());

      //  ProxyFactory proxyFactory = new ProxyFactory();
        //Proxy proxy = proxyFactory.getProxy(ProxyFactory.ProxyType.ANONYMOUS);
    }

    @Override
    public Elements downloadList() {
        boolean retry = false;
        int count = 1;
        do {
            try {
                Document doc = Jsoup.connect(link).userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36").get();
                return doc.select("tbody tr");
            } catch (IOException ex) {
                ex.printStackTrace();
                count++;
                if(count < 3)
                    retry = true;
            }
        } while (retry);
        return null;
    }

    @Override
    public List<Record> getProxies() {
        return null;
    }

    @Override
    public List<Record> getAnonymousProxies() {
        return anonymousProxies;
    }

    @Override
    public List<Record> getTranparentProxies() {
        return transparentProxies;
    }

    @Override
    public List<Record> parse(Elements rawElements) {
        anonymousProxies = new ArrayList<>();
        transparentProxies = new ArrayList<>();

        rawElements.forEach(rawElement -> {
            if(rawElement.text().contains("anonymous"))
                anonymousProxies.add(getRecordFromRawLine(rawElement));
            else if(rawElement.text().contains("transparent"))
                transparentProxies.add(getRecordFromRawLine(rawElement));
        });
        return null;
    }

    private Record getRecordFromRawLine(Element rawElement) {
        Elements subs = rawElement.select("td");
        Record record = new Record();
        record.setIp(subs.get(0).text());
        record.setPort(subs.get(1).text());
        record.setLocation(subs.get(2).text());
        record.setType(subs.get(4).text());
        return record;
    }

}
