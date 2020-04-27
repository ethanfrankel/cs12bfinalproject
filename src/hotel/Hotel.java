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

}
