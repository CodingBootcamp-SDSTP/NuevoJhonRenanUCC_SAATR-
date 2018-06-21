import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.lang.StringBuilder;
import java.util.ArrayList;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AppointmentDatesServlet extends HttpServlet
{
	AppDatabase ad;
	ArrayList<AppointmentDate> dates;

	public void init() throws ServletException {
		ad=AppDatabase.instance();
		dates=ad.getAppDates();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out=res.getWriter();
		res.setContentType("application/json");
		StringBuilder sb=new StringBuilder("[");
		out.println(generateJSON(sb));
		// out.println("<h1>haelloworld</h1>");
	}

	public String generateJSON(StringBuilder sb) {
		for(int i=0; i<dates.size(); i++) {
			sb.append("{");
			sb.append("\"id\":\""+dates.get(i).ID+"\",");
			sb.append("\"date\":\""+dates.get(i).DATE+"\"");
			sb.append(i==dates.size()-1?"}":"},");
		}
		sb.append("]");
		return sb.toString();
	}

	public void destroy() {
		ad=null;
		dates.clear();
	}
}
