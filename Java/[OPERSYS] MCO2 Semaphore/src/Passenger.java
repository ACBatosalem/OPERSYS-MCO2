
public class Passenger implements Runnable{
	private caltrain sync;
	private Station inStation;
	private String name;
	private Thread t = new Thread(this);
	
	public Passenger(String name, Station inStation, caltrain c){
		this.name = name;
		this.inStation = inStation;
		this.sync = c;
		this.t.start();
	}
	
	public String getName(){
		return this.name;
	}
	
	@Override
	public void run() {
		while(true){
			while(inStation.hasTrain() && inStation.getTrain().trywaitTrain()){
				
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
					}
				if(inStation.getTrain().getLimit().availablePermits() > 0 && inStation.getPeople().size() > 0){
						
					sync.station_on_board(inStation);
				}
				inStation.getTrain().signalTrain();
			}
			try{
				Thread.sleep(10);
			}catch(Exception e){
			}
			
		}
	}
}
