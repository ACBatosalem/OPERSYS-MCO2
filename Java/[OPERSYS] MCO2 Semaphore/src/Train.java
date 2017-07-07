import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Train implements Runnable{
	private caltrain sync;
	
	private Semaphore canRide;
	private String name;
	private Semaphore limit;
	
	
	private Station inStation;
	private Thread t = new Thread(this);
	
	private ArrayList<Passenger> passengers;	
	
	public Train(String name, int limit, Station inStation, caltrain c){
		this.name = name;
		this.passengers = new ArrayList<>();
		this.limit = new Semaphore(limit);
		this.canRide = new Semaphore(0);
		this.sync = c;
		try {
			System.out.println(this.limit.availablePermits());
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
			limit.acquire();
			passengers.add(p);
		} catch (InterruptedException e) {
		}
	}
	
	public void getOffBoard(Passenger p){
		limit.release();
		passengers.remove(p);
	}
	
	public int getSeated(){
		return this.passengers.size();
	}
	
	public Semaphore getLimit(){
		return this.limit;
	}
	
	
	public void waitTrain(){
		try {
			canRide.acquire();
		} catch (Exception e) {
		}
	}
	
	public boolean trywaitTrain(){
		try{
			if(canRide.tryAcquire()){
				return true;
			}
		}catch(Exception e){
		}
		return false;
	}
	
	public void signalTrain(){
		try {
			canRide.release();
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
			
			inStation.waitStation();
			signalTrain();
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
			inStation.getsOccupiedBy(this);
			
			str = this.name + " has arrived.\n";
			System.out.println(str + inStation.getName() + "\n");
			System.out.println("CURRENT TRAIN: " + inStation.getTrain().getName());

			System.out.println("\tENTERING STATION: " + inStation.getName());
			
			while(limit.availablePermits() > 0 && inStation.getPeople().size() > 0){
				//get passengers
			}
			waitTrain();
			inStation.removeTrain();
			
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
			
			inStation.signalStation();
			inStation = inStation.getNextStation();
			
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}
		}
		sync.caltrainGUI.toggleButtons(true);
	}
}
