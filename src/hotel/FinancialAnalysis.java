package hotel;

import java.util.ArrayList;

public class FinancialAnalysis {
	
	ArrayList<Room> discountedRooms;
	double dailyFixedHotelCosts; //per big tick
	double totalHotelCosts;
	double dailyFixedRestaurantCosts; //per big tick
	double totalRestaurantCosts;
	double dailyFixedPoolCosts = 50;
	double totalPoolCosts;
	double staffSalary = 150.0; //per big tick per staff member
	double totalStaffComp; //Comp = compensation
	double explicitCosts;//All costs
	double hotelRevenue;
	double restaurantRevenue;
	double totalRevenue;
	double profit;
	double numSmallTicks;
	double currentAccountBalance; //Updated at the end of each day
	
	
	public FinancialAnalysis(double initialMoney, int numRooms) {
		this.discountedRooms = new ArrayList<Room>();
		this.currentAccountBalance = initialMoney; //passed from hotel
		//costs of hotel and restaurant are based on the number of rooms
		this.dailyFixedHotelCosts = numRooms * 14;
		this.dailyFixedRestaurantCosts = numRooms * 8;
	}
	
	public double dayRevenue(Room[] rooms, Restaurant restaurant) {
		/**
		 * Calculates the day's revenue
		 * hotel room sales
		 * 
		 * restaurant sales
		 */
		double roomEarnings = 0;
		double restaurantEarnings = 0;
		for (int i = 0; i < rooms.length; i++) {
			if (!rooms[i].available) { //if room is occupied
				if (!rooms[i].cleaned) {
					roomEarnings = roomEarnings + (rooms[i].price * 0.75); //25% discount if room was dirty
					this.discountedRooms.add(rooms[i]);
				}
				else {
				roomEarnings = roomEarnings + rooms[i].price; //price specific to room type
				}
			}
		}
		restaurantEarnings = restaurant.daySales;
		double dayRevenue = roomEarnings + restaurantEarnings;
		return dayRevenue;
	}

	public double dayCosts(int numStaff) {
		/**
		 * Calculates the day's costs
		 * fixed hotel costs
		 * fixed restaurant costs
		 * fixed pool costs
		 * staff compensation
		 */
		double dayCosts = this.dailyFixedHotelCosts + this.dailyFixedRestaurantCosts + this.dailyFixedPoolCosts + (this.staffSalary * numStaff);
		return dayCosts;
	}
	
	public double calculateCurrentAccountBalance(double dayRevenue, double dayCosts) {
		/**
		 * calculates and updates the current balance 
		 * add profit or subtract loss (if profit is negative)
		 */
		this.currentAccountBalance = this.currentAccountBalance + dayRevenue - dayCosts;
		return this.currentAccountBalance;
	}

	
	public void calcExplicitCosts(int numStaff, int numDays) { 
		/**
		 * Calculates total explicit costs at the end of the simulation
		 * explicit costs include total hotel, restaurant, and staff costs/compensation 
		 */
		this.totalStaffComp = this.staffSalary * numStaff * numDays;
		this.totalHotelCosts = this.dailyFixedHotelCosts * numDays;
		this.totalRestaurantCosts = this.dailyFixedRestaurantCosts * numDays;
		this.totalPoolCosts = this.dailyFixedPoolCosts * numDays;
		this.explicitCosts = this.totalHotelCosts + this.totalRestaurantCosts + this.totalPoolCosts + this.totalStaffComp;
	}
	
	
	public void calcTotalRevenue(double initialMoney) { 
		/**
		 * calculates total revenue from all sources
		 * total hotel room sales
		 * total restaurant sales
		 */
		this.totalRevenue = this.currentAccountBalance - initialMoney + this.explicitCosts;
	}
	
	public void resetDayVariables() {
		/**
		 * clear discountedRooms, which changes every day
		 */
		this.discountedRooms.clear();
	}
	
	public void printFinancialReport(int currentDay, double dayRevenue, double dayCosts, double currentAccountBalance) {
		/**
		 * prints financial report when called
		 * prints day's financial activity: revenue, costs, current account balance
		 */
		System.out.println("\r\n" + "***DAY " + currentDay + " FINANCIAL REPORT***" + "\r\n");
		System.out.println("Discounted rooms: " + this.discountedRooms.toString());
		System.out.printf("%-30s %-10.2f %n", "Day " + currentDay + " revenue: ", dayRevenue);
		System.out.printf("%-30s %-10.2f %n", "Day " + currentDay + " costs: ", dayCosts);
		System.out.printf("%-30s %-10.2f %n", "Current Account Balance: ", currentAccountBalance);
		System.out.println(" ");
	}

	
	public void financialOverview(int numStaff, int numDays, int totalGuests, int totalServed) {
		/**
		 * calculates profit
		 * prints hotel overview nicely
		 * prints financial overview nicely
		 */
		this.profit = this.totalRevenue - this.explicitCosts;
		System.out.println("\r\n " + "***Overview of Hotel Opporations***");
		System.out.println("Total Hotel Guests: " + totalGuests);
		System.out.println("Total People Served at Restaurant: " + totalServed);
		System.out.println("Total Paid Staff: " + numStaff);
		System.out.println("Length of Simulation: " + numDays + " day(s)");
		System.out.println("\r\n " + "***Financial Overview***");
		System.out.printf("%-30s %-10.2f %n", "Total Revenue: ", this.totalRevenue);
		System.out.printf("%-30s %-10.2f %n", "Total (explicit) Costs: ", this.explicitCosts);
		System.out.printf("%-30s %-10.2f %n", "Total Profit: ", this.profit);
		System.out.println("");
		System.out.printf("%-30s %-10.2f %n", "Final Account Balance: ", this.currentAccountBalance);
		
		
	}

}
