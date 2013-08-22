package objectoriented;

public class Student {
	private int id;
	private BirthDate birthDate;
	
	public Student(int ssn, int y, int m, int d) {
		id = ssn;
		birthDate = new BirthDate(y, m, d);
	}

	public int getId() {
		return id;
	}
	
	public BirthDate getBirthDate() {
		return birthDate;
	}
}