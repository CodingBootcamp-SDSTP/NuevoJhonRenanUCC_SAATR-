import java.time.LocalDate;
import java.util.ArrayList;

public class AppointmentList
{
	ArrayList<Appointment> appointments;

	public AppointmentList(){
		appointments=new ArrayList<Appointment>();
	}

	public void addAppointment(Appointment appointment) {
		appointments.add(appointment);
	}

	public ArrayList<Appointment> getAppointments() {
		return appointments;
	}
	// public AppointmentList(int id,String appId,LocalDate d,String name) {
	// 	appDates=new ArrayList<LocalDate>();
	// 	APPOINTMENTID=id;
	// 	appointmentName=name;
	// 	applicantId=appId;
	// 	date=d;
	// }
}
