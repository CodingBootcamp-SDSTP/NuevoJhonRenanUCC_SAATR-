import java.util.ArrayList;

public class UserCollection
{
	ArrayList<UserAccount> users;

	public UserCollection() {
		users=new ArrayList<UserAccount>();
	}

	public void addUser(UserAccount user) {
		users.add(user);
	}

	public ArrayList<UserAccount> getAllUsers() {
		return users;
	}
}
