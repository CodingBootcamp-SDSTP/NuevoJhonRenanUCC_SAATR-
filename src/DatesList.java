import java.time.LocalDate;
import java.util.ArrayList;

public class DatesList
{
	ArrayList<AppointmentDate> appDates;

	public DatesList(){
		appDates=new ArrayList<AppointmentDate>();
	}

	public void addAppDate(AppointmentDate ad) {
		appDates.add(ad);
	}

	public ArrayList<AppointmentDate> getAppDates() {
		return appDates;
	}
}
