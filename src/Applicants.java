import java.time.LocalDate;

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
		String appDateId;
		int id;
		LocalDate natal;

		public Builder natal(LocalDate natal) {
			this.natal=natal;
			return(this);
		}

		public Builder fname(String fn) {
			fName=fn;
			return(this);
		}

		public Builder mname(String mn) {
			mName=mn;
			return(this);
		}

		public Builder id(int id) {
			this.id=id;
			return(this);
		}

		public Builder lname(String ln) {
			lName=ln;
			return(this);
		}

		public Builder address(String add) {
			address=add;
			return(this);
		}

		public Builder age(int age) {
			this.age=age;
			return(this);
		}

		public Builder sex(String sex) {
			this.sex=sex;
			return(this);
		}

		public Builder mobile(String mobile) {
			mobileNumber=mobile;
			return(this);
		}

		public Builder email(String email) {
			emailAdd=email;
			return(this);
		}

		public Builder username(String user) {
			userName=user;
			return(this);
		}

		public Builder appDateId(String dateid) {
			appDateId=dateid;
			return(this);
		}

		public Builder status(String stat) {
			status=stat;
			return(this);
		}

		public Applicants build() {
			return new Applicants(this);
		}

		public LocalDate getNatal() {
			return natal;
		}

		public String getFname() {
			return fName;
		}

		public String getAppDateId() {
			return appDateId;
		}

		public int getId() {
			return id;
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
	final int ID;
	final String APPDATEID;
	final LocalDate NATAL;

	public Applicants(Builder b) {
		ID=b.getId();
		FNAME=b.getFname();
		MNAME=b.getMname();
		LNAME=b.getLname();
		ADDRESS=b.getAddress();
		AGE=b.getAge();
		MOBILENUMBER=b.getMobile();
		EMAILADD=b.getEmail();
		USERNAME=b.getUsername();
		STATUS=b.getStatus();
		SEX=b.getSex();
		APPDATEID=b.getAppDateId();
		NATAL=b.getNatal();
	}
}
