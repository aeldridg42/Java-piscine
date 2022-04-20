public class Program {
	public static void main(String[] args) throws UserNotFoundException {
		TransactionsService service = new TransactionsService();

		service.addUser(new User("Jimmy", 3000));
		service.addUser(new User("Kim", 2700));
		service.addUser(new User("Mike", 4900));
		service.addUser(new User("Nacho", 2000));

		System.out.println("Jimmy before: " + service.retrieveBalance(1));
		System.out.println("Kim before: " + service.retrieveBalance(2));
		service.transfer(1, 2, 200);
		System.out.println("Jimmy after: " + service.retrieveBalance(1));
		System.out.println("Kim after: " + service.retrieveBalance(2));
		System.out.println("\n");
		System.out.println("Mike before: " + service.retrieveBalance(3));
		System.out.println("Nacho before: " + service.retrieveBalance(4));
		service.transfer(3, 4, -100);
		System.out.println("Mike after: " + service.retrieveBalance(3));
		System.out.println("Nacho after: " + service.retrieveBalance(4));

		System.out.println("\n");

		service.getUsersTransactions(1)[0].printInfo();
		service.getUsersTransactions(2)[0].printInfo();
		System.out.println("\n");
		System.out.println(service.getUsersTransactions(1).length);
		service.removeUsersTransaction(1, service.getUsersTransactions(1)[0].getIdentifier());
		System.out.println(service.getUsersTransactions(1).length);

	}
}
