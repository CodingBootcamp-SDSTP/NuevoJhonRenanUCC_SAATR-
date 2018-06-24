import java.time.LocalDate;

public class AppointmentDate
{
	public static class Builder
	{
		int id;
		LocalDate date;
		int counter;

		public Builder setId(int i) {
			id=i;
			return(this);
		}

		public Builder setDate(LocalDate ld) {
			date=ld;
			return(this);
		}

		public Builder setCounter(int c) {
			counter=c;
			return(this);
		}

		public int getId() {
			return id;
		}

		public LocalDate getDate() {
			return date;
		}

		public int getCounter() {
			return counter;
		}

		public AppointmentDate build() {
			return new AppointmentDate(this);
		}
	}

	final int ID;
	final LocalDate DATE;
	final int COUNTER;

	public AppointmentDate(Builder b) {
		ID=b.getId();
		DATE=b.getDate();
		COUNTER=b.getCounter();
	}
}
