public class Applicants
{
	public static class Builder {
		String fName;
		String mName;
		String lName;
		String address;
		int age;
		String mobileNumber;
		String emailAdd;
		String userName;
		String status;
		String sex;

		public Builder setFname(String fn) {
			fName=fn;
			return(this);
		}

		public Builder setMname(String mn) {
			fMame=mn;
			return(this);
		}

		public Builder setLname(String ln) {
			fLame=ln;
			return(this);
		}

		public Builder setAddress(String add) {
			address=add;
			return(this);
		}

		public Builder setAge(int age) {
			this.age=age;
			return(this);
		}

		public Builder setSex(String sex) {
			this.sex=sex;
			return(this);
		}

		public Builder setMobile(String mobile) {
			mobileNumber=mobile;
			return(this);
		}

		public Builder setEmail(String email) {
			emailAdd=email;
			return(this);
		}

		public Builder setUsername(String user) {
			userName=user;
			return(this);
		}

		public Builder setStatus(String stat) {
			status=stat;
			return(this);
		}

		public Applicants build() {
			return new Applicants(this);
		}

		public String getFname() {
			return fName;
		}

		public String getMname() {
			return mName;
		}

		public String getLname() {
			return lName;
		}

		public String getAddress() {
			return address;
		}

		public int getAge() {
			return age;
		}

		public String getMobile() {
			return mobileNumber;
		}

		public String getEmail() {
			return emailAdd;
		}

		public String getUsername() {
			return userName;
		}

		public String getStatus() {
			return status;
		}
		
		public String getSex() {
			return sex;
		}
	}

	final String FNAME;
	final String MNAME;
	final String LNAME;
	final String ADDRESS;
	final int AGE;
	final String MOBILENUMBER;
	final String EMAILADD;
	final String USERNAME;
	final String STATUS;
	final String SEX;

	public Applicants(Builder b) {
		FNAME=b.getFname();
		MNAME=b.getMname();
		LNAME=b.getLname();
		ADDRESS=b.getAdress();
		AGE=b.getAge();
		MOBILENUMBER=b.getMobile();
		EMAILADD=b.getEmail();
		USERNAME=b.getUsername();
		STATUS=b.getStatus();
		SEX=b.getSex();
	}

	public String toString() {
		return(FNAME+" "+MNAME+" "+LNAME+" "+ADDRESS+" "+AGE+" "+MOBILENUMBER+" "+EMAILADD+" "+USERNAME+" "+STATUS+" "+SEX);
	}
}