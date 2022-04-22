import java.util.UUID;

public class Program {
	public static void main(String[] args) {
		User jimmy = new User("Jimmy", 3000);
		User kim = new User("Kim", 2700);
		User mike = new User("Mike", 4900);
		User nacho = new User("Nacho", -120);

		Transaction transaction1 = new Transaction(kim, jimmy, Category.INCOME , -2000);
		Transaction transaction2 = new Transaction(kim, jimmy, Category.OUTCOME, 1000);
		Transaction transaction3 = new Transaction(mike, nacho, Category.OUTCOME, 150);
		Transaction transaction4 = new Transaction(mike, nacho, Category.INCOME, -100);
		Transaction transaction5 = new Transaction(kim, mike, Category.INCOME, -240);
		Transaction transaction6 = new Transaction(mike, jimmy, Category.OUTCOME, 1000);

		TransactionsLinkedList list = new TransactionsLinkedList();
		list.addTransaction(transaction1);
		list.addTransaction(transaction2);
		list.addTransaction(transaction3);
		list.addTransaction(transaction4);
		list.addTransaction(transaction5);
		list.addTransaction(transaction6);

		Transaction[] array = list.toArray();
		for (int i = 0; i < array.length; i++)
			array[i].printInfo();
		System.out.println("\n\n");

		list.remove(transaction1.getIdentifier());

		array = list.toArray();
		for (int i = 0; i < array.length; i++)
			array[i].printInfo();
		System.out.println("\n\n");

		list.remove(transaction4.getIdentifier());

		array = list.toArray();
		for (int i = 0; i < array.length; i++)
			array[i].printInfo();
		System.out.println("\n\n");

	   list.remove(transaction6.getIdentifier());

		array = list.toArray();
		for (int i = 0; i < array.length; i++)
			array[i].printInfo();
		System.out.println("\n\n");


		if (false)
			list.remove(UUID.randomUUID());
	}

}
