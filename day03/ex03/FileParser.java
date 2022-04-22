import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileParser {
	private FileParser() { }

	public static void parse() throws IOException {
		String path = "./files_urls.txt";
		int i = 0;
		BufferedReader br = new BufferedReader(new FileReader(path));

		String url = "";

		while (br.ready()) {
			url = br.readLine();
			if (!url.contains("http"))
				throw new IOException();
			url = url.substring(url.indexOf("http"));
			Program.queue.add(url);
			Program.map.put(url, ++i);
		}
		br.close();
	}
}
