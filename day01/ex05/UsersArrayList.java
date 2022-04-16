public class UsersArrayList implements UsersList {
	private User[] users;
	private Integer usersCount;

	public UsersArrayList() {
		users = new User[10];
		usersCount = 0;
	}

	@Override
	public void addUser(User user) {
		if (users.length == usersCount) {
			User[] tmp = users;
			users = new User[(int) (tmp.length * 1.5)];
			for (int i = 0; i < tmp.length; i++)
				users[i] = tmp[i];
		}
		users[usersCount++] = user;
	}

	@Override
	public User retrieveUserByID(Integer id) throws UserNotFoundException {
		for (int i = 0; i < usersCount; i++) {
			if (users[i].getId().equals(id))
				return users[i];
		}
		throw new UserNotFoundException();
	}

	@Override
	public User retrieveUserByIndex(Integer index) {
		if (index >= 0 && index < usersCount)
			return users[index];
		return null;
	}

	@Override
	public Integer retrieveNumberOfUsers() {
		return usersCount;
	}
}
