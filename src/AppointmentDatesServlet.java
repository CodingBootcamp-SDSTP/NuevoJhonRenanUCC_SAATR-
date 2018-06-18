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
	ArrayList<Appointment> dates;

	public void init() throws ServletException {
		ad=AppDatabase.instance();
		dates=ad.getAppointments();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out=res.getWriter();
		res.setContentType("text/xml");
		StringBuilder sb=new StringBuilder("<dates>");
		out.println(generateXML(sb));
		// out.println("<h1>haelloworld</h1>");
	}

	public String generateXML(StringBuilder sb) {
		for(int i=0; i<dates.size(); i++) {
			sb.append("<id>"+dates.get(i).getId()+"</id>");
			sb.append("<id>"+dates.get(i).getDate()+"</id>");
			
		}
		sb.append("</dates>");
		return sb.toString();
	}

	public void destroy() {
		ad=null;
		dates.clear();
	}
}
