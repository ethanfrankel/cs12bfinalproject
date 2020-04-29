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
	
	public static final int PROBABILITY_GUEST_RESTAURANT = 2;
	public static final int PROBABILITY_CUSTOMER_RESTAURANT = 4;
	
	public static void main(String[] args) throws FileNotFoundException {
		
		Random random = new Random();
		
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
		//for (int i = 0; i < hotel.rooms.size(); i++) {
			//System.out.println(hotel.rooms.get(i).detailedToString());
		//}
		
		System.out.println("This is a hotel simulation that keeps track of people's activities in the hotel as well as a financial report.");
		String startCommand = "";
		System.out.println("Start simulation (type \"yes\"): ");
		while (!startCommand.equals("yes")) {
			String temp = in.nextLine();
			startCommand = temp;
		}
		for (int i = 1; i < numDays + 1; i++) {
			for (int j = 0; j < 10; j++) {
				String dayLittleTick = i + "." + j;
				double dayNotation = Double.parseDouble(dayLittleTick);
				System.out.println("Day " + dayNotation);
				int randNum = generateRandomNumber(random);
				//double numDaysCheckIn = (double) random.nextInt(7) + 1;
				double numDaysCheckIn = (double) random.nextInt(3) + 1;
				
				hotel.restaurant.payMeals();
				hotel.updateCounters();	
				//updateCustomerCounters
				
				if (randNum > 0) {
					System.out.println("A group of " + randNum + " guests want to check in for " + (int) numDaysCheckIn + " days.");
					hotel.determineRoomType(randNum, numDaysCheckIn, dayNotation);
				}
				
				int randCustomerNum = generateRandomNumberCustomer(random);
				hotel.handleCustomers(PROBABILITY_CUSTOMER_RESTAURANT, randCustomerNum);
				
					
			}
			hotel.restaurant.resetDaySales();
			System.out.println("Day "+ i + " is over");
			int choice = userChoices();
			boolean calledToday = false;
			while(choice != 1) {
				if (choice == 2) {
					//customer activity report
					choice = userChoices();
				}
				else if (choice == 3) {
					//staff activity report
					choice = userChoices();
				}
				else if (choice == 4) {
					double dayRevenue = hotel.financialAnalysis.dayRevenue(hotel.rooms, hotel.restaurant);
					double dayCosts = hotel.financialAnalysis.dayCosts(hotel.numStaff);
					double currentAccountBalance = hotel.financialAnalysis.calculateCurrentAccountBalance(dayRevenue, dayCosts, calledToday);
					calledToday = true;
					System.out.println("\r\n" + "***Day " + i + " Financial Report***");
					System.out.printf("%-30s %-10.2f %n", "Day " + i + " revenue: ", dayRevenue);
					//System.out.println("Day " + i + " revenue: " + dayRevenue);
					System.out.printf("%-30s %-10.2f %n", "Day " + i + " costs: ", dayCosts);
					//System.out.println("Day " + i + " costs: " + dayCosts);
					System.out.printf("%-30s %-10.2f %n", "Current Account Balance: ", currentAccountBalance);
					System.out.println(" ");
					//System.out.println("Current Account Balance: " + currentAccountBalance);
					choice = userChoices();
				}
			}
			continue;
				//random chance for people to go to restaurant, pool
				//staff clean rooms
			
			//1,2,3,4
		}
		hotel.financialAnalysis.calcExplicitCosts(numStaff, numDays);
		hotel.financialAnalysis.calcTotalRevenue(hotel.initialMoney);
		hotel.financialAnalysis.financialOverview(hotel.numStaff, numDays, hotel.totalGuests, 100);
		
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
	
	public static int userChoices() {
		Scanner in = new Scanner(System.in);
		System.out.println("Type: [1] Start next day [2] Customer Activity Report [3] Staff Activity Report [4] Day Financial Report");
		int choice = in.nextInt();
		return choice;
	}
	
}

