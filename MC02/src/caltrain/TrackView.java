package caltrain;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class TrackView {
	public Pane layout;
	public Scene scene;
	public ArrayList<TrainAnimation> anim = new ArrayList<TrainAnimation>();
	public ImageView station;
	
	public TrackView(){
		layout = new Pane();
		scene = new Scene(layout, 500, 500);
		
		createStation(20, 150);
		createStation(20, 300);
	}
	
	public void createStation(double x, double y){
		station = new ImageView();
		station.setImage(new Image("sprites/horizontalstation.png"));
		
		station.setX(x);
		station.setY(y);
		
		layout.getChildren().add(station);
	}
	
	public void addTrain(){
		TrainAnimation t = new TrainAnimation(scene);
		layout.getChildren().add(t.getTrain());
		anim.add(t);
		
		layout.getChildren().remove(station);
		layout.getChildren().add(station);
	}
}
