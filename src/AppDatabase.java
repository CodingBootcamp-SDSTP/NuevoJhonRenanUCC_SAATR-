import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class AppDatabase
{
	private static AppDatabase _instance=null;
	private ApplicantsCollection applicants;
	AppointmentList app;

	public static AppDatabase instance() {
		if(_instance==null) {
			_instance=new AppDatabase();
		}
		return _instance;
	}

	Connection conn;

	public AppDatabase() {
		applicants=new ApplicantsCollection();
		app=new AppointmentList();
		try {
			Class.forName("org.sqlite.JDBC");
			conn=DriverManager.getConnection("jdbc:sqlite:projectappdb");
			Statement stmt=conn.createStatement();
			stmt.executeUpdate("DROP TABLE applicants_tbl");
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS applicants_tbl"+
			" (id INTEGER PRIMARY KEY,fname VARCHAR(20) NOT NULL,"+
			" mname VARCHAR(20) NOT NULL,lname VARCHAR(20) NOT NULL,address VARCHAR(30) NOT NULL,"+
			" age VARCHAR(3),sex VARCHAR(10) NOT NULL,mobilenumber VARCHAR(11) NOT NULL,"+
			" emailadd VARCHAR(30),username VARCHAR(20),app_sched DATE not null,status VARCHAR(15))");
			stmt.executeUpdate("DROP TABLE IF EXISTS examinees_tbl");
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS examinees_tbl"+
			"(examineeid INTEGER PRIMARY KEY,applicantid INTEGER,"+
			" remarks VARCHAR(15),roomnumber VARCHAR(6),examdate DATE);");
			stmt.executeUpdate("DROP TABLE IF EXISTS rooms_tbl");
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS rooms_tbl(roomid"+
			" INTEGER PRIMARY KEY,buildingname VARCHAR(20),room_no VARCHAR(10) not null,"+
			"assigneeid VARCHAR(10));");
			stmt.executeUpdate("DROP TABLE IF EXISTS proctors_tbl");
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS proctors_tbl(id INTEGER PRIMARY KEY,"+
			"name VARCHAR(40));");
			stmt.executeUpdate("DROP TABLE IF EXISTS app_schedule_dates_tbl");
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS app_schedule_dates_tbl(id INTEGER PRIMARY KEY,"+
			"date Date)");
			stmt.executeUpdate("DROP TABLE IF EXISTS appointments_tbl");
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS appointments_tbl(id INTEGER PRIMARY KEY,"+
			"date Date, applicantid VARCHAR(10))");
			LocalDate date=LocalDate.of(2018,12,2);
			LocalDate date1=LocalDate.of(2018,6,20);
			LocalDate date2=LocalDate.of(2018,6,21);
			LocalDate date3=LocalDate.of(2018,6,22);
			addAppSchedDate(date1);
			addAppSchedDate(date2);
			addAppSchedDate(date3);
			addApplicant("Renan","Cabug","Nuevo","Caloocan",23,"Male","0987678923","renan.nuevo@gmail.com","heisenberg",date,"ongoing");
			addApplicant("Dudung","Dudung","Dudung","Gen-San",27,"Male","09223789342","did.nuevo@gmail.com","heisenberg",date,"ongoing");
			getDataFromDB(conn);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Appointment> getAppointments() {
		return app.getAppointments();
	}

	public ApplicantsCollection getApplicants() {
		return applicants;
	}

	// public ArrayList<Appointment> getAppointments() {
	// 	ArrayList<Appointment> appointments=new ArrayList<Appointment>();
	// 	Statement stmt=null;
	// 	ResultSet rs=null;
	// 	try{
	// 		stmt=conn.createStatement();
	// 		rs=stmt.executeQuery("SELECT * FROM applicants_tbl as applicantes inner join app_schedule_date_tbl as dates on applicantes.id=dates.id;");
	// 		while(rs.next()) {
	// 			rs.setString
	// 		}
	// 	}
	// }
	
	public boolean addApplicant(String fname,String mname,String lname,String address,int age,String sex,String mobilenumber,String emailadd,String username,LocalDate date,String status) {
		boolean added=false;
		try {
			PreparedStatement ps=conn.prepareStatement("INSERT INTO applicants_tbl (fname,mname,lname,address,age,sex,mobilenumber,emailadd,username,app_sched,status) values(?,?,?,?,?,?,?,?,?,?,?);");
			ps.setString(1,fname);
			ps.setString(2,mname);
			ps.setString(3,lname);
			ps.setString(4,address);
			ps.setInt(5,age);
			ps.setString(6,sex);
			ps.setString(7,mobilenumber);
			ps.setString(8,emailadd);
			ps.setString(9,username);
			ps.setString(10,date.toString());
			ps.setString(11,status);
			ps.executeUpdate();
			added=true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return added;
	}

	public boolean addAppSchedDate(LocalDate date) {
		boolean added=false;
		try {
			PreparedStatement ps=conn.prepareStatement("INSERT INTO app_schedule_dates_tbl(date) VALUES(?);");
			ps.setString(1,date.toString());
			ps.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return added;
	}

	public void getAllApplicants() {
		Statement stmt=null;
		ResultSet rs=null;
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery("SELECT * FROM applicants_tbl;");
			System.out.println("___________________________________________________________________________________________\n"+String.format("%-5s","ID")+String.format("%-4s","|")+String.format("%-30s","NAME")+String.format("%-15s","AGE")+String.format("%-15s","Respondents")+"\n-------------------------------------------------------------------------------------------");
			while(rs.next()) {
				System.out.println(String.format("%-5s",(rs.getInt("id")))+String.format("%-4s","|")+String.format("%-30s",rs.getString("fname")+" "+rs.getString("mname")+". "+rs.getString("lname"))+String.format("%-10s",rs.getInt("age")));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void createObject(String[] data) {
		int len=data.length-1;
		String user = data[len];
		String status=data[len-1];
		if("applicant".equals(user)) {
		String[] date=data[10].split("-");
		LocalDate ld=LocalDate.of(Integer.parseInt(date[0]),Integer.parseInt(date[1]),Integer.parseInt(date[2]));
		Applicants applicant=new Applicants.Builder().setId(Integer.parseInt(data[0])).setFname(data[1]).setMname(data[2]).setLname(data[3]).setAddress(data[4]).setAge(Integer.parseInt(data[5])).setSex(data[6]).setMobile(data[7]).setEmail(data[8]).setUsername(data[9]).setAppSched(ld).setStatus(status).build();
			applicants.addApplicant(applicant);
		}
		else if("appdate".equals(user)) {
			app.addAppointment(new Appointment(Integer.parseInt(data[0]),LocalDate.parse(data[1])));
		}
	}

	public boolean getDataFromDB(Connection conn) {
		Statement stmt=null;
		ResultSet rs=null;
		boolean f=false;
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery("SELECT * FROM applicants_tbl;");
			while(rs.next()) {
				String id=String.valueOf(rs.getInt("id"));
				String age=String.valueOf(rs.getInt("age"));
				String[] result={
					id,
					rs.getString("fname"),
					rs.getString("mname"),
					rs.getString("lname"),
					rs.getString("address"),
					age,
					rs.getString("sex"),
					rs.getString("mobilenumber"),
					rs.getString("emailadd"),
					rs.getString("username"),
					rs.getString("app_sched"),
					rs.getString("status"),
					"applicant"
				};
				// if()
				createObject(result);
			}
			rs=stmt.executeQuery("SELECT * FROM app_schedule_dates_tbl;");
			while(rs.next()) {
				String[] dates={
					String.valueOf(rs.getInt("id")),
					rs.getString("date"),
					"appdate"
				};
				createObject(dates);
			}
			f=true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try { if(stmt!=null) {stmt=null;}} catch(Exception e){}
			try { if(rs!=null) {rs=null;}} catch(Exception e){}
		}
		return(f);
	}
}
