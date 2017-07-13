import java.util.*;

public class CalTrain {
	public Station station_init() {
		System.out.println("Init");
		return new Station();
	}

	public void station_load_train(Station station, int count) {
	//	station.getLock().lock();
		station.setEmptySeats(count);
		System.out.println("Train arrives (Count: " + count + ")->");
		while(station.getWaitingPass() > 0 && station.getEmptySeats() > 0) {
			station.signalTrain();
			try { station.waitPassSeated(); 
				System.out.println("Train left");}
			catch(Exception e) {}
		}

		station.setEmptySeats(0);
	//	station.getLock().unlock();
	}

	public void station_wait_for_train(Station station, int test, Thread pThread) {
		station.getLock().lock();
		station.incWaitPass();
		System.out.println("Passenger arrived ->" + test);
		System.out.println(station.getStandingPass() +" " + station.getEmptySeats());
		while(station.getStandingPass() == station.getEmptySeats()) {
			try {
				//Thread.sleep(100);
				station.waitTrain(); 

				 }
			catch(Exception e) {}
		}
		station.incStandPass();
		station.decWaitPass();
		System.out.println("Passenger boarding ->"+ test);
		station.getLock().unlock();
	}

	public void station_on_board(Station station) {
		station.getLock().lock();
		
		station.decStandPass();
		station.decEmptySeats();
		System.out.println("Passenger on board");
		if (station.getEmptySeats() == 0 || station.getStandingPass() == 0)
			station.signalPassSeated();
		station.getLock().unlock();
	}
}