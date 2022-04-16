import java.util.UUID;

class TransactionNotFoundException extends RuntimeException {
	public String toString() {
		return "Wrong transaction UUID";
	}
}

public interface TransactionsListinterface {
	public void addTransaction(Transaction transaction);

	public void remove(UUID uuid) throws TransactionNotFoundException;

	public Transaction[] toArray();
}
