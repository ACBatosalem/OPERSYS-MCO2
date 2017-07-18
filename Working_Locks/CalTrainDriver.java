import java.util.*;
import java.lang.*;

public class CalTrainDriver {
	public static int outStat(int num) {
		int number = (int)Math.floor(Math.random()*8);
		return (num != number) ? number : outStat(num);
	}

	public static void main(String[] args) {
		CalTrain ctrain = new CalTrain();
		ArrayList<Station> allStations = new ArrayList<Station>();
		ArrayList<Train> allTrains = new ArrayList<Train>();

		/* Initialize Stations */
		for(int i=0;i<8;i++) {
			allStations.add(ctrain.station_init(i));
			if (i >= 1 && i < 8) {
				allStations.get(i-1).setRightStation(allStations.get(i));
				allStations.get(i).setLeftStation(allStations.get(i-1));
			}
		}
		System.out.println();

		/* Initialize Passengers */
		int totalPassengers = 10;
		int passengersLeft = totalPassengers;
		int passengersServed = totalPassengers;
		boolean trainsReturned = true;
		for(int i=0;i<totalPassengers;i++) {
			int inStatNum = (int)Math.floor(Math.random()*8);
			Passenger robot = new Passenger(allStations.get(inStatNum), ctrain, i, allStations.get(CalTrainDriver.outStat(inStatNum)));
			threadsCompleted++;
			try {Thread.sleep(300);} catch(Exception e){}
		}

		/* Actual Program */
		System.out.println("\n---------------------\n");
		int totalPassengersBoarded = 0;
		int maxFreeSeats = 5;
		int trainCtr = 0;
		while(totalPassServed != totalPassengers || trainsReturned) {
			
			/* Add Trains into System */
			if(totalNumSeats < totalPassengers) {
				int freeSeats = 5;
				totalNumSeats += freeSeats;
				/* Train is entering first station */
				loadTrainReturned = false;
				Train newTrain = new Train(allStations.get(0), ctrain, freeSeats, trainCtr);
				loadTrainReturned = true;
				allTrains.add(newTrain);
				trainCtr++;
			}

			/* How Train Works */
			for(int j=0;j<allTrains.size();j++) {
				int tempStatNum = allTrains.get(j).getBoardStation().getStationNum();
				boolean tempDirection = allTrains.get(j).getDirection();
				int threadsToReap = -1;
				int threadsReaped = 0;

				if ((tempDirection && tempStatNum >= 0 && tempStatNum <= 6) ||
					(!tempDirection && tempStatNum >= 1 && tempStatNum <= 7))
					threadsToReap = Math.min(allTrains.get(j).getBoardStation().getWaitingPass(tempDirection), 
											 allTrains.get(j).getFreeSeats());

				else if ((tempDirection && tempStatNum == 7) ||
						 (!tempDirection && tempStatNum == 0))
					threadsToReap = Math.min(allTrains.get(j).getBoardStation().getWaitingPass(!tempDirection),
											 allTrains.get(j).getFreeSeats());

				/* Passengers board train */
				while(threadsReaped < threadsToReap) {
					boolean boarded = false;
					if(threadsCompleted > 0) {
						if ((tempDirection && tempStatNum >= 0 && tempStatNum <= 6) ||
							(!tempDirection && tempStatNum >= 1 && tempStatNum <= 7))
							boarded = ctrain.station_on_board(allTrains.get(j).getBoardStation(), threadsReaped + 1 == threadsToReap, 
													allTrains.get(j).getBoardStation().getWaitPassengers(tempDirection).get(0));
						else if ((tempDirection && tempStatNum == 7) ||
								 (!tempDirection && tempStatNum == 0))
							boarded = ctrain.station_on_board(allTrains.get(j).getBoardStation(), threadsReaped + 1 == threadsToReap, 
													allTrains.get(j).getBoardStation().getWaitPassengers(!tempDirection).get(0));
						if(boarded)
							threadsReaped++;
						//try{allTrains.get(j).trainThread.sleep(500);} catch(Exception e){}
					}
				}

				passengersLeft -= threadsReaped;
				//passengersServed -= served;
				totalPassengersBoarded += threadsReaped;

				if(threadsToReap != threadsReaped)
					System.out.println("Error: Too many passengers on this train!");
				try{allTrains.get(j).trainThread.sleep(500);} catch(Exception e){}

				/* Make sure all trains return to first station */
				if (totalPassServed == totalPassengers && allTrains.get(j).getBoardStation().getStationNum() == 0) {
					allTrains.get(j).stopRun();
					allTrains.remove(allTrains.get(j));
					j--;

					if (allTrains.size() == 0) {
						System.out.println("All trains are gone!");
						trainsReturned = false;
					}
				}
			}
		
			/* Notes */
			System.out.println("Passengers left: " + passengersLeft);
			System.out.println("Passengers boarded: " + totalPassengersBoarded);
			System.out.println("Passengers served: " + totalPassServed);
			System.out.println("\n-------------------------------\n");
		}

		if(totalPassengersBoarded == totalPassengers) {
			System.out.println("All Passengers served!");
			System.exit(0);
		}
	}

	public static boolean loadTrainReturned = false;
	public static int threadsCompleted = 0;
	public static int totalNumSeats = 0;
	public static int totalPassServed = 0;
}