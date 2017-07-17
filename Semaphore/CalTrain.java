import java.util.*;
import java.lang.*;


public class CalTrain {
	public Station station_init(int num) {
		System.out.println("Initializing Station " + num);
		return new Station(num);
	}

	public void station_load_train(Station station, Train curr, boolean direction) {
		if (direction && curr.getTrainNum() == station.getRightTrain().getTrainNum()) {
			/* Train arrives at specific station */
			station.waitStation("load 1");
			int trainExiters = station_off_board(station, curr);
			CalTrainDriver.totalPassServed += trainExiters;
			station.setEmptySeats(direction, curr.getFreeSeats());
			station.setTotalSeats(direction, curr.getNumSeats());
			station.signalStation("load 1");
			System.out.println("Train " + curr.getTrainNum() + " arrives in Station " + station.getStationNum() 
							   + ". Train's number of available seats = " + curr.getFreeSeats());

			if (station.getStationNum() == 7) {
				while(station.getLeftWaitPass() > 0 && station.getRightEmptySeats() > 0) {
					try {
						station.signalTrain("l1");
						station.waitPassSeated("l1");
						//Thread.sleep(1000);
					} catch(Exception e) {}
				}
				System.out.println("Station " + station.getStationNum() + " Waiting Passengers - " 
								   + station.getLeftWaitPass() + " Empty Seats - " 
								   + station.getRightEmptySeats());
			}
			else {
				while(station.getRightWaitPass() > 0 && station.getRightEmptySeats() > 0) {
					try {
						station.signalTrain("l2"); 
						station.waitPassSeated("l2");
						//Thread.sleep(1000);
					} catch(Exception e) {}
				}
				System.out.println("Station " + station.getStationNum() + " Waiting Passengers - " 
								   + station.getRightWaitPass() + " Empty Seats - " 
								   + station.getRightEmptySeats());
			}

			/* Train leaves specific station */
			station.waitStation("l3");
			station.setEmptySeats(direction, 0);
			station.setTotalSeats(direction, 0);
			System.out.println("Train " + curr.getTrainNum() + " leaves Station " + station.getStationNum());
			station.setRightTrain(null);
			station.signalStation("l3");
		}
		else if (!direction && curr.getTrainNum() == station.getLeftTrain().getTrainNum()) {
			/* Train arrives at specific station */
			station.waitStation("l4");
			int trainExiters = station_off_board(station, curr);
			CalTrainDriver.totalPassServed += trainExiters;
			station.setEmptySeats(direction, curr.getFreeSeats());
			station.setTotalSeats(direction, curr.getNumSeats());
			station.signalStation("l4");

			if (station.getStationNum() == 0) {
				while(station.getRightWaitPass() > 0 && station.getLeftEmptySeats() > 0) {
					try {
						station.signalTrain("l5"); 
						station.waitPassSeated("l5");
					} catch(Exception e) {}
				}
				System.out.println("Station " + station.getStationNum() + " Waiting Passengers - " 
								   + station.getRightWaitPass() + " Empty Seats - " 
								   + station.getLeftEmptySeats());				
			}
			else {
				while(station.getLeftWaitPass() > 0 && station.getLeftEmptySeats() > 0) {
					try {
						station.signalTrain("l6"); 
						station.waitPassSeated("l6");
					} catch(Exception e) {}
				}
				System.out.println("Station " + station.getStationNum() + " Waiting Passengers - " 
								   + station.getLeftWaitPass() + " Empty Seats - " 
								   + station.getLeftEmptySeats());
			}

			/* Train leaves specific station */
			station.waitStation("l7");
			station.setEmptySeats(direction, 0);
			station.setTotalSeats(direction, 0);
			System.out.println("Train " + curr.getTrainNum() + " leaves Station " + station.getStationNum());
			station.setLeftTrain(null);
			station.signalStation("l7");
		}
	}

	public boolean station_wait_for_train(Station station, Passenger pass, boolean alreadyWaited, boolean direction) {
		boolean alreadyBoarded = false;
		if(!alreadyWaited) {
			station.waitStation("w1");
			System.out.println("Passenger " + pass.getPassNum() + " arrives at Station " + station.getStationNum()
							   + " (Leaves = Station " + pass.getLeaveStation().getStationNum() + 
							   " Direction = " + pass.getDirection() + " )");
			station.signalStation("w1");
		}
		if(direction) 
		{
			while(station.getRightTrainPass() <= station.getRightEmptySeats()) {
				try {
					station.waitTrain("w2");
				//	Thread.sleep(800);
				}
				catch(Exception e) {}
			}

		}
		else 
		{
			while(station.getLeftTrainPass() <= station.getLeftEmptySeats()) {
				try {
					station.waitTrain("w4");
				//	Thread.sleep(800);
				}
				catch(Exception e) {}
			}
		
		}
		return alreadyBoarded;
	}

	public boolean station_on_board(Station station, boolean all, Passenger pass) {
		
		boolean boarded = false;
		System.out.println("BOARDING: Pass " + pass.getPassNum() + " Direction = " + pass.getDirection() + station.getRightTrain());						 
		//  " Train = " + station.getRightTrain());
		if (pass.getDirection() && station.getRightTrain() != null)	// GENERAL: Train is to the right
		{
			station.getRightTrain().addRiding(pass);
			station.waitStation("b1");
			station.decWaitPass(pass, pass.getDirection());
			station.decStandPass(pass.getDirection());
			station.decEmptySeats(pass.getDirection());
			station.signalStation("b1");
			System.out.println("\n???Passenger " + pass.getPassNum() + " is on board at Train " + 
						       station.getRightTrain().getTrainNum() + " " +all);
			if (station.getRightEmptySeats() == 0 || all)
				station.signalPassSeated("b1-s");
			boarded = true;
		}
		else if (!pass.getDirection() && station.getRightTrain() != null) // If Passenger boards on Station 7
		{
			station.getRightTrain().addRiding(pass);
			station.waitStation("b2");
			station.decWaitPass(pass, pass.getDirection());
			station.decStandPass(pass.getDirection());
			station.decEmptySeats(pass.getDirection());
			station.signalStation("b2");
			System.out.println("\n???Passenger " + pass.getPassNum() + " is on board at Train " + 
						       station.getRightTrain().getTrainNum() + " " +all);
			if (station.getRightEmptySeats() == 0 || all)
				station.signalPassSeated("b2-s");
			boarded = true;
		}
		else if (!pass.getDirection() && station.getLeftTrain() != null) // GENERAL: Train is to the left
		{
			station.getLeftTrain().addRiding(pass);
			station.waitStation("b3");
			station.decWaitPass(pass, pass.getDirection());
			station.decStandPass(pass.getDirection());
			station.decEmptySeats(pass.getDirection());
			station.signalStation("b3");
			System.out.println("\n???Passenger " + pass.getPassNum() + " is on board at Train " + 
						       station.getLeftTrain().getTrainNum() + " " +all);
			if (station.getLeftEmptySeats() == 0 || all)
				station.signalPassSeated("b3-s");
			boarded = true;
		}
		else if (pass.getDirection() && station.getLeftTrain() != null)	// If Passenger boards on Station 0
		{
			station.getLeftTrain().addRiding(pass);
			station.waitStation("b4");
			station.decWaitPass(pass, pass.getDirection());
			station.decStandPass(pass.getDirection());
			station.decEmptySeats(pass.getDirection());
			station.signalStation("b4");
			System.out.println("\n???Passenger " + pass.getPassNum() + " is on board at Train " + 
						       station.getLeftTrain().getTrainNum() + " " +all);
			if (station.getLeftEmptySeats() == 0 || all)
				station.signalPassSeated("b4-s");
			boarded = true;
		}

		try {Thread.sleep(1000);} catch(Exception e) {}
		return boarded;
	}

	public int station_off_board(Station station, Train t) {
		int ctr = 0;
		if (!t.getRiding().isEmpty()) {
			station.waitStation("ob1");
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
			station.signalStation("ob1");
			try {Thread.sleep(500);} catch(Exception e) {}
		}
		return ctr;
	}

	public void countPassengersBoarded(int num) {
		passengersBoarded += num;
	}

	public void countPassengersLeft(int num) {
		passengersLeft -= num;
	}

	public int getPassBoarded() {
		return passengersBoarded;
	}

	public int getPassLeft() {
		return passengersLeft;
	}

	private int passengersBoarded = 0;
	private int passengersLeft = 10;
	private int threadsCompleted = 10;
}