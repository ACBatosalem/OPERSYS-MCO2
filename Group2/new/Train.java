public class Train implements Runnable {
	public Train(Station in, CalTrain system, int free) {
		boardStation = in;
		sync = system;
		this.free = free;
		trainThread.start();
	}

	public Station getBoardStation() {
		return boardStation;
	}

	@Override
	public void run() {
		while(true) {
			sync.station_load_train(boardStation, free);
			try {Thread.sleep(60);} catch(Exception e){}
		}
	}

	private CalTrain sync;
	private Station boardStation;
	private int free;
	private Thread trainThread = new Thread(this);
}