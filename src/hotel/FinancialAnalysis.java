package hotel;

public class FinancialAnalysis {
	
	double dailyFixedHotelCosts = 300.0; //per big tick
	double totalHotelCosts;
	double dailyFixedRestaurantCosts = 150.0; //per big tick
	double totalRestaurantCosts;
	double staffSalary = 10.0; //per small tick
	double totalStaffComp;//comp=compensation
	double explicitCosts;
	double hotelRevenue;
	double restaurantRevenue;
	double totalRevenue;
	double profit;
	double numSmallTicks;
	double currentAccountBalance;
	
	public FinancialAnalysis() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * for current analysis when prompted by user
	 */
	
	public void earningsPerRoom() { //not done
		//calculated at end of day (price of room per day
		
	}
	
	public void setCurrentAccountBalance(double currentTime, double initialMoney) { //not done
		
	}
	
	public double getCurrentAccountBalance() { //complete
		return currentAccountBalance;
	}
	
	
	/**
	 * for final analysis at the end
	 */
	
	public void calcExplicitCosts(int numStaff, int numDays) { //complete
		this.numSmallTicks = 10 * numDays;
		this.totalStaffComp = this.staffSalary * this.numSmallTicks * numStaff ;
		this.totalHotelCosts = this.dailyFixedHotelCosts * numDays;
		this.totalRestaurantCosts = this.dailyFixedRestaurantCosts * numDays;
		this.explicitCosts = this.totalHotelCosts + this.totalRestaurantCosts + this.totalStaffComp;
	}
	
	
	public void calcTotalRevenue() { //not done
		
	}

	
	public void financialOverview(int numStaff, int numDays, int totalGuests, int totalCustomers, double initialMoney) { //not done
		this.profit = this.totalRevenue - this.explicitCosts;
		
		
	}

}
