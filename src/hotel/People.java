package hotel;

public class People {
	double balance;
	String type;
	String name;
	double currentTimeCounter;
	double timeEnterRestaurant;
	boolean inRestaurant;
	boolean inPool;

	protected People() {
		// TODO Auto-generated constructor stub
	}
	
	public void setCurrentTimeCounter(double value) {
		this.currentTimeCounter = value;
	}
	
	public void setTimeEnterRestaurant(double value) {
		this.timeEnterRestaurant = value;
		this.currentTimeCounter = value;
	}
	
	public void setInRestaurant(boolean value) {
		this.inRestaurant = value;
	}
	
	public void setInPool(boolean value) {
		this.inPool = value;
	}
	
	public void updateBalance(double value) {
		this.balance = this.balance + value;
	}

}
