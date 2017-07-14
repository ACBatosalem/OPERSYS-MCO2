public class Passenger implements Runnable {
	public Passenger(Station in, CalTrain system, int i) {
		boardStation = in;
		sync = system;
		test = i;
		alreadyBoarded = false;
		alreadyWaited = false;
		passThread.start();
	}

	public Station getBoardStation() {
		return boardStation;
	}

	@Override
	public void run() {
		while(true) {
			//System.out.println(test);
			while(!alreadyBoarded){
				alreadyBoarded = sync.station_wait_for_train(boardStation, test, alreadyWaited);
				alreadyWaited = true;
			}
			//System.out.println(test+"HAAAAAAAAAAA");
			try {Thread.sleep(60);} catch(Exception e){}
		}
	}

	private CalTrain sync;
	private Station boardStation;
	private int test;
	private boolean alreadyBoarded;
	private boolean alreadyWaited;
	private Thread passThread = new Thread(this);
}