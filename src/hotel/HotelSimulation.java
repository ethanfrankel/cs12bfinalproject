package hotel;

import java.util.Random;
import java.util.Scanner;

public class HotelSimulation {
	/**
	 *two tick system
	 *day and time
	 *
	 *input number of days for simulation as parameter
	 *
	 *1 = next day, 2 = staff activity report, 3 = customer activity report, 4 = sales report
	 */
	
	public static void main(String[] args) {
		
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
		for (int i = 0; i < hotel.rooms.size(); i++) {
			System.out.println(hotel.rooms.get(i).detailedToString());
		}
		
		
		for (int i = 1; i < numDays + 1; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.println("Day " + i + "." + j);
				int randNum = generateRandomNumber(random);
				int numDaysCheckIn = random.nextInt(7) + 1;
				if (randNum > 0) {
					System.out.println("A group of " + randNum + " guests want to check in for " + numDaysCheckIn + " days.");
				}
				determineRoomType(hotel, randNum, numDaysCheckIn);
				}

				//people who are gone come back
				//random chance for people to go to restaurant, pool
				//staff clean rooms
				
			}
			//1,2,3,4
			for (int j = 0; j < hotel.rooms.size(); j++) {
				System.out.println(hotel.rooms.get(j).toStringRoomOccupancy());
			}
		}
	
	public static void checkIn(Hotel hotel, int randNum, int numDaysCheckIn, String roomType) {
		for (int i = 0; i < hotel.rooms.size(); i++ ) {
			if (hotel.rooms.get(i).type == roomType && hotel.rooms.get(i).available) {
				for (int j = 0; j < randNum; j++) {
					Guest guest = hotel.createGuest(numDaysCheckIn);
					hotel.rooms.get(i).addGuests(guest);
				}
				break;
			}
		}
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
	
	public static void determineRoomType(Hotel hotel, int randNum, int numDaysCheckIn) {
		if (0 < randNum && randNum < 3) {
			checkIn(hotel, randNum, numDaysCheckIn, "Two Guest Room");
		}
		else if (2 < randNum && randNum < 5) {
			checkIn(hotel, randNum, numDaysCheckIn, "Four Guest Room");
		}
		else if (4 < randNum && randNum < 9) {
			checkIn(hotel, randNum, numDaysCheckIn, "Suite Room");
		}
		for (int a = 0; a < hotel.rooms.size(); a++ ) {
			if (hotel.rooms.get(a).numOfPeople > 0) {
				hotel.rooms.get(a).setAvailable(false);
			}
		}
	}
	


}
