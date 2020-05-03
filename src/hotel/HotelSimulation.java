package hotel;

import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
//test
//test 2
public class HotelSimulation {
	/**
	 *HotelSimulation runs the simulation
	 * @throws FileNotFoundException 
	 */
	
	//chance for guests and customers to go to certain places (out of 10)
	public static final int ODDS_GUEST_RESTAURANT = 3;
	public static final int ODDS_CUSTOMER_RESTAURANT = 2;
	public static final int ODDS_GUEST_POOL = 2;
	
	public static void main(String[] args) throws FileNotFoundException {
		
		Random random = new Random();
		
		System.out.println("This is a simulation that tracks the activities of randomly " + "\n" +
				"generated people in a combined hotel and restaurant."+ "\n" +
				"There are three types of rooms. The first can hold two people ($200 per night)," + "\n" +
				"the second can hold four people ($300 per night), and the last is a suite that" + "\n" +
				"can hold eight people ($500 per night). There are also paid staff that make $15 per hour." + "\n" +
				"The simulation will last for a specified number of days, and at the end" + "\n" +
				"there will be both a general overview and a financial report of this time period." + "\n" +
				"At the end of each day, the user will input a number 1-4 that will either" + "\n" +
				"start the next day, access customer/guest activities, access staff activities, or" + "\n" +
				"view the financial activty that transpired that day." + "\r\n");
		
		//inputs ask for number of each type of room, staff, and days to start simulation
		Scanner in = new Scanner(System.in);
		System.out.println("Number of Rooms holding up to two guests max: ");
		int numTwoGuestRooms = in.nextInt();
		
		System.out.println("Number of Rooms holding up to four guests max: ");
		int numFourGuestRooms = in.nextInt();
		
		System.out.println("Number of Rooms holding up to eight guests max: ");
		int numSuiteRooms = in.nextInt();
		
		System.out.println("Number of hotel staff: ");
		int numStaff = in.nextInt();
		
		System.out.println("Number of days for the simulation to run: ");
		int numDays = in.nextInt();
		
		//creates hotel and initializes rooms and staff
		Hotel hotel = new Hotel(numTwoGuestRooms, numFourGuestRooms, numSuiteRooms, numStaff);
		hotel.addRooms();
		hotel.setRoomNumbers();
		hotel.setStaffNumbers();
		
		//loop to start simulation once user types in "yes"
		String startCommand = "";
		System.out.println("Start simulation (type \"yes\"): ");
		while (!startCommand.equals("yes")) {
			String temp = in.nextLine();
			startCommand = temp;
		}
		
		//this is the dual tick system that runs the simulation
		//the first for loop iterates over every day
		//the second for loop iterates over small/"hour" ticks (there are 10 per day)
		for (int i = 1; i < numDays + 1; i++) {
			//at the beginning of the day, certain hotel rooms become dirty
			hotel.dirtyRooms();
			for (int j = 0; j < 10; j++) {
				//keeps track of current time and day
				String dayLittleTick = i + "." + j;
				double dayNotation = Double.parseDouble(dayLittleTick);
				hotel.setCurrentTime(dayNotation);
				
				//generates random number of guests that want to check in
				int randNum = generateRandomNumber(random);
				//max check-in days
				double numDaysCheckIn = (double) random.nextInt(7) + 1;
				//double numDaysCheckIn = (double) random.nextInt(3) + 1;
				//double numDaysCheckIn = (double) random.nextInt(5) + 1;
				//double numDaysCheckIn = (double) random.nextInt(14) + 1;
				
				hotel.updateCounters();	
				
				//determines what room guests will check into (if able)
				if (randNum > 0) {
					hotel.determineRoomType(randNum, numDaysCheckIn);
				}
				
				//generates random number of customers
				int randCustomerNum = generateRandomNumberCustomer(random);
				
				//every small tick, people move and rooms are cleaned
				hotel.movePeople(ODDS_GUEST_RESTAURANT, ODDS_CUSTOMER_RESTAURANT, ODDS_GUEST_POOL, randCustomerNum);
				hotel.cleanRooms();		
			}
			
			//end of each day, calculate day revenue, costs, and balance of hotel
			System.out.println("Day "+ i + " is over");
			double dayRevenue = hotel.financialAnalysis.dayRevenue(hotel.rooms, hotel.restaurant);
			double dayCosts = hotel.financialAnalysis.dayCosts(hotel.numStaff);
			double currentAccountBalance = hotel.financialAnalysis.calculateCurrentAccountBalance(dayRevenue, dayCosts);
			
			//Access day reports
			//at the end of every day, the user can choose to access different reports on hotel operations
			if(i < numDays + 1) {
				int choice = userChoices(i, numDays);
				while(choice != 1) {
					if (choice == 2) {
						//customer and guest activity report and check in and check out 
						System.out.println("\r\n" + "***CUSTOMER AND GUEST REPORT***");
						hotel.checkInCheckOut();
						hotel.printHotel();
						
						//restaurant activity
						hotel.restaurant.printRestaurantActivity();
						
						//pool activity
						hotel.pool.printPoolActivity();
						choice = userChoices(i, numDays);
					}
					else if (choice == 3) {
						//staff activity report
						hotel.printStaffReport();
						choice = userChoices(i, numDays);
					}
					else if (choice == 4) {
						//day financial report
						hotel.financialAnalysis.printFinancialReport(i, dayRevenue, dayCosts, currentAccountBalance);
						choice = userChoices(i, numDays);
					}
				}
			}
		
			//if the user types 1, the simulation continues to the next day
			//after all reports have been accessed, the simulation clears the variables that change every day
			hotel.resetDayVariables();
			hotel.financialAnalysis.resetDayVariables();
			hotel.restaurant.resetDayVariables();
			hotel.pool.resetDayVariables();
			continue;
		}
		
		//after the simulation ends, print out final report of hotel
		System.out.println("The Simulation is Complete!");
		hotel.financialAnalysis.calcExplicitCosts(numStaff, numDays);
		hotel.financialAnalysis.calcTotalRevenue(hotel.initialMoney);
		hotel.financialAnalysis.financialOverview(hotel.numStaff, numDays, hotel.totalGuests, hotel.restaurant.totalServed);
	}
	
	public static int generateRandomNumber(Random random) {
		/**
		 * method that generates a random number of guests from 1-8 and returns it
		 * Probability:
		 * 	0 people: 50%
		 * 	1-2 people: 20%
		 * 	3-4 people: 20%
		 * 	4-8 people: 10%
		 */
		int randNum = 0;
		int randPercentage = random.nextInt(10);
		if (0 <= randPercentage && randPercentage < 5) {
			randNum = 0;
		}
		else if (4 < randPercentage && randPercentage < 7) {
			randNum = random.nextInt(2) + 1;
		}
		else if (6 < randPercentage && randPercentage < 9) {
			randNum = random.nextInt(2) + 3;
		}
		else if (randPercentage == 9) {
			randNum = random.nextInt(4) + 5;
		}
		return randNum;
	}
	
	public static int generateRandomNumberCustomer(Random random) {
		/**
		 * method that generates a random number of customers from 1-8 and returns it
		 * 	1-2 customers: 40%
		 *  3-4 customers: 40%
		 *  4-8 customers: 20%
		 */
		int randCustomerNum = 0;
		int randPercentage = random.nextInt(5);
		if (0 <= randPercentage && randPercentage < 2) {
			randCustomerNum = random.nextInt(2) + 1;
		}
		else if (2 <= randPercentage && randPercentage < 4) {
			randCustomerNum = random.nextInt(2) + 3;
		}
		else if (randPercentage == 4) {
			randCustomerNum = random.nextInt(4) + 5;
		}
		return randCustomerNum;
	}
	
	public static int userChoices(int currentDay, int totalDays) {
		/**
		 * method that lets the user input what reports to see at the end of the day or continue
		 */
		Scanner in = new Scanner(System.in);
		//after the last day, prints a special line to end simulation
		if (currentDay == totalDays) {
			System.out.println("\r\n" + "Type: [1] End Simulation  [2] Customer and Guest Report  [3] Staff Activity Report  [4] Day Financial Report");
		}
		else {
			System.out.println("\r\n" + "Type: [1] Start next day  [2] Customer and Guest Report  [3] Staff Activity Report  [4] Day Financial Report");
		}
		int choice = in.nextInt();
		return choice;
	}
	
}