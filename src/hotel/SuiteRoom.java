package hotel;

public class SuiteRoom extends Room {
	/**
	 * SuiteRooms are Rooms that hold 5-8 Guests
	 * 	- $500 per night, 4 small ticks to clean
	 * Subclass of Room
	 */

	public SuiteRoom() {
		super();
		this.price = 500;
		this.cleaningTime = 0.4;
		this.type = "Suite Room";
	}

}
