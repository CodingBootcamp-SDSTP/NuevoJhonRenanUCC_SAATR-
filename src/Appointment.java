import java.time.LocalDate;

public class Appointment
{
	private int id;
	private LocalDate date;

	public Appointment(int id,LocalDate date) {
		this.id=id;
		this.date=date;
	}

	public int getId() {
		return id;
	}

	public LocalDate getDate() {
		return date;
	}
}
