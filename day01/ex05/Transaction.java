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

	public Transaction(User sender, User recipient, Category category, Integer amount) {
		identifier = UUID.randomUUID();
		this.recipient = recipient;
		this.sender = sender;
		this.category = category;
		this.amount = setAmount(amount);
	}

	public Transaction(Transaction transaction) {
		identifier = transaction.getIdentifier();
		this.recipient = transaction.recipient;
		this.sender = transaction.sender;
		this.category = Category.INCOME;
		this.amount = transaction.amount * -1;
	}
	private Integer setAmount(Integer amount) {
		if (category == Category.OUTCOME)
			return amount <= 0 ? amount : 0;
		return amount >= 0 ? amount : 0;
	}

	public UUID getIdentifier() { return identifier; }

	public User getRecipient() { return recipient; }

	public User getSender() { return sender; }

	public Category getCategory() { return category; }

	public Integer getAmount() { return amount; }

	public void printInfo() {
		if (category == Category.INCOME)
			System.out.printf("From %s(id = %d) %d with id = %s\n", sender.getName(), sender.getId(), amount, identifier.toString());
		else
			System.out.printf("To %s(id = %d) %d with id = %s\n", recipient.getName(), recipient.getId(), amount, identifier.toString());
	}
}
