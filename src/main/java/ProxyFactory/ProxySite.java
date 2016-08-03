package ProxyFactory;

import org.jsoup.select.Elements;

import java.util.List;

/**
 * Created by zenbox on 3/2/2016.
 */
public interface ProxySite {
    List<Record> getProxies();
    List<Record> getAnonymousProxies();
    List<Record> getTranparentProxies();
    List<Record> parse(Elements rawElements);
    Elements downloadList();


}
