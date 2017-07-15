public class Passenger implements Runnable {
	public Passenger(Station in, CalTrain system, int i) {
		boardStation = in;
		boardStation.addPassenger(this);
		sync = system;
		passNum = i;
		alreadyBoarded = false;
		alreadyWaited = false;
		passThread.start();
	}

	public Station getBoardStation() {
		return boardStation;
	}

	public int getPassNum() {
		return passNum;
	}

	@Override
	public void run() {
		while(true) {
			while(!alreadyBoarded) {
				alreadyBoarded = sync.station_wait_for_train(boardStation, this, alreadyWaited);
				alreadyWaited = true;
			}
			try {Thread.sleep(200);} catch(Exception e){}
		}
	}

	private CalTrain sync;
	private Station boardStation;
	private int passNum;
	private boolean alreadyBoarded;
	private boolean alreadyWaited;
	private Thread passThread = new Thread(this);
}