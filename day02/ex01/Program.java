import java.io.*;
import java.util.*;

public class Program {
	public static void main(String[] args) throws IOException {
		if (args.length != 2) {
			System.out.println("Usage: Program file1 file2");
			return;
		}
		BufferedReader br1 = new BufferedReader(new FileReader(args[0]));
		BufferedReader br2 = new BufferedReader(new FileReader(args[1]));
        BufferedWriter bw = new BufferedWriter(new FileWriter("dictionary.txt"));

		Map<String, Integer> map1 = new HashMap<>();
		Map<String, Integer> map2 = new HashMap<>();

		String text1 = "", text2 = "";

		while (br1.ready())
			text1 += br1.readLine();
		while (br2.ready())
			text2 += br2.readLine();

		String[] textArr1 = text1.split(" ");
		String[] textArr2 = text2.split(" ");

		fillMap(textArr1, map1);
		fillMap(textArr2, map2);

		Set<String> set = new TreeSet<>(map1.keySet());
		set.addAll(map2.keySet());

		Vector<Integer> vector1 = new Vector<>(set.size());
		Vector<Integer> vector2 = new Vector<>(set.size());

		for (String string : set) {
			vector1.add(map1.getOrDefault(string, 0));
			vector2.add(map2.getOrDefault(string, 0));
		}

		int numerator = 0;
		int numA = 0;
		int numB = 0;
		for (int i = 0; i < set.size(); i++) {
			numerator += vector1.get(i) * vector2.get(i);
			numA += vector1.get(i) * vector1.get(i);
			numB += vector2.get(i) * vector2.get(i);
		}

		System.out.printf("Similarity = %.2f\n", numerator / (Math.sqrt(numA) * Math.sqrt(numB)));
	}

	private static void fillMap(String[] strings, Map<String, Integer> map) {
		for (String string : strings) {
			if (map.containsKey(string))
				map.put(string, map.get(string) + 1);
			else
				map.put(string, 1);
		}
	}
}
