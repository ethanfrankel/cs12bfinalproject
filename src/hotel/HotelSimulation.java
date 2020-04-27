package hotel;

import java.util.Random;
import java.util.Scanner;
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
		//for (int i = 0; i < hotel.rooms.size(); i++) {
			//System.out.println(hotel.rooms.get(i).detailedToString());
		//}
		
		for (int i = 1; i < numDays + 1; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.println("Day " + i + "." + j);
				int randNum = generateRandomNumber(random);
				//int numDaysCheckIn = random.nextInt(7) + 1;
				int numDaysCheckIn = random.nextInt(3) + 1;
				if (randNum > 0) {
					System.out.println("A group of " + randNum + " guests want to check in for " + numDaysCheckIn + " days.");
				}
				hotel.determineRoomType(randNum, numDaysCheckIn);	
			}
				//people who are gone come back
				//random chance for people to go to restaurant, pool
				//staff clean rooms
			hotel.updateGuestCounters();	
		}
			//1,2,3,4
		
		for (int j = 0; j < hotel.rooms.length; j++) {
			System.out.println(hotel.rooms[j].toStringRoomOccupancy());
		}
		System.out.println("done");
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
}
