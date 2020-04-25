package hotel;

public class TwoGuestRoom extends Room {
	/**
	 * $200 per night
	 */

	public TwoGuestRoom() {
		super();
		this.price = 200;
		this.cleaningTime = 0.1;
		this.type = "Two Guest Room";
	}

}
