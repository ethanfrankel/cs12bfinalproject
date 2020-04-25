package hotel;

public class SuiteRoom extends Room {
	/**
	 * $500 per night
	 */

	public SuiteRoom() {
		super();
		this.price = 500;
		this.cleaningTime = 0.3;
		this.type = "Suite Room";
	}

}
