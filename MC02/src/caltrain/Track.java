package caltrain;

import java.util.ArrayList;

import javafx.animation.PathTransition;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathElement;

public class Track {
	private final String[] stationType = {"sHorizontal", "sVertical"};
	
	private Button stations[] = new Button[8];
	private Pane layout = new Pane();
	private Group root = new Group();
	
	private ArrayList<PathTransition> animsNorth = new ArrayList<>();
	private ArrayList<PathTransition> animsSouth = new ArrayList<>();
	private ArrayList<Path> roads = new ArrayList<>();
	private ArrayList<ImageView> cars = new ArrayList<>();
	
	private PathElement[] pathNorth = 
		   {
				   new MoveTo(10, 145),
				   new LineTo(10, 80),
				   new ArcTo(90, 90, 0, 80, 10, false, true),
				   new LineTo(290, 10),
				   new ArcTo(90, 90, 0, 400, 110, false, true),
				   new LineTo(390, 300),
				   new ArcTo(90, 90, 0, 300, 390, false, true),
				   new LineTo(100, 390),
				   new ArcTo(90, 90, 0, 10, 300, false, true),
				   new ClosePath()
		   };
	private PathElement[] pathSouth = 
	      {
	    	 new MoveTo(40, 145),
	         new LineTo(40, 280),
	         new ArcTo(90, 90, 0, 80, 360, false, false),
	         new LineTo(300, 360),
	         new ArcTo(90, 90, 0, 360, 290, false, false),
	         new LineTo(360, 80),
	         new ArcTo(90, 90, 0, 310, 40, false, false),
	         new LineTo(90, 40),
	         new ArcTo(90, 90, 0, 40, 90, false, false),
	         new ClosePath()
	      };
	
	public Track(){
		layout.setId("layout");
	}

	public Button[] getStations() {
		return stations;
	}

	public void setStations(Button[] stations) {
		this.stations = stations;
	}

	public Pane getLayout() {
		return layout;
	}

	public void setLayout(Pane layout) {
		this.layout = layout;
	}

	public Group getRoot() {
		return root;
	}

	public void setRoot(Group root) {
		this.root = root;
	}

	public ArrayList<PathTransition> getAnimsNorth() {
		return animsNorth;
	}

	public void setAnimsNorth(ArrayList<PathTransition> animsNorth) {
		this.animsNorth = animsNorth;
	}

	public ArrayList<PathTransition> getAnimsSouth() {
		return animsSouth;
	}

	public void setAnimsSouth(ArrayList<PathTransition> animsSouth) {
		this.animsSouth = animsSouth;
	}
	
}
