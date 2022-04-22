import java.util.UUID;

class IllegalTransactionException extends RuntimeException {
	public String toString() {
		return "illegal transaction attempt";
	}
}

public class TransactionsService {
	private UsersArrayList users;
	private TransactionsLinkedList transactions;

	public TransactionsService() {
		users = new UsersArrayList();
		transactions = new TransactionsLinkedList();
	}

	public void addUser(User user) {
		users.addUser(user);
	}

	public Integer retrieveBalance(Integer id) throws UserNotFoundException {
		return users.retrieveUserByID(id).getBalance();
	}

	public void transfer(Integer id1, Integer id2, Integer amount) throws IllegalTransactionException, UserNotFoundException {
		if (id1 == id2 || amount < 0)
			throw new IllegalTransactionException();
		User sender = users.retrieveUserByID(id1);
		User recipient = users.retrieveUserByID(id2);
		Transaction tr1 = new Transaction(sender, recipient, Category.OUTCOME, amount);
		Transaction tr2 = new Transaction(tr1);
		transactions.addTransaction(tr1);
		if (sender.getBalance() - amount < 0)
			throw new IllegalTransactionException();
		sender.changeBalance(-amount);
		recipient.changeBalance(+amount);
		sender.addTransaction(tr1);
		recipient.addTransaction(tr2);
	}

	public Transaction[] getUsersTransactions(Integer id) throws UserNotFoundException {
		return users.retrieveUserByID(id).getTransactions();
	}

	public void removeUsersTransaction(Integer userId, UUID trId) throws UserNotFoundException, TransactionNotFoundException {
		users.retrieveUserByID(userId).removeTransaction(trId);
	}

	public Transaction[] unpairedTransactionsArray() {
		Transaction[] arrayT = transactions.toArray();
		TransactionsLinkedList result = new TransactionsLinkedList();
		for (Transaction t : arrayT) {
			User u1 = t.getRecipient();
			User u2 = t.getSender();
			if (!u1.isTransactionInList(t.getIdentifier()) || !u2.isTransactionInList(t.getIdentifier()))
				result.addTransaction(t);
		}
		return result.toArray();
	}

}
