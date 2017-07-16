import java.util.*;

public class Train implements Runnable {
	public Train(Station in, CalTrain system, int free, int trainNum) {
		boardStation = in;
		sync = system;
		this.free = free;
		numSeats = free;
		this.trainNum = trainNum;
		toTheRight = true;
		trainThread.start();
		riding = new ArrayList<Passenger>();
	}

	public int getTrainNum() {
		return trainNum;
	}

	public int getFreeSeats() {
		return free;
	}

	public int getNumSeats() {
		return numSeats;
	}

	public Station getBoardStation() {
		return boardStation;
	}

	public boolean getDirection() {
		return toTheRight;
	}

	public ArrayList<Passenger> getRiding() {
		return riding;
	}

	public void addRiding(Passenger pass) {
		riding.add(pass);
		free = numSeats - riding.size();
	}

	public void removeRiding(Passenger pass) {
		riding.remove(pass);
		free = numSeats + riding.size();
	}

	@Override
	public void run() {
		while(true) {
			if(toTheRight && boardStation.getRightTrain() == null) {
				boardStation.getLock().lock();
				boardStation.setRightTrain(this);
				boardStation.getLock().unlock();
				sync.station_load_train(boardStation, this, toTheRight); 

				if (boardStation.getRightStation() == null)
					toTheRight = false;
				else if (boardStation.getLeftStation() == null)
					toTheRight = true;
				boardStation = boardStation.getNextStation(toTheRight);
				
				System.out.println("Train " + trainNum + " going to next station: " 
							       + boardStation.getStationNum());
			}
			else if(!toTheRight && boardStation.getLeftTrain() == null) {
				boardStation.getLock().lock();
				boardStation.setLeftTrain(this);
				boardStation.getLock().unlock();
				sync.station_load_train(boardStation, this, toTheRight);

				if (boardStation.getLeftStation() == null)
					toTheRight = true;
				else if (boardStation.getRightStation() == null)
					toTheRight = false;		
				boardStation = boardStation.getNextStation(toTheRight);

				System.out.println("Train " + trainNum + " going to next station: " 
							       + boardStation.getStationNum());
			}
			try{Thread.sleep(1000);} catch(Exception e) {}
		}
	}

	private CalTrain sync;
	private Station boardStation;
	private int free, trainNum;
	private final int numSeats;
	private boolean toTheRight; /* True = Right. False = Left */
	public Thread trainThread = new Thread(this);
	private ArrayList<Passenger> riding;
}