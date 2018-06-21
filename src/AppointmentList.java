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
}
