import java.util.*;
import java.lang.*;

public class CalTrainDriver {
	public static void main(String[] args) {
		CalTrain ctrain = new CalTrain();
		ArrayList<Station> allStations = new ArrayList<Station>();

		/* Station init */
		for(int i=0;i<8;i++) {
			allStations.add(new Station(i));
			if (i >= 1 && i < 7)
				allStations.get(i-1).setNextStation(allStations.get(i));
		}

		/* Passenger init */
		int tot_passengers = 20;
		int passengers_left = tot_passengers;
		for(int i=0;i<tot_passengers;i++) {
			Passenger robot = new Passenger(allStations.get((int)Math.floor(Math.random() * 8)), ctrain, i);
			threads_completed++;
		}

		int tot_passengers_boarded = 0;
		int max_free_seats = 10;
		int pass = 0;
		while (passengers_left > 0) {
			int free_seats = 5;
			System.out.println("Train entering station with " + free_seats + " free seats");
			load_train_returned = false;
			Train train = new Train(allStations.get(0), ctrain, free_seats, pass);
			load_train_returned = true;

			int threads_to_reap = Math.min(passengers_left, free_seats);
			System.out.println("Expected Free Seats: " + free_seats + ", Expected Passengers Left: " + passengers_left);
			int threads_reaped = 0;
			while (threads_reaped < threads_to_reap) {
				if (threads_completed > 0) {
					threads_reaped++;
					ctrain.station_on_board(train.getBoardStation(), threads_reaped == threads_to_reap);
					threads_completed--;
				}
			}
					

		/*	for(int i=0;i<10;i++) {
				if (i > 5 && load_train_returned)
					break;
			}


			if(!load_train_returned) {
				System.out.println("Error: station_load_train failed to return");
				break;
			}


			while(threads_completed > 0) {
				threads_reaped++;
				threads_completed--;
			}*/
			//try{Thread.sleep(1000);} catch(Exception e){}

			passengers_left -= threads_reaped;
			tot_passengers_boarded += threads_reaped;

			if (threads_to_reap != threads_reaped) {
				System.out.println("Error: Too many passengers on this train!");
			}

			pass++;
			System.out.println("Passengers Left: "+passengers_left);
			System.out.println("Passengers boarded: "+tot_passengers_boarded);
		}

		if (tot_passengers_boarded == tot_passengers) {
			System.out.println("Looks good!");
			System.exit(0);
		}
		else {
			System.out.println("Error: expected " + tot_passengers + " total boarded passengers, but got " 
				               + tot_passengers_boarded + "!");
		}
	}

	public static boolean load_train_returned = false;
	public static int threads_completed = 0;
}