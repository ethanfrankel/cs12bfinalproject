package hotel;

public class FourGuestRoom extends Room {
	/**
	 * $300 per night
	 */

	public FourGuestRoom() {
		super();
		this.price = 300;
		this.cleaningTime = 0.2;
		this.type = "Four Guest Room";
	}

}
