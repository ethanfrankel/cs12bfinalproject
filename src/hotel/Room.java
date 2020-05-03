package hotel;

import java.util.ArrayList;

public class Room {
	ArrayList<Guest> occupants;
	int roomNumber;//room number
	double price;//price of each room: $200, $300, $500
	int numOfPeople;//number of people in the room
	boolean cleaned;//for staff use
	boolean available;//people are checked into room
	boolean occupied;//people are currently in the room
	double cleaningTime;//number of small/hour ticks each room takes to clean
	String type;//twoGuestRoom, fourGuestRoom, or suitRoom
	
	protected Room() {
		this.occupants = new ArrayList<Guest>();
		this.cleaned = true;
		this.available = true;
		this.occupied = false;
	}
	
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
		//System.out.println(this.toString() + " has checked out.");
		
	}
	
	public String toString() {
		/**
		 * simple toString for room
		 */
		return "Room " + this.roomNumber;
	}
	
	public String toStringRoomOccupancy() {
		/**
		 * toString for each room and its occupancy
		 */
		if (this.numOfPeople == 0) {
			return "Room " + this.roomNumber + ": Vacant";
		}
		return "Room " + this.roomNumber + ": " + this.numOfPeople + " guests";
	}
	
	public String detailedToString() {
		/*
		 * toString with type of room 
		 */
		return "Room " + this.roomNumber + " (" + this.type + ")";
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
