import java.sql.*;
import java.sql.Date;

public class Main
{
	public static void main(String[] args) {
		AppDatabase ad=AppDatabase.instance();
		// Date date=new Date();
		// Date date=new java.util.Date().getTime();
		ad.addApplicant("Renan","Cabug","Nuevo","Caloocan",21,"Male","09369680108","renan.nuevo@gmail.com","heisenberg","on-going");
	}
}