public class Program {
	public static void main(String[] args) {
		UsersArrayList users = new UsersArrayList();
		for (int i = 0; i < 17; i++)
			users.addUser(new User("user" + (i + 1), i / 2 == 0 ? (i + 1) * 100 : i * 75));

		System.out.println("Users count: " + users.retrieveNumberOfUsers());

		try {
			users.retrieveUserByID(11).printInfo();
		}
		catch (UserNotFoundException e) {
			System.err.println(e.toString());
		}

		try {
			users.retrieveUserByID(19).printInfo();
		}
		catch (UserNotFoundException e) {
			System.err.println(e.toString());
		}

		if (users.retrieveUserByIndex(4) != null)
			users.retrieveUserByIndex(4).printInfo();

		if (users.retrieveUserByIndex(20) != null)
			users.retrieveUserByIndex(20).printInfo();

	}
}
