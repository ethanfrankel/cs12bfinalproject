package hotel;

public class Guest extends People{
	/**
	 * guests are spending the night on the property
	 * how long they stay 
	 * 
	 */
	String name;

	public Guest(double numDaysCheckedIn, String guestName) {
		super();
		this.name = guestName;
	}
	
	public String toString() {
		return this.name;
	}

}
