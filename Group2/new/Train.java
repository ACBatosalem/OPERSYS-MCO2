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
		while(boardStation != null) {
			if (boardStation.train_num == -1) {
				boardStation.train_num = trainNum;
				sync.station_load_train(boardStation, free, trainNum);
				try {Thread.sleep(60);} catch(Exception e){}
				boardStation = boardStation.getNextStation();
				System.out.println("Train " + trainNum + " going to next station: " + boardStation.getStationNum());
			}
		}
	}

	private CalTrain sync;
	private Station boardStation;
	private int free, trainNum;
	private Thread trainThread = new Thread(this);

}