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
		/* Traversal to the right */
		for(int i=0;i<8;i++) {
			allStations.add(new Station(this, "Station" + (i+1)));
			if (i < 7 && i >= 1)
				allStations.get(allStations.size()-2).setRightStation(allStations.get(allStations.size()-1));
			else
				allStations.get(i).setRightStation(null);
		}
		/* Traversal to the left */
		for(int i=7;i>=0;i--) {
			if (i > 0)
				allStations.get(i).setLeftStation(allStations.get(i-1));
			else
				allStations.get(i).setLeftStation(null);
		} 
	}

	public void station_load_train(Station station, int count) {
		addTrain(new Train("Train" + (allTrains + 1), count, station, this));
	}

	public void station_wait_for_train(Station station, Station depart) {
		station.addNewPass(numPeople + 1, station, depart, this);
		numPeople++;
	}

	public void station_off_board(Station out) {
		/* Passengers get off train */
		for(Passenger pass : out.getPassengers()) {
			if (pass.getDepartStation().getStationName().equals(out.getStationName()))
				out.getArrivedTrain().leaveTrain(pass);
		}
	}

	public void station_on_board(Station in) {
		/* Get first passenger waiting in Station*/
		Passenger tempPass = in.removePass();

		/* Passenger rides train */
		if (in.getArrivedTrain().getCapacity() > 0)
			in.getArrivedTrain().rideTrain(tempPass);
	}


	/* Variables */
	private int numTrains;
	private int numPeople;
	private ArrayList<Station> allStations;
	private ArrayList<Train> allTrains;
	public static CalTrain singleton;
}