package hotel;

import java.util.ArrayList;
import java.util.Random;

public class Restaurant {
	public ArrayList<People> occupants;
	double daySales;
	int dayGuests;
	int dayCustomers;
	Random random;
	public static final int MAXIMUM_CAPACITY = 50;
	
	public Restaurant() {
		this.occupants = new ArrayList<People>();
		this.random = new Random();
		this.daySales = 0;
		this.dayGuests = 0;
		this.dayCustomers = 0;
	}
	
	//adds guests already in hotel into the restaurant
	public void addGuestToRestaurant (ArrayList<Guest> newGuests) {
		if (newGuests.size() < (MAXIMUM_CAPACITY - this.occupants.size())) {
			for (int i=0; i<newGuests.size(); i++) {
				this.occupants.add(newGuests.get(i));
				this.dayGuests++;
				System.out.print(newGuests.get(i) + " has entered the Restaurant.");
			}
		}
		else {
			System.out.println("The restaurant does not have enough space to seat a group of " + newGuests.size() + " people.");
		}
	}
	
	//adds customers not in hotel into the restaurant
	public void addCustomerToRestaurant(Customer customer, int customerNum) {
		if (customerNum < (MAXIMUM_CAPACITY - this.occupants.size())) {
			for (int i = 0; i < customerNum; i++) {
				this.occupants.add(customer);
				this.dayCustomers++;
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
				System.out.println(person + " has left the Restaurant.");
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
	
	public void resetDayVariables() {
		this.daySales = 0;
		this.dayGuests = 0;
		this.dayCustomers = 0;
	}
	
	public void printRestaurantActivity() {
		System.out.println(this.dayGuests + " guests dined at the restaurant.");
		System.out.println(this.dayCustomers + " customers that are not guests in the hotel dined at the restaurant");
		System.out.println("Total people served: " + this.dayGuests + this.dayCustomers);
	}
}
