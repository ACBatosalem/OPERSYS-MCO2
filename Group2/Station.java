import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Station {
	/* Constructors */
	public Station(CalTrain system, String name) {
		sync = system;
		stationName = name;
		stationLock = new ReentrantLock();
		stationSemaphore = stationLock.newCondition();
		passStation = new ArrayList<Passenger>();
		leftStation = null;
		rightStation = null;
		trainArrive = null;
	}

	/* Getters and Setters */
	public String getStationName() {
		return stationName;
	}

	public Station getLeftStation() {
		return leftStation;
	}

	public Station getRightStation() {
		return rightStation;
	}

	public Lock getStationLock() {
		return stationLock;
	}

	public ArrayList<Passenger> getPassengers() {
		return passStation;
	}

	public Train getArrivedTrain() {
		return trainArrive;
	}

	public void setLeftStation(Station nStation) {
		leftStation = nStation;
	}

	public void setRightStation(Station nStation) {
		rightStation = nStation;
	}

	public void setArrivedTrain(Train nTrain) {
		trainArrive = nTrain;
	}

	/* Other Functions */
	public void addNewPass(int count, Station out) {
		passStation.add(new Passenger("Passenger" + count, this, out, sync));
	}

	public Passenger removePass() {
		return p.remove(0);
	}

	public boolean isTrainHere() {
		if (trainArrive == null)
			return false;
		return true;
	}

	public void trainLeft() {
		this.trainArrive = null;
	}

	/* Variables */
	private CalTrain sync;
	private Lock stationLock;
	private Condition stationSemaphore;

	private String stationName;
	private Station leftStation;
	private Station rightStation;
	private Train trainArrive;
	private ArrayList<Passenger> passStation;
}