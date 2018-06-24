import java.time.LocalDate;

public class Appointment
{
	public static class Builder
	{
		int appointmentId;
		int id;
		LocalDate date;
		String applicantId;
		String name;
		String applicantAddress;
		String appointmentStatus;
		String applicantEmail;
		String applicantSex;
		String applicantPhone;

		public Builder applicantName(String name) {
			this.name=name;
			return(this);
		}

		public Builder appointmentId(int id) {
			appointmentId=id;
			return(this);
		}

		public Builder id(int i) {
			id=i;
			return(this);
		}

		public Builder date(LocalDate ld) {
			date=ld;
			return(this);
		}

		public Builder applicantId(String ai) {
			applicantId=ai;
			return(this);
		}

		public Builder applicantAddress(String aa) {
			applicantAddress=aa;
			return(this);
		}

		public Builder appointmentStatus(String as) {
			appointmentStatus=as;
			return(this);
		}

		public Builder applicantEmail(String e) {
			applicantEmail=e;
			return(this);
		}

		public Builder applicantSex(String sex) {
			applicantSex=sex;
			return(this);
		}

		public Builder applicantPhone(String ph) {
			applicantPhone=ph;
			return(this);
		}

		public int getAppointmentId() {
			return appointmentId;
		}

		public String getApplicantName() {
			return name;
		}

		public int getId() {
			return id;
		}

		public LocalDate getDate() {
			return date;
		}

		public String getApplicantId() {
			return applicantId;
		}

		public String getApplicantAddress() {
			return applicantAddress;
		}

		public String getAppointmentStatus() {
			return appointmentStatus;
		}

		public String getApplicantEmail() {
			return applicantEmail;
		}

		public String getApplicantSex() {
			return applicantSex;
		}

		public String getApplicantPhone() {
			return applicantPhone;
		}

		public Appointment build() {
			return new Appointment(this);
		}
	}

	final int ID;
	final int APPOINTMENTID;
	final LocalDate DATE;
	final String APPLICANTID;
	final String NAME;
	final String ADDRESS;
	final String STATUS;
	final String EMAIL;
	final String SEX;
	final String MOBILENUMBER;

	public Appointment(Builder b) {
		ID=b.getId();
		DATE=b.getDate();
		APPLICANTID=b.getApplicantId();
		APPOINTMENTID=b.getAppointmentId();
		NAME=b.getApplicantName();
		ADDRESS=b.getApplicantAddress();
		STATUS=b.getAppointmentStatus();
		EMAIL=b.getApplicantEmail();
		SEX=b.getApplicantSex();
		MOBILENUMBER=b.getApplicantPhone();
	}
}
