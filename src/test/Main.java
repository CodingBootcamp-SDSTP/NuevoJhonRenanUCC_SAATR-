import java.sql.*;
import java.time.Month;
import java.time.LocalDate;
import java.util.ArrayList;

public class Main
{
	public static void main(String[] args) {
		AppDatabase ad=AppDatabase.instance();
		// LocalDate date=LocalDate.of(2018,12,2);
		// Date date=new Date();
		// Date date=new java.util.Date().getTime();
		// Applicants applicant=new Applicants.Builder().setFname("Renan").setMname("Cabug").setLname("Nuevo").setAddress("Caloocan").setAge(23).setSex("Male").setMobile("09369680108").setEmail("renan.nuevo@gmail.com").setUsername("heisenberg").setAppSched(date).setStatus("on-going").build();
		// ad.addApplicant(applicant.FNAME,applicant.MNAME,applicant.LNAME,applicant.ADDRESS,applicant.AGE,applicant.SEX,applicant.MOBILENUMBER,applicant.EMAILADD,applicant.USERNAME,applicant.APPSCHED,applicant.STATUS);
		// ad.getAllApplicants();
		Appointment app=new Appointment();
		ArrayList<LocalDate> date=app.getDates();
		// for(int i=0; i<date.size(); i++) {
		System.out.println(date.size());
		// }
	}
}