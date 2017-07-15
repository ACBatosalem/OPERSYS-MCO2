public class Train implements Runnable {
	public Train(Station in, CalTrain system, int free, int trainNum) {
		boardStation = in;
		sync = system;
		this.free = free;
		numSeats = free;
		this.trainNum = trainNum;
		toTheRight = true;
		trainThread.start();
	}

	public int getTrainNum() {
		return trainNum;
	}

	public int getFreeSeats() {
		return free;
	}

	public Station getBoardStation() {
		return boardStation;
	}

	@Override
	public void run() {
		while(true) {
			if (boardStation.train_num == -1) {
				boardStation.train_num = trainNum;
				sync.station_load_train(boardStation, free, numSeats, trainNum);
				try {Thread.sleep(1000);} catch(Exception e){}
				if (boardStation.getRightStation() == null)
					toTheRight = false;
				else if (boardStation.getLeftStation() == null)
					toTheRight = true;
				boardStation = boardStation.getNextStation(toTheRight);
				System.out.println("Train " + trainNum + " going to next station: " + boardStation.getStationNum());
			}
		}
	}

	private CalTrain sync;
	private Station boardStation;
	private int free, trainNum;
	private final int numSeats;
	private boolean toTheRight; /* True = Right. False = Left */
	private Thread trainThread = new Thread(this);

}