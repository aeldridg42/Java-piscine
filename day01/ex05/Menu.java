import java.util.Scanner;
import java.util.UUID;

public class Menu {
	private TransactionsService service;
	private final boolean MODE;

	public Menu(boolean mode) {
		service = new TransactionsService();
		MODE = mode;
	}

	private void printMessage(boolean mode) {
		System.out.println("1. Add a user");
		System.out.println("2. View user balances");
		System.out.println("3. Perform a transfer");
		System.out.println("4. View all transactions for a specific user");
		if (mode) {
			System.out.println("5. DEV – remove a transfer by ID");
			System.out.println("6. DEV – check transfer validity");
		}
		System.out.print((mode ? 7 : 5) + ". Finish execution\n-> ");
	}

	public void launch() {
		Scanner scanner = new Scanner(System.in);
		int input;
		while (true) {
			printMessage(MODE);
			try {
				input = scanner.nextInt();
				scanner.nextLine();
				if (input < 1 || input > (MODE ? 7 : 5)) {
					System.out.println("Please enter number between 1 and " + (MODE ? 7 : 5));
					System.out.println("---------------------------------------------------------");
					continue;
				}
				switch (input) {
					case 1:
						System.out.print("Enter a user name and a balance\n-> ");
						service.addUser(new User(scanner.next(), scanner.nextInt()));
						System.out.printf("User with id = %d is added\n", service.retrieveNumberOfUsers());
						break;
					case 2:
						System.out.print("Enter a user ID\n-> ");
						int id = scanner.nextInt();
						try {
							System.out.println(service.retrieveUsername(id) + " "
									+ service.retrieveBalance(id));
						} catch (UserNotFoundException e) {
							System.out.println(e);
						}
						break;
					case 3:
						System.out.print("Enter a sender ID, a recipient ID, and a transfer amount\n-> ");
						try {
							service.transfer(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
						} catch (UserNotFoundException | IllegalTransactionException e) {
							System.out.println(e);
							System.out.println("The transfer is failed");
							break;
						}
						System.out.println("The transfer is completed");
						break;
					case 4:
						System.out.print("Enter a user ID\n-> ");
						try {
							Transaction[] transactions = service.getUsersTransactions(scanner.nextInt());
							for (Transaction t : transactions)
								t.printInfo();
						} catch (UserNotFoundException e) {
							System.out.println(e);
						}
						break;
					case 5:
						if (!MODE)
							System.exit(0);
						else {
							System.out.print("Enter a user ID and a transfer ID\n-> ");
							try {
								id = scanner.nextInt();
								UUID uuid = UUID.fromString(scanner.next());
								Transaction[] transactions = service.getUsersTransactions(id);
								for (Transaction t : transactions) {
									if (t.getIdentifier().equals(uuid)) {
										User user = t.getAmount() > 0 ? t.getSender() : t.getRecipient();
										System.out.printf("Transfer %s %s(id = %d) %d removed\n",
												t.getAmount() > 0 ? "from" : "to", user.getName(),
												user.getId(), t.getAmount());
									}
								}
								service.removeUsersTransaction(id, uuid);
							} catch (UserNotFoundException e) {
								System.out.println(e);
							}
						}
						break;
					case 6:
						System.out.println("Check results:");
						Transaction[] array = service.unpairedTransactionsArray();
						if (array.length > 0) {
							for (Transaction t : array) {
								User owner = t.getCategory() == Category.INCOME ? t.getRecipient() : t.getSender();
								User deleted = t.getCategory() == Category.OUTCOME ? t.getRecipient() : t.getSender();
								System.out.printf("%s(id == %d) has an unacknowledged transfer id = %s %s %s(id = %d) for %d\n",
										owner.getName(), owner.getId(), t.getIdentifier().toString(),
										t.getCategory() == Category.INCOME ? "from" : "to", deleted.getName(),
										deleted.getId(), t.getAmount());
							}
						} else
							System.out.println("No unacknowledged transfers found");
						break;
					case 7:
						System.exit(0);
				}
			}
			catch (Exception e) {
				System.out.println("Something went wrong. Please try again");
				break;
			}
			System.out.println("---------------------------------------------------------");
		}
	}
}
