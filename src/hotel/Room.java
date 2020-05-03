package hotel;

import java.util.ArrayList;

public class Room {
	/**
	 * Rooms can be Two Guest Rooms, Four Guest Rooms, or Suite Rooms (subclasses of Room)
	 */
	ArrayList<Guest> occupants;
	int roomNumber;
	double price;
	int numOfPeople;
	boolean cleaned;
	boolean beingCleaned;
	ArrayList<Staff> staffCleaning; //holds staff member that is cleaning the room
	boolean available;
	boolean occupied;
	double cleaningTime;
	String type;

	protected Room() {
		this.occupants = new ArrayList<Guest>();
		this.staffCleaning = new ArrayList<Staff>();
		this.cleaned = true;
		this.available = true;
		this.occupied = false;
	}
	
	//getters and setters
	public void setRoomNumber(int value) {
		this.roomNumber = value;
	}

	public boolean getCleaned() {
		return this.cleaned;
	}

	public void setCleaned(boolean value) {
		this.cleaned = value;
	}

	public boolean getAvailable() {
		return this.available;
	}

	public void setAvailable(boolean value) {
		this.available = value;
	}

	public boolean getOccupied() {
		return this.occupied;
	}

	public void setOccupied(boolean value) {
		this.occupied = value;
	}

	public double getPrice() {
		return this.price;
	}

	public void addStaffCleaning(Staff staff) {
		/**
		 * add staff member cleaning the room
		 */
		this.staffCleaning.add(staff);
	}

	public void removeStaffCleaning() {
		/**
		 * remove staff member once room is finished being cleaned
		 */
		this.staffCleaning.clear();
	}

	public void addGuests(Guest guest) {
		/**
		 * adds guests to ArrayList occupants
		 * updates counter
		 */
		this.occupants.add(guest);
		this.numOfPeople++;
	}

	public void checkOut() {
		/**
		 * function to clear ArrayList occupants and reset counter
		 * rooms are set to available
		 */
		this.occupants.clear();
		this.numOfPeople = 0;
		this.available = true;
		this.occupied = false;
		//System.out.println(this.toString() + " has checked out.");

	}

	public String toString() {
		return "Room #" + this.roomNumber;
	}

	public String toStringRoomOccupancy() {
		/**
		 * toString for each room and its occupancy
		 * not used
		 */
		if (this.numOfPeople == 0) {
			return "Room #" + this.roomNumber + ": Vacant";
		}
		return "Room #" + this.roomNumber + ": " + this.numOfPeople + " guests";
	}

	public String detailedToString() {
		/**
		 * toString for each room, room type, and its occupancy
		 */
		return "Room #" + this.roomNumber + " (" + this.type + ")";
	}

	public String printGuests() {
		/**
		 * prints guests in rooms
		 */
	    String values = "Guests: ";
	    for (int i = 0; i < this.occupants.size(); i++) {
	      //this if statement essentially does not print a comma after the last item in list
	      if (i == this.occupants.size() - 1) {
	        values = values + this.occupants.get(i).toString();
	      }
	      else {
	        values = values + this.occupants.get(i).toString() + ", ";
	      }
	    }
	    return values;
	}

}
