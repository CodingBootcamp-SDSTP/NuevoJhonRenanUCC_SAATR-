import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.lang.StringBuilder;
import java.util.ArrayList;
import java.sql.*;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.time.LocalDate;
import java.time.Month;

public class SignupServlet extends HttpServlet
{

	ApplicantsCollection ac;
	AppDatabase ad;

	public void init() throws ServletException {
		ad=AppDatabase.instance();
		ac=ad.getApplicants();
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("post hihi");
		InputStreamReader reader=new InputStreamReader(req.getInputStream());
		BufferedReader br=new BufferedReader(reader);
		String line=br.readLine();
		String[] userData=line.split("&");
		String fName=userData[0].split("=")[1];
		String mName=userData[1].split("=")[1];
		String lName=userData[2].split("=")[1];
		String address=userData[3].split("=")[1];
		String age=userData[4].split("=")[1];
		String bday=userData[5].split("=")[1];
		int year=Integer.parseInt(bday.split("-")[0]);
		int day=Integer.parseInt(bday.split("-")[2]);
		Month mo=Month.of(Integer.parseInt(bday.split("-")[1]));
		System.out.println(year);
		String email=userData[6].split("=")[1];
		String phone=userData[7].split("=")[1];
		String user=userData[8].split("=")[1];
		String appDateId=userData[9].split("=")[1];
		String sex=userData[10].split("=")[1];
		System.out.println(fName+mName+lName+address+age+email+phone+user+appDateId+sex);
		Applicants newApplicant=new Applicants.Builder().sex(sex).age(Integer.parseInt(age)).email(email).mobile(phone).username(user).appDateId(appDateId).fname(fName).mname(mName).lname(lName).address(address).natal(LocalDate.of(year,mo,day)).build();
		ad.addApplicant(newApplicant);
	}

	public void destory() {
		ac=null;
		ad=null;
	}
}
