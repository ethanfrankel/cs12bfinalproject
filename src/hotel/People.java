package hotel;

public class People {
	String type;//guest or customer
	String name;//randomly assigned name from names.txt
	double timeEnterRestaurant;
	boolean inRestaurant;
	boolean inPool;

	protected People() {
		// TODO Auto-generated constructor stub
	}
	
	public void setTimeEnterRestaurant(double value) {
		this.timeEnterRestaurant = value;
	}
	
	public void setInRestaurant(boolean value) {
		this.inRestaurant = value;
	}
	
	public void setInPool(boolean value) {
		this.inPool = value;
	}
}
