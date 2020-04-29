package hotel;

public class FinancialAnalysis {
	
	double dailyFixedHotelCosts = 300.0; //per big tick
	double totalHotelCosts;
	double dailyFixedRestaurantCosts = 150.0; //per big tick
	double totalRestaurantCosts;
	double staffSalary = 100.0; //per big tick per staff member
	double totalStaffComp; //Comp = compensation
	double explicitCosts;//All costs
	double hotelRevenue;
	double restaurantRevenue;
	double totalRevenue;
	double profit;
	double numSmallTicks;
	double currentAccountBalance; //Updated at the end of each day
	
	
	public FinancialAnalysis(double initialMoney) {
		this.currentAccountBalance = initialMoney;//passed from hotel
	}
	
	public double dayRevenue(Room[] rooms, Restaurant restaurant) { //add restaurant calcs
		/**
		 * Calculates the day's revenue
		 * hotel room sales
		 * restaurant sales
		 */
		double roomEarnings = 0;
		double restaurantEarnings = 0;
		for (int i = 0; i < rooms.length; i++) {
			if (!rooms[i].available) {//if room is occupied
				roomEarnings = roomEarnings + rooms[i].price;//price specific to room type
			}
		}
		//restaurant calcs
		double dayRevenue = roomEarnings + restaurantEarnings;
		return dayRevenue;
	}

	public double dayCosts(int numStaff) {
		/**
		 * Calculates the day's costs
		 * fixed hotel costs
		 * fixed restaurant costs
		 * staff compensation
		 */
		double dayCosts = this.dailyFixedHotelCosts + this.dailyFixedRestaurantCosts + (this.staffSalary * numStaff);
		return dayCosts;
	}
	
	public double calculateCurrentAccountBalance(double dayRevenue, double dayCosts, boolean calledToday) {
		/**
		 * calculates and updates the current balance 
		 * add profit or subtract loss (if profit is negative)
		 */
		if(calledToday == false) {//avoid updates to balance multiple times in one day tick
			this.currentAccountBalance = this.currentAccountBalance + dayRevenue - dayCosts;
		}
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
		this.explicitCosts = this.totalHotelCosts + this.totalRestaurantCosts + this.totalStaffComp;
	}
	
	
	public void calcTotalRevenue(double initialMoney) { 
		/**
		 * calculates total revenue from all sources
		 * total hotel room sales
		 * total restaurant sales
		 */
		this.totalRevenue = this.currentAccountBalance - initialMoney + this.explicitCosts;
	}

	
	public void financialOverview(int numStaff, int numDays, int totalGuests, int totalCustomers) {
		/**
		 * calculates profit
		 * prints hotel overview nicely
		 * prints financial overview nicely
		 */
		this.profit = this.totalRevenue - this.explicitCosts;
		System.out.println("\r\n " + "***Overview of Hotel Opporations***");
		System.out.println("Total Hotel Guests: " + totalGuests);
		System.out.println("Total Restaurant Customers: " + totalCustomers);
		System.out.println("Total Paid Staff: " + numStaff);
		System.out.println("Length of Simulation: " + numDays + " day(s)");
		System.out.println("\r\n " + "***Financial Overview***");
		System.out.printf("%-30s %-10.2f %n", "Total Revenue: ", this.totalRevenue);
		System.out.printf("%-30s %-10.2f %n", "Total (explicit) Costs: ", this.explicitCosts);
		System.out.printf("%-30s %-10.2f %n", "Total Profit: ", this.profit);
		System.out.println("\r\n" + "Current Account Balance of the Hotel: " + this.currentAccountBalance);
		
		
	}

}
