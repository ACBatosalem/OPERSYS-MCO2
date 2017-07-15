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
		for(int i=0;i<totalPassengers;i++) {
			int inStatNum = (int)Math.floor(Math.random()*8);
			Passenger robot = new Passenger(allStations.get(inStatNum), ctrain, i, allStations.get(CalTrainDriver.outStat(inStatNum)));
			threadsCompleted++;
			try {Thread.sleep(1200);} catch(Exception e){}
		}

		/* Actual Program */
		System.out.println("\n---------------------\n");
		int totalPassengersBoarded = 0;
		int maxFreeSeats = 5;
		int trainCtr = 0;
		while(passengersServed > 0 && trainCtr < 6) {
			int freeSeats = (int)(Math.floor(Math.random() * maxFreeSeats)) + 1;

			/* Train is entering first station */
			loadTrainReturned = false;
			Train newTrain = new Train(allStations.get(0), ctrain, freeSeats, trainCtr);
			loadTrainReturned = true;
			System.out.println("Train " + newTrain.getTrainNum() + " entering Station 0 with "
							   + freeSeats + " free seats");
			allTrains.add(newTrain);

			for(int j=0;j<allTrains.size();j++) {
				/* Passengers leave train */
				int served = ctrain.station_off_board(allTrains.get(j).getBoardStation(), allTrains.get(j));

				int threadsToReap = Math.min(allTrains.get(j).getBoardStation().getWaitingPass(allTrains.get(j).getDirection()), allTrains.get(j).getFreeSeats());
				System.out.println("Expected Free Seats: " + allTrains.get(j).getFreeSeats() + 
								   ", Expected Passengers Left: " + allTrains.get(j).getBoardStation().getWaitingPass(allTrains.get(j).getDirection()));
				int threadsReaped = 0;

				/* Passengers board train */
				while(threadsReaped < threadsToReap) {
					if(threadsCompleted > 0) {
						threadsReaped++;
						ctrain.station_on_board(allTrains.get(j).getBoardStation(), threadsReaped == threadsToReap,
												allTrains.get(j).getBoardStation().getWaitPassengers(allTrains.get(j).getDirection()).get(0));
					}
				}

				passengersLeft -= threadsReaped;
				passengersServed -= served;
				totalPassengersBoarded += threadsReaped;

				if(threadsToReap != threadsReaped)
					System.out.println("Error: Too many passengers on this train!");

				try{allTrains.get(j).trainThread.sleep(500);} catch(Exception e){}
			}
		
			trainCtr++;
			System.out.println("Passengers left: " + passengersLeft);
			System.out.println("Passengers boarded: " + totalPassengersBoarded);
		}

		if(totalPassengersBoarded == totalPassengers) {
			System.out.println("All Passengers served!");
			System.exit(0);
		}
	}

	public static boolean loadTrainReturned = false;
	public static int threadsCompleted = 0;
}