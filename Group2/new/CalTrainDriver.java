import java.util.*;
import java.lang.*;

public class CalTrainDriver {
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
		int totalPassengers = 20;
		int passengersLeft = totalPassengers;
		for(int i=0;i<totalPassengers;i++) {
			Passenger robot = new Passenger(allStations.get((int)Math.floor(Math.random()*8)), ctrain, i);
			threadsCompleted++;
			try {Thread.sleep(1200);} catch(Exception e){}
		}

		/* Actual Program */
		System.out.println("\n---------------------\n");
		int totalPassengersBoarded = 0;
		int maxFreeSeats = 10;
		int trainCtr = 0;
		while(passengersLeft > 0) {
			int freeSeats = (int)(Math.floor(Math.random() * maxFreeSeats));

			/* Train is entering first station */
			loadTrainReturned = false;
			Train newTrain = new Train(allStations.get(0), ctrain, freeSeats, trainCtr);
			loadTrainReturned = true;
			System.out.println("Train " + newTrain.getTrainNum() + " entering first station with "
							   + freeSeats + " free seats");
			allTrains.add(newTrain);

			for(int j=0;j<allTrains.size();j++) {
				int threadsToReap = Math.min(allTrains.get(j).getBoardStation().getWaitingPass(), allTrains.get(j).getFreeSeats());
				System.out.println("Expected Free Seats: " + allTrains.get(j).getFreeSeats() + 
								   ", Expected Passengers Left: " + allTrains.get(j).getBoardStation().getWaitingPass());
				int threadsReaped = 0;

				/* Passengers board train */
				while(threadsReaped < threadsToReap) {
					if (threadsCompleted > 0) {
						threadsReaped++;
						ctrain.station_on_board(allTrains.get(j).getBoardStation(), threadsReaped == threadsToReap,
												allTrains.get(j).getBoardStation().getWaitPassengers().get(0));
					}
				}

				passengersLeft -= threadsReaped;
				totalPassengersBoarded += threadsReaped;

				if(threadsToReap != threadsReaped)
					System.out.println("Error: Too many passengers on this train!");

				try{Thread.sleep(1500);} catch(Exception e){}
			}
		
			trainCtr++;
			System.out.println("Passngers left: " + passengersLeft);
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