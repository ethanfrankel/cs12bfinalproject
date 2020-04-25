package hotel;

import java.util.Random;

public class HotelSimulation {
	/**
	 *two tick system
	 *day and time
	 *
	 *input number of days for simulation as parameter
	 *
	 *1 = next day, 2 = staff report, 3 = customer report, 4 = sales report
	 */
	private Random random = new Random();
	
	public static void main(String[] args) {
		Hotel hotel = new Hotel(10, 10, 10, 6);
		hotel.setRoomNumbers();
		for (int i = 0; i < hotel.rooms.size(); i++) {
			System.out.println(hotel.rooms.get(i).detailedToString());
		}
	}

}
