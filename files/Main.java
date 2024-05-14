
import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

//Main class includes starting the javafx application,switching scenes,reading file and methods related with 
//calculations for paths.
public class Main extends Application {
	//MainMenu is the first scene when user runs the program.
	//It contains Main Menu nodes.
	public Scene MainMenu;
	
	//This level counter is used for primary stage to load the correct level.
	public int levelCounter=0;
	
	@Override//start method of javafx application.
	public void start(Stage primaryStage) throws IOException, Exception {
			//Main menu image.
		 	Image menuImage = new Image("themenuupng.png");
	        ImageView menuImage2 = new ImageView(menuImage);
	        menuImage2.setFitHeight(800);
	        menuImage2.setFitWidth(800);

	        //Start button image.
	        Image startImage = new Image("startbutton.png");
	        ImageView startImageV = new ImageView(startImage);
	        //Start button image with user interaction.
	        Image startImage2 = new Image("startbutton2.png");
	        startImageV.setLayoutX(123);
	        startImageV.setLayoutY(114);
	        
	        //Quit button image.
	        Image quitImage = new Image("quit.png");
	        //Quit image with user interaction.
	        Image quitImage2 = new Image("quit2.png");
	        ImageView quitImageV = new ImageView(quitImage);
	        quitImageV.setLayoutX(123);
	        quitImageV.setLayoutY(408);

	        //levels button image.
	        Image levelImage = new Image("levels.png");
	        ImageView levelImageV = new ImageView(levelImage);
	        levelImageV.setLayoutX(123);
	        levelImageV.setLayoutY(274);

	        //levels image with user interaction.
	        Image levelImage2 = new Image("levels2.png");
	       
	        //setting the start image according to mouse events.
	        startImageV.setOnMouseEntered(event -> {
	            startImageV.setImage(startImage2);
	        });
	        startImageV.setOnMouseExited(event -> {
	            startImageV.setImage(startImage);
	        });
	        
	        //setting the quit image according to mouse events.
	        quitImageV.setOnMouseEntered(event -> {
	            quitImageV.setImage(quitImage2);
	        });
	        quitImageV.setOnMouseExited(event -> {
	            quitImageV.setImage(quitImage);
	        });

	        //setting the levels image according to mouse events.
	        levelImageV.setOnMouseEntered(event -> {
	            levelImageV.setImage(levelImage2);
	        });
	        levelImageV.setOnMouseExited(event -> {
	            levelImageV.setImage(levelImage);
	        });
	        
	        //Whenever user clicks levels image primary stage is set to display LevelsScene Menu.
	        levelImageV.setOnMouseClicked(event -> {
	            primaryStage.setScene(LevelsScene(primaryStage, MainMenu));
	        });

	        // Whenever user clicks quit image program ends.
	        quitImageV.setOnMouseClicked(event -> Platform.exit());

	        //If user directly starts the game level levelCounter is updated by 1 and primaryStage
	        //is set to display level 1.
	        startImageV.setOnMouseClicked(e -> {

	            try {
	            	levelCounter++;
	                primaryStage.setScene(startLevel(primaryStage,levelCounter,MainMenu));
	            } catch (Exception e2) {
	                e2.printStackTrace();
	            }
	        });
	        
	        //Adding all buttons and images to MainMenu pain.
	        Pane sPane = new Pane();
	        sPane.getChildren().addAll(menuImage2, startImageV, quitImageV, levelImageV);

	        //creating a MainMenu scene.
	        MainMenu = new Scene(sPane, 800, 800);
	        primaryStage.setResizable(false);
	        primaryStage.setScene(MainMenu);
	        primaryStage.show();
	    }
	
		//main method of this class launches start method.
	    public static void main(String[] args) {
	        launch();
	    }
	    
	    
	    //ExitToMenu method is used for whenever user fails in a level this method is invoked and displays a gameover scene.
	    private Scene ExitToMenu(Stage primaryStage,Scene scene) {
	    	//Creating exitImage and ExitImage View
	    	Image exitImage = new Image("gameoverscene.png");
	    	ImageView exitImageV = new ImageView(exitImage);
	    	Pane pane = new Pane(exitImageV);
	    	//This image covers whole screen.
	    	exitImageV.setFitHeight(800);
		    exitImageV.setFitWidth(800);
		    
		    //When user clicks the exit image primary stage is set to display Main Menu scene.
		    exitImageV.setOnMouseClicked(e->{
		    	primaryStage.setScene(MainMenu);
		    });
			//Create Exit Scene.
			Scene sceneExit = new Scene(pane,800,800);
			return sceneExit;
	    	
	    }
	    
	    //NextLevel method returns a scene.This scene is displayed whenever user completes a level succesfully.
	    private Scene NextLevel(Stage primaryStage,Scene scene) {
	    	//Creating next level image and next level image View
	    	Image nextLevelImage = new Image("congratsNextLevel.png");
	    	ImageView nextLevelImageV = new ImageView(nextLevelImage);
	    	Pane pane = new Pane(nextLevelImageV);
	    	//This image covers whole screen.
	    	nextLevelImageV.setFitHeight(800);
		    nextLevelImageV.setFitWidth(800);
		    Scene sceneNext = new Scene(pane,800,800);
		    //When user clicks the next level image primary stage is set to display next level scene scene.
		    nextLevelImageV.setOnMouseClicked(e->{
		    	try {
					primaryStage.setScene(startLevel(primaryStage, levelCounter, sceneNext));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
		    });
			//creating next scene.
			return sceneNext;
	    	
	    }
	    
	    //disPlayWin method returns a winning screen after user completes final level succesfully.
	    private Scene displayWin(Stage primaryStage,Scene scene) {
	    	//setting level counter to 0 after user wins the game.
	    	levelCounter=0;
	    	
	    	//Creating win screen.
	    	Image exitImage = new Image("winScreen.png");
	    	ImageView exitImageV = new ImageView(exitImage);
	    	Pane pane = new Pane(exitImageV);
	    	//Win Screen covers whole screen.
	    	exitImageV.setFitHeight(800);
		    exitImageV.setFitWidth(800);
		    
		    Scene sceneExit = new Scene(pane,800,800);
		    //If user clicks to anywhere on image primary stage is set to Main Menu.
		    exitImageV.setOnMouseClicked(e->{
		    	try {
					primaryStage.setScene(MainMenu);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
		    });
	   	
			return sceneExit;
	    	
	    }
	    //This method returns a scene.This Scene contains levels with user interaction
	    //User can choose every level here.
	    private Scene LevelsScene(Stage primaryStage,Scene scene) {
	        // Background image for the levels screen
	        Image levelsScreen = new Image("levelsbackgrounds.png");
	        ImageView levelsScreenV = new ImageView(levelsScreen);

	        // Create a pane to hold the elements of the levels scene
	        Pane pane = new Pane(levelsScreenV);

	        // Set exit to menu button
	        Image exitToMenu = new Image("exitToMenu.png");
	        ImageView exitToMenuV = new ImageView(exitToMenu);
	        exitToMenuV.setFitWidth(200);
	        exitToMenuV.setFitHeight(100);
	        exitToMenuV.setLayoutX(300);
	        exitToMenuV.setLayoutY(300);

	        Image exitToMenus = new Image("exitToMenuOnAct2.png");
	        ImageView exitToMenusV = new ImageView(levelsScreen);

	        // Change the image when mouse exited
	        exitToMenuV.setOnMouseExited(e->{
	            try {
	                exitToMenuV.setImage(exitToMenu);

	            } catch (Exception e2) {
	                e2.printStackTrace();
	            }
	        });

	        // Change the image when mouse entered
	        exitToMenuV.setOnMouseEntered(e -> {

	            try {
	                exitToMenuV.setImage(exitToMenus);

	            } catch (Exception e2) {
	                e2.printStackTrace();
	            }
	        });

	        // Return to Main Menu when the user clicked.
	        exitToMenuV.setOnMouseClicked(e -> {

	            try {

	                primaryStage.setScene(MainMenu);
	            } catch (Exception e2) {
	                e2.printStackTrace();
	            }
	        });

	        Image level1 = new Image("level1s.png");
	        ImageView level1V = new ImageView("level1s.png");
	        level1V.setFitWidth(150);
	        level1V.setFitHeight(300);
	        level1V.setLayoutX(1);
	        level1V.setLayoutY(450);

	        // Set level 1 button
	        Image level1s = new Image("level1ss.png");
	        ImageView level1sV = new ImageView(level1s);

	        // Change the image when mouse exited
	        level1V.setOnMouseExited(e->{
	            try {
	                level1V.setImage(level1);

	            } catch (Exception e2) {
	                e2.printStackTrace();
	            }
	        });

	        // Change the image when mouse entered
	        level1V.setOnMouseEntered(e -> {

	            try {
	                level1V.setImage(level1s);

	            } catch (Exception e2) {
	                e2.printStackTrace();
	            }
	        });
	        
	        // Set the scene by calling the startLevel method when the mouse is clicked
	        level1V.setOnMouseClicked(e -> {

	            try {
	                levelCounter++;
	                primaryStage.setScene(startLevel(primaryStage,levelCounter,scene));
	            } catch (Exception e2) {
	                e2.printStackTrace();
	            }
	        });
	        
	        // Same procedures as the level1 part is applied in the rest of this code.
	        Image level2 = new Image("level2s.png");
	        ImageView level2V = new ImageView(level2);
	        Image level2s = new Image("level2ss.png");
	        ImageView level2sV = new ImageView(level2s);

	        level2V.setFitWidth(150);
	        level2V.setFitHeight(300);
	        level2V.setLayoutX(175);
	        level2V.setLayoutY(450);

	        level2V.setOnMouseExited(e->{
	            try {
	                level2V.setImage(level2);

	            } catch (Exception e2) {
	                e2.printStackTrace();
	            }
	        });

	        level2V.setOnMouseClicked(e -> {

	            try {
	                levelCounter+=2;
	                primaryStage.setScene(startLevel(primaryStage,levelCounter,scene));
	            } catch (Exception e2) {
	                e2.printStackTrace();
	            }
	        });


	        level2V.setOnMouseEntered(e -> {

	            try {
	                level2V.setImage(level2s);

	            } catch (Exception e2) {
	                e2.printStackTrace();
	            }
	        });


	        Image level3 = new Image("level3s.png");
	        ImageView level3V = new ImageView(level3);
	        level3V.setFitWidth(150);
	        level3V.setFitHeight(300);
	        level3V.setLayoutX(325);
	        level3V.setLayoutY(450);

	        Image level3s = new Image("level3ss.png");
	        ImageView level3sV = new ImageView(level3);

	        level3V.setOnMouseExited(e->{
	            try {
	                level3V.setImage(level3);

	            } catch (Exception e2) {
	                e2.printStackTrace();
	            }
	        });


	        level3V.setOnMouseEntered(e -> {

	            try {
	                level3V.setImage(level3s);

	            } catch (Exception e2) {
	                e2.printStackTrace();
	            }
	        });

	        level3V.setOnMouseClicked(e -> {

	            try {
	                levelCounter+=3;
	                primaryStage.setScene(startLevel(primaryStage,levelCounter,scene));
	            } catch (Exception e2) {
	                e2.printStackTrace();
	            }
	        });

	        Image level4 = new Image("level4S.png");
	        ImageView level4V = new ImageView(level4);
	        level3V.setFitWidth(150);
	        level3V.setFitHeight(300);
	        level3V.setLayoutX(325);
	        level3V.setLayoutY(450);



	        Image level4s = new Image("level4ss.png");
	        ImageView level4sV = new ImageView(level4);
	        level4V.setFitWidth(150);
	        level4V.setFitHeight(300);
	        level4V.setLayoutX(475);
	        level4V.setLayoutY(450);
	        level4V.setOnMouseExited(e->{
	            try {
	                level4V.setImage(level4);

	            } catch (Exception e2) {
	                e2.printStackTrace();
	            }
	        });


	        level4V.setOnMouseEntered(e -> {

	            try {
	                level4V.setImage(level4s);

	            } catch (Exception e2) {
	                e2.printStackTrace();
	            }
	        });
	        level4V.setOnMouseClicked(e -> {

	            try {
	                levelCounter+=4;
	                primaryStage.setScene(startLevel(primaryStage,levelCounter,scene));
	            } catch (Exception e2) {
	                e2.printStackTrace();
	            }
	        });

	        Image level5 = new Image("level5S.png");
	        ImageView level5V = new ImageView(level5);
	        level5V.setFitWidth(150);
	        level5V.setFitHeight(300);
	        level5V.setLayoutX(625);
	        level5V.setLayoutY(450);

	        Image level5s = new Image("level5ss.png");
	        ImageView level5sV = new ImageView(level5);

	        level5V.setOnMouseExited(e->{
	            try {
	                level5V.setImage(level5);

	            } catch (Exception e2) {
	                e2.printStackTrace();
	            }
	        });


	        level5V.setOnMouseEntered(e -> {

	            try {
	                level5V.setImage(level5s);

	            } catch (Exception e2) {
	                e2.printStackTrace();
	            }
	        });
	        level5V.setOnMouseClicked(e -> {

	            try {
	                levelCounter+=5;
	                primaryStage.setScene(startLevel(primaryStage,levelCounter,scene));
	            } catch (Exception e2) {
	                e2.printStackTrace();
	            }
	        });
	        
	        
	        pane.getChildren().addAll(level1V,level2V,level3V,level4V,level5V,exitToMenuV);
	        Scene levelScene = new Scene(pane,800,800);

	        return levelScene;
	    }

	   //This method reads a file and returns a level scene.
	private Scene startLevel(Stage primaryStage, int level,Scene scene) throws Exception {
		String levelS = level + "";
		//create file object for txt file.
		File file = new File("level"+levelS+".txt");
	
		//read data from file.
		Scanner input = new Scanner(file);
		
		//Path arraylist for paths with different indexes also their index is also the position in this arraylist.
		ArrayList<Path> pathArrayList = new ArrayList<Path>();
		
		//arraylist of all traffic lights in this level.
		ArrayList<TrafficLight> trafficLightList = new ArrayList<TrafficLight>();
		
		//In this arraylist every path lenght is in the same place with its path index.
		ArrayList<Double> pathLenList = new ArrayList<Double>();
		
		//carList represents cars on the road that not involved any crash
		ArrayList<Car> carList = new ArrayList<Car>();
		
		//crashedCars list represents all the cars involved in collusion.This arraylist for removing cars
		//from pane 0.5 second lateer.
		ArrayList<Car> crashedCars = new ArrayList<Car>();
		
		//Create spawn Manager and ScoreBoard instances for new level.
		SpawnManager spawnManager = new SpawnManager();
		ScoreBoard scoreBoard = new ScoreBoard();
		
		//Create StackPane for add all panes at the end.
		StackPane stackPane = new StackPane();
		stackPane.setMaxSize(800, 800);
		Pane pane = new Pane();
		pane.setMaxSize(800, 800);

		//GridPane for adding road tiles and buildings.
		GridPane gridPane = new GridPane();
		double cellWidth = 0;
		double cellHeight = 0;
		double sceneWidth = 0, sceneHeight = 0;
		
		//read data until the end of the file.
		while (input.hasNext()) {
			//read string to the end of line
			String str = input.nextLine();
			//tokenize the string
			String[] tokens = str.split(" ");

			
			//read first element of tokens and establish classes.
			switch (tokens[0]) {
			case "Metadata":
				//set the scene width and height.
				sceneWidth = Double.parseDouble(tokens[1]);
				sceneHeight = Double.parseDouble(tokens[2]);
				gridPane.setMaxSize(sceneWidth, sceneHeight);
				
				//Background of level with using rectangles.
				for (int i = 0; i < Integer.parseInt(tokens[3]); i++) {
					for (int j = 0; j < Integer.parseInt(tokens[4]); j++) {
						Rectangle square = new Rectangle();

						cellWidth = Double.parseDouble(tokens[1]) / Double.parseDouble(tokens[3]);
						cellHeight = Double.parseDouble(tokens[2]) / Double.parseDouble(tokens[4]);

						square.setHeight(cellHeight);
						square.setWidth(cellWidth);
						square.setFill(Color.LAVENDER);
						//Put in every cell to create background.
						gridPane.add(square, i, j);
						
					//Set the max crash count and win count. 	
					Car.CarCrash= Integer.parseInt(tokens[7]);
						Car.CarWin= Integer.parseInt(tokens[6]);
					}
				}

				break;
			case "RoadTile":
				//Extract data from text file for roadtiles.
				int roadType = Integer.parseInt(tokens[1]);
				int roadRotation = Integer.parseInt(tokens[2]);
				double x = Integer.parseInt(tokens[3]);
				double y = Integer.parseInt(tokens[4]);
				
				//Construct RoadTile objects.
				RoadTile roadTile = new RoadTile(roadType, roadRotation);
				//Add gridPane roadTile panes.
				gridPane.add(roadTile.buildRoad(cellWidth, cellHeight, x, y), (int) x, (int) y);
				break;

			case "TrafficLight":
				//Create a traffic Light object.
				TrafficLight trafficLight = new TrafficLight(Double.parseDouble(tokens[1]),
						Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3]), Double.parseDouble(tokens[4]));
			//Add this traffic light to the list.
				trafficLightList.add(trafficLight);
				
				
				break;
			case "Building":
				// Construct buildings and create building objects. 
				Building building = new Building(Integer.parseInt(tokens[1]),
				Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]), Integer.parseInt(tokens[5]));
				if(Integer.parseInt(tokens[1]) == 0 || Integer.parseInt(tokens[1])  == 1) {
				if(Integer.parseInt(tokens[2]) == 0 || Integer.parseInt(tokens[2]) == 180)
				gridPane.add(building.buildBuilding(), Integer.parseInt(tokens[4]), Integer.parseInt(tokens[5]), 3, 3);
				//Rotate of the building according to file.
				if(Integer.parseInt(tokens[2]) == 90 || Integer.parseInt(tokens[2]) == 270) {
					if(level==3)
						gridPane.add(building.buildBuilding(), Integer.parseInt(tokens[4])-1, Integer.parseInt(tokens[5]), 5, 3);
					else
						gridPane.add(building.buildBuilding(), Integer.parseInt(tokens[4]), Integer.parseInt(tokens[5]), 5, 5);
					}
				}
				else
				gridPane.add(building.buildBuilding(), Integer.parseInt(tokens[4]), Integer.parseInt(tokens[5]));

				break;

			case "Path":
				//Create a new Path obejct with MoveTo element.
				if (tokens[2].equals("MoveTo")) {
					MoveTo moveTo = new MoveTo(Double.parseDouble(tokens[3]), Double.parseDouble(tokens[4]));
					Path path = new Path(moveTo);
					
					//Add this path to the path arraylist.
					pathArrayList.add(path);
					
					//Send the spawn points to the spawn manager.
					spawnManager.spawnPoints.add(extractSpawnPoints(moveTo));
					
				}
				//Add line to elements to related paths.
				if (tokens[2].equals("LineTo")) {
					pathArrayList.get(Integer.parseInt(tokens[1])).getElements()
							.add((new LineTo(Double.parseDouble(tokens[3]), Double.parseDouble(tokens[4]))));
				}
				break;
			default:
				
				System.out.println(tokens[0]);
				break;
			}
		}
		//Initializing the path length to 0.0
		for (int i = 0; i < pathArrayList.size(); i++) {
			pathLenList.add(0.0);
		}
		//Calculate path length.
		for (int i = 0; i < pathArrayList.size(); i++) {

			pathLenList.set(i, calculateTotalPathLength(pathArrayList.get(i)));
		}
		
		//This timeLine is responsible for spawning cars every 1 second.
		 Timeline timeline = new Timeline(
		            new KeyFrame(Duration.seconds(1.0), event -> {
		            	//Send the car list to spawn manager.
		            	spawnManager.carsOnMap=carList;
		            	//Request a spawn point from spawn manager. 
		            	int carPathIndex = spawnManager.returnFreeSpawn();
		            	//If it is not -1 spawn a car.-1 means no spawn point available anywhere on map.
		            	if(carPathIndex!=-1) {
		            		
		            		PathTransition pt = new PathTransition();
		            		//Create car object.
							Car car = new Car(carPathIndex, pt);
							//Add car object to the car list.
							carList.add(car);
							
							pt.setPath(pathArrayList.get(carPathIndex));
							
							pt.setNode(car.getCar());
							//Set path transition oriantation for cars 
							pt.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
							pt.setAutoReverse(false);
							//Set Interpolator for cars travelling with constant speed everywhere
							pt.setInterpolator(Interpolator.LINEAR); 
							//Whenever a car succesfully ends path transition will finish.
							pt.setOnFinished(e -> {
								//update scoreboard.
								ScoreBoard.UpdateScore();
								//remove car from pane.
								pane.getChildren().remove(car.getCar());
								//remove the car from the car list.
								carList.remove(car);
								
							});
							//For every path, duration is set to calculated value according to their path length so
							// every car on map has same constant speed.Calculation is based on the formula
							//time = distance/velocity.
							pt.setDuration(Duration.seconds(pathLenList.get(carPathIndex) / 90.0));
							pt.play();
							//add car to the pane.
							pane.getChildren().add(car.getCar());
		            	}
		            	//No cars going to spawn in this duration.
		            	else ;
		            })
		        );
		        // timeline work until it stops.
		        timeline.setCycleCount(Timeline.INDEFINITE);
		        timeline.play();
		        
		        //This timeline is used for 3 purposes:7
		        // 1.stopping the cars on traffic lights 
		        // 2.It is used for stopping the car at certain distance when the car in front of it stopping.
		        // 3.It is used for cars to stop at safe distance when a collusion occurs.
		        Timeline timeline2 = new Timeline(
		        		//this timeline updates status every  6.9 millis.(144fps)
		                new KeyFrame(Duration.millis(6.9), event -> {
		                	
		                	//Check traffic light for every car.
		                	for (int i = 0; i < carList.size(); i++) {
		                		carList.get(i).checkTrafficLights(trafficLightList);
							}
		                	
		                	//Stop the car when the car in front is stopping.	                	
								for (int i = 0; i < carList.size(); i++) {
									for (int j = 0; j < carList.size(); j++) {
										//Check if the cars are moving together on the same adnd check they are not the same object.
										//Since cars waiting at the traffic light are unlikely to have different path indexes, this condition is valid for every cars.
										if(carList.get(i).index==carList.get(j).index && !(carList.get(i).equals(carList.get(j))) ) {
											//Calculate time passed since this car is on the road.
											//When cars are traveling on the same path, we should maintain a safe distance between them to avoid collisions.
											//We can achieve this by looking at the time intervals between cars.
											//For example :First car starts at a time t=0 seconds.The second car starts at a time t=0.600 seconds.The third car starts at a time t=1.200 seconds.
											//In this scenario there is time gap between cars 600 milliseconds.If the first car encounters a traffic light or collusion, the pathtransition time
											// will stop.Therefore second car gets too close to the one in front for example within a 300-millisecond threshold, the car behind should stop to maintain a safe distance.
											
											Double first = carList.get(i).pathTransition.getCurrentTime().toMillis();
											Double second = carList.get(j).pathTransition.getCurrentTime().toMillis();
											
											//second>first condition is for stopping the right car.If it has less duration for its path duration it means that it is in behind. 
											//Stop the car behind it they are closer than 300 millisecond(300 millisecond is safe distance for cars).
											if(second>first && second-first<300) {
												carList.get(i).pathTransition.pause();
											}
										}
									}
								}
								
								// Collision Detection for cars.
								for (int i = 0; i < carList.size(); i++) {
									//Check if the list contains a crahed cars.
									if(!crashedCars.isEmpty()) {
										carList.get(i).checkCollisions(crashedCars);
									}
								}
		                })
		                
		        );
		        timeline2.setCycleCount(Timeline.INDEFINITE);
		        timeline2.play();
		        	    
		 //This animation is used for collision detection.
		        	AnimationTimer animationTimer = new AnimationTimer() {
						@Override
						public void handle(long now) {
							//In this for loop every car checks each other.
							outerLoop:
			        	        for (int i = 0; i < carList.size() - 1; i++) {
			        	            for (int j = i + 1; j < carList.size(); j++) {
			        	            	//Cars can collide only if the four condition satisfied.
			        	            	//The cars should intersect
			        	            	//If one of the car is stopping this car can not involve collusion.So for collusion both cars should be moving.
			        	            	//If they have the same path this cars cannot collide with each other.This prevents collusion for cars waiting in the light.
			        	                if (carList.get(i).getCar().getBoundsInParent().intersects(carList.get(j).getCar().getBoundsInParent()) && !(carList.get(i).pathTransition.getStatus()==Animation.Status.PAUSED) && !(carList.get(j).pathTransition.getStatus()==Animation.Status.PAUSED) && (carList.get(i).index!=carList.get(j).index)) {
			        	                	
			        	                	//turn red the cars involved in collision
			        	                	carList.get(i).Car.setFill(Color.RED);
			        	                	carList.get(j).Car.setFill(Color.RED);
			        	                	
			        	                	//Add the cars to crashed cars list for other cars to detect this cars.
			        	                	crashedCars.add(carList.get(i));
			        	                	crashedCars.add(carList.get(j));
			        	                	
			        	                	//Timeline for removing them from the pane.
			        	                	 Timeline timelineR = new Timeline(
			        	                             new KeyFrame(Duration.seconds(0.5), event -> {
			        	                            	 
			        	                            	  //Delete the first two cars on crashed car arraylist.Because there could be other crashes.
			        	                            	  //This cars are in the 0 and 1 position of the arraylist.If other crash occurs later on 
			        	                            	  //that cars will be added to 2, 3, 4... place of crashed cars arraylist.After removing the first
			        	                            	  //two elements 2 , 3, 4 places will be shifted 2 places to the right and will be 0, 1 , 2 in this 
			        	                            	  //arraylist.they will be removed from the pane later on in different time line.
			        	                            	 for (int k = 0; k < 2; k++) {
			        	                            		 if (!crashedCars.isEmpty()) {
			        	                            			pane.getChildren().remove(crashedCars.get(k).Car);
			        	                            			crashedCars.remove(k);
			        	                            			//After removing one car the other car will shift one place right so decrement k by 1.
			        	                            			k--;
			        	                            		 }
			        	                            	 }
			        	                            	 //Update the score board.
			        	                                 ScoreBoard.UpdateCrash();
			        	                             })
			        	                         );
			        	                	 	
			        	                         timelineR.play();
			        	                         //Immediately pause transitions.
			        	                         carList.get(i).pathTransition.pause();
			        	                         carList.remove(carList.get(i));
			        	                         //after removing i the j will shift to right j-1 will be crashed car.
			        	                         carList.get(j-1).pathTransition.pause();
	        	                                 carList.remove(carList.get(j - 1));
	        	                                 //No further check in this animation.
			        	                    break outerLoop; 
			        	                }
			        	            }
			        	        }
						}
					};
		        	animationTimer.start();
		        	
					
					Scene scene2 = new Scene(stackPane, 800, 800);
					Button quitButton = new Button("BACK TO LEVELS MENU");
		        	
					
			//This animation is used for continuously check the score and crash counts.
			//It switches the current scene to winning screne or losing screne.		
		     AnimationTimer scoreTimer = new AnimationTimer() {
				@Override
				public void handle(long arg0) {
					// If crash count exceeds Max car crash allowed in this level.It is fails.
					if(ScoreBoard.Crash >= Car.CarCrash) {
						//reset the scoreboard.
						ScoreBoard.resetScore();
						
						//Stop the animations and timelines.
						timeline.stop();
						timeline2.stop();
					animationTimer.stop();
					
					//Pause all path transitions for cars on the road.
						for (int i = 0; i < carList.size(); i++) {
							carList.get(i).pathTransition.pause();
						}
						//Since it is failed the level counter is set to zero.
						levelCounter=0;
						
						//Display exit To Menu scene.
						primaryStage.setScene(ExitToMenu(primaryStage, scene2));
						//Stop the current animation timer.
						stop();
					}
					
					// If score count exceeds Max score in this level.Scene switches to next level.
					if(ScoreBoard.Score>=Car.CarWin) {
						try {
							//Reset the scoreboard.
							ScoreBoard.resetScore();
							
							//Stop the animations and timelines.
							timeline.stop();
							timeline2.stop();
						animationTimer.stop();
						
						//Set the current crash and win cars to zero.
						Car.CarCrash=0;
						Car.CarWin=0;
						
						//Pause all path transitions for cars on the road.
							for (int i = 0; i < carList.size(); i++) {
								carList.get(i).pathTransition.pause();
							}
							//Increment level counter for possible next level scene or win scene.
							levelCounter++;
							
							//If level counter smaller than zero than it switches next level.
							if(levelCounter<6) {
							primaryStage.setScene(NextLevel(primaryStage,scene2));
							//Stop the current animation timer.
							stop();
							}
							
							//If level counter is bigger than 5 than primary stage is set to display win screen.
							if(levelCounter==6) {
								primaryStage.setScene(displayWin(primaryStage, scene2));
								//Stop the current animation timer.
								stop();
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			};  
			scoreTimer.start();		
		
		//closing the scanner.	
		input.close();

		//This button is in the bottom right corner for exiting back to levels menu while user is playing game.
		quitButton.setOnAction(e->{
			try {
				//Reset the scoreboard.
				ScoreBoard.resetScore();
				
				//stop the animations and timelines.
				timeline.stop();
				timeline2.stop();
			animationTimer.stop();
			scoreTimer.stop();
			
			//Pause all pathtransitions for not them to crash.
				for (int i = 0; i < carList.size(); i++) {
					carList.get(i).pathTransition.pause();
				}
				//Set the level counter to 0 when exit to current level.
				levelCounter=0;
				primaryStage.setScene(LevelsScene(primaryStage, scene));
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		});
		// Putting different pane traffic lights and Quit button for make it interactable.
		Pane trAndQuitPane = new Pane();
		
		//Add all traffic light nodes to pane.
		for (int i = 0; i < trafficLightList.size(); i++) {
			trAndQuitPane.getChildren().add(trafficLightList.get(i).trNode);
	
		}
		//Set appearance and location of quit button.
		quitButton.setPrefWidth(150);
		quitButton.setPrefHeight(50);
		quitButton.setStyle("-fx-background-color: black; -fx-text-fill: yellow;-fx-font-size: 12px;-fx-font-weight: bold;");
		quitButton.setLayoutX(60);
		quitButton.setLayoutY(700);
		trAndQuitPane.getChildren().add(quitButton);
		
		//Create scorePane for score board.
		Pane ScorePane = new Pane();
	
		ScorePane.getChildren().add(scoreBoard.createScoreBoard());
		ScorePane.setPrefWidth(100);
		ScorePane.setPrefHeight(100);
		
		//Add all panes to stackPane.
		stackPane.getChildren().addAll(gridPane,pane,ScorePane,trAndQuitPane);
		
		
		return scene2;
	}
	//This method is calculates a distance between two points in plane.
	public static double calculateDistance(double x1, double y1, double x2, double y2) {
		double distance=Math.sqrt( Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2) );
		return distance;
	}
	
	//This method takes path element for calculating total path length for
	// speed of cars.
	public static double calculateTotalPathLength(Path path) {
		double startingPointX = 0.0;
		double startingPointY = 0.0;
		double totalLength = 0.0;
	
		//Searching for MoveTo element and initializing StartPoint
		for (int i = 0; i < path.getElements().size(); i++) {
			if(path.getElements().get(i) instanceof MoveTo) {
				startingPointX =  ((MoveTo)path.getElements().get(i)).getX();
				startingPointY =  ((MoveTo)path.getElements().get(i)).getY();
			}
		}
		//Adding LineTo elements to total length and changing the starting point of calculation.
		for (int i = 0; i < path.getElements().size(); i++) {
			if(path.getElements().get(i) instanceof LineTo) {
				totalLength += calculateDistance(startingPointX, startingPointY, ((LineTo)path.getElements().get(i)).getX(), ((LineTo)path.getElements().get(i)).getY());
				startingPointX = ((LineTo)path.getElements().get(i)).getX();
				startingPointY =((LineTo)path.getElements().get(i)).getY();
			}
		}
		return totalLength;
	
	}

	//This method creates a spawnPoint object from MoveTo element.
	public static SpawnPoint extractSpawnPoints(MoveTo moveTo) {
		SpawnPoint spawnPoint =new SpawnPoint(moveTo.getX(), moveTo.getY());
   
		return spawnPoint;
	}
	
}	

