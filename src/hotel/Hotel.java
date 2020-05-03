package hotel;

import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

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
	int dayRoomsCleaned;
	double initialMoney;
	int numTwoGuestRoom;
	int numFourGuestRoom;
	int numSuiteRoom;
	double currentTime;
	//for option 2, checkIn/checkOut
	ArrayList<String> guestCheckInStatementsLine1;
	ArrayList<String> guestCheckInStatementsLine2;
	ArrayList<String> guestCheckInStatementsLine3;
	ArrayList<String> guestCheckOutStatements;
	//for option 3, staff report
	ArrayList<String> staffReportStatements;
	
	
	public static final int NUM_NAMES = 3000;

	public Hotel(int numTwoGuestRoom, int numFourGuestRoom, int numSuiteRoom, int numStaff) throws FileNotFoundException {
		this.initialMoney = 10000;
		this.random = new Random();
		this.pool = new Pool();
		this.numStaff = numStaff;
		this.restaurant = new Restaurant(numStaff);
		this.numTwoGuestRoom = numTwoGuestRoom;
		this.numFourGuestRoom = numFourGuestRoom;
		this.numSuiteRoom = numSuiteRoom;
		this.rooms = new Room[numTwoGuestRoom + numFourGuestRoom + numSuiteRoom];
		this.staff = new Staff[numStaff];
		this.totalGuests = 0;
		this.totalCustomers = 0;
		this.dayRoomsCleaned = 0;
		this.guestCheckInStatementsLine1 = new ArrayList<String>() ;
		this.guestCheckInStatementsLine2 = new ArrayList<String>() ;
		this.guestCheckInStatementsLine3 = new ArrayList<String>() ;
		this.guestCheckOutStatements = new ArrayList<String>();
		this.staffReportStatements = new ArrayList<String>();
		this.financialAnalysis = new FinancialAnalysis(this.initialMoney, this.rooms.length);
	}
	
	public void setRoomNumbers() {
		for (int i = 0; i < this.rooms.length; i++) {
			this.rooms[i].setRoomNumber(i + 1);
		}
	}

	public void setStaffNumbers() {
		for (int i = 0; i < this.staff.length; i++) {
			this.staff[i] = new Staff();
			this.staff[i].setStaffNumber(i + 1);
		}
	}
	
	public void setCurrentTime(double value) {
		this.currentTime = value;
	}
	
	public double getCurrentTime() {
		return this.currentTime;
	}
	
	public double getInitialMoney() {
		return this.initialMoney;
	}
	
	public void setInitialMoney(int value) {
		this.initialMoney = value;
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
	
	public void printHotel() {
		//test
		//System.out.println("Two Guest Rooms: " + this.numTwoGuestRoom);
		//System.out.println("Four Guest Rooms: " + this.numFourGuestRoom);
		//System.out.println("Suite Rooms: " + this.numSuiteRoom);
		//System.out.println("Staff: " + this.numStaff);
		for(int i = 0; i < this.rooms.length; i++) {
			if(this.rooms[i].available == false) {
				System.out.printf("%-27s %-5s %n",this.rooms[i].detailedToString(), "Occupied");
			}
			else {
				System.out.printf("%-27s %-5s %n",this.rooms[i].detailedToString(), "Available");
			}
			
		}
	}
	
	public Guest createGuest(double timeAtCheckIn, double numDaysCheckIn, String guestName) {
		Guest guest = new Guest(timeAtCheckIn, numDaysCheckIn, guestName);
		this.totalGuests++;
		return guest;
	}
	
	public Customer createCustomer(String guestName) {
		Customer customer = new Customer(guestName);
		//this.totalCustomers++;
		return customer;
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
	
	public void checkIn(int randNum, double numDaysCheckIn, String roomType, int roomTypeNum) throws FileNotFoundException {
		this.guestCheckInStatementsLine1.add("A group of " + randNum + " guest(s) want to check in for " + numDaysCheckIn + " days");
		int unavailableRooms = 0;
		for (int i = 0; i < this.rooms.length; i++ ) {
			if (this.rooms[i].type.equals(roomType)) {
				if (this.rooms[i].available && this.rooms[i].cleaned) {
					for (int j = 0; j < randNum; j++) {
						//call function to generate random name
						String guestName = this.randomName();
						
						Guest guest = this.createGuest(this.currentTime, numDaysCheckIn, guestName);
						this.rooms[i].addGuests(guest);
					}
					//for(int n = 0; n < this.rooms[i].occupants; n++) {
					String names = this.rooms[i].occupants.toString();
					//this.guestCheckInStatementsLine1.add("A group of " + randNum + " guest(s) want to check in for " + numDaysCheckIn + " days");
					this.guestCheckInStatementsLine2.add(names);
					//occupants = "";
					this.guestCheckInStatementsLine3.add("The " + randNum + " guest(s) checked into " + this.rooms[i].detailedToString() + " at " + this.currentTime);
					this.rooms[i].setAvailable(false);
					this.rooms[i].setOccupied(true);
					break;
				}
				else if (!this.rooms[i].available || !this.rooms[i].cleaned) {
					unavailableRooms++;
				}
			}
		}
		if (unavailableRooms == roomTypeNum) {
			this.guestCheckInStatementsLine2.add("");
			this.guestCheckInStatementsLine3.add("Room unavailable.");
		}
	}
	
	public void determineRoomType(int randNum, double numDaysCheckIn) throws FileNotFoundException {
		/*if(randNum > 0) {
			this.guestCheckInStatementsLine1.add("A group of " + randNum + " guest(s) want to check in for " + numDaysCheckIn + " days");
		}*/
		if (0 < randNum && randNum < 3) {
			this.checkIn(randNum, numDaysCheckIn, "Two Guest Room", this.numTwoGuestRoom);
		}
		else if (2 < randNum && randNum < 5) {
			this.checkIn(randNum, numDaysCheckIn, "Four Guest Room", this.numFourGuestRoom);
		}
		else if (4 < randNum && randNum < 9) {
			this.checkIn(randNum, numDaysCheckIn, "Suite Room", this.numSuiteRoom);
		}
	}
	
	public void updateCounters() {
		//updates every Guest in hotel
		String checkOutStatement = "";
		for (int i = 0; i < this.rooms.length; i++) {
			if (!this.rooms[i].available) {
				for (int j = 0; j < this.rooms[i].occupants.size(); j++) {
					//this.rooms[i].occupants.get(j).setCurrentTimeCounter(this.currentTime);
					if (this.currentTime >= this.rooms[i].occupants.get(j).timeAtCheckIn + this.rooms[i].occupants.get(j).numDaysStay) {
						this.rooms[i].checkOut();
						checkOutStatement = this.rooms[i].toString() + " has checked out at " + this.currentTime;
						this.guestCheckOutStatements.add(checkOutStatement);
					} //this.rooms[i].occupants.get(j).currentTimeCounter
				}
			}
		}
		this.restaurant.clearRestaurant();
		this.pool.clearPool();
		for (int i = 0; i < this.rooms.length; i++) {
			for (int j = 0; j < this.rooms[i].occupants.size(); j++) {
				if (this.rooms[i].occupants.get(j).inRestaurant == false && this.rooms[i].occupants.get(j).inPool == false) {
					this.rooms[i].setOccupied(false);
				}
			}
		}
	}
	
	public void movePeople(int guestRestaurantOdds, int customerRestaurantOdds, int guestPoolOdds, int randCustomerNum) throws FileNotFoundException {
		//moves guests
		for (int i = 0; i < this.rooms.length; i++) {
			if (!this.rooms[i].available) {
				if (this.rooms[i].occupied) {
					int guestRestaurantProbability = random.nextInt(10) + 1;
					if (guestRestaurantProbability <= guestRestaurantOdds) {
						/*
						for (int j = 0; j < this.rooms[i].occupants.size(); j++) {
							this.rooms[i].occupants.get(j).setInRestaurant(true);
						}*/
						this.rooms[i].setOccupied(false);
						this.restaurant.addGuestToRestaurant(this.rooms[i].occupants, this.currentTime);
					}
				}
					
				if (this.rooms[i].occupied) {
					int guestPoolProbability = random.nextInt(10) + 1;
					if (guestPoolProbability <= guestPoolOdds) {
						/*
						for (int j = 0; j < this.rooms[i].occupants.size(); j++) {
							this.rooms[i].occupants.get(j).setInPool(true);
						}*/
						this.rooms[i].setOccupied(false);
						this.pool.addToPool(this.rooms[i].occupants, this.currentTime);
					}
				}
			}
		}
		
		//moves customers
		int customerProbability = random.nextInt(10) + 1;
		if (customerProbability <= customerRestaurantOdds) {
			for (int k = 0; k < randCustomerNum; k++) {
				String customerName = this.randomName();
				Customer customer = this.createCustomer(customerName);
				this.restaurant.addCustomerToRestaurant(customer, this.currentTime);
			}
		}
	}
	
	
	
	public void checkInCheckOut() {
		for(int i = 0; i < this.guestCheckInStatementsLine1.size(); i++) {
			System.out.println(this.guestCheckInStatementsLine1.get(i));
			if (this.guestCheckInStatementsLine2.get(i).equals("")) {
				System.out.print(this.guestCheckInStatementsLine2.get(i));
			}
			else {
			System.out.println(this.guestCheckInStatementsLine2.get(i));
			}
			System.out.println(this.guestCheckInStatementsLine3.get(i) + "\r\n");
		}
		for(int i = 0; i < this.guestCheckOutStatements.size(); i++) {
			System.out.println(this.guestCheckOutStatements.get(i));
		}
	}
	
	public void printStaffReport() {
		System.out.println("\r\n" + "***STAFF ACTIVITY REPORT***");
		System.out.println(this.dayRoomsCleaned + " rooms were cleaned: " + "\r\n");
		for (int i = 0; i < this.staffReportStatements.size(); i++) {
			System.out.println(this.staffReportStatements.get(i));
		}
	}
	
	public void cleanRooms() {
		ArrayList<Room> roomsToClean = new ArrayList<Room>();
		for (int i = 0; i < this.rooms.length; i++) {
			if (this.rooms[i].beingCleaned == false && this.rooms[i].cleaned == false) {
				roomsToClean.add(this.rooms[i]);
			}
		}
		ArrayList<Staff> staffReady = new ArrayList<Staff>();
		for (Staff staff : this.staff) {
			if (staff.currentlyCleaning == false) {
				staffReady.add(staff);
			}
		}
		Collections.shuffle(roomsToClean);
		Collections.shuffle(staffReady);
		if (roomsToClean.size() > staffReady.size()) {
			//int difference = roomsToClean.size() - staffReady.size();
			for (int i = 0; i < staffReady.size(); i++) {
				roomsToClean.get(i).addStaffCleaning(staffReady.get(i));
				roomsToClean.get(i).beingCleaned = true;
				staffReady.get(i).setTimeStartedCleaning(this.currentTime);
				staffReady.get(i).setCurrentlyCleaning(true);
				this.staffReportStatements.add(staffReady.get(i) + " started cleaning " + roomsToClean.get(i) + " at " + this.currentTime);
			}
		}
		
		else {
			for (int i = 0; i < roomsToClean.size(); i++) {
				roomsToClean.get(i).addStaffCleaning(staffReady.get(i));
				roomsToClean.get(i).beingCleaned = true;
				staffReady.get(i).setTimeStartedCleaning(this.currentTime);
				staffReady.get(i).setCurrentlyCleaning(true);
				this.staffReportStatements.add(staffReady.get(i) + " started cleaning " + roomsToClean.get(i) + " at " + this.currentTime);
				this.dayRoomsCleaned++;
			}
		}
		
		for (int i = 0; i < this.rooms.length; i++) {
			if (this.rooms[i].beingCleaned) {
				if (this.currentTime >= this.rooms[i].staffCleaning.get(0).timeStartedCleaning + this.rooms[i].cleaningTime) {
					this.rooms[i].setCleaned(true);
					this.rooms[i].beingCleaned = false;
					this.rooms[i].staffCleaning.get(0).setCurrentlyCleaning(false);
					this.staffReportStatements.add(this.rooms[i].staffCleaning.get(0) + " finished cleaning " + this.rooms[i] + " at " + this.currentTime);
					this.dayRoomsCleaned++;
					this.rooms[i].removeStaffCleaning();
				}
			}
		}
	}
	
	public void dirtyRooms() {
		for (int i = 0; i < this.rooms.length; i++) {
			if (this.rooms[i].available == false) {
				this.rooms[i].setCleaned(false);
			}
		}
	}
	
	public void resetDayVariables() {
		this.dayRoomsCleaned = 0;
		this.staffReportStatements.clear();
		this.guestCheckInStatementsLine1.clear();
		this.guestCheckInStatementsLine2.clear();
		this.guestCheckInStatementsLine3.clear();
		this.guestCheckOutStatements.clear();
	}

}