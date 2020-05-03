package hotel;

import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Hotel {
	FinancialAnalysis financialAnalysis;
	Random random;
	Room[] rooms;
	Restaurant restaurant;
	Pool pool;
	//Staff[] staff;
	int numStaff;
	int totalGuests;
	int totalCustomers;
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
		//this.staff = new Staff[this.numStaff];
		this.totalGuests = 0;
		this.totalCustomers = 0;
		this.guestCheckInStatementsLine1 = new ArrayList<String>() ;
		this.guestCheckInStatementsLine2 = new ArrayList<String>() ;
		this.guestCheckInStatementsLine3 = new ArrayList<String>() ;
		this.guestCheckOutStatements = new ArrayList<String>();
		this.financialAnalysis = new FinancialAnalysis(this.initialMoney, this.rooms.length);
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
		int counter = 0;
		for (int i = 0; i < this.rooms.length; i++ ) {
			if (this.rooms[i].type == roomType && this.rooms[i].available) {
				for (int j = 0; j < randNum; j++) {
					//call function to generate random name
					String guestName = this.randomName();
					
					Guest guest = this.createGuest(this.currentTime, numDaysCheckIn, guestName);
					this.rooms[i].addGuests(guest);
				}
				String occupants = "";
				//for(int n = 0; n < this.rooms[i].occupants; n++) {
				occupants = this.rooms[i].occupants.toString();
				this.guestCheckInStatementsLine1.add("A group of " + randNum + " guest(s) want to check in for " + numDaysCheckIn + " days");
				this.guestCheckInStatementsLine2.add(occupants);
				//occupants = "";
				this.guestCheckInStatementsLine3.add("The " + randNum + " guest(s) checked into " + this.rooms[i].detailedToString() + " at " + this.currentTime);
				this.rooms[i].setAvailable(false);
				this.rooms[i].setOccupied(true);
				break;
			}
			else if (this.rooms[i].type == roomType && !this.rooms[i].available) {
				counter++;
			}
		}
		if (counter == roomTypeNum) {
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
			this.checkIn(randNum, numDaysCheckIn, "Suite", this.numSuiteRoom);
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
						checkOutStatement = this.rooms[i].toString() + " has checked out.";
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
			System.out.println(this.guestCheckInStatementsLine2.get(i));
			System.out.println(this.guestCheckInStatementsLine3.get(i) + "\r\n");
		}
		for(int i = 0; i < this.guestCheckOutStatements.size(); i++) {
			System.out.println(this.guestCheckOutStatements.get(i));
		}
	}
	
	public void cleanRooms() {
		for (int i = 0; i < this.numStaff; i++) {
			int roomIndex = random.nextInt(this.rooms.length + 1);
			
		}
	}
	
	public void clearGuestStatements() {
		this.guestCheckInStatementsLine1.clear();
		this.guestCheckInStatementsLine2.clear();
		this.guestCheckInStatementsLine3.clear();
		this.guestCheckOutStatements.clear();
	}

}