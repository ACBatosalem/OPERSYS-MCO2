import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Station {
	/* Constructors */
	public Station() {
		train_arrived = new ReentrantLock().newCondition();
		all_pass_seated = new ReentrantLock().newCondition();
		lock = new ReentrantLock();
		waiting_passengers = 0;
		train_empty_seats = 0;
		train_stand_pass = 0;
		train_num_seats = 0;
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

	public void setEmptySeats(int seats) {
		train_empty_seats = seats;
	}

	public void setNumSeats(int seats) {
		train_num_seats = seats;
	}

	/* Other Functions */
	public void incWaitPass() {
		waiting_passengers++;
	}

	public void incStandPass() {
		train_stand_pass++;
	}

	public void decWaitPass() {
		waiting_passengers--;
	}

	public void decStandPass() {
		train_stand_pass--;
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

	/* Variables */
	private Condition train_arrived;
	private Condition all_pass_seated;
	private Lock lock;
	private int waiting_passengers;
	private int train_empty_seats;
	private int train_num_seats;
	private int train_stand_pass;
}