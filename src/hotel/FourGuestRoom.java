package hotel;

public class FourGuestRoom extends Room {
	/**
	 * FourGuestRooms are Rooms that hold 3-4 Guests
	 * 	- $300 per night, 3 small ticks to clean
	 * Subclass of Room
	 */

	public FourGuestRoom() {
		super();
		this.price = 300;
		this.cleaningTime = 0.3;
		this.type = "Four Guest Room";
	}

}
