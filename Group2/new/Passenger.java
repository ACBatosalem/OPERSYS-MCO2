public class Passenger implements Runnable {
	public Passenger(Station in, CalTrain system, int i, Station out) {
		boardStation = in;
		leaveStation = out;
		boardStation.addPassenger(this, determineDirection());
		direction = determineDirection();
		sync = system;
		passNum = i;
		alreadyBoarded = false;
		alreadyWaited = false;
		passThread.start();
	}

	public Station getBoardStation() {
		return boardStation;
	}

	public Station getLeaveStation() {
		return leaveStation;
	}

	public int getPassNum() {
		return passNum;
	}

	public boolean getDirection() {
		return direction;
	}

	public boolean determineDirection() {
		if (boardStation.getStationNum() < leaveStation.getStationNum()) // to the right
			return true;
		else if (boardStation.getStationNum() > leaveStation.getStationNum())
			return false;
		return false;
	}

	@Override
	public void run() {
		while(true) {
			while(!alreadyBoarded) {
				alreadyBoarded = sync.station_wait_for_train(boardStation, this, alreadyWaited, direction);
				alreadyWaited = true;
			}
			try {Thread.sleep(500);} catch(Exception e){}
		}
	}

	private CalTrain sync;
	private Station boardStation;
	private Station leaveStation;
	private int passNum;
	private boolean alreadyBoarded;
	private boolean alreadyWaited;
	private boolean direction;
	private Thread passThread = new Thread(this);
}