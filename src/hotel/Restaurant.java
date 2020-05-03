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
	int totalServed;
	int maxCapacity;
	
	public Restaurant(int numStaff) {
		this.occupants = new ArrayList<People>();
		this.random = new Random();
		this.daySales = 0;
		this.dayTotalPeople = new ArrayList<People>();
		this.dayTotalPrices = new ArrayList<Integer>();
		this.dayTimes = new ArrayList<Double>();
		this.totalServed = 0;
		this.maxCapacity = numStaff * 2;
	}
	
	//adds guests already in hotel into the restaurant
	public void addGuestToRestaurant (ArrayList<Guest> newGuests, double timeAdded) {
		if (newGuests.size() < (this.maxCapacity - this.occupants.size())) {
			for (int i=0; i<newGuests.size(); i++) {
				this.occupants.add(newGuests.get(i));
				this.dayTotalPeople.add(newGuests.get(i));
				this.dayTimes.add(timeAdded);
				newGuests.get(i).setInRestaurant(true);
				this.payMeals(newGuests.get(i));
			}
		}
	}
	
	//adds customers not in hotel into the restaurant
	public void addCustomerToRestaurant(Customer customer, double timeAdded) {
		if (this.occupants.size() < this.maxCapacity) {
			this.occupants.add(customer);
			this.dayTotalPeople.add(customer);
			this.dayTimes.add(timeAdded);
			this.payMeals(customer);
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
	
	public void payMeals(People person) {
		int mealPrice = random.nextInt(31) + 15;
		this.dayTotalPrices.add(mealPrice);
		this.daySales = this.daySales + mealPrice;
	}
	
	public void resetDayVariables() {
		this.totalServed = this.totalServed + this.dayTotalPeople.size();
		this.daySales = 0;
		this.dayTotalPeople.clear();
		this.dayTotalPrices.clear();
		this.dayTimes.clear();
	}
	
	public void printRestaurantActivity() {
		int totalServed = this.dayTotalPeople.size();
		int guestsServed = 0;
		for (People person : this.dayTotalPeople) {
			if (person.type.equals("Guest")) {
				guestsServed++;
			}
		}
		int customersServed = this.dayTotalPeople.size() - guestsServed;
		System.out.println("\r\n" + "***Restaurant Activity***");
		System.out.println("The restaurant made $" + this.daySales + "0 today");
		System.out.println(totalServed + " people were served (" + guestsServed + " guests, " + customersServed + " customers):" + "\r\n");
		for (int i = 0; i < this.dayTotalPeople.size(); i++) {
			System.out.println(this.dayTotalPeople.get(i) + " paid $" + this.dayTotalPrices.get(i) + 
					" for their meal at " + this.dayTimes.get(i));
		}
	}
}
