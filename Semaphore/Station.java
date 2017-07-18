import java.util.*;
import java.util.concurrent.Semaphore;

public class Station {
	/* Constructors */
	public Station(int num) {
		train_arrived = new Semaphore(0, true);
		all_pass_seated = new Semaphore(0, true);
		station_lock = new Semaphore(1, true);
		off_board = new Semaphore(1,true);
		stationNum = num;
		leftEmptySeats = rightEmptySeats = 0;
		leftTrainPass = rightTrainPass = 0;
		leftTotalSeats = rightTotalSeats = 0;
		left_train = right_train = null;
		leftStation = rightStation = null;
		waitLeftPassengers = new ArrayList<Passenger>();
		waitRightPassengers = new ArrayList<Passenger>();
	}

	/* Getters */
	public Semaphore getArrivedTrain() {
		return train_arrived;
	}

	public Semaphore getAllPassSeated() {
		return all_pass_seated;
	}

	public Semaphore getLock() {
		return station_lock;
	}

	public int getStationNum() {
		return stationNum;
	}

	public Train getLeftTrain() {
		return left_train;
	}

	public Train getRightTrain() {
		return right_train;
	}

	public int getLeftWaitPass() {
		return waitLeftPassengers.size();
	}

	public int getRightWaitPass() {
		return waitRightPassengers.size();
	}

	public int getWaitingPass(boolean direction) {
		if (direction)
			return getRightWaitPass();
		return getLeftWaitPass();
	}

	public int getLeftEmptySeats() {
		return leftEmptySeats;
	}

	public int getRightEmptySeats() {
		return rightEmptySeats;
	}

	public int getLeftTotalSeats() {
		return leftTotalSeats;
	}

	public int getRightTotalSeats() {
		return rightTotalSeats;
	}

	public int getLeftTrainPass() {
		return leftTrainPass;
	}

	public int getRightTrainPass() {
		return rightTrainPass;
	}

	public Station getLeftStation() {
		return leftStation;
	}

	public Station getRightStation() {
		return rightStation;
	}

	public ArrayList<Passenger> getLeftPassengers() {
		return waitLeftPassengers;
	}

	public ArrayList<Passenger> getRightPassengers() {
		return waitRightPassengers;
	}

	public ArrayList<Passenger> getWaitPassengers(boolean direction) {
		if (direction)
			return getRightPassengers();
		return getLeftPassengers();
	}

	/* Setters */
	public void setEmptySeats(boolean direction, int seats) {
		if(direction)
			rightEmptySeats = seats;
		else
			leftEmptySeats = seats;
	}

	public void setTotalSeats(boolean direction, int seats) {
		if(direction)
			rightTotalSeats = seats;
		else
			leftTotalSeats = seats;
	}

	public void setLeftStation(Station next) {
		leftStation = next;
	}

	public void setRightStation(Station next) {
		rightStation = next;
	}

	public void setLeftTrain(Train train) {
		left_train = train;
	}

	public void setRightTrain(Train train) {
		right_train = train;
	}

	/* Other Functions */
	public void addPassenger(Passenger newPass, boolean direction) {
		if (direction)
			waitRightPassengers.add(newPass);
		else
			waitLeftPassengers.add(newPass);
	}

	public void decWaitPass(Passenger pass, boolean direction) {
		if (direction) {
			for(Passenger p : waitRightPassengers) {
				if (p.getPassNum() == pass.getPassNum()) {
					waitRightPassengers.remove(p);
					break;
				}
			}
		}
		else {
			for(Passenger p : waitLeftPassengers) {
				if (p.getPassNum() == pass.getPassNum()) {
					waitLeftPassengers.remove(p);
					break;
				}
			}
		}
	}

	public void incStandPass(boolean direction) {
		if (direction)
			rightTrainPass++;
		else
			leftTrainPass++;
	}

	public void decStandPass(boolean direction) {
		if (direction)
			rightTrainPass--;
		else
			leftTrainPass--;
	}

	public void incEmptySeats(boolean direction) {
		if (direction)
			rightEmptySeats++;
		else
			leftEmptySeats++;
	}

	public void decEmptySeats(boolean direction) {
		if (direction)
			rightEmptySeats--;
		else
			leftEmptySeats--;
	}

	public void waitPassSeated(String w) {
		try {
		//	System.out.println("wait pass "+ w);
			all_pass_seated.acquire();
			Thread.sleep(1000);
		} catch(Exception e){}
	}

	public void signalPassSeated(String w) {
		try {
		//	System.out.println("signal pass "+ w);
			all_pass_seated.release();
		} catch(Exception e){}
	}

	public void waitTrain(String w) {
		try {
		//	System.out.println("wait train "+w);
			train_arrived.acquire();
		} catch(Exception e){}
	}

	public void signalTrain(String w) {
		try {
		//	System.out.println("signal train "+w);
			train_arrived.release();
		} catch(Exception e){}
	}

	public void waitStation(String w) {
		try {
		//	System.out.println("wait station "+w);
			station_lock.acquire();
		} catch(Exception e){}
	}

	public void signalStation(String w) {
		try {

		//	System.out.println("signal station "+w);
			station_lock.release();
		} catch(Exception e){}
	}

	public void waitOffBoard(String w) {
		try {
		//	System.out.println("wait offboard "+w);
			off_board.acquire();
		} catch(Exception e){}
	}

	public void signalOffBoard(String w) {
		try {

		//	System.out.println("signal offboard "+w);
			off_board.release();
		} catch(Exception e){}
	}

	public Station getNextStation(boolean toTheRight) {
		if (toTheRight)
			return rightStation;
		return leftStation;
	}

	public String displayNextStations() {
		String left = (leftStation == null) ? "null" : "Station " + leftStation.getStationNum(); 
		String right = (rightStation == null) ? "null" : "Station " + rightStation.getStationNum(); 
		return "Station " + stationNum + ": Left Station = " + left + " Right Station = " + right;
	}

	/* Variables */
	private Semaphore train_arrived;
	private Semaphore all_pass_seated;
	private Semaphore station_lock;
	private Semaphore off_board;
	private int stationNum;
	private ArrayList<Passenger> waitLeftPassengers, waitRightPassengers;
	private int leftEmptySeats, rightEmptySeats;
	private int leftTotalSeats, rightTotalSeats;
	private int leftTrainPass, rightTrainPass;
	private Station leftStation, rightStation;
	private Train left_train, right_train;
}