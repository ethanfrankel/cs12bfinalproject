package hotel;

public class Customer extends People{
	/**
	 * Customers are People that only go to the restaurant
	 * Subclass of People
	 * @param customerName
	 */
	
	public Customer(String customerName) {
		super();
		this.type = "Customer"; //customers assigned customer type
		this.name = customerName;
	}
	
	public String toString() {
		return "Customer " + this.name;
	}
	

}
