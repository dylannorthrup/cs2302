package objectoriented;

public class BirthDate {
	private int year;
	private int month;
	private int day;
	
	public BirthDate(int y, int m, int d) {
		this.year = y;
		this.month = m;
		this.day = d;
	}
	
	public void setYear(int newYear) {
		this.year = newYear;
	}

	// In this method, 'month' refers to what's passed into the method
	// and 'this.month' refers to the instance variable of the Class
	public void setMonth(int month) {
		if (month > 0 && month <= 12)
			this.month = month;
	}
	
	public String toString() {
		return this.month + "/" + this.day + "/" + this.year;
	}
}
