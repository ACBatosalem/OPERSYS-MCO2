import java.util.*;

public class CalTrain {
	public Station station_init(int num) {
		System.out.println("Initializing Station " + num);
		return new Station(num);
	}

	public void station_load_train(Station station, int count, int totSeats, int trainNum) {
		if (trainNum == station.train_num) {
			station.getLock().lock();
			station.setEmptySeats(count);
			station.setNumSeats(totSeats);
			station.getLock().unlock();
			System.out.println("Train " + trainNum + " arrives in Station " + station.getStationNum() +
						       ". Train's number of seats = " + count);
			while(station.getWaitingPass() > 0 && station.getEmptySeats() > 0) {
				try {
					station.signalTrain(); 
					station.waitPassSeated(); 
				}
				catch(Exception e) {}
			}

			System.out.println("Station's Waiting Passengers - " + station.getWaitingPass() + 
							   "\nEmpty Passengers - "+ station.getEmptySeats());

			station.getLock().lock();
			station.setEmptySeats(0);
			station.setNumSeats(0);
			System.out.println("Train " + trainNum + " leaves Station " + station.getStationNum());
			station.train_num = -1;
			station.getLock().unlock();
		}
	}

	public boolean station_wait_for_train(Station station, Passenger pass, boolean alreadyWaited) {
		boolean alreadyBoarded = false;
		if(!alreadyWaited) {
			station.getLock().lock();
			System.out.println("Passenger " + pass.getPassNum() + " arrives at Station " + station.getStationNum());
			station.getLock().unlock();
		}
		while(station.getStandingPass() <= station.getEmptySeats()) {
			try {
				station.waitTrain(); 
				Thread.sleep(1000);
			}
			catch(Exception e) {}
		}

		station.getLock().lock();
		if(station.getStandingPass() + 1 < station.getNumSeats()) {
			station.incStandPass();
			System.out.println("Passenger " + pass.getPassNum() + " boards Train " + station.train_num);
			alreadyBoarded = true;
		}
		station.getLock().unlock();
		return alreadyBoarded;
	}

	public void station_on_board(Station station, boolean all, Passenger pass) {
		station.getLock().lock();
		station.decWaitPass(pass);
		station.decStandPass();
		station.decEmptySeats();
		station.getLock().unlock();
		System.out.println("Passenger " + pass.getPassNum() + " is on board at Train " + 
					       station.train_num);
		if (station.getEmptySeats() == 0 || station.getStandingPass() == 0 || all)
			station.signalPassSeated();
	}
}