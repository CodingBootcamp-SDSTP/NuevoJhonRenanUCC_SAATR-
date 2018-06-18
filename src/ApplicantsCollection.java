import java.util.ArrayList;

public class ApplicantsCollection
{
	ArrayList<Applicants> applicants;

	public ApplicantsCollection() {
		applicants=new ArrayList<Applicants>();
	}

	public void addApplicant(Applicants applicant) {
		applicants.add(applicant);
	}

	public void removeApplicant(Applicants applicant) {
		applicants.remove(applicant);
	}

	public ArrayList<Applicants> getAllApplicants() {
		return(applicants);
	}

	public Applicants getApplicantByIndex(int i) {
		return(applicants.get(i));
	}

	public int geApplicationCount() {
		return(applicants.size());
	}
}
