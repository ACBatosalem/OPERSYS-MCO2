

public class Passenger implements Runnable{
	private caltrain sync;
	private Station inStation;
	private String name;
	private Thread t = new Thread(this);
	
	public Passenger(String name, Station inStation, caltrain c){
		this.name = name;
		this.inStation = inStation;
		this.sync = c;
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
							caltrain.getInstance().station_on_board(inStation);
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
}
