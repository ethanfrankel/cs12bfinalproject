package hotel;

import java.util.ArrayList;
import java.util.Random;

public class Restaurant {
	public ArrayList<People> occupants;
	double daySales;
	ArrayList<Guest> dayGuests;
	ArrayList<Customer> dayCustomers;
	ArrayList<Integer> dayGuestPrices;
	ArrayList<Integer> dayCustomerPrices;
	Random random;
	public static final int MAXIMUM_CAPACITY = 50;
	
	public Restaurant() {
		this.occupants = new ArrayList<People>();
		this.random = new Random();
		this.daySales = 0;
		this.dayGuests = new ArrayList<Guest>();
		this.dayCustomers = new ArrayList<Customer>();
		this.dayGuestPrices = new ArrayList<Integer>();
		this.dayCustomerPrices = new ArrayList<Integer>();
	}
	
	//adds guests already in hotel into the restaurant
	public void addGuestToRestaurant (ArrayList<Guest> newGuests) {
		if (newGuests.size() < (MAXIMUM_CAPACITY - this.occupants.size())) {
			for (int i=0; i<newGuests.size(); i++) {
				this.occupants.add(newGuests.get(i));
				this.dayGuests.add(newGuests.get(i));
			}
		}
	}
	
	//adds customers not in hotel into the restaurant
	public void addCustomerToRestaurant(Customer customer) {
		if (this.occupants.size() < MAXIMUM_CAPACITY) {
			this.occupants.add(customer);
			this.dayCustomers.add(customer);
		}
	}
	
	public void clearRestaurant() {
		for (People person : this.occupants) {
			if (person.type.equals("Guest")) {
				person.setInRestaurant(false);
			}
		}
		this.occupants.clear();	
	}
	
	public void payMeals() {
		for (People person : this.occupants) {
			int mealPrice = random.nextInt(31) + 15;
			if (person.type.equals("Guest")) {
				this.dayGuestPrices.add(mealPrice);
			}
			if (person.type.equals("Customer")) {
				this.dayCustomerPrices.add(mealPrice);
			}
			this.daySales = this.daySales + mealPrice;
		}
	}
	
	public void resetDayVariables() {
		this.daySales = 0;
		this.dayGuests.clear();
		this.dayCustomers.clear();
	}
	
	public void printRestaurantActivity() {
		int totalServed = this.dayGuests.size() + this.dayCustomers.size();
		System.out.println(totalServed + " people were served at the restaurant:");
		/*
		for (int i = 0; i < this.dayGuests.size(); i++) {
			System.out.println(this.dayGuests.get(i) + " paid $" + this.dayGuestPrices.get(i) + " for their meal.");
		}
		for (int i = 0; i < this.dayCustomers.size(); i++) {
			System.out.println(this.dayCustomers.get(i) + " paid $" + this.dayCustomerPrices.get(i) + " for their meal.");
		}*/
		System.out.println(this.dayGuests.size());
		System.out.println(this.dayCustomers.size());
		System.out.println(this.dayGuestPrices.size());
		System.out.println(this.dayCustomerPrices.size());
	}
}
