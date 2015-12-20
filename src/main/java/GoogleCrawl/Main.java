package GoogleCrawl;

import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.*;

/**
 * Created by bittstream on 12/7/2015.
 */
public class Main {
	private int rsz = 8;
	private int startIndex = 0;
	private String google = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";
	private String search = "\"gmail.com\" \"linkedin.com\" mortgage loan officer";
	private String userIp = "&userip=89.103.144.65";
	private String var = "&rsz=8";
	private String start = "&start" + startIndex;
	private String charset = "UTF-8";

	public static void main(String[] args) throws Exception {
		Main app = new Main();
		app.start();

	//	for(int i=0;i<109000;i++) {

//			if(i % 8 == 0)
//				System.out.println(i);
//		}
	}

	public void start() throws Exception {
		System.out.println("START LOOP");

		for(int i=0;i<109000;i += 8) {
			start = "&start=" + i;
			URL url = new URL(google + URLEncoder.encode(search, charset) + userIp + var + start);

			Reader reader = new InputStreamReader(url.openStream(), charset);
			GoogleResults results = new Gson().fromJson(reader, GoogleResults.class);
			reader.close();

			for(int j=0;j<8;j++) {
				System.out.println(j);
				System.out.println("URL: " + url);
				System.out.println(results.getResponseData().getResults().get(j).getTitle());
			}
			Thread.sleep(3000);
		}
	}
}
