import java.util.*;
import java.util.concurrent.*;

public class Station {
	/* Constructors */
	public Station(CalTrain system, String name) {
		sync = system;
		stationName = name;
		stationLock = new ReentrantLock();
		stationSemaphor = stationLock.newCondition();
		passStation = new ArrayList<Passenger>();
		nextStation = null;
		trainArrive = null;
	}

	/* Getters and Setters */
	public String getStationName() {
		return stationName;
	}

	public Station getNextStation() {
		return nextStation;
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

	public setNextStation(Station nStation) {
		nextStation = nStation;
	}

	public setArrivedTrain(Train nTrain) {
		trainArrive = nTrain;
	}

	/* Other Functions */
	public void addNewPass(int count, Station s) {
		passStation.add(new Passenger("Passenger" + count, s, sync, this));
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
	private Station nextStation;
	private Train trainArrive;
	private ArrayList<Passenger> passStation;
}