package hotel;
import java.util.ArrayList;

public class Restaurant {
	public ArrayList<Customer> occupants;
	public static final int MAXIMUM_CAPACITY = 50; 
	//maybe some Random that determines how much money each customer is willing to spend
	
	
	public Restaurant() {
		this.occupants = new ArrayList<Customer>();
	}
	
	public void addToRestaurant (ArrayList<Customer> newCustomers) {
		if (newCustomers.size() > (MAXIMUM_CAPACITY - this.occupants.size())) {
			for (int i=0; i<newCustomers.size(); i++) {
				this.occupants.add(newCustomers.get(i));
				System.out.print(newCustomers.get(i).name + "has entered the Restaurant.");
			}
		}
	}
	
	public Customer removeFromRestaurant(int i) {
		Customer c = this.occupants.get(i);
		this.occupants.remove(i);
		System.out.println(this.occupants.get(i).name + "has left the Restaurant.");
		return c; 
	}

	public String toString() {
		return "Restaurant has" + " " + this.occupants.size() + " " + "occupants currently.";
	}
	
	public String detailedToString() {
		String firstPart = "Restaurant has" + " " + this.occupants.size() + " " + "occupants currently.";
		String secondPart = "Those occupants are as follows: ";
		String thirdPart = "";
		for (int i=0;i<this.occupants.size();i++) {
			thirdPart += (this.occupants.get(i).name + " ");
		}
		
		return firstPart + secondPart + thirdPart;
	}
}
