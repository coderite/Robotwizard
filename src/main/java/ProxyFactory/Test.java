package ProxyFactory;

import java.util.List;

/**
 * Created by zenbox on 3/3/2016.
 */
public class Test {
    public static void main(String[] args) {
        Test app = new Test();
        app.run();
    }

    public void run() {
        ProxySite sites = new UsProxyOrg();
        List<Record> list = sites.getAnonymousProxies();
        list.forEach(record -> System.out.println(record.getIp() + ":" + record.getPort()));
    }
}
