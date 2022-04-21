import java.io.FileInputStream;
import java.io.IOException;

public class FillSignatures {
	public static void fillMap() throws IOException {
		FileInputStream fis = new FileInputStream("./signatures.txt");
		byte[] text = new byte[fis.available()];
		fis.read(text);
		String result = "";
		for (int i = 0; i < text.length; i++)
			if (text[i] != 32)
				result += (char)text[i];
		String[] strings = result.split("\n");
		for (String s : strings) {
			int tmp = s.substring(s.indexOf(",") + 1).length() / 2;
			Program.maxSignSize = tmp > Program.maxSignSize ? tmp : Program.maxSignSize;
			Program.map.put(s.substring(s.indexOf(",") + 1), s.substring(0, s.indexOf(",")));
		}
		fis.close();
	}
}
