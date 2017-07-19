package caltrain;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Sample extends Application{
	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage window) throws Exception {
		Pane l = new Pane();
		l.setId("layout");
		
		Scene s = new Scene(l, 500, 500);
		
		s.getStylesheets().add("style.css");
		
		window.setScene(s);
		window.show();
	}
	
	
}
