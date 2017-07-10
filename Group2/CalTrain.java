import java.util.*;

public class CalTrain {
	/* Constructor */
	public CalTrain() {
		allStations = new ArrayList<Station>();
		allTrains = new ArrayList<Train>();
		allPeople = new ArrayList<Passenger>();
		numTrains = allTrains.size();
		numPeople = allPeople.size();
	}

	/* Getters and Setters */
	public int getNumTrains() {
		return numTrains;
	}

	public int getNumPeople() {
		return numPeople;
	}

	public ArrayList<Station> getAllStations() {
		return allStations;
	}

	public ArrayList<Train> getAllTrains() {
		return allTrains;
	}

	public ArrayList<Passenger> getAllPeople() {
		return allPeople;
	}

	public void addTrain(Train newTrain) {
		if (numTrains < 16) {
			allTrains.add(newTrain);
			numTrains = allTrains.size();
		}
	}

	public void addPeople(Passenger newPass) {
		allPeople.add(newPass);
		numPeople = allPeople.size();
	}

	/* Main Functions */
	public void station_init() {
		for(int i=0;i<8;i++) {
			allStations.add(new Station(this, "Station" + (i+1)));
			if (i < 7 && i >= 1)
				allStations.get(allStations.size()-2).setNextStation(allStations.get(allStations.size()-1));
			else
				allStations.get(i).setNextStation(allStations.get(0));
		}
	}

	public void station_load_train(Station station, int count) {
		addTrain(new Train("Train" + (allTrains + 1), count, station, this));
	}

	public void station_wait_for_train(Station station) {
		station.addNewPass(numPeople, station)
	}


	/* Variables */
	private int numTrains;
	private int numPeople;
	private ArrayList<Station> allStations;
	private ArrayList<Train> allTrains;
	private ArrayList<Passenger> allPeople;
	public static CalTrain singleton;
}