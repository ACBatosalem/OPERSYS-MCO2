package caltrain;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application{

	@Override
	public void start(Stage window) throws Exception {
		
		Pane layout = new Pane();
		
		Scene scene = new Scene(layout, 500, 500);
		
		TrainAnimation t = new TrainAnimation(scene);
		TrainAnimation t2 = new TrainAnimation(scene);
		
		layout.getChildren().add(t.getTrain());
		layout.getChildren().add(t2.getTrain());
		
		
		window.setScene(scene);
		window.show();
		
	}
	
	private int outStat(int num) {
		int number = (int)Math.floor(Math.random()*8);
		return (num != number) ? number : outStat(num);
	}
	
	public static void main(String[] args){
		launch(args);
	}
}
