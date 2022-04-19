import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		long weeksStorage = 0;
		int weekOrder = 0;
		String string;
		
		while (weekOrder < 18 && !(string = scanner.nextLine()).equals("42")) {
			if (!string.equals("Week " + ++weekOrder)) {
				scanner.close();
				System.exit(illegalArgument());
			}
			long grades = 10;
			for (int i = 0; i < 5; i++) {
				if (!scanner.hasNextInt()) {
					scanner.close();
					System.exit(illegalArgument());
				}
				int tmp = scanner.nextInt();
				if (tmp < 1 || tmp > 9) {
					scanner.close();
					System.exit(illegalArgument());
				}
				grades = tmp < grades ? tmp : grades;
			}
			scanner.nextLine();
			for (int i = 0; i < weekOrder - 1; i++)
				grades *= 10;
			weeksStorage += grades;
		}

		long tmp = weeksStorage;

		for (int i = 1; tmp > 0; tmp /= 10, i++)
			showStat((int) (tmp % 10), i);
		
		scanner.close();
	}

	private static void showStat(int count, int week) {
		System.out.print("Week " + week + (week < 10 ? "  " : " "));
		for (int i = 0; i < count; i++)
			System.out.print("=");
		System.out.println(">");
	}

	private static int illegalArgument() {
		System.err.println("Illegal Argument");
		return -1;
	}
}
