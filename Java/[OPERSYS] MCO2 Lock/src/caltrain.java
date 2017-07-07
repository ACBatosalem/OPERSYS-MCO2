import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class caltrain{
	public static caltrain singleton;
	
	public GUIDrive caltrainGUI;

	ArrayList<Station> stations = new ArrayList<>();
	
	public int trainCtr;
	public int peopleCtr;

	public caltrain(){
		this.trainCtr = 1;
		this.peopleCtr = 1;
		this.stations = new ArrayList<>();
		station_init();
	}
	
	public void setGUI(GUIDrive GUI){
		this.caltrainGUI = GUI;
	}
	
	public static caltrain getInstance(){
		if(singleton == null){
			singleton = new caltrain();
		}
		return singleton;
	}
	
	
	public void station_load_train(Station s, int count){
		new Train("Train " + trainCtr, count, s, this);
		trainCtr++;
	}
	
	public void station_wait_for_train(Station s){
		String str;
		s.addNewPassenger(peopleCtr);
		
		str = s.getPeople().get(s.getPeople().size()-1).getName() + " is now waiting.\n";		
		if(caltrainGUI != null)
			caltrainGUI.stationList.get(stations.indexOf(s)).append(str);
		System.out.println("\t\t" + str);
		peopleCtr++;
	}
	
	public void station_on_board(Station s){
		String str;
		Passenger p = s.removePassenger();
		
		str = p.getName() + " is riding " + s.getTrain().getName() + ".\n";
		
		System.out.println("\t" + str);
		
		if(caltrainGUI != null)
			for(int i = 0; i < stations.size(); i++){
				if(s.getName().equals(stations.get(i).getName())){
					this.caltrainGUI.stationList.get(i).append(str);		
				}
			}
		
		s.getTrain().getOnBoard(p);
		
		str = p.getName() + " has been seated.\n";
		
		if(caltrainGUI != null)
			for(int i = 0; i < stations.size(); i++){
				if(s.getName().equals(stations.get(i).getName())){
					caltrainGUI.stationList.get(i).append(str);		
				}
			}
		
		
		System.out.println("\t\t" + str);
		
		if(s.getPeople().size() == 0){
			str = "No more waiting passengers.\n";
		
			if(caltrainGUI != null)
				for(int i = 0; i < stations.size(); i++){
					if(s.getName().equals(stations.get(i).getName())){
						caltrainGUI.stationList.get(i).append(str);		
					}
				}
			System.out.println(str);
		}
	
		if(s.getTrain().getLimit() == 0){
			str = "Train is full.\n";
			
			if(caltrainGUI != null)
				for(int i = 0; i < stations.size(); i++){
					if(s.getName().equals(stations.get(i).getName())){
						caltrainGUI.stationList.get(i).append(str);		
					}
				}
			System.out.println(str);
		}
		
	}
	
	public void station_init(){
		stations.add(new Station("Station " + (stations.size()+1), this));
		if(stations.size() > 1){
			stations.get(stations.size()-2).setNextStation(stations.get(stations.size()-1));
		}
	}
	
	public ArrayList<Station> getStations(){
		return this.stations;
	}
	
	public void showSummary(){
		for(int i = 0; i < stations.size(); i++){
			Station s = stations.get(i);
			
			System.out.println("====================================================");
			System.out.println(s.getName());
			System.out.println("NO. OF PASSENGERS ON TRAIN: " + s.getPeople().size());
		}
	}
	
	public void showStatus(){
		for(int i = 0; i < stations.size(); i++){
			System.out.println("====================================================");
			Station s = stations.get(i);
			System.out.println("STATION: " + s.getName());
			System.out.println("NO. OF PASSENGERS ON TRAIN: " + s.getPeople().size());
			if(s.getPeople().size() == 0){
				System.out.println("\tNO PASSENGERS TO SHOW");
			}else{
				for(int j = 0; j < s.getPeople().size(); j++){
					if(j == 0){
						System.out.println("PASSENGERS WAITING:");
					}
					System.out.println("\t" + s.getPeople().get(j).getName());
				}
			}
		}
		System.out.println("====================================================");	
	}
}
