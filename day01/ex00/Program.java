public class Program {
	public static void main(String[] args) {
		User kim = new User("Kim", 2700);
		User jimmy = new User("Jimmy", 3000);

		kim.printInfo();
		jimmy.printInfo();

		Transaction transaction1 = new Transaction(kim, jimmy, Category.OUTCOME, 2000);

		kim.printInfo();
		jimmy.printInfo();
		transaction1.printInfo();
		kim.printInfo();
		jimmy.printInfo();
		Transaction transaction2 = new Transaction(kim, jimmy, Category.INCOME, -10000);
		transaction2.printInfo();
		Transaction transaction3 = new Transaction(kim, jimmy, Category.INCOME, -1100);
		transaction3.printInfo();
		kim.printInfo();
		jimmy.printInfo();
	}
}
