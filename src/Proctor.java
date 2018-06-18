import java.time.LocalDate;

public class Proctor
{
	private final int ID;
	private String name;

	public Proctor(int i,String n) {
		ID=i;
		name=n;
	}

	public Proctor(String n) {
		name=n;
		ID=0;
	}

	public void setName(String n) {
		name=n;
	}

	public int getId() {
		return ID;
	}

	public String getName() {
		return name;
	}
}
