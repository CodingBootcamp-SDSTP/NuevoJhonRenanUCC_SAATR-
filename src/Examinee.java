import java.time.LocalDate;

public class Examinee
{
	private final int ID;
	private int applicantId;
	private String remarks;
	private String room;
	private LocalDate examDate;

	public Examinee(int i,int ai,String rem,String rm,LocalDate exd) {
		ID=i;
		applicantId=ai;
		remarks=rem;
		room=rm;
		examDate=exd;
	}

	public Examinee(int ai,String rem,String rm,LocalDate exd) {
		applicantId=ai;
		remarks=rem;
		room=rm;
		examDate=exd;
		ID=0;
	}

	public void setApplicantId(int ai) {
		applicantId=ai;
	}

	public void setRemarks(String rem) {
		remarks=rem;
	}

	public void setRoom(String rm) {
		room=rm;
	}

	public void setExamDate(LocalDate exd) {
		examDate=exd;
	}

	public int getId() {
		return ID;
	}

	public int getApplicantId() {
		return applicantId;
	}

	public String getRemarks() {
		return remarks;
	}

	public String getRoom() {
		return room;
	}

	public LocalDate getExamDate() {
		return examDate;
	}
}
