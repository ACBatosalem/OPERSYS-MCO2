package views;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class InfoPanel {
	public VBox layout;
	public Station s;
	public TrainPreview p;

	public InfoPanel(){
		layout = new VBox(0);
		
		createStation(0, 10);
		train(1, 0, 0);
	}
	
	public void createStation(int index, int num){
		s = new Station(index, num);
		layout.getChildren().clear();
		addLayout(s.layout);
		if(p != null)
			addLayout(p.layout);
	}
	
	public void removeChildren(){
		System.out.println("s " + layout.getChildren().remove(s.layout));
		System.out.println("p " + layout.getChildren().remove(p.layout));
	}
	
	public void addLayout(Pane n){
		layout.getChildren().add(n);
	}
	
	public void resetLayout(){
		removeChildren();
		addLayout(s.layout);
		addLayout(p.layout);
	}
	
	public void train(int trainNum, int passNum, int nextStation){
		p = new TrainPreview(trainNum, passNum, nextStation);
		layout.getChildren().clear();
		if(s != null)
			addLayout(s.layout);
		addLayout(p.layout);
	}
	
}
