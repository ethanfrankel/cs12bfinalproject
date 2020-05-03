package hotel;

import java.util.ArrayList;

public class Pool {
	public ArrayList<Guest> occupants;//people in pool for hour/small tick
	ArrayList<Guest> dayGuests;//people in pool the entire day
	ArrayList<Double> dayGuestTime;//for reference in report
	public static final int MAXIMUM_CAPACITY = 30;

	public Pool() {
		this.occupants = new ArrayList<Guest>();
		this.dayGuests = new ArrayList<Guest>();
		this.dayGuestTime = new ArrayList<Double>();
	}
	
	public void addToPool (ArrayList<Guest> newGuests, double timeAdded) {
		/**
		 * adds guests to pool for one small/hour tick
		 * adds them to the ArrayList of guests for the day
		 * adds the time they were at the pool to the ArrayList of times for the day
		 */
		if (newGuests.size() < (MAXIMUM_CAPACITY - this.occupants.size())) {
			for (int i=0; i<newGuests.size(); i++) {
				this.occupants.add(newGuests.get(i));
				this.dayGuests.add(newGuests.get(i));
				this.dayGuestTime.add(timeAdded);
				newGuests.get(i).setInPool(true);
			}
		}
	}

	public void clearPool() {
		/**
		 * empties the pool after one small/hour tick
		 */
		for (int i = 0; i < this.occupants.size(); i++) {
			this.occupants.get(i).setInPool(false);
		}
		this.occupants.clear();
	}
	
	public void resetDayVariables() {
		/**
		 * clears/resets the variables and ArrayLists for the next hour/small tick
		 */
		this.dayGuests.clear();
		this.dayGuestTime.clear();
	}
	
	public void printPoolActivity() {
		/**
		 * method to print pool activity in report
		 * prints guest name and the time they were in the pool
		 */
		System.out.println("\r\n" + "***Pool Activity***");
		System.out.println(this.dayGuests.size() + " used the pool today:" + "\r\n");
		for (int i = 0; i < this.dayGuests.size(); i++) {
			System.out.println(this.dayGuests.get(i) + " used the pool at " + this.dayGuestTime.get(i));
		}	
	}

}
