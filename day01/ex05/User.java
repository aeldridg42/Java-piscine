import java.util.UUID;

public class User {
	private final Integer id;
	private String name;
	private Integer balance;
	private TransactionsList transactions;

	public User(String name, Integer balance) {
		id = UserIdsGenerator.getInstance().generateId();
		this.name = name;
		this.balance = balance >= 0 ? balance : 0;
		transactions = new TransactionsLinkedList();
	}

	public String getName() { return this.name; }

	public Integer getBalance() { return this.balance; }

	public Integer getId() { return this.id; }

	public void addTransaction(Transaction transaction) {
		transactions.addTransaction(transaction);
	}

	public void removeTransaction(UUID uuid) throws TransactionNotFoundException {
		transactions.remove(uuid);
	}

	public boolean isTransactionInList(UUID uuid) {
		Transaction[] array = transactions.toArray();
		for (Transaction t : array) {
			if (t.getIdentifier().equals(uuid))
				return true;
		}
		return false;
	}

	public Transaction getTransaction(UUID uuid) {
		Transaction[] array = transactions.toArray();
		for (Transaction t : array) {
			if (t.getIdentifier().equals(uuid))
				return t;
		}
		throw new TransactionNotFoundException();
	}

	public Transaction[] getTransactions() {
		return transactions.toArray();
	}

	public void changeBalance(Integer amount) { this.balance += amount; }

	public void printInfo() {
		System.out.printf("username: %s, id: %d, balance: %d\n", name, id, balance);
	}
}
