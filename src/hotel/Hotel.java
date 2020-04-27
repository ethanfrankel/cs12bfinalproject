package hotel;

public class Hotel {
	Room[] rooms;
	Restaurant restaurant;
	Pool pool;
	Staff[] staff;
	int numStaff;
	double totalMoney;
	int numTwoGuestRoom;
	int numFourGuestRoom;
	int numSuiteRoom;

	public Hotel(int numTwoGuestRoom, int numFourGuestRoom, int numSuiteRoom, int numStaff) {
		this.restaurant = new Restaurant();
		this.pool = new Pool();
		this.numStaff = numStaff;
		this.numTwoGuestRoom = numTwoGuestRoom;
		this.numFourGuestRoom = numFourGuestRoom;
		this.numSuiteRoom = numSuiteRoom;
		this.rooms = new Room[numTwoGuestRoom + numFourGuestRoom + numSuiteRoom];
		this.staff = new Staff[this.numStaff];
		this.totalMoney = 10000;
	}
	
	public void addRooms() {
		for(int i=0; i<this.numTwoGuestRoom; i++) {
		      this.rooms[i] = new TwoGuestRoom();
		}
		for(int i=this.numTwoGuestRoom; i<this.numFourGuestRoom + this.numTwoGuestRoom; i++) {
		      this.rooms[i] = new FourGuestRoom();
		}
		for(int i=this.numTwoGuestRoom + this.numFourGuestRoom; i<this.rooms.length; i++) {
		      this.rooms[i] = new SuiteRoom();
		}
	}
	
	public void setRoomNumbers() {
		for (int i = 0; i < this.rooms.length; i++) {
			this.rooms[i].setRoomNumber(i + 1);
		}
	}
	
	public double getTotalMoney() {
		return this.totalMoney;
	}
	
	public void setTotalMoney(int value) {
		this.totalMoney = value;
	}
	
	public void printHotel() {
		//test
		System.out.println("Two Guest Rooms: " + this.numTwoGuestRoom);
		System.out.println("Four Guest Rooms: " + this.numFourGuestRoom);
		System.out.println("Suite Rooms: " + this.numSuiteRoom);
		System.out.println("Staff: " + this.numStaff);
	}
	
	public Guest createGuest(double numDaysCheckIn) {
		Guest guest = new Guest(numDaysCheckIn);
		return guest;
	}
	
	public void checkIn(int randNum, double numDaysCheckIn, double dayNotation, String roomType, int roomTypeNum) {
		int counter = 0;
		for (int i = 0; i < this.rooms.length; i++ ) {
			if (this.rooms[i].type == roomType && this.rooms[i].available) {
				for (int j = 0; j < randNum; j++) {
					Guest guest = this.createGuest(numDaysCheckIn);
					this.rooms[i].addGuests(guest);
				}
				System.out.println("The " + randNum + " guests checked in " + this.rooms[i].detailedToString());
				this.rooms[i].setNumDaysStay(numDaysCheckIn);
				this.rooms[i].setTimeAtCheckIn(dayNotation);
				this.rooms[i].setAvailable(false);
				break;
			}
			else if (this.rooms[i].type == roomType && !this.rooms[i].available) {
				counter++;
			}
		}
		if (counter == roomTypeNum) {
			System.out.println("Room unavailable.");
		}
	}
	
	public void determineRoomType(int randNum, double numDaysCheckIn, double dayNotation) {
		if (0 < randNum && randNum < 3) {
			this.checkIn(randNum, numDaysCheckIn, dayNotation, "Two Guest Room", this.numTwoGuestRoom);
		}
		else if (2 < randNum && randNum < 5) {
			this.checkIn(randNum, numDaysCheckIn, dayNotation, "Four Guest Room", this.numFourGuestRoom);
		}
		else if (4 < randNum && randNum < 9) {
			this.checkIn(randNum, numDaysCheckIn, dayNotation, "Suite Room", this.numSuiteRoom);
		}
	}
	
	public void updateGuestCounters() {
		for (int i = 0; i < this.rooms.length; i++) {
			if (!this.rooms[i].available) {
				this.rooms[i].setCurrentTimeCounter(this.rooms[i].currentTimeCounter + 0.1);
				if (this.rooms[i].currentTimeCounter == this.rooms[i].timeAtCheckIn + this.rooms[i].numDaysStay) {
					this.rooms[i].checkOut();
				}
			}
		}
	}

}