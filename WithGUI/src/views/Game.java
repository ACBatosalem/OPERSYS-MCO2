package views;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Game {
	public BorderPane layout;
	public Scene scene;
	public Track t;
	public InfoPanel p;
	public static int ctr = 0;
	
	public Game(){
		layout = new BorderPane();
		t = new Track(this);
		p = new InfoPanel();
		
		setUpLayout();
		
		scene = new Scene(layout, 800, 500);
		
		scene.getStylesheets().add("style.css");
		
		handleEvents();
	}
	
	public void setUpLayout(){
		layout.setCenter(t.layout);
		layout.setRight(p.layout);
	}
	
	public void handleEvents(){
		scene.setOnKeyPressed(e -> {
			switch(e.getCode()){
			case K: t.createTrain(this); break;
			default: 
				for(int i = 0; i < t.anims.size(); i++){
					if(ctr % 2 == 0)
						t.getAnim(i).stop();
					else
						t.getAnim(i).start();
				}
				ctr++;
				break;
			}
		});
		
		for(int i = 0; i < t.stations.length; i++){
			int j = i;
			t.stations[i].setOnMouseClicked(e -> {
				p.createStation(j, 10);
				layout.setRight(p.layout);
			});
		}
	}
}
