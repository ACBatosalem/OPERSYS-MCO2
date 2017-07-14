import java.util.*;

public class CalTrain {
	public Station station_init() {
		System.out.println("Init");
		return new Station();
	}

	public void station_load_train(Station station, int count, int trainNum) {
		if (trainNum == station.train_num) {
			station.getLock().lock();
			station.setEmptySeats(count);
			station.setNumSeats(count);
			station.getLock().unlock();
			System.out.println("Train arrives (Count: " + count + ") Train Num->"+station.train_num);
			station.signalTrain();
			while(station.getWaitingPass() > 0 && station.getEmptySeats() > 0) {
				
			//	System.out.println("Outside: Waiting"+ station.getWaitingPass() + " "+ station.getEmptySeats());

				try { 
			//		System.out.println("Waiting"+ station.getWaitingPass() + " "+ station.getEmptySeats());
					station.waitPassSeated(); 
					}
				catch(Exception e) {}
			}

			System.out.println("wait finished: "+"Waiting"+ station.getWaitingPass() + " "+ station.getEmptySeats());

			station.getLock().lock();
			station.setEmptySeats(0);
			station.setNumSeats(0);
			System.out.println("Train left");
			station.train_num = -1;
			station.getLock().unlock();
		}
	}

	public boolean station_wait_for_train(Station station, int test, boolean alreadyWaited) {
		boolean alreadyBoarded = false;
		if(!alreadyWaited) {
			station.getLock().lock();
			station.incWaitPass();
			System.out.println("Passenger arrived ->" + test);
			station.getLock().unlock();
		}
		
	//	System.out.println("Standing: "+station.getStandingPass() +" Empty: " + station.getEmptySeats()
	//		+" NumSeats: " + station.getNumSeats());
		while(station.getStandingPass() == station.getEmptySeats()) {
			try {
				
				station.waitTrain(); 
				//Thread.sleep(60);
				 }
			catch(Exception e) {}
		}

		station.getLock().lock();
	//	System.out.println("Standing: "+ station.getStandingPass());
	//	System.out.println("Num Seats: "+ station.getNumSeats());
		if(station.getStandingPass() + 1 < station.getNumSeats()){
			station.incStandPass();
			System.out.println("Passenger boarding ->"+ test + " Train num: "+station.train_num);
			alreadyBoarded = true;
		}
		station.getLock().unlock();
		//System.out.println("boarded:"+alreadyBoarded);
		return alreadyBoarded;
	}

	public void station_on_board(Station station, boolean all) {
		station.getLock().lock();
		station.decWaitPass();
		station.decStandPass();
		station.decEmptySeats();
		station.getLock().unlock();
		System.out.println("Passenger on board: " + station.train_num);
		if (station.getEmptySeats() == 0 || station.getStandingPass() == 0 || all){
			//System.out.println("Signaled");
			station.signalPassSeated();
			
		}
		
	}
}