import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TextDrive {

	public static void main(String[] args) {
		caltrain c = new caltrain();
		Scanner sc = new Scanner(System.in);

		System.out.println("===================================================");
		System.out.println("               CALTRAIN SYNCHRONIZER               ");
		System.out.println("===================================================");
				
		while(true){
			System.out.println("1-ADD STATION");
			System.out.println("2-ADD PASSENGER");
			System.out.println("3-LET TRAIN IN ");
			System.out.println("4-DISPLAY STATUS");
			
			int input = sc.nextInt();
			
			if(input == 1){
				c.station_init();
				c.showSummary();
			}else if(input == 2){
				System.out.println();
				System.out.println("ENTER STATION NUMBER [1] - [" + (c.getStations().size()) + "]");
				int ininput1 = sc.nextInt();
				c.station_wait_for_train(c.getStations().get(ininput1-1));
				c.showSummary();
			}else if(input == 3){
				System.out.println("STARTING FROM WHERE  [1] - [" + (c.getStations().size()) + "]");
				int ininput2 = sc.nextInt();
				System.out.println("HOW MANY SEATS WOULD THE TRAIN HAVE? ");
				int ininput3 = sc.nextInt();
				c.station_load_train(c.getStations().get(ininput2-1), ininput3);
				c.showSummary();
			}else if(input == 4){
				c.showStatus();
			}
		}
	}
}

