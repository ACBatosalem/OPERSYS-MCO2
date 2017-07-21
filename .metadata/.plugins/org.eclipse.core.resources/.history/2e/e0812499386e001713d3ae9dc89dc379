package controllers;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;
import models.CalTrain;
import models.CalTrainDriver;
import models.Passenger;
import models.Station;
import models.Train;
import views.Game;

public class Main extends Application{
	
	@Override
	public void start(Stage window) throws Exception {
		CalTrain ctrain = new CalTrain();
		ArrayList<Station> allStations = new ArrayList<Station>();
		ArrayList<Train> allTrains = new ArrayList<Train>();
		
		/* Passenger-related variables */
		int totalPassengers = 10;
		int passengersLeft = totalPassengers;	// Passengers left to be picked up
		int passengersServed = totalPassengers;	// Passengers who haven't arrived to their destination
		boolean trainsReturned = true;			// If trains haven't returned to Station 0
		
		/* Program running-related variables */
		int totalPassengersBoarded = 0;
		int totalNumSeats = 0;
		int threadsCompleted = 0;
		int maxFreeSeats = 5;
		int trainCtr = 0;
		int passCtr = 10;
		int maxInsert = 0;
		boolean loadTrainReturned = false;
		
		/* Temporary Variables */
		int inStationNum, freeSeats;
		Passenger tempRobot;
		Train tempTrain;
		
		/* Initialize Stations */
		for(int i=0;i<8;i++) {
			allStations.add(ctrain.station_init(i));
			if (i >= 1 && i < 8) {
				allStations.get(i-1).setRightStation(allStations.get(i));
				allStations.get(i).setLeftStation(allStations.get(i-1));
			}
			System.out.println(allStations.get(i).displayNextStations());
		}
		System.out.println();	// Separate Station Initialization Printing
		
		/* Initialize Passengers */
		for(int i=0;i<totalPassengers;i++) 
		{
			inStationNum = (int)Math.floor(Math.random()*8);
			tempRobot = new Passenger(allStations.get(inStationNum), ctrain, i, allStations.get(CalTrainDriver.outStat(inStationNum)));
			threadsCompleted++;
			try {Thread.sleep(300);} catch(Exception e){}
		}
		
		Game g = new Game();
		
		window.setScene(g.scene);
		window.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
