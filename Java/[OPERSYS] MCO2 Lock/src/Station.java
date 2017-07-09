import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Station{
	private caltrain sync;
	private Lock lock;
	private Condition sem; 
	private String name;
	private Station nextStation;
	
	private Train arrivedTrain;
	
	private ArrayList<Passenger> p;
	
	public Station(String name, caltrain c){
		this.p = new ArrayList<>();
		this.lock = new ReentrantLock();
		this.name = name;
		this.nextStation = null;
		this.sem = lock.newCondition();
		this.arrivedTrain = null;
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
	
	public void addNewPassenger(int ctr, Station o){
		p.add(new Passenger("Passenger " + ctr, this, o, sync));
	}
	
	public Passenger removePassenger(){
		return(p.remove(0));
	}
	
	public Lock getLock(){
		return this.lock;
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
