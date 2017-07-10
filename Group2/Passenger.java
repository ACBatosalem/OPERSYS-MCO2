public class Passenger implements Runnable {
	/* Constructors */
	public Passenger(String name, Station in, Station out, CalTrain system) {
		passName = name;
		boardStat = in;
		departStat = out;
		sync = system;
		passThread.start();
	}

	/* Getters and Setters */
	public String getPassName() {
		return name;
	}

	public Station getBoardStation() {
		return boardStat;
	}

	public Station getDepartStation() {
		return departStat;
	}

	public void setDepartStation(Station out) {
		departStat = out;
	}

	/* Other Functions */
	@Override
	public void run() {
		while(true) {
			/* If Train is Here */
			while(boardStation.isTrainHere()) {
				try {
					/* Wait for Train */
					boardStation.getArrivedTrain().waitTrain();
					Thread.sleep(200);
				} 
				catch (InterruptedException e) {}
				finally {
					if (boardStation.getArrivedTrain().getCapacity() > 0 &&
						boardStation.getPassengers().size() > 0) 
					{
						sync.station_on_board(boardStation);
						boardStation.getArrivedTrain().signalTrain();
					}
				}
			}
			try {Thread.sleep(60);} catch(Exception e){}
		}
	}

	/* Variables */
	private CalTrain sync;
	private Station boardStat;
	private Station departStat;
	private Thread passThread = new Thread(this);
	private String passName;
}