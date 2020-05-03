package hotel;

import java.util.ArrayList;

public class Room {
	ArrayList<Guest> occupants;
	int roomNumber;
	double price;
	int numOfPeople;
	boolean cleaned;
	boolean beingCleaned;
	ArrayList<Staff> staffCleaning;
	boolean available;
	boolean occupied;
	double cleaningTime;
	String type;
	
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
	
	public void addStaffCleaning(Staff staff) {
		this.staffCleaning.add(staff);
	}
	
	public void removeStaffCleaning() {
		this.staffCleaning.clear();
	}
	
	public void addGuests(Guest guest) {
		this.occupants.add(guest);
		this.numOfPeople++;
	}
	
	public void checkOut() {
		this.occupants.clear();
		this.numOfPeople = 0;
		this.available = true;
		//System.out.println(this.toString() + " has checked out.");
		
	}
	
	public String toString() {
		return "Room #" + this.roomNumber;
	}
	
	public String toStringRoomOccupancy() {
		if (this.numOfPeople == 0) {
			return "Room #" + this.roomNumber + ": Vacant";
		}
		return "Room #" + this.roomNumber + ": " + this.numOfPeople + " guests";
	}
	
	public String detailedToString() {
		//test
		return "Room #" + this.roomNumber + " (" + this.type + ")";
	}
	
	public String printGuests() {
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
