
import java.util.ArrayList;
import java.util.concurrent.locks.Condition;

public class Train implements Runnable{
	private caltrain sync;
	
	private Condition canRide;
	private String name;
	private int limit;
	
	
	private Station inStation;
	private Thread t = new Thread(this);
	
	private ArrayList<Passenger> passengers;	
	
	public Train(String name, int limit, Station inStation, caltrain c){
		this.name = name;
		this.passengers = new ArrayList<>();
		this.limit = limit;
		this.canRide = inStation.getLock().newCondition();
		this.sync = c;
		
		try {
			System.out.println(limit);
			Thread.sleep(1000);
			
		} catch (InterruptedException e) {
		}
		
		this.inStation = inStation;
		t.start();
	}
	
	public String getName(){
		return this.name;
	}
	
	public void getOnBoard(Passenger p){
		try {
			limit--;
			passengers.add(p);
		} catch (Exception e) {
		}
	}
	
	public void getOffBoard(Passenger p){
		limit++;
		passengers.remove(p);
	}
	
	public int getSeated(){
		return this.passengers.size();
	}
	
	public int getLimit(){
		return this.limit;
	}
	
	
	public void waitTrain(){
		try {
			canRide.await();
		} catch (Exception e) {
		}
	}
	
	public void signalTrain(){
		try {
			canRide.signal();
		} catch (Exception e) {
		}
	}
	
	public void run(){
		while(inStation != null){
			
			String str = this.name + " is approaching.\n";
			System.out.println(str);
			
			
			if(sync.caltrainGUI != null)
				for(int i = 0; i < 8; i++){
					if(inStation.getName().equals(sync.getStations().get(i).getName())){
						sync.caltrainGUI.stationList.get(i).append(str);		
					}
				}
			
			inStation.getLock().lock();
			inStation.getsOccupiedBy(this);

			str = this.name + " has arrived.\n";
			System.out.println(str + inStation.getName() + "\n");
			System.out.println("CURRENT TRAIN: " + inStation.getTrain().getName());

			System.out.println("\tENTERING STATION: " + inStation.getName());
			
			while(limit > 0 && inStation.getPeople().size() > 0){
				sync.station_on_board(inStation);
			}
			
			inStation.removeTrain();
			inStation.getLock().unlock();
			
			str = this.name + " is leaving\n";
			System.out.println("\t" + str + inStation.getName() + "\n");
			
			if(sync.caltrainGUI != null)
				for(int i = 0; i < 8; i++){
					if(inStation.getName().equals(sync.getStations().get(i).getName())){
						sync.caltrainGUI.stationList.get(i).append(str);		
					}
				}
			
			try{
				Thread.sleep(1000);
			}catch(Exception e){
			}
			
			inStation = inStation.getNextStation();
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}
		}	
		sync.caltrainGUI.toggleButtons(true);
		
		if(sync.getTrainCtr() == 16)
			sync.caltrainGUI.toggleButtons(false);
	}
}
