import java.sql.*;
import java.util.Date;

public class AppDatabase
{
	public static AppDatabase _instance;

	public static AppDatabase instance() {
		if(_instance==null) {
			_instance=new AppDatabase();
		}
		return _instance;
	}

	Connection conn;

	public AppDatabase() {
		try {
			Class.forName("org.sqlite.JDBC");
			conn=DriverManager.getConnection("jdbc:sqlite:projectappdb");
			Statement stmt=conn.createStatement();
			stmt.executeUpdate("DROP TABLE applicants_tbl");
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS applicants_tbl"+
			" (id INTEGER PRIMARY KEY,fname VARCHAR(20) NOT NULL,"+
			" mname VARCHAR(20) NOT NULL,lname VARCHAR(20) NOT NULL,address VARCHAR(30) NOT NULL,"+
			" age INTEGER,sex VARCHAR(10) NOT NULL,mobilenumber VARCHAR(11) NOT NULL,"+
			" emailadd VARCHAR(30),username VARCHAR(20),app_sched DATE,status VARCHAR(15))");
			stmt.executeUpdate("DROP TABLE examinees_tbl");
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS examinees_tbl"+
			"(examineeid INTEGER PRIMARY KEY,applicantid INTEGER NOT NULL,"+
			"fname VARCHAR(20) not null,mname VARCHAR(20) not null,lname "+
			"VARCHAR(20) not null,remarks VARCHAR(15),roomnumber "+
			"VARCHAR(6),examdate DATE);");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean addApplicant(String fname,String mname,String lname,String address,int age,String sex,String mobilenumber,String emailadd,String username,String status) {
		boolean added=false;
		try {
			PreparedStatement ps=conn.prepareStatement("INSERT INTO applicants_tbl (fname,mname,lname,address,age,sex,mobilenumber,emailadd,username,status) values(?,?,?,?,?,?,?,?,?,?);");
			System.out.println("Tables created");
			ps.setString(1,fname);
			ps.setString(2,mname);
			ps.setString(3,lname);
			ps.setString(4,address);
			ps.setInt(5,age);
			ps.setString(6,sex);
			ps.setString(7,mobilenumber);
			ps.setString(8,emailadd);
			ps.setString(9,username);
			// ps.setDate(10,NOW());
			ps.setString(10,status);
			ps.executeUpdate();
			added=true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return added;
	}
}
