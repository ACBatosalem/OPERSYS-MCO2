import java.util.*;

public class CalTrain {
	public Station station_init(int num) {
		System.out.println("Initializing Station " + num);
		return new Station(num);
	}

	public void station_load_train(Station station, Train curr, boolean direction) {
		if (direction && curr.getTrainNum() == station.getRightTrain().getTrainNum()) {
			station.getLock().lock();
			station.setEmptySeats(direction, curr.getFreeSeats());
			station.setTotalSeats(direction, curr.getNumSeats());
			station.getLock().unlock();
			System.out.println("Train " + curr.getTrainNum() + " arrives in Station " + 
							   station.getStationNum() + ". Train's number of available seats = " + curr.getFreeSeats());
			while(station.getRightWaitPass() > 0 && station.getRightEmptySeats() > 0) {
				try {
					station.signalTrain(); 
					station.waitPassSeated();
					try {Thread.sleep(1000);} catch(Exception e) {} 
				}
				catch(Exception e) {}
			}

			System.out.println("Station " + station.getStationNum() + " Waiting Passengers - " + station.getRightWaitPass() + 
							   "\nEmpty Seats - "+ station.getRightEmptySeats());

			station.getLock().lock();
			station.setEmptySeats(direction, 0);
			station.setTotalSeats(direction, 0);
			System.out.println("Train " + curr.getTrainNum() + " leaves Station " + station.getStationNum());
			station.setRightTrain(null);
			station.getLock().unlock();
			try {Thread.sleep(1000);} catch(Exception e) {}
		}
		else if (!direction && curr.getTrainNum() == station.getLeftTrain().getTrainNum()) {
			station.getLock().lock();
			station.setEmptySeats(direction, curr.getFreeSeats());
			station.setTotalSeats(direction, curr.getNumSeats());
			station.getLock().unlock();
			System.out.println("Train " + curr.getTrainNum() + " arrives in Station " + 
							   station.getStationNum() + ". Train's number of available seats = " + curr.getFreeSeats());
			while(station.getLeftWaitPass() > 0 && station.getLeftEmptySeats() > 0) {
				try {
					station.signalTrain(); 
					station.waitPassSeated();
					try {Thread.sleep(1000);} catch(Exception e) {} 
				}
				catch(Exception e) {}
			}

			System.out.println("Station " + station.getStationNum() + " Waiting Passengers - " + station.getLeftWaitPass() + 
							   "\nEmpty Seats - "+ station.getLeftEmptySeats());

			station.getLock().lock();
			station.setEmptySeats(direction, 0);
			station.setTotalSeats(direction, 0);
			System.out.println("Train " + curr.getTrainNum() + " leaves Station " + station.getStationNum());
			station.setLeftTrain(null);
			station.getLock().unlock();
			try {Thread.sleep(1000);} catch(Exception e) {}
		}
		try {Thread.sleep(1000);} catch(Exception e) {}
	}

	public boolean station_wait_for_train(Station station, Passenger pass, boolean alreadyWaited, boolean direction) {
		boolean alreadyBoarded = false;
		if(!alreadyWaited) {
			station.getLock().lock();
			System.out.println("Passenger " + pass.getPassNum() + " arrives at Station " + station.getStationNum()
							   + " (Leaves = Station " + pass.getLeaveStation().getStationNum() + 
							   " Direction = " + pass.getDirection() + " )");
			station.getLock().unlock();
		}
		if(direction) 
		{
			while(station.getRightTrainPass() <= station.getRightEmptySeats()) {
				try {
					station.waitTrain();
					Thread.sleep(1000);
				}
				catch(Exception e) {}
			}

			station.getLock().lock();
			if(station.getRightTrainPass() + 1 < station.getRightTotalSeats()) {
				station.incStandPass(direction);
				System.out.println("Passenger " + pass.getPassNum() + " boards Train " + station.getRightTrain().getTrainNum());
				alreadyBoarded = true;
			}
			station.getLock().unlock();
		}
		else 
		{
			while(station.getLeftTrainPass() <= station.getLeftEmptySeats()) {
				try {
					station.waitTrain();
					Thread.sleep(1000);
				}
				catch(Exception e) {}
			}

			station.getLock().lock();
			if(station.getLeftTrainPass() + 1 < station.getLeftTotalSeats()) {
				station.incStandPass(direction);
				System.out.println("Passenger " + pass.getPassNum() + " boards Train " + station.getLeftTrain().getTrainNum());
				alreadyBoarded = true;
			}
			station.getLock().unlock();			
		}
		return alreadyBoarded;
	}

	public void station_on_board(Station station, boolean all, Passenger pass) {
		station.getLock().lock();
		station.decWaitPass(pass, pass.getDirection());
		station.decStandPass(pass.getDirection());
		station.decEmptySeats(pass.getDirection());
		station.getLock().unlock();
		if (pass.getDirection() && station.getRightTrain() != null) {
			station.getRightTrain().addRiding(pass);
			System.out.println("Passenger " + pass.getPassNum() + " is on board at Train " + 
						       station.getRightTrain().getTrainNum());
			if (station.getRightEmptySeats() == 0 || station.getRightTrainPass() == 0 || all)
				station.signalPassSeated();
		}
		else if (!pass.getDirection() && station.getLeftTrain() != null) {
			station.getLeftTrain().addRiding(pass);
			System.out.println("Passenger " + pass.getPassNum() + " is on board at Train " + 
						       station.getLeftTrain().getTrainNum());
			if (station.getLeftEmptySeats() == 0 || station.getLeftTrainPass() == 0 || all)
				station.signalPassSeated();
		}
		try {Thread.sleep(1000);} catch(Exception e) {}
	}

	public int station_off_board(Station station, Train t) {
		int ctr = 0;
		if (!t.getRiding().isEmpty()) {
			station.getLock().lock();
			for(int k=0;k<t.getRiding().size();k++) {
				if (t.getRiding().get(k).getLeaveStation().getStationNum() == station.getStationNum()) {
					station.incEmptySeats(t.getRiding().get(k).getDirection());
					System.out.println("Passenger " + t.getRiding().get(k).getPassNum() + " leaves Train " 
									   + t.getTrainNum() + " at Station " 
									   + station.getStationNum());
					t.removeRiding(t.getRiding().get(k));
					k--;
					ctr++;
				}
			}
			station.getLock().unlock();
			try {Thread.sleep(500);} catch(Exception e) {}
		}
		return ctr;
	}
}