import java.util.*;

public class CalTrain {
	public Station station_init() {
		return new Station();
	}

	public void station_load_train(Station station, int count) {
		station.getLock().lock();
		station.setEmptySeats(count);

		while(station.getWaitingPass() > 0 && station.getEmptySeats() > 0) {
			station.getArrivedTrain().signal();
			try { station.getAllPassSeated().await(); }
			catch(Exception e) {}
		}

		station.setEmptySeats(0);
		station.getLock().unlock();
	}

	public void station_wait_for_train(Station station) {
		station.getLock().lock();
		station.incWaitPass();
		while(station.getStandPass() == station.getEmptySeats()) {
			try { station.getArrivedTrain().await(); }
			catch(Exception e) {}
		}
		station.incStandPass();
		station.decWaitPass();
		station.getLock().unlock();
	}

	public void station_on_board(Station station) {
		station.getLock().lock();
		station.decStandPass();
		station.decEmptySeats();
		if (station.getEmptySeats() == 0 || station.getStandPass() == 0)
			station.getAllPassSeated().signal();
		station.getLock().unlock();
	}
}