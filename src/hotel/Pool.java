package hotel;

import java.util.ArrayList;

public class Pool {
	public ArrayList<Guest> occupants;
	ArrayList<Guest> dayGuests;
	ArrayList<Double> dayGuestTime;
	public static final int MAXIMUM_CAPACITY = 30;

	public Pool() {
		this.occupants = new ArrayList<Guest>();
		this.dayGuests = new ArrayList<Guest>();
		this.dayGuestTime = new ArrayList<Double>();
	}
	
	public void addToPool (ArrayList<Guest> newGuests, double timeAdded) {
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
		for (int i = 0; i < this.occupants.size(); i++) {
			this.occupants.get(i).setInPool(false);
		}
		this.occupants.clear();
	}
	
	public void resetDayVariables() {
		this.dayGuests.clear();
		this.dayGuestTime.clear();
	}
	
	public void printPoolActivity() {
		System.out.println("\r\n" + "***Pool Activity***");
		System.out.println(this.dayGuests.size() + " used the pool today:");
		for (int i = 0; i < this.dayGuests.size(); i++) {
			System.out.println(this.dayGuests.get(i) + " used the pool at " + this.dayGuestTime.get(i));
		}	
	}

}
