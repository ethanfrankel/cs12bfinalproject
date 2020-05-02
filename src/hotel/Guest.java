package hotel;

public class Guest extends People{
	/**
	 * guests are spending the night on the property
	 * how long they stay 
	 * 
	 */
	double numDaysStay;
	double timeAtCheckIn;


	public Guest(double timeAtCheckIn, double numDaysCheckedIn, String guestName) {
		super();
		this.type = "Guest";
		this.name = guestName;
		this.numDaysStay = numDaysCheckedIn;
		this.timeAtCheckIn = timeAtCheckIn;
		this.inRestaurant = false;
		this.inPool = false;
	}
	
	public void setTimeAtCheckIn(double value) {
		this.timeAtCheckIn = value;
		//this.currentTimeCounter = value;
	}
	
	public String toString() {
		return this.name;
	}

}
