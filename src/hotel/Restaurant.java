package hotel;

import java.util.ArrayList;
import java.util.Random;

public class Restaurant {
	public ArrayList<People> occupants;
	double daySales;
	Random random;
	public static final int MAXIMUM_CAPACITY = 50; 
	//maybe some Random that determines how much money each customer is willing to spend
	
	
	public Restaurant() {
		this.occupants = new ArrayList<People>();
		this.random = new Random();
		this.daySales = 0;
	}
	
	//adds guests already in hotel into the restaurant
	public void addGuestToRestaurant (ArrayList<Guest> newCustomers) {
		if (newCustomers.size() < (MAXIMUM_CAPACITY - this.occupants.size())) {
			for (int i=0; i<newCustomers.size(); i++) {
				this.occupants.add(newCustomers.get(i));
				System.out.print(newCustomers.get(i) + " has entered the Restaurant.");
			}
		}
		else {
			System.out.println("The restaurant does not have enough space to seat a group of " + newCustomers.size());
		}
	}
	
	//adds customers not in hotel into the restaurant
	public void addCustomerToRestaurant(Customer customer, int customerNum) {
		if (customerNum < (MAXIMUM_CAPACITY - this.occupants.size())) {
			for (int i = 0; i < customerNum; i++) {
				this.occupants.add(customer);
				System.out.println(customer + " has entered the Restaurant.");
			}
		}
		else {
			System.out.println("The restaurant does not have enough space to seat a group of " + customerNum);
		}
	}
	
	public void clearRestaurant() {
		for (People person : this.occupants) {
			if (person.type.equals("Guest")) {
				person.setInRestaurant(false);
				System.out.println(person.name + " has left the Restaurant.");
			}
		}
		this.occupants.clear();	
	}
	
	public void payMeals() {
		for (People person : this.occupants) {
			int mealPrice = random.nextInt(31) + 15;
			this.daySales = this.daySales + mealPrice;
			System.out.println(person + " paid " + mealPrice + " for their meal.");
		}
	}
	
	public void resetDaySales() {
		this.daySales = 0;
	}

	public String toString() {
		return "Restaurant has" + " " + this.occupants.size() + " " + "occupants currently.";
	}
	
	public String detailedToString() {
		String firstPart = "Restaurant has" + " " + this.occupants.size() + " " + "occupants currently.";
		String secondPart = "Those occupants are as follows: ";
		String thirdPart = "";
		for (int i=0;i<this.occupants.size();i++) {
			//thirdPart += (this.occupants.get(i).name + " ");
		}
		
		return firstPart + secondPart + thirdPart;
	}
}
