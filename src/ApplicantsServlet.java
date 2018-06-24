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
		res.setContentType("application/json");
		StringBuilder sb=new StringBuilder("[");
		out.println(generateJSON(sb));
	}

	public String generateJSON(StringBuilder sb) {
		for(int i=0; i<applicants.size(); i++) {
			sb.append("{\"id\":\""+applicants.get(i).ID+"\",");
			sb.append("\"firstname\":\""+applicants.get(i).FNAME+"\",");
			sb.append("\"middlename\":\""+applicants.get(i).MNAME+"\",");
			sb.append("\"lastname\":\""+applicants.get(i).LNAME+"\",");
			sb.append("\"address\":\""+applicants.get(i).ADDRESS+"\",");
			sb.append("\"age\":\""+applicants.get(i).AGE+"\",");
			sb.append("\"natalday\":\""+applicants.get(i).NATAL+"\",");
			sb.append("\"sex\":\""+applicants.get(i).SEX+"\",");
			sb.append("\"mobilenumber\":\""+applicants.get(i).MOBILENUMBER+"\",");
			sb.append("\"emailadd\":\""+applicants.get(i).EMAILADD+"\",");
			sb.append("\"username\":\""+applicants.get(i).USERNAME+"\",");
			sb.append("\"status\":\""+applicants.get(i).STATUS+"\",");
			sb.append("\"appdateid\":\""+applicants.get(i).APPDATEID+"\"");
			sb.append(i==applicants.size()-1?"}":"},");
		}
		sb.append("]");
		return sb.toString();
	}

	public void destroy() {
		ad=null;
		ac=null;
		applicants.clear();
	}
}
