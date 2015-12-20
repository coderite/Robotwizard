package GoogleCrawl;

import java.io.*;

/**
 * Created by bittstream on 12/8/2015.
 */
public class contentPraser {
	public static void main(String[] args) {
		contentPraser app = new contentPraser();
		app.start();
	}

	public void start() {

		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		InputStream stream = loader.getResourceAsStream("Travellers/content");

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));

			String line;
			while((line = reader.readLine()) != null) {
				String[] words = line.split((" "));
				StringBuilder sb = new StringBuilder();

				for(int i=0;i<words.length;i++) {
					if(words[i].contains("@")) {
						sb.append(words[i] + " ");
					}
				}
				System.out.println(sb.toString());
			}
			reader.close();
		} catch (IOException ex) {
			ex.printStackTrace();

		}
	}

	class Pair {
		private String title;
		private String content;
		private String email;

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
	}
}
