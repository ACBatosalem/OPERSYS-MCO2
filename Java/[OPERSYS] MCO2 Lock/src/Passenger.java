

public class Passenger implements Runnable{
	private caltrain sync;
	private Station inStation;
	private Station outStation;
	private String name;
	private Thread t = new Thread(this);
	
	public Passenger(String name, Station inStation, Station outStation, caltrain c){
		this.name = name;
		this.inStation = inStation;
		this.outStation = outStation;
		this.sync = c;
		this.t.start();
	}
	
	public String getName(){
		return this.name;
	}
	
	@Override
	public void run() {
		while(true){
			while(inStation.hasTrain()){
					try {
						inStation.getTrain().waitTrain();
						Thread.sleep(100);
					} catch (InterruptedException e) {
					}finally{
						if(inStation.getTrain().getLimit() > 0 && inStation.getPeople().size() > 0){
							sync.station_on_board(inStation);
							inStation.getTrain().signalTrain();
						}
					}
			}
			try{
				Thread.sleep(10);
			}catch(Exception e){
			}	
		}
	}

	public Station getOutStation() {
		return outStation;
	}

	public void setOutStation(Station outStation) {
		this.outStation = outStation;
	}
}
