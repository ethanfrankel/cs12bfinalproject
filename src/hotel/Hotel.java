package hotel;

import java.util.ArrayList;

public class Hotel {
	Room[] rooms;
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
		this.rooms = new Room[numTwoGuestRoom + numFourGuestRoom + numSuiteRoom];
		this.staff = new Staff[this.numStaff];
		this.totalMoney = 10000;
	}
	
	public void addRooms() {
		for(int i=0; i<this.numTwoGuestRoom; i++) {
		      this.rooms[i] = new TwoGuestRoom();
		}
		for(int i=this.numTwoGuestRoom; i<this.numFourGuestRoom + this.numTwoGuestRoom; i++) {
		      this.rooms[i] = new FourGuestRoom();
		}
		for(int i=this.numTwoGuestRoom + this.numFourGuestRoom; i<this.rooms.length; i++) {
		      this.rooms[i] = new SuiteRoom();
		}
	}
	
	public void setRoomNumbers() {
		for (int i = 0; i < this.rooms.length; i++) {
			this.rooms[i].setRoomNumber(i + 1);
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
		for (int i = 0; i < this.rooms.length; i++ ) {
			if (this.rooms[i].type == roomType) {
				if (this.rooms[i].available) {
					for (int j = 0; j < randNum; j++) {
						Guest guest = this.createGuest(numDaysCheckIn);
						this.rooms[i].addGuests(guest);
					}
					this.rooms[i].setNumDaysCounter(numDaysCheckIn);
					break;
				}
				else if (!this.rooms[i].available) {
					System.out.println("Room unavailable.");
					break;
				}
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
		for (int a = 0; a < this.rooms.length; a++ ) {
			if (this.rooms[a].numOfPeople > 0) {
				this.rooms[a].setAvailable(false);
			}
		}
	}
	
	public void updateGuestCounters() {
		for (int i = 0; i < this.rooms.length; i++) {
			this.rooms[i].setNumDaysCounter(this.rooms[i].numDaysCounter - 1);
			if (this.rooms[i].numDaysCounter == 0) {
				this.rooms[i].checkOut();
			}
		}
	}

}
