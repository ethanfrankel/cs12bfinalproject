package hotel;

public class FinancialAnalysis {
	
	double dailyFixedHotelCosts = 300.0; //per big tick
	double totalHotelCosts;
	double dailyFixedRestaurantCosts = 150.0; //per big tick
	double totalRestaurantCosts;
	double staffSalary = 100.0; //per big tick
	double totalStaffComp; //comp=compensation
	double explicitCosts;
	double hotelRevenue;
	double restaurantRevenue;
	double totalRevenue;
	double profit;
	double numSmallTicks;
	double currentAccountBalance;
	
	/**
	 * for current analysis when prompted by user
	 */
	
	public FinancialAnalysis(double initialMoney) {
		this.currentAccountBalance = initialMoney;
	}
	
	public double dayRevenue(Room[] rooms, Restaurant restaurant) { //add restaurant calcs
		double roomEarnings = 0;
		double restaurantEarnings = 0;
		for (int i = 0; i < rooms.length; i++) {
			if (!rooms[i].available) {
				roomEarnings = roomEarnings + rooms[i].price;
			}
		}
		//restaurant calcs
		double dayRevenue = roomEarnings + restaurantEarnings;
		return dayRevenue;
	}

	public double dayCosts(int numStaff) { //add restaurant calcs
		//calculated at end of day: dailyFixedHotelCosts + dailyFixedRestaurantCosts + staffSalary(numStaff)
		double dayCosts = this.dailyFixedHotelCosts + this.dailyFixedRestaurantCosts + (this.staffSalary * numStaff);
		return dayCosts;
	}
	
	public double calculateCurrentAccountBalance(double dayRevenue, double dayCosts) {
		this.currentAccountBalance = this.currentAccountBalance + dayRevenue - dayCosts;
		return this.currentAccountBalance;
	}
	
	public double getCurrentAccountBalance() { 
		return currentAccountBalance;
	}
	
	
	/**
	 * for final analysis at the end
	 */
	
	public void calcExplicitCosts(int numStaff, int numDays) { 
		this.totalStaffComp = this.staffSalary * numStaff * numDays;
		this.totalHotelCosts = this.dailyFixedHotelCosts * numDays;
		this.totalRestaurantCosts = this.dailyFixedRestaurantCosts * numDays;
		this.explicitCosts = this.totalHotelCosts + this.totalRestaurantCosts + this.totalStaffComp;
	}
	
	
	public void calcTotalRevenue(double initialMoney) { 
		this.totalRevenue = this.currentAccountBalance - initialMoney + this.explicitCosts;
	}

	
	public void financialOverview(int numStaff, int numDays, int totalGuests, int totalCustomers) { //not done
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
