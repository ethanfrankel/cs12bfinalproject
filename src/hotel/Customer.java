package hotel;

import java.util.Random;

public class Customer extends People{
	
	public Customer(String customerName) {
		super();
		this.type = "Customer";
		this.name = customerName;
	}
	
	public String toString() {
		return "Customer " + this.name;
	}
	

}
