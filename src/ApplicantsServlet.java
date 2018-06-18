import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.lang.StringBuilder;
import java.util.ArrayList;
import java.sql.*;

public class ApplicantsServlet extends HttpServlet
{
	AppDatabase ad;
	ApplicantsCollection ac;
	ArrayList<Applicants> applicants;

	public void init() throws ServletException {
		ad=AppDatabase.instance();
		ac=ad.getApplicants();
		applicants=ac.getAllApplicants();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out=res.getWriter();
		res.setContentType("text/xml");
		StringBuilder sb=new StringBuilder("<applicants>");
		out.println(generateXML(applicants,sb));
		// out.println("<h1>helloworld</h1>");
	}

	public String generateXML(ArrayList<Applicants> appsColl,StringBuilder sb) {
		for(Applicants appKey:appsColl) {
			sb.append("<applicant><applicantid>");
			sb.append(appKey.ID);
			sb.append("</applicantid><firstname>");
			sb.append(appKey.FNAME);
			sb.append("</firstname><middlename>");
			sb.append(appKey.MNAME);
			sb.append("</middlename><lastname>");
			sb.append(appKey.LNAME);
			sb.append("</lastname><address>");
			sb.append(appKey.ADDRESS);
			sb.append("</address><age>");
			sb.append(appKey.AGE);
			sb.append("</age><sex>");
			sb.append(appKey.SEX);
			sb.append("</sex><mobilenumber>");
			sb.append(appKey.MOBILENUMBER);
			sb.append("</mobilenumber><emailadd>");
			sb.append(appKey.EMAILADD);
			sb.append("</emailadd><username>");
			sb.append(appKey.USERNAME);
			sb.append("</username><app-sched>");
			sb.append(appKey.APPSCHED);
			sb.append("</app-sched><status>");
			sb.append(appKey.STATUS);
			sb.append("</status></applicant>");
		}
		sb.append("</applicants>");
		return sb.toString();
	}

	public void destroy() {
		ad=null;
		ac=null;
		applicants.clear();
	}
}
