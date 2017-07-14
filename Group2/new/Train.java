public class Train implements Runnable {
	public Train(Station in, CalTrain system, int free, int trainNum) {
		boardStation = in;
		sync = system;
		this.free = free;
		trainThread.start();
		this.trainNum = trainNum;
	}

	public Station getBoardStation() {
		return boardStation;
	}

	@Override
	public void run() {
		while(true) {
			//while(boardStation.train_num != -1);
			boardStation.getLock().lock();
			boardStation.train_num = trainNum;
			boardStation.getLock().unlock();
			sync.station_load_train(boardStation, free, trainNum);
			
			//boardStation.train_num = -1;
			try {Thread.sleep(60);} catch(Exception e){}
			break;
		}
	}

	private CalTrain sync;
	private Station boardStation;
	private int free, trainNum;
	private Thread trainThread = new Thread(this);

}