package hotel;

import java.util.ArrayList;

public class Pool {
	public ArrayList<People> occupants;
	int dayGuests;
	public static final int MAXIMUM_CAPACITY = 30;

	public Pool() {
		this.occupants = new ArrayList<People>();
		this.dayGuests = 0;
	}
	
	public void addToPool (ArrayList<Guest> newGuests) {
		if (newGuests.size() < (MAXIMUM_CAPACITY - this.occupants.size())) {
			for (int i=0; i<newGuests.size(); i++) {
				this.occupants.add(newGuests.get(i));
				this.dayGuests++;
				System.out.print(newGuests.get(i) + " has entered the pool area.");
			}
		}
		else {
			System.out.println("The pool does not have enough space to hold a group of " + newGuests.size() + " people.");
		}
	}
	
	public void clearPool() {
		for (People person : this.occupants) {
			if (person.type.equals("Guest")) {
				person.setInPool(false);
				System.out.println(person + " has left the pool area.");
			}
		}
		this.occupants.clear();	
	}
	
	
	public void resetDayVariables() {
		this.dayGuests = 0;
	}
	
	public void printPoolActivity() {
		System.out.println(this.dayGuests + " used the pool today.");
	}

}
