import java.util.*;

public class CalTrain {
	public Station station_init() {
		System.out.println("Init");
		return new Station();
	}

	public void station_load_train(Station station, int count) {
		station.getLock().lock();
		station.setEmptySeats(count);
		System.out.println("Train arrives (Count: " + count + ")->");
		while(station.getWaitingPass() > 0 && station.getEmptySeats() > 0) {
			station.getArrivedTrain().signal();
			try { station.getAllPassSeated().await(); }
			catch(Exception e) {}
		}

		station.setEmptySeats(0);
		station.getLock().unlock();
	}

	public void station_wait_for_train(Station station, int test) {
		station.getLock().lock();
		station.incWaitPass();
		System.out.println("Passenger arrived ->" + test);
		System.out.println(station.getStandingPass() +" " + station.getEmptySeats());
		if(station.getStandingPass() == station.getEmptySeats()) {
			try { station.getArrivedTrain().await(); }
			catch(Exception e) {}
		}
		station.incStandPass();
		station.decWaitPass();
		System.out.println("Passenger boarding ->");
		station.getLock().unlock();
	}

	public void station_on_board(Station station) {
		station.getLock().lock();
		station.decStandPass();
		station.decEmptySeats();
		System.out.println("Passenger on board");
		if (station.getEmptySeats() == 0 || station.getStandingPass() == 0)
			station.getAllPassSeated().signal();
		station.getLock().unlock();
	}
}