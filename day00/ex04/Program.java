import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String line = scanner.nextLine();
		scanner.close();
		short[] arrayOfSymbols = fillArray(line);
		char[] result = getResult(arrayOfSymbols);
		showStatistics(result, arrayOfSymbols);
	}

	private static void showStatistics(char[] result, short[] arrayOfSymbols) {
		if (result.length == 0)
			return;
		float delimetr = (float)arrayOfSymbols[result[0]] / 10;
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < result.length; j++) {
				if ((int)((float)arrayOfSymbols[result[j]] / delimetr) == 10 - i) {
					if (j > 0)
						System.out.print(" ");
					if (arrayOfSymbols[result[j]] < 100)
						System.out.print(" ");
					if (arrayOfSymbols[result[j]] < 10)
						System.out.print(" ");
					System.out.print(arrayOfSymbols[result[j]]);
				}
				if ((int)((float)arrayOfSymbols[result[j]] / delimetr) > 10 - i) {
					if (j > 0)
						System.out.print(" ");
					System.out.print("  #");
				}
			}
			System.out.println();;
		}
		for (int i = 0; i < result.length; i++) {
			if (i > 0)
				System.out.print(" ");
			System.out.print("  " + result[i]);
		}
		System.out.println();
	}

	private static short[] fillArray(String line) {
		short[] result = new short[65536];
		char[] lineArray = line.toCharArray();
		for (int i = 0; i < lineArray.length; i++) {
			if (result[lineArray[i]] < 999)
				result[lineArray[i]]++;
		}
		return result;
	}

	private static char[] getResult(short[] array) {
		char[] result = new char[10];
		for (int i = 0; i < array.length; i++) {
			if (array[i] > 0) {
				for (int j = 0; j < 10; j++) {
					if (array[i] >= array[result[j]]) {
						if (array[i] == array[result[j]])
							if (i > result[j])
								continue;
						changeResult(result, array, i, j);
						break;
					}
				}
			}
		}
		return checkResult(result);
	}

	private static char[] checkResult(char[] result) {
		if (result[9] == 0) {
			int size = 10;
			for (; size - 1 > -1 && result[size - 1] == 0; size--);
			char[] newRes = new char[size];
			for (int i = 0; i < size; i++)
				newRes[i] = result[i];
			result = newRes;
		}
		return result;
	}

	private static void changeResult(char[] result, short[] array, int i, int j) {
		for (int k = 9; k > j; k--)
			result[k] = result[k - 1];
		result[j] = (char) i;
	}
}
