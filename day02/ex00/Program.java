import java.io.*;
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
					result += toHex(fis.read());
				fis.close();
				for (String key : map.keySet()) {
					if (result.startsWith(key)) {
						System.out.println("PROCESSED");
						resultF.write((map.get(key) + "\n").getBytes());
						flag = true;
						break;
					}
				}
				if (flag) continue;
			}
			System.out.println("UNDEFINED");
		}
		scanner.close();
        resultF.close();
	}

	private static String toHex(Integer value) {
		String result = "";
		int tmp = value / 16;
		if (tmp % 16 > 9)
			result += (char)(55 + tmp % 16);
		else
			result += (char)(48 + tmp % 16);
		if (value % 16 > 9)
			result += (char)(55 + value % 16);
		else
			result += (char)(48 + value % 16);
		return result;
	}
}
