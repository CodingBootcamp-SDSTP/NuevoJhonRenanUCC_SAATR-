import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.lang.StringBuilder;
import java.util.ArrayList;
import java.sql.*;

public class LoginServlet extends HttpServlet
{
	AppDatabase ad;
	UserCollection uc;
	ArrayList<UserAccount> users;

	public void init() throws ServletException {
		ad=AppDatabase.instance();
		uc=ad.getUsers();
		users=uc.getAllUsers();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out=res.getWriter();
		res.setContentType("application/json");
		StringBuilder sb=new StringBuilder("[");
		out.println(generateJSON(sb));
	}

	public String generateJSON(StringBuilder sb) {
		for(int i=0; i<users.size(); i++) {
			sb.append("{");
			sb.append("\"id\":\""+users.get(i).ID+"\",");
			System.out.println(users.get(i).USERNAME);
			sb.append("\"username\":\""+users.get(i).USERNAME+"\",");
			sb.append("\"usertype\":\""+users.get(i).USERTYPE+"\",");
			sb.append("\"email\":\""+users.get(i).EMAIL+"\",");
			sb.append("\"password\":\""+users.get(i).PASSWORD+"\"");
			sb.append(i==users.size()-1?"}":"},");
		}
		sb.append("]");
		return sb.toString();
	}

	public void destroy() {
		ad=null;
		users.clear();
	}
}
