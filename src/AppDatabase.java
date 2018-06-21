import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class AppDatabase
{
	private static AppDatabase _instance;
	private ApplicantsCollection applicants;
	AppointmentList app;
	DatesList appDates;

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
		appDates=new DatesList();
		try {
			Class.forName("org.sqlite.JDBC");
			conn=DriverManager.getConnection("jdbc:sqlite:projectappdb");
			Statement stmt=conn.createStatement();
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS applicants_tbl"+
			" (id INTEGER PRIMARY KEY,fname VARCHAR(20) NOT NULL,"+
			" mname VARCHAR(20),lname VARCHAR(20) NOT NULL,address VARCHAR(30) NOT NULL,"+
			" age VARCHAR(3),sex VARCHAR(10),mobilenumber VARCHAR(11),"+
			" emailadd VARCHAR(30),username VARCHAR(20),status VARCHAR(15),natal Date,dateid VARCHAR(9))");
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS examinees_tbl"+
			"(examineeid INTEGER PRIMARY KEY,applicantid INTEGER,"+
			" remarks VARCHAR(15),roomnumber VARCHAR(6),examdate DATE);");
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS rooms_tbl(roomid"+
			" INTEGER PRIMARY KEY,buildingname VARCHAR(20),room_no VARCHAR(10) not null,"+
			"assigneeid VARCHAR(10));");
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS proctors_tbl(id INTEGER PRIMARY KEY,"+
			"name VARCHAR(40));");
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS app_schedule_dates_tbl(id INTEGER PRIMARY KEY,"+
			"date Date,counter INTEGER,identifier VARCHAR(15));");
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS appointments_tbl(id INTEGER PRIMARY KEY,"+
			"dateid INTEGER, applicantid VARCHAR(10))");
			LocalDate date1=LocalDate.of(2018,6,20);
			LocalDate date2=LocalDate.of(2018,6,21);
			LocalDate date3=LocalDate.of(2018,6,22);
			addAppSchedDate(date1);
			addAppSchedDate(date2);
			addAppSchedDate(date3);
			getDataFromDB(conn);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<AppointmentDate> getAppDates() {
		return appDates.getAppDates();
	}

	public ArrayList<Appointment> getAppointments() {
		return app.getAppointments();
	}

	public ApplicantsCollection getApplicants() {
		return applicants;
	}
	
	public boolean addApplicant(Applicants appl) {
		boolean added=false;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			PreparedStatement ps=conn.prepareStatement("INSERT INTO applicants_tbl (fname,mname,lname,address,age,sex,mobilenumber,emailadd,username,status,natal,dateid) values(?,?,?,?,?,?,?,?,?,?,?,?);");
			ps.setString(1,appl.FNAME);
			ps.setString(2,appl.MNAME);
			ps.setString(3,appl.LNAME);
			ps.setString(4,appl.ADDRESS);
			ps.setInt(5,appl.AGE);
			ps.setString(6,appl.SEX);
			ps.setString(7,appl.MOBILENUMBER);
			ps.setString(8,appl.EMAILADD);
			ps.setString(9,appl.USERNAME);
			ps.setString(10,appl.STATUS);
			ps.setString(11,appl.NATAL.toString());
			ps.setString(12,appl.APPDATEID);
			ps.executeUpdate();
			stmt=conn.createStatement();
			rs=stmt.executeQuery("SELECT LAST_INSERT_ROWID() as id");
			if(rs.next()) {
				String id=String.valueOf(rs.getInt("id"));
				applicants.addApplicant(new Applicants.Builder().id(rs.getInt("id")).fname(appl.FNAME).mname(appl.MNAME).lname(appl.LNAME).address(appl.ADDRESS).age(appl.AGE).sex(appl.SEX).mobile(appl.MOBILENUMBER).email(appl.EMAILADD).username(appl.USERNAME).status(appl.STATUS).natal(appl.NATAL).appDateId(appl.APPDATEID).build());
				rs=stmt.executeQuery("SELECT id FROM app_schedule_dates_tbl where id="+appl.APPDATEID+";");
				int dateId=0;
				while(rs.next()) {
					dateId=rs.getInt("id");
				}
				Appointment newAppointment=new Appointment.Builder().appointmentId(Integer.parseInt(id)).id(dateId).applicantId(id).build();
				addAppointment(newAppointment);
				app.addAppointment(newAppointment);
			}
			added=true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return added;
	}

	public boolean checkDate(LocalDate date) {
		boolean flag;
		return flag=date.isEqual(LocalDate.now())?true:false;
	}

	public boolean addAppSchedDate(LocalDate date) {
		boolean added=false;
		try {
			PreparedStatement ps=conn.prepareStatement("INSERT INTO app_schedule_dates_tbl(date) VALUES(?);");
			ps.setString(1,date.toString());
			ps.executeUpdate();
			added=true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return added;
	}

	public boolean addAppointment(Appointment apnt) {
		boolean added=false;
		try {
			PreparedStatement ps=conn.prepareStatement("INSERT INTO appointments_tbl(dateid,applicantid) VALUES(?,?);");
			ps.setInt(1,apnt.ID);
			ps.setString(2,apnt.APPLICANTID);
			ps.executeUpdate();
			added=true;
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
		Applicants applicant=new Applicants.Builder().id(Integer.parseInt(data[0])).fname(data[1]).mname(data[2]).lname(data[3]).address(data[4]).age(Integer.parseInt(data[5])).sex(data[6]).mobile(data[7]).email(data[8]).username(data[9]).status(status).natal(LocalDate.parse(data[11])).appDateId(data[12]).build();
			applicants.addApplicant(applicant);
		}
		else if("appdate".equals(user)) {
			appDates.addAppDate(new AppointmentDate.Builder().setId(Integer.parseInt(data[0])).setDate(LocalDate.parse(data[1])).build());
		}
		else if("appointment".equals(user)) {
			app.addAppointment(new Appointment.Builder().appointmentId(Integer.parseInt(data[0])).applicantId(data[1]).date(LocalDate.parse(data[2])).applicantName(data[3]).applicantAddress(data[4]).appointmentStatus(data[5]).applicantEmail(data[6]).applicantSex(data[7]).applicantPhone(data[8]).build());
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
					rs.getString("status"),
					rs.getString("natal"),
					rs.getString("dateid"),
					"applicant"
				};
				createObject(result);
			}
			rs=stmt.executeQuery("SELECT * FROM app_schedule_dates_tbl;");
			LocalDate date;
			int counter=0;
			while(rs.next()) {
				date=LocalDate.parse(rs.getString("date"));
				counter=rs.getInt("counter");
				if(checkDate(date)==false && counter<50) {
					String[] dates={
					String.valueOf(rs.getInt("id")),
					rs.getString("date"),
					"appdate"
					};
					createObject(dates);
				}
			}
			rs=stmt.executeQuery("SELECT apps.fname||' '||substr(apps.mname,1,1)"+
			"||'. '||apps.lname as name,"+
			"apps.address as address,apnts.id as thisid,apps.id"+
			" as applicantid,dates.date as appDate,apps.status as status,"+
			"apps.emailadd as email,apps.sex as sex,apps.mobilenumber as phone"+
			" FROM ((appointments_tbl as apnts inner join"+
			" app_schedule_dates_tbl as dates on apnts.dateid=dates.id)"+
			" inner join applicants_tbl as apps on apnts.applicantid=apps.id);");
			while(rs.next()) {
				String[] apnts={
					String.valueOf(rs.getInt("thisid")),
					rs.getString("applicantid"),
					rs.getString("appDate"),
					rs.getString("name"),
					rs.getString("address"),
					rs.getString("status"),
					rs.getString("email"),
					rs.getString("sex"),
					rs.getString("phone"),
					"appointment"
				};
				createObject(apnts);
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
