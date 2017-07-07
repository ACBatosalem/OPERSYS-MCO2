import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Station{
	private caltrain sync;
	private Semaphore sem;
	
	private String name;
	private Station nextStation;
	
	private Train arrivedTrain;
	
	private ArrayList<Passenger> p;
	
	public Station(String name, caltrain c){
		this.name = name;
		this.nextStation = null;
		this.sem = new Semaphore(1, true);
		arrivedTrain = null;
		this.p = new ArrayList<>(); 
		this.sync = c;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setNextStation(Station s){
		this.nextStation = s;
	}
	
	public Station getNextStation(){
		return this.nextStation;
	}
	
	public void addNewPassenger(int ctr){
		p.add(new Passenger("Passenger " + ctr, this, sync));
	}
	
	public Passenger removePassenger(){
		return(p.remove(0));
	}
	
	public void waitStation(){
		try {
			this.sem.acquire();
		} catch (Exception e) {
		}
	}
	
	public void signalStation(){
		try {
			this.sem.release();
		} catch (Exception e) {
		}
	}
	
	public ArrayList<Passenger> getPeople(){
		return this.p;
	}
	
	public Train getTrain(){
		return this.arrivedTrain;
	}
	
	public boolean getsOccupiedBy(Train t){
		if(!this.hasTrain()){
			this.arrivedTrain = t;
			return true;
		}
		return false;
	}
	
	public void removeTrain(){
		this.arrivedTrain = null;
	}
	
	public boolean hasTrain(){
		if(this.arrivedTrain == null)
			return false;
		return true;
	}
}
