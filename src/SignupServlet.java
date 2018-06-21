import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.lang.StringBuilder;
import java.util.ArrayList;
import java.sql.*;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.time.LocalDate;

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
		System.out.println(fName+mName+lName+address);
		Applicants newApplicant=new Applicants.Builder().appDateId("1").fname(fName).mname(mName).lname(lName).address(address).natal(LocalDate.of(2019,2,1)).build();
		ad.addApplicant(newApplicant);
	}

	public void destory() {
		ac=null;
		ad=null;
	}
}
