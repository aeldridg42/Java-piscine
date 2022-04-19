import java.util.UUID;

public class Program {
	public static void main(String[] args) {
		User jimmy = new User("Jimmy", 3000);
		User kim = new User("Kim", 2700);
		User mike = new User("Mike", 4900);
		User nacho = new User("Nacho", -120);

		Transaction transaction1 = new Transaction(kim, jimmy, -2000);
		Transaction transaction2 = new Transaction(kim, jimmy, 1000);
		Transaction transaction3 = new Transaction(mike, nacho, 150);
		Transaction transaction4 = new Transaction(mike, nacho, -100);
		Transaction transaction5 = new Transaction(kim, mike, -240);
		Transaction transaction6 = new Transaction(mike, jimmy, 1000);

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

		try {
			list.remove(UUID.randomUUID());
		} catch (RuntimeException e) {
			System.err.println(e);
		}


	}

}
