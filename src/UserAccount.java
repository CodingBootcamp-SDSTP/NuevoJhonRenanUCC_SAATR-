public class UserAccount
{
	public static class Builder
	{
		private int id;
		private String username;
		private String email;
		private String password;
		private String userType;

		public Builder userType(String ut) {
			userType=ut;
			return this;
		}

		public Builder id(int i) {
			id=i;
			return this;
		}

		public Builder username(String u) {
			username=u;
			return this;
		}

		public Builder email(String e) {
			email=e;
			return this;
		}

		public Builder password(String pw) {
			password=pw;
			return this;
		}

		public UserAccount build() {
			return new UserAccount(this);
		}

		public String getUserType() {
			return userType;
		}

		public int getId() {
			return id;
		}

		public String getUsername() {
			return username;
		}

		public String getEmail() {
			return email;
		}

		public String getPassword() {
			return password;
		}
	}

	final int ID;
	final String USERNAME;
	final String EMAIL;
	final String PASSWORD;
	final String USERTYPE;
	
	public UserAccount(Builder builder) {
		ID=builder.getId();
		USERNAME=builder.getUsername();
		PASSWORD=builder.getPassword();
		USERTYPE=builder.getUserType();
		EMAIL=builder.getEmail();
	}
}
