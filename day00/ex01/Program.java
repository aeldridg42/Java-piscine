import java.util.Scanner;

public class Program {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            scanner.close();
            System.exit(illegalArgument());
        }
        int number = scanner.nextInt();
		int operations = 2;
		boolean result = true;

		if (number < 2) {
            scanner.close();
			System.exit(illegalArgument());
        }

		for (; operations * operations <= number || number < 4; operations++) {
			if (number % operations == 0) {
				result = false;
				break;
			}
		}

		System.out.println(result + " " + --operations);
        scanner.close();
	}

	private static int illegalArgument() {
		System.err.println("Illegal Argument");
		return -1;
	}
}
