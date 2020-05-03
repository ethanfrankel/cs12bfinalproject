package hotel;

public class TwoGuestRoom extends Room {
	/**
	 * TwoGuestRooms are Rooms that hold 1-2 Guests
	 * 	- $200 per night, 2 small ticks to clean
	 * Subclass of Room
	 */

	public TwoGuestRoom() {
		super();
		this.price = 200;
		this.cleaningTime = 0.2;
		this.type = "Two Guest Room";
	}

}
