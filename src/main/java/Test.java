import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by zenbox on 1/27/16.
 */
public class Test {
    public static void main(String[] args) {
        Test app = new Test();
        app.start();
    }

    public void start() {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL("http://www.amazon.com/gp/product/B00E6GRHBO").openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_5_8; en-US) AppleWebKit/532.5 (KHTML, like Gecko) Chrome/4.0.249.0 Safari/532.5");
            connection.setRequestProperty("Referer", "http://www.google.com/");
            connection.connect();
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
