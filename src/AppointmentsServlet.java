import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.lang.StringBuilder;
import java.util.ArrayList;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AppointmentsServlet extends HttpServlet
{
	AppDatabase ad;
	ArrayList<Appointment> appointments;

	public void init() throws ServletException {
		ad=AppDatabase.instance();
		appointments=ad.getAppointments();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out=res.getWriter();
		res.setContentType("application/json");
		StringBuilder sb=new StringBuilder("[");
		out.println(generateJSON(sb));
	}

	public String generateJSON(StringBuilder sb) {
		for(int i=0; i<appointments.size(); i++) {
			sb.append("{\"id\":\""+appointments.get(i).APPOINTMENTID+"\",");
			sb.append("\"applicantid\":\""+appointments.get(i).APPLICANTID+"\",");
			sb.append("\"name\":\""+appointments.get(i).NAME+"\",");
			sb.append("\"address\":\""+appointments.get(i).ADDRESS+"\",");
			sb.append("\"status\":\""+appointments.get(i).STATUS+"\",");
			sb.append("\"emailadd\":\""+appointments.get(i).EMAIL+"\",");
			sb.append("\"sex\":\""+appointments.get(i).SEX+"\",");
			sb.append("\"mobilenumber\":\""+appointments.get(i).MOBILENUMBER+"\",");
			sb.append("\"date\":\""+appointments.get(i).DATE+"\"");
			sb.append(i==appointments.size()-1?"}":"},");
		}
		sb.append("]");
		return sb.toString();
	}

	public void destroy() {
		ad=null;
		appointments.clear();
	}
}
