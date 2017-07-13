public class Passenger implements Runnable {
	public Passenger(Station in, CalTrain system, int i) {
		boardStation = in;
		sync = system;
		test = i;
		passThread.start();
	}

	public Station getBoardStation() {
		return boardStation;
	}

	@Override
	public void run() {
		while(true) {
			//System.out.println(test);
			sync.station_wait_for_train(boardStation, test, passThread);
			//System.out.println(test+"HAAAAAAAAAAA");
			try {Thread.sleep(60);} catch(Exception e){}
		}
	}

	private CalTrain sync;
	private Station boardStation;
	private int test;
	private Thread passThread = new Thread(this);
}