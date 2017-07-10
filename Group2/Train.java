import java.util.*;

public class Train implements Runnable {
	/* Constructors */
	public Train(String name, int numPass, Station in, CalTrain system) {
		sync = system;
		boardTrain = boardStation.getLock().newCondition();
		trainName = name;
		trainCapacity = numPass;
		trainPass = new ArrayList<Passenger>();
	
		boardStat = in;
		trainThread.start();
	}

	/* Getters and Setters */
	public String getName() {
		return trainName;
	}

	public String getCurrNumPass() {
		return trainPass.size();
	}

	public int getCapacity() {
		return trainCapacity;
	}

	public ArrayList<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(ArrayList<Passenger> pass) {
		trainPass = pass;
	}

	/* Other Functions */
	public void rideTrain(Passenger pass) {
		try {
			trainCapacity--;
			trainPass.add(pass);
		} catch (Exception e) {}
	}

	public void leaveTrain(Passenger pass) {
		trainCapacity++;
		trainPass.remove(pass);
	}

	public void waitTrain() {
		try {boardTrain.await();}
		catch(Exception e) {}
	}

	public void signalTrain() {
		try {boardTrain.signal();}
		catch(Exception e) {}
	}

	@Override
	public void run() {
		while (boardStat != null) {
			/* Train arrives at Station */
			boardStat.getStationLock().lock();
			if(!boardStat.isTrainHere())
				boardStat.setArrivedTrain(this);

			/* Passengers get off at train */
			sync.station_out_board(boardStat);

			/* Train leaves Station */
			boardStat.trainLeft();
			boardStat.getLock().unlock();

			try { Thread.sleep(15); }
			catch(InterruptedException e) {}
		}
	}

	/* Variables */
	private CalTrain sync;
	private Condition boardTrain;
	private Station boardStat;
	private Thread trainThread = new Thread(this);

	private String trainName;
	private int trainCapacity;
	private ArrayList<Passenger> trainPass;
}