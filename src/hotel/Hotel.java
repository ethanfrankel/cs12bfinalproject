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
	int dayRoomsCleaned; //total number of rooms cleaned per day
	double initialMoney; //money the hotel initially starts with
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
	
	public static final int NUM_NAMES = 3000; //maximum number of names in text file of names

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
		/**
		 * sets a room number for every room
		 */
		for (int i = 0; i < this.rooms.length; i++) {
			this.rooms[i].setRoomNumber(i + 1);
		}
	}

	public void setStaffNumbers() {
		/**
		 * sets a staff number for every staff member
		 */
		for (int i = 0; i < this.staff.length; i++) {
			this.staff[i] = new Staff();
			this.staff[i].setStaffNumber(i + 1);
		}
	}
	
	//setters and getters here 
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
		/**
		 * method that adds the number of each type of room to the hotel using for loops
		 * 	in order of two guest rooms, four guest rooms, suite rooms
		 */
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
		/**
		 *  method that prints every room and if they are occupied or available for check-in
		 */
		System.out.println("\r\n" + "***Rooms Status***");
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
		/**
		 * method that creates a Guest and adds them to the total number of guests in hotel
		 */
		Guest guest = new Guest(timeAtCheckIn, numDaysCheckIn, guestName);
		this.totalGuests++;
		return guest;
	}
	
	public Customer createCustomer(String guestName) {
		/**
		 * method that creates a Customer
		 */
		Customer customer = new Customer(guestName);
		return customer;
	}
	
	public String randomName() throws FileNotFoundException {
		/**
		 * method that determines a random name to assign to a guest by reading through a text file of names
		 * stops at a random point to choose name for Guest
		 */
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
		/**
		 * method that checks people into a room after determining the type of room
		 * can check into room if it is available and clean
		 * if no rooms are available and clean, the Guests cannot check in
		 */
		this.guestCheckInStatementsLine1.add("A group of " + randNum + " guest(s) want to check in for " + numDaysCheckIn + " days");
		//counter keeps track of number of unavailable rooms
		int unavailableRooms = 0;
		for (int i = 0; i < this.rooms.length; i++ ) {
			if (this.rooms[i].type.equals(roomType)) {
				if (this.rooms[i].available && this.rooms[i].cleaned) {
					for (int j = 0; j < randNum; j++) {
						//if there is an available room, call method to generate random name for each guest,
						//create the guest, and add them to the room
						String guestName = this.randomName();
						
						Guest guest = this.createGuest(this.currentTime, numDaysCheckIn, guestName);
						this.rooms[i].addGuests(guest);
					}
					//add guests to check-in statements in report, set status of room
					String names = this.rooms[i].occupants.toString();
					this.guestCheckInStatementsLine2.add(names);
					this.guestCheckInStatementsLine3.add("The " + randNum + " guest(s) checked into " + this.rooms[i].detailedToString() + " at " + this.currentTime);
					this.rooms[i].setAvailable(false);
					this.rooms[i].setOccupied(true);
					break;
				}
				//if the room is unavailable, counter goes up
				else if (!this.rooms[i].available || !this.rooms[i].cleaned) {
					unavailableRooms++;
				}
			}
		}
		//if no rooms are available, say so
		if (unavailableRooms == roomTypeNum) {
			this.guestCheckInStatementsLine2.add("");
			this.guestCheckInStatementsLine3.add("Room unavailable.");
		}
	}
	
	public void determineRoomType(int randNum, double numDaysCheckIn) throws FileNotFoundException {
		/**
		 * method that matches the number of guests wanting to check in with the corresponding type of room
		 * 	1-2 guests = two guest room
		 * 	3-4 guests = four guest room
		 *  4-8 guests = suite room
		 * then, call checkIn method
		 */
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
		/**
		 * method that updates counters of guests and places in hotel by small tick
		 * checks people out of they stayed for the number of days stayed after checking in
		 * clears restaurant and pool occupants every small tick
		 */
		String checkOutStatement = "";
		for (int i = 0; i < this.rooms.length; i++) {
			if (!this.rooms[i].available) {
				//if a room has guests staying in it, if the guests stayed for allotted days, check them out of room
				for (int j = 0; j < this.rooms[i].occupants.size(); j++) {
					if (this.currentTime >= this.rooms[i].occupants.get(j).timeAtCheckIn + this.rooms[i].occupants.get(j).numDaysStay) {
						this.rooms[i].checkOut();
						checkOutStatement = this.rooms[i].toString() + " has checked out at " + this.currentTime;
						this.guestCheckOutStatements.add(checkOutStatement);
					}
				}
			}
		}
		this.restaurant.clearRestaurant();
		this.pool.clearPool();
		//for loop changes status of room if people leave the restaurant or pool and are back in the room
		for (int i = 0; i < this.rooms.length; i++) {
			for (int j = 0; j < this.rooms[i].occupants.size(); j++) {
				if (this.rooms[i].occupants.get(j).inRestaurant == false && this.rooms[i].occupants.get(j).inPool == false) {
					this.rooms[i].setOccupied(false);
				}
			}
		}
	}
	
	public void movePeople(int guestRestaurantOdds, int customerRestaurantOdds, int guestPoolOdds, int randCustomerNum) throws FileNotFoundException {
		/**
		 * method that moves people and customers every small tick
		 * 	30% chance for each room to go to restaurant if they are not in restaurant or pool
		 *  20% chance for each room to go to pool if they are not in restaurant or pool
		 *  20% chance for random number of customers to go to restaurant
		 */
		//moves guests to restaurant and pool
		for (int i = 0; i < this.rooms.length; i++) {
			if (!this.rooms[i].available) {
				if (this.rooms[i].occupied) {
					int guestRestaurantProbability = random.nextInt(10) + 1;
					if (guestRestaurantProbability <= guestRestaurantOdds) {
						//change status when they move
						this.rooms[i].setOccupied(false);
						this.restaurant.addGuestToRestaurant(this.rooms[i].occupants, this.currentTime);
					}
				}
					
				if (this.rooms[i].occupied) {
					int guestPoolProbability = random.nextInt(10) + 1;
					if (guestPoolProbability <= guestPoolOdds) {
						//change status when they move
						this.rooms[i].setOccupied(false);
						this.pool.addToPool(this.rooms[i].occupants, this.currentTime);
					}
				}
			}
		}
		
		//moves customers to restaurant
		int customerProbability = random.nextInt(10) + 1;
		if (customerProbability <= customerRestaurantOdds) {
			for (int k = 0; k < randCustomerNum; k++) {
				//if probability happens, generates random name for customers, creates them, and adds them to restaurant
				String customerName = this.randomName();
				Customer customer = this.createCustomer(customerName);
				this.restaurant.addCustomerToRestaurant(customer, this.currentTime);
			}
		}
	}
	
	
	
	public void checkInCheckOut() {
		/**
		 * method that prints check in and check out report
		 * every time guests check into or check out of the hotel during the day
		 * prints names of guests, time checked in/checked out, room checked into/out of
		 */
		System.out.println("\r\n" + "***Check In/Check Out***");
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
		/**
		 * method that prints staff report
		 * every room the staff started and finished cleaning each day
		 * prints number of staff member and what room they started/finished cleaning
		 */
		System.out.println("\r\n" + "***STAFF ACTIVITY REPORT***");
		System.out.println(this.dayRoomsCleaned + " rooms were cleaned: " + "\r\n");
		for (int i = 0; i < this.staffReportStatements.size(); i++) {
			System.out.println(this.staffReportStatements.get(i));
		}
	}
	
	public void cleanRooms() {
		/**
		 * method that cleans the rooms in the hotel
		 * assigns random staff member to clean random room if the room is not clean and not being cleaned currently
		 * if a staff member is assigned a room, change statuses of staff member and room
		 * records times staff member starts and ends cleaning
		 */
		
		//creates an ArrayList of rooms that need to be cleaned based on fulfilling criteria:
		//not clean and not currently being cleaned
		ArrayList<Room> roomsToClean = new ArrayList<Room>();
		for (int i = 0; i < this.rooms.length; i++) {
			if (this.rooms[i].beingCleaned == false && this.rooms[i].cleaned == false) {
				roomsToClean.add(this.rooms[i]);
			}
		}
		
		//creates an ArrayList of staff that are not busy cleaning, which are able to clean a room
		ArrayList<Staff> staffReady = new ArrayList<Staff>();
		for (Staff staff : this.staff) {
			if (staff.currentlyCleaning == false) {
				staffReady.add(staff);
			}
		}
		//randomizes order of each list to assign random staff member to random room
		Collections.shuffle(roomsToClean);
		Collections.shuffle(staffReady);

		if (roomsToClean.size() > staffReady.size()) {
			//iterate over staff if there are less staff available than dirty rooms
			for (int i = 0; i < staffReady.size(); i++) {
				roomsToClean.get(i).addStaffCleaning(staffReady.get(i));
				roomsToClean.get(i).beingCleaned = true;
				staffReady.get(i).setTimeStartedCleaning(this.currentTime);
				staffReady.get(i).setCurrentlyCleaning(true);
				this.staffReportStatements.add(staffReady.get(i) + " started cleaning " + roomsToClean.get(i) + " at " + this.currentTime);
			}
		}
		
		else {
			//iterate over rooms if there are less rooms available than staff
			for (int i = 0; i < roomsToClean.size(); i++) {
				roomsToClean.get(i).addStaffCleaning(staffReady.get(i));
				roomsToClean.get(i).beingCleaned = true;
				staffReady.get(i).setTimeStartedCleaning(this.currentTime);
				staffReady.get(i).setCurrentlyCleaning(true);
				this.staffReportStatements.add(staffReady.get(i) + " started cleaning " + roomsToClean.get(i) + " at " + this.currentTime);
			}
		}
		
		//for every room being cleaned:
		//if room has been cleaned for allotted time, change status of room and remove staff member, add to number of rooms cleaned during day
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
		/**
		 * method that makes every room not clean during the morning if guests are staying in the room
		 */
		for (int i = 0; i < this.rooms.length; i++) {
			if (this.rooms[i].available == false) {
				this.rooms[i].setCleaned(false);
			}
		}
	}
	
	public void resetDayVariables() {
		/**
		 * method that resets all of the variables that change during the day
		 */
		this.dayRoomsCleaned = 0;
		this.staffReportStatements.clear();
		this.guestCheckInStatementsLine1.clear();
		this.guestCheckInStatementsLine2.clear();
		this.guestCheckInStatementsLine3.clear();
		this.guestCheckOutStatements.clear();
	}

}