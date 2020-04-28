package hotel;

import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Hotel {
	FinancialAnalysis financialAnalysis;
	Random random;
	Room[] rooms;
	Restaurant restaurant;
	Pool pool;
	Staff[] staff;
	int numStaff;
	int totalGuests;
	int totalCustomers;
	double initialMoney;
	int numTwoGuestRoom;
	int numFourGuestRoom;
	int numSuiteRoom;
	
	public static final int NUM_NAMES = 3000;

	public Hotel(int numTwoGuestRoom, int numFourGuestRoom, int numSuiteRoom, int numStaff) throws FileNotFoundException {
		this.initialMoney = 10000;
		this.financialAnalysis = new FinancialAnalysis(this.initialMoney);
		this.random = new Random();
		this.restaurant = new Restaurant();
		this.pool = new Pool();
		this.numStaff = numStaff;
		this.numTwoGuestRoom = numTwoGuestRoom;
		this.numFourGuestRoom = numFourGuestRoom;
		this.numSuiteRoom = numSuiteRoom;
		this.rooms = new Room[numTwoGuestRoom + numFourGuestRoom + numSuiteRoom];
		this.staff = new Staff[this.numStaff];
		this.totalGuests = 0;
		this.totalCustomers = 0;
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
	
	public double getInitialMoney() {
		return this.initialMoney;
	}
	
	public void setInitialMoney(int value) {
		this.initialMoney = value;
	}
	
	public void printHotel() {
		//test
		System.out.println("Two Guest Rooms: " + this.numTwoGuestRoom);
		System.out.println("Four Guest Rooms: " + this.numFourGuestRoom);
		System.out.println("Suite Rooms: " + this.numSuiteRoom);
		System.out.println("Staff: " + this.numStaff);
	}
	
	public Guest createGuest(double numDaysCheckIn, String guestName) {
		Guest guest = new Guest(numDaysCheckIn, guestName);
		this.totalGuests++;
		return guest;
	}
	
	public String randomName() throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("names.txt"));
		String guestName = "";
		int lineCounter = 1;
		int lineStop = this.random.nextInt(NUM_NAMES) + 1;
		while (scanner.hasNextLine()) {
			String name = scanner.next();
			lineCounter++;
			if (lineCounter == lineStop) {
				guestName = name;
			}
		}
		return guestName;
	}
	
	public void checkIn(int randNum, double numDaysCheckIn, double dayNotation, String roomType, int roomTypeNum) throws FileNotFoundException {
		int counter = 0;
		for (int i = 0; i < this.rooms.length; i++ ) {
			if (this.rooms[i].type == roomType && this.rooms[i].available) {
				for (int j = 0; j < randNum; j++) {
					//call function to generate random name
					String guestName = this.randomName();
					System.out.println(guestName);
					Guest guest = this.createGuest(numDaysCheckIn, guestName);
					this.rooms[i].addGuests(guest);
				}
				System.out.println("The " + randNum + " guests checked in " + this.rooms[i].detailedToString());
				this.rooms[i].setNumDaysStay(numDaysCheckIn);
				this.rooms[i].setTimeAtCheckIn(dayNotation);
				this.rooms[i].setAvailable(false);
				break;
			}
			else if (this.rooms[i].type == roomType && !this.rooms[i].available) {
				counter++;
			}
		}
		if (counter == roomTypeNum) {
			System.out.println("Room unavailable.");
		}
	}
	
	public void determineRoomType(int randNum, double numDaysCheckIn, double dayNotation) throws FileNotFoundException {
		if (0 < randNum && randNum < 3) {
			this.checkIn(randNum, numDaysCheckIn, dayNotation, "Two Guest Room", this.numTwoGuestRoom);
		}
		else if (2 < randNum && randNum < 5) {
			this.checkIn(randNum, numDaysCheckIn, dayNotation, "Four Guest Room", this.numFourGuestRoom);
		}
		else if (4 < randNum && randNum < 9) {
			this.checkIn(randNum, numDaysCheckIn, dayNotation, "Suite Room", this.numSuiteRoom);
		}
	}
	
	public void updateGuestCounters() {
		for (int i = 0; i < this.rooms.length; i++) {
			if (!this.rooms[i].available) {
				this.rooms[i].setCurrentTimeCounter(this.rooms[i].currentTimeCounter + 0.10);
				if (this.rooms[i].currentTimeCounter >= this.rooms[i].timeAtCheckIn + this.rooms[i].numDaysStay) {
					this.rooms[i].checkOut();
				}
			}
		}
	}

}