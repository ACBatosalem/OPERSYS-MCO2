package views;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{
	
	@Override
	public void start(Stage window) throws Exception {
		
		Game g = new Game();
		
		window.setScene(g.scene);
		window.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}