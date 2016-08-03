import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Created by zenbox on 1/27/16.
 */
public class Test {
    private static final Logger log = LogManager.getLogger();

    public static void main(String[] args) throws InterruptedException {
        Test app = new Test();
        app.go();
    }

    public void go() throws InterruptedException {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread " + Thread.currentThread().getName() + " says hello");
            }
        };

        Thread t = new Thread(runnable);
        t.start();

        Runnable runnableLambda = () -> System.out.println("Thread " + Thread.currentThread().getName() + " also says hi!");
        Thread t2 = new Thread(runnableLambda);
        t2.start();

        Runnable runnableLambda2 = () -> {
            InnerGlory glory = new InnerGlory();
            glory.setGlory("its glorious!");
            System.out.println(glory.getGlory());
        };

        Thread t3 = new Thread(runnableLambda2);
        t3.start();
        t3.join();


    }

    public class InnerGlory {
        private String glory;
        public void setGlory(String glory) {
            this.glory = glory;
        }
        public String getGlory() {
            if(glory != null)
                return glory;
            return "N/A";
        }
    }

    public void start() {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL("http://www.amazon.com/gp/product/B00E6GRHBO").openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_5_8; en-US) AppleWebKit/532.5 (KHTML, like Gecko) Chrome/4.0.249.0 Safari/532.5");
            connection.setRequestProperty("Referer", "http://www.google.com/");
            connection.connect();
            log.info("connection connected");
            InputStream stream = connection.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            String line ="";
            while((line = reader.readLine()) != null) {
                if(line.contains("large\"\\,\""))
                    System.out.println(line);
            }

            connection.disconnect();
            reader.close();

        } catch(MalformedURLException ex) {
            ex.printStackTrace();
        } catch(IOException ex) {
            ex.printStackTrace();
        }

    }

}
