package hotel;

import java.util.ArrayList;
import java.util.Random;

public class Restaurant {
	public ArrayList<People> occupants;
	double daySales;
	ArrayList<People> dayTotalPeople;
	ArrayList<Integer> dayTotalPrices;
	ArrayList<Double> dayTimes;
	Random random;
	public static final int MAXIMUM_CAPACITY = 50;
	
	public Restaurant() {
		this.occupants = new ArrayList<People>();
		this.random = new Random();
		this.daySales = 0;
		this.dayTotalPeople = new ArrayList<People>();
		this.dayTotalPrices = new ArrayList<Integer>();
		this.dayTimes = new ArrayList<Double>();
	}
	
	//adds guests already in hotel into the restaurant
	public void addGuestToRestaurant (ArrayList<Guest> newGuests, double timeAdded) {
		if (newGuests.size() < (MAXIMUM_CAPACITY - this.occupants.size())) {
			for (int i=0; i<newGuests.size(); i++) {
				this.occupants.add(newGuests.get(i));
				this.dayTotalPeople.add(newGuests.get(i));
				this.dayTimes.add(timeAdded);
				newGuests.get(i).setInRestaurant(true);
			}
		}
	}
	
	//adds customers not in hotel into the restaurant
	public void addCustomerToRestaurant(Customer customer, double timeAdded) {
		if (this.occupants.size() < MAXIMUM_CAPACITY) {
			this.occupants.add(customer);
			this.dayTotalPeople.add(customer);
			this.dayTimes.add(timeAdded);
		}
	}
	
	public void clearRestaurant() {
		for (int i = 0; i < this.occupants.size(); i++) {
			if (this.occupants.get(i).type.equals("Guest")) {
				this.occupants.get(i).setInRestaurant(false);
			}
		}
		this.occupants.clear();
	}
	
	public void payMeals() {
		for (People person : this.occupants) {
			int mealPrice = random.nextInt(31) + 15;
			this.dayTotalPrices.add(mealPrice);
			this.daySales = this.daySales + mealPrice;
		}
	}
	
	public void resetDayVariables() {
		this.daySales = 0;
		this.dayTotalPeople.clear();
		this.dayTotalPrices.clear();
		this.dayTimes.clear();
	}
	
	public void printRestaurantActivity() {
		int totalServed = this.dayTotalPeople.size();
		System.out.println("\r\n" + "***Restaurant Activity***");
		System.out.println("The restaurant made $" + this.daySales + "0 today" + "\r\n");
		System.out.println(totalServed + " people were served:");
		for (int i = 0; i < this.dayTotalPeople.size(); i++) {
			System.out.println(this.dayTotalPeople.get(i) + " paid $" + this.dayTotalPrices.get(i) + 
					" for their meal at " + this.dayTimes.get(i));
		}

	}
}
