class UserNotFoundException extends Exception {
	public String toString() {
		return "Wrong user id";
	}
}

public interface UsersList {
	public void addUser(User user);

	public User retrieveUserByID(Integer id) throws UserNotFoundException;

	public User retrieveUserByIndex(Integer index);

	public Integer retrieveNumberOfUsers();
}
