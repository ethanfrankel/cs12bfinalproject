package hotel;

public class Staff {
	boolean currentlyCleaning;
	double timeStartedCleaning;
	int staffNumber;

	public Staff() {
		this.currentlyCleaning = false;
	}
	
	//getters and setters
	public void setCurrentlyCleaning(boolean value) {
		this.currentlyCleaning = value;
	}
	
	public void setTimeStartedCleaning(double value) {
		this.timeStartedCleaning = value;
	}
	
	public void setStaffNumber(int value) {
		this.staffNumber = value;
	}
	
	public String toString() {
		return "Staff Member #" + this.staffNumber;
	}

}
