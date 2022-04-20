import java.util.UUID;

enum Category {
	OUTCOME, INCOME;
}

public class Transaction {
	private UUID identifier;
	private User recipient;
	private User sender;
	private Category category;
	private Integer amount;
	private boolean success;

	public Transaction(User sender, User recipient, Integer amount) {
		identifier = UUID.randomUUID();
		this.recipient = recipient;
		this.sender = sender;
		category = Category.INCOME;
		if (amount > 0)
			category = Category.OUTCOME;
		this.amount = -amount;
		if (category == Category.OUTCOME) {
			if (sender.getBalance() - amount >= 0)
				success = true;
		}
		else {
			if (recipient.getBalance() + amount >= 0)
				success = true;
		}
		if (success) {
			this.sender.changeBalance(-amount);
			this.recipient.changeBalance(amount);
		}
	}

	public UUID getIdentifier() { return identifier; }

	public User getRecipient() { return recipient; }

	public User getSender() { return sender; }

	public Category getCategory() { return category; }

	public Integer getAmount() { return amount; }

	public void printInfo() {
		System.out.printf("status - %s | ", success ? "success" : "failure");
		System.out.printf("sender: %s, recipient: %s, category: %s, amount: %d, transaction id: %s\n",
				sender.getName(), recipient.getName(), category, amount, identifier.toString());
	}
}
