import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Station {
	/* Constructors */
	public Station(int num) {
		train_arrived = new ReentrantLock().newCondition();
		all_pass_seated = new ReentrantLock().newCondition();
		lock = new ReentrantLock();
		waiting_passengers = 0;
		train_empty_seats = 0;
		train_stand_pass = 0;
		train_num_seats = 0;
		train_stand_pass = 0;
		train_num = -1;
		stationNum = num;
		leftStation = null;
		rightStation = null;
		waitPassengers = new ArrayList<Passenger>();
	}

	/* Getters and Setters */
	public Condition getArrivedTrain() {
		return train_arrived;
	}

	public Condition getAllPassSeated() {
		return all_pass_seated;
	}

	public Lock getLock() {
		return lock;
	}

	public int getWaitingPass() {
		return waiting_passengers;
	}

	public int getEmptySeats() {
		return train_empty_seats;
	}

	public int getNumSeats() {
		return train_num_seats;
	}

	public int getStandingPass() {
		return train_stand_pass;
	}

	public Station getLeftStation() {
		return leftStation;
	}

	public Station getRightStation() {
		return rightStation;
	}

	public int getStationNum() {
		return stationNum;
	}

	public ArrayList<Passenger> getWaitPassengers() {
		return waitPassengers;
	}

	public Station getNextStation(boolean toTheRight) {
		if (toTheRight)
			return rightStation;
		return leftStation;
	}

	public void setEmptySeats(int seats) {
		train_empty_seats = seats;
	}

	public void setNumSeats(int seats) {
		train_num_seats = seats;
	}

	public void setLeftStation(Station next) {
		leftStation = next;
	}

	public void setRightStation(Station next) {
		rightStation = next;
	}

	/* Other Functions */
	public void addPassenger(Passenger newPass) {
		waitPassengers.add(newPass);
		waiting_passengers = waitPassengers.size();
	}

	public void decWaitPass(Passenger pass) {
		for(Passenger p : waitPassengers) {
			if (p.getPassNum() == pass.getPassNum()) {
				waitPassengers.remove(p);
				break;
			}
		}
		waiting_passengers = waitPassengers.size();
	}

	public void incStandPass() {
		train_stand_pass++;
	}

	public void decStandPass() {
		train_stand_pass--;
	}

	public void incEmptySeats() {
		train_empty_seats++;
	}

	public void decEmptySeats() {
		train_empty_seats--;
	}

	public void waitPassSeated() {
		try {
			all_pass_seated.await();
		} catch(Exception e){}
	}

	public void signalPassSeated() {
		try {
			all_pass_seated.signal();
		} catch(Exception e){}
	}

	public void waitTrain() {
		try {
			train_arrived.await();
		} catch(Exception e){}
	}

	public void signalTrain() {
		try {
			train_arrived.signal();
		} catch(Exception e){}
	}

	public String displayNextStations() {
		String left = (leftStation == null) ? "null" : "Station " + leftStation.getStationNum(); 
		String right = (rightStation == null) ? "null" : "Station " + rightStation.getStationNum(); 
		return "Station " + stationNum + ": Left Station = " + left + " Right Station = " + right;
	}

	/* Variables */
	private Condition train_arrived;
	private Condition all_pass_seated;
	private Lock lock;
	private int waiting_passengers;
	private ArrayList<Passenger> waitPassengers;
	private int train_empty_seats;
	private int train_num_seats;
	private int train_stand_pass;
	private int stationNum;
	private Station leftStation;
	private Station rightStation;
	public int train_num;
}