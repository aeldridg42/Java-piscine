import java.util.Scanner;

public class Program {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int coffeeCounter = 0;
		int tmp;
		while ((tmp = scanner.nextInt()) != 42)
			if (isPrime(tmp))
				coffeeCounter++;
		System.out.println("Count of coffee-request – " + coffeeCounter);
		scanner.close();
	}

	private static boolean isPrime(int input) {
		int number = 0;
		input *= input < 0 ? -1 : 1;
		while (input > 0) {
			number += input % 10;
			input /= 10;
		}
		for (int i = 2; i * i <= number; i++)
			if (number % i == 0)
				return false;
		return true;
	}
}
