import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Program {
	public static Map<String, String> map = new HashMap<>();
	public static int maxSignSize = 0;

	public static void main(String[] args) throws IOException {
		try {
			FillSignatures.fillMap();
		} catch (IOException e) {
			System.err.println("Please add \"signutures.txt\" file");
			System.exit(-1);
		}
		Scanner scanner = new Scanner(System.in);

		String input = "";
		FileOutputStream resultF = new FileOutputStream("result.txt");
		while (!(input = scanner.nextLine()).equals("42")) {
			File file = new File(input);
			boolean flag = false;
			if (file.exists() && !file.isDirectory()) {
				FileInputStream fis = new FileInputStream(file);
				String result = "";
				for (int i = 0; i < maxSignSize && fis.available() > 0; i++)
					result += Integer.toHexString(fis.read()).toUpperCase();
				for (String key : map.keySet()) {
					if (result.startsWith(key)) {
						System.out.println("PROCESSED");
						resultF.write(map.get(key).getBytes());
						flag = true;
						break;
					}
				}
                fis.close();
				if (flag) continue;
			}
			System.out.println("UNDEFINED");
		}
	}
}
