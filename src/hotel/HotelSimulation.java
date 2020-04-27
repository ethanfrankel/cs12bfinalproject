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
	private Random random = new Random();
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		System.out.println("Number of Rooms holding two guests max: ");
		int numTwoGuestRooms = in.nextInt();
		
		System.out.println("Number of Rooms holding four guests max: ");
		int numFourGuestRooms = in.nextInt();
		
		System.out.println("Number of Rooms holding eight guests max: ");
		int numSuiteRooms = in.nextInt();
		
		System.out.println("Number of hotel staff: ");
		int numStaff = in.nextInt();
		
		Hotel hotel = new Hotel(numTwoGuestRooms, numFourGuestRooms, numSuiteRooms, numStaff);
		hotel.addRooms();
		hotel.setRoomNumbers();
		for (int i = 0; i < hotel.rooms.size(); i++) {
			System.out.println(hotel.rooms.get(i).detailedToString());
		}
	}

}
