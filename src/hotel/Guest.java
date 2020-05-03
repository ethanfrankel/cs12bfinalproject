package hotel;

public class Guest extends People{
	/**
	 * Guests are People that stay at the hotel
	 * Keeps track of how long they stay and time at check-in
	 */

	double numDaysStay;
	double timeAtCheckIn;


	public Guest(double timeAtCheckIn, double numDaysCheckedIn, String guestName) {
		super();
		this.type = "Guest"; //Guests assigned guest type
		this.name = guestName;
		this.numDaysStay = numDaysCheckedIn;
		this.timeAtCheckIn = timeAtCheckIn;
		//keeps track if a guest is in the restaurant or pool
		this.inRestaurant = false;
		this.inPool = false;
	}
	
	//setter
	public void setTimeAtCheckIn(double value) {
		this.timeAtCheckIn = value;
	}
	
	public String toString() {
		return this.name;
	}

}
