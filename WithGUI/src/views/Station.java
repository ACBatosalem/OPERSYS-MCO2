package views;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Station {
	public final static String waiting = "Waiting: ";
	public Pane layout;
	public int stationNum;
	public Label text;
	public Label text2;
	
	public Station(int num, int pass){
		layout = new Pane();
		text = new Label("" + (num + 1));
		text2 = new Label(waiting + pass);
		
		stationNum = num;
		
		text.setLayoutX(220);
		text.setLayoutY(33);
		
		text2.setLayoutX(5);
		text2.setLayoutY(40);
		
		text.setFont(new Font("Arial", 16));
		text.setTextFill(Color.WHITE);
		
		text2.setFont(new Font("Arial", 14));
		text2.setTextFill(Color.WHITE);
		
		setUpLayout();
	}
	
	public void setUpLayout(){
		layout.setStyle("-fx-background-image: url('sprites/stationprev.png')");
		layout.setMinSize(300, 200);
		layout.setMaxSize(300, 200);
		
		layout.getChildren().addAll(text, text2);
	}
	
}
