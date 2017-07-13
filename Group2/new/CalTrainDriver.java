import java.util.*;

public class CalTrainDriver {
	public static void main(String[] args) {
		CalTrain ctrain = new CalTrain();
		Station firstStation = new Station();

		ctrain.station_load_train(firstStation, 0);
		ctrain.station_load_train(firstStation, 10);

		int tot_passengers = 5;
		int passengers_left = tot_passengers;
		for(int i=0;i<tot_passengers;i++) {
			Passenger robot = new Passenger(firstStation, ctrain, i);
			threads_completed++;
		}

		//Passenger robot = new Passenger(firstStation, ctrain, 2);
		//threads_completed++;

		ctrain.station_load_train(firstStation, 0);

		int tot_passengers_boarded = 0;
		int max_free_seats = 50;
		int pass = 0;
		while (passengers_left > 0) {
			int free_seats = 5;
			System.out.println("Train entering station with " + free_seats + " free seats");
			load_train_returned = false;
			Train train = new Train(firstStation, ctrain, free_seats);
			load_train_returned = true;

			int threads_to_reap = Math.min(passengers_left, free_seats);
			System.out.println("Expected Free Seats: " + free_seats + ", Expected Passengers Left: " + passengers_left);
			int threads_reaped = 0;
			while (threads_reaped < threads_to_reap) {
				if (threads_completed > 0) {
					threads_reaped++;
			System.out.println("reaped: "+threads_reaped+" to reap: "+threads_to_reap +" complete"+threads_completed);
					
					ctrain.station_on_board(firstStation);

					threads_completed++;
				}
			}

			for(int i=0;i<10;i++) {
				if (i > 5 && load_train_returned)
					break;
			}

			if(!load_train_returned) {
				System.out.println("Error: station_load_train failed to return");
				break;
			}

			System.out.println("???????????????????reaped: "+threads_reaped+" to reap: "+threads_to_reap +" complete"+threads_completed);

			/*while(threads_completed > 0) {
				threads_reaped++;
				threads_completed--;
			}*/

			passengers_left -= threads_reaped;
			tot_passengers_boarded += threads_reaped;
			System.out.println("!!!!!!!!!!reaped: "+threads_reaped+" to reap: "+threads_to_reap +" complete"+threads_completed);

			if (threads_to_reap != threads_reaped) {
				System.out.println("Error: Too many passengers on this train!");
			}

			pass++;
			System.out.println("Passengers left: "+passengers_left);
		}

		if (tot_passengers_boarded == tot_passengers) {
			System.out.println("Looks good!");
		}
		else {
			System.out.println("Error: expected " + tot_passengers + " total boarded passengers, but got " 
				               + tot_passengers_boarded + "!");
		}
	}

	public static boolean load_train_returned = false;
	public static int threads_completed = 0;
}