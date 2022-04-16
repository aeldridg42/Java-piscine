import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {
	private Node head;
	private Node tail;
	private Integer listSize;


	public TransactionsLinkedList() {
		head = null;
		tail = null;
		listSize = 0;
	}

	@Override
	public void addTransaction(Transaction transaction) {
		if (head == null) {
			head = new Node(transaction);
			tail = head;
		}
		else {
			Node tmp = new Node(transaction);
			tmp.previous = tail;
			tail.next = tmp;
			tail = tmp;
		}
		listSize++;
	}

	@Override
	public void remove(UUID uuid) throws TransactionNotFoundException {
		if (listSize > 0) {
			Node tmp = head;
			if (tmp.data.getIdentifier().equals(uuid)) {
				head = head.next;
				if (head != null)
					head.previous = null;
				listSize--;
				if (listSize == 0)
					tail = null;
				return;
			} else {
				while (tmp.next != null) {
					if (tmp.next.data.getIdentifier().equals(uuid)) {
						tmp.next = tmp.next.next;
						if (tmp.next != null)
							tmp.next.previous = tmp;
						else
							this.tail = tmp;
						listSize--;
						return;
					}
					tmp = tmp.next;
				}
			}
		}
		throw new TransactionNotFoundException();
	}

	@Override
	public Transaction[] toArray() {
		Transaction[] array = new Transaction[listSize];
		Node tmp = head;
		for (int i = 0; i < listSize; i++) {
			array[i] = tmp.data;
			tmp = tmp.next;
		}
		return array;
	}

	private class Node {
		Node next;
		Node previous;
		Transaction data;

		public Node(Transaction data) {
			this.data = data;
			this.previous = null;
			this.next = null;
		}
	}
}
