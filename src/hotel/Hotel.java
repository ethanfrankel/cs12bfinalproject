package hotel;

import java.util.ArrayList;

public class Hotel {
	ArrayList<Room> rooms;
	Restaurant restaurant;
	Pool pool;
	Staff[] staff;
	int numStaff;
	double totalMoney;
	int numTwoGuestRoom;
	int numFourGuestRoom;
	int numSuiteRoom;

	public Hotel(int numTwoGuestRoom, int numFourGuestRoom, int numSuiteRoom, int numStaff) {
		this.restaurant = new Restaurant();
		this.pool = new Pool();
		this.numStaff = numStaff;
		this.numTwoGuestRoom = numTwoGuestRoom;
		this.numFourGuestRoom = numFourGuestRoom;
		this.numSuiteRoom = numSuiteRoom;
		this.rooms = new ArrayList<Room>();
		this.staff = new Staff[this.numStaff];
		this.totalMoney = 10000;
	}
	
	public void addRooms() {
		for(int i=0; i<this.numTwoGuestRoom; i++) {
		      this.rooms.add(new TwoGuestRoom());
		}
		for(int i=0; i<this.numFourGuestRoom; i++) {
		      this.rooms.add(new FourGuestRoom());
		}
		for(int i=0; i<this.numSuiteRoom; i++) {
		      this.rooms.add(new SuiteRoom());
		}
	}
	
	public void setRoomNumbers() {
		for (int i = 0; i < this.rooms.size(); i++) {
			this.rooms.get(i).setRoomNumber(i + 1);
		}
	}
	
	public double getTotalMoney() {
		return this.totalMoney;
	}
	
	public void setTotalMoney(int value) {
		this.totalMoney = value;
	}
	
	public void printHotel() {
		//test
		System.out.println("Two Guest Rooms: " + this.numTwoGuestRoom);
		System.out.println("Four Guest Rooms: " + this.numFourGuestRoom);
		System.out.println("Suite Rooms: " + this.numSuiteRoom);
		System.out.println("Staff: " + this.numStaff);
	}
	
	public Guest createGuest(int numDaysCheckIn) {
		Guest guest = new Guest(numDaysCheckIn);
		return guest;
	}
	
	public void checkIn(int randNum, int numDaysCheckIn, String roomType) {
		for (int i = 0; i < this.rooms.size(); i++ ) {
			if (this.rooms.get(i).type == roomType) {
				if (this.rooms.get(i).available) {
					for (int j = 0; j < randNum; j++) {
						Guest guest = this.createGuest(numDaysCheckIn);
						this.rooms.get(i).addGuests(guest);
					}
					this.rooms.get(i).setNumDaysCounter(numDaysCheckIn);
					break;
				}
				/*
				if (!this.rooms.get(i).available) {
					System.out.println("Room unavailable.");
					break;
				}
				*/
			}
		}
	}
	
	public void determineRoomType(int randNum, int numDaysCheckIn) {
		if (0 < randNum && randNum < 3) {
			this.checkIn(randNum, numDaysCheckIn, "Two Guest Room");
		}
		else if (2 < randNum && randNum < 5) {
			this.checkIn(randNum, numDaysCheckIn, "Four Guest Room");
		}
		else if (4 < randNum && randNum < 9) {
			this.checkIn(randNum, numDaysCheckIn, "Suite Room");
		}
		for (int a = 0; a < this.rooms.size(); a++ ) {
			if (this.rooms.get(a).numOfPeople > 0) {
				this.rooms.get(a).setAvailable(false);
			}
		}
	}
	
	public void updateGuestCounters() {
		for (int i = 0; i < this.rooms.size(); i++) {
			this.rooms.get(i).setNumDaysCounter(this.rooms.get(i).numDaysCounter - 1);
			if (this.rooms.get(i).numDaysCounter == 0) {
				this.rooms.get(i).checkOut();
			}
		}
	}

}
