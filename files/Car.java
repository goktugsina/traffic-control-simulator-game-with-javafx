
import java.util.ArrayList;
import javafx.animation.PathTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

//This class represents car objects.
public class Car extends Pane {
	// Appearance of car object
	private final int ARCHEIGHT_OF_CAR = 7;
	private final int ARCWIDTH_OF_CAR = 7;
	private final int HEIGHT_OF_CAR = 8;
	private final int WIDTH_OF_CAR = 16;
	private Color COLOR_OF_CAR = Color.BLACK;
	private final double DISTANCE_TO_CRASHED_CARS = 21;

	// Initial position is set to -50 beacuse for a split second it is displayed in
	// (0,0).
	private final int INITIAL_POSITION_OF_CAR = -50;

	// Path transition of this car.
	public PathTransition pathTransition;

	// Index of the path transition.Different index number means that this cars are
	// not on the same path.
	public int index;

	// Rectangle is car object as a node.
	public Rectangle Car;

	// CarCrash and CarWin is the number that will be initialized to Max CarCrash
	// and Max CarWin for every level.
	public static int CarCrash = 0;
	public static int CarWin = 0;

	public Car(int index, PathTransition pt) {
		this.index = index;
		buildCar();
		this.pathTransition = pt;

	}

	public void buildCar() {
		// Car object is built using Rectangle class.
		Rectangle rectangle = new Rectangle();

		rectangle.setX(INITIAL_POSITION_OF_CAR);
		rectangle.setY(INITIAL_POSITION_OF_CAR);

		rectangle.setArcHeight(ARCHEIGHT_OF_CAR);
		rectangle.setArcWidth(ARCWIDTH_OF_CAR);
		rectangle.setHeight(HEIGHT_OF_CAR);
		rectangle.setWidth(WIDTH_OF_CAR);
		rectangle.setFill(COLOR_OF_CAR);
		this.Car = rectangle;

	}

	public int getIndex() {
		return this.index;
	}

	public Rectangle getCar() {
		return this.Car;
	}

	// This method is used for checking if there is red light ahead for cars.
	public void checkTrafficLights(ArrayList<TrafficLight> trArrayList) {
		boolean isTrafficLightNearby = false;

		for (int i = 0; i < trArrayList.size(); i++) {
			if (trArrayList.get(i).getTrNode().getBoundsInParent().intersects((Car.getBoundsInParent()))
					&& trArrayList.get(i).getColor().equals(Color.RED)) {
				pathTransition.pause();
				isTrafficLightNearby = true;
				break;
			}
		}

		// The animation should always play if there is no red light nearby.
		if (!isTrafficLightNearby) {
			pathTransition.play();
		}
	}

	// This method is used for cars to detect crashed cars ahaed.If it is closer
	// then certain distance car will stop.
	public void checkCollisions(ArrayList<Car> crashedCarList) {

		for (int i = 0; i < crashedCarList.size(); i++) {
			// calculating the distance between this car and car that involved in crash.
			double distance = Main.calculateDistance(Car.getTranslateX(), Car.getTranslateY(),
					crashedCarList.get(i).Car.getTranslateX(), crashedCarList.get(i).Car.getTranslateY());

			// Animation of this car will pause it is closer then certain distance.
			if (distance < DISTANCE_TO_CRASHED_CARS) {
				pathTransition.pause();
				// If it closer even 1 crashed cars animation should pause.So, loop will
				// terminate after detecting 1 car.
				break;
			}
		}
	}

}
