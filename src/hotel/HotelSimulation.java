package hotel;

import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
//test
//test 2
public class HotelSimulation {
	/**
	 *two tick system
	 *day and time
	 *
	 *input number of days for simulation as parameter
	 *
	 *1 = next day, 2 = staff activity report, 3 = customer activity report, 4 = sales report
	 * @throws FileNotFoundException 
	 */
	
	public static final int ODDS_GUEST_RESTAURANT = 3;
	public static final int ODDS_CUSTOMER_RESTAURANT = 2;
	public static final int ODDS_GUEST_POOL = 2;
	
	public static void main(String[] args) throws FileNotFoundException {
		
		Random random = new Random();
		
		System.out.println("This is a simulation that tracks the activities of randomly " + "\n" +
				"generated people in a combined hotel and restaurant."+ "\n" +
				"There are three types of rooms. The first can hold two people ($200 per night)," + "\n" +
				"the second can hold four people ($300 per night), and the last is a suit that" + "\n" +
				"can hold eight people ($500 per night). There are also paid staff that make $10 per hour." + "\n" +
				"The simulation will last for a specified number of days, and at the end" + "\n" +
				"there will be both a general overview and a financial report of this time period." + "\n" +
				"At the end of each day, the user will input a number 1-4 that will either" + "\n" +
				"start the next day, access customer/guest activities, access staff activities, or" + "\n" +
				"view the financial activty that transpired that day." + "\r\n");
		
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
		
		Hotel hotel = new Hotel(numTwoGuestRooms, numFourGuestRooms, numSuiteRooms, numStaff);
		hotel.addRooms();
		hotel.setRoomNumbers();
		hotel.setStaffNumbers();
		//for (int i = 0; i < hotel.rooms.size(); i++) {
			//System.out.println(hotel.rooms.get(i).detailedToString());
		//}
		
		String startCommand = "";
		System.out.println("Start simulation (type \"yes\"): ");
		while (!startCommand.equals("yes")) {
			String temp = in.nextLine();
			startCommand = temp;
		}
		for (int i = 1; i < numDays + 1; i++) {
			hotel.dirtyRooms();
			for (int j = 0; j < 10; j++) {
				String dayLittleTick = i + "." + j;
				double dayNotation = Double.parseDouble(dayLittleTick);
				hotel.setCurrentTime(dayNotation);
				//System.out.println("Day " + dayNotation);
				int randNum = generateRandomNumber(random);
				double numDaysCheckIn = (double) random.nextInt(7) + 1;
				//double numDaysCheckIn = (double) random.nextInt(3) + 1;
				
				hotel.updateCounters();	
				
				if (randNum > 0) {
					//System.out.println("A group of " + randNum + " guests want to check in for " + (int) numDaysCheckIn + " days.");
					hotel.determineRoomType(randNum, numDaysCheckIn);
				}
				
				int randCustomerNum = generateRandomNumberCustomer(random);
				hotel.movePeople(ODDS_GUEST_RESTAURANT, ODDS_CUSTOMER_RESTAURANT, ODDS_GUEST_POOL, randCustomerNum);
				
					
			}
			
			System.out.println("Day "+ i + " is over");
			double dayRevenue = hotel.financialAnalysis.dayRevenue(hotel.rooms, hotel.restaurant);
			double dayCosts = hotel.financialAnalysis.dayCosts(hotel.numStaff);
			double currentAccountBalance = hotel.financialAnalysis.calculateCurrentAccountBalance(dayRevenue, dayCosts);
			hotel.clearGuestStatements();
			hotel.restaurant.resetDayVariables();
			hotel.pool.resetDayVariables();
			hotel.cleanRooms();
			if(i < numDays + 1) {
				int choice = userChoices(i, numDays);
				while(choice != 1) {
					if (choice == 2) {
						//customer and guest activity report
						System.out.println("\r\n" + "***Customer and Guest Activity Report***");
						System.out.println("\r\n" + "***Check In/Check Out***");
						hotel.checkInCheckOut();
						System.out.println("\r\n" + "***Rooms Status***");
						hotel.printHotel();
						//check in and check out 
						
						
						//restaurant activity
						hotel.restaurant.printRestaurantActivity();
						
						//pool activity
						hotel.pool.printPoolActivity();
						choice = userChoices(i, numDays);
					}
					else if (choice == 3) {
						//staff activity report
						System.out.println("\r\n" + "***Staff Activity Report***");
						System.out.println("TBD..." + "\r\n");
						choice = userChoices(i, numDays);
					}
					else if (choice == 4) {
						//day financial report
						System.out.println("\r\n" + "***Day " + i + " Financial Report***");
						System.out.printf("%-30s %-10.2f %n", "Day " + i + " revenue: ", dayRevenue);
						System.out.printf("%-30s %-10.2f %n", "Day " + i + " costs: ", dayCosts);
						System.out.printf("%-30s %-10.2f %n", "Current Account Balance: ", currentAccountBalance);
						System.out.println(" ");
						choice = userChoices(i, numDays);
					}
				}
			
			}
			continue;
		}
		System.out.println("The Simulation is Complete!");
		hotel.financialAnalysis.calcExplicitCosts(numStaff, numDays);
		hotel.financialAnalysis.calcTotalRevenue(hotel.initialMoney);
		hotel.financialAnalysis.financialOverview(hotel.numStaff, numDays, hotel.totalGuests, hotel.restaurant.totalServed);
		
		/*
		for (int j = 0; j < hotel.rooms.length; j++) {
			System.out.println(hotel.rooms[j].toStringRoomOccupancy());
			System.out.println(hotel.rooms[j].printGuests());
		}*/
	}
	
	public static int generateRandomNumber(Random random) {
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
		Scanner in = new Scanner(System.in);
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

