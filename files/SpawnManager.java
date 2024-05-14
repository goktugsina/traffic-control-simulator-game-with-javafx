
import java.util.ArrayList;
import javafx.scene.shape.Circle;
//This class is used for determining the Spawn Points are free or not.
//Also it is returning a randomly determined free path for path Transition.If no Path is available then it 
//returns so path meaning that no cars should spawn rightnow.

public class SpawnManager {
	// Cars on Map arraylist is arraylist of cars currently is on the map.
	public ArrayList<Car> carsOnMap;
	// Spawn Point arraylist holds spawnPoint of the each path.
	// In this arraylist path indexes also equal to the index of Spawn point.Because in main it is initialized with for loop.
	
	public ArrayList<SpawnPoint> spawnPoints;

	private final int MAX_ITERATION_FOR_FINDING_PATH = 5;

	public SpawnManager() {
		carsOnMap = new ArrayList<>();
		spawnPoints = new ArrayList<>();
	}

	// This method is called to look indivial spawn points element if this spawn
	// point is available or not for car spawning.
	public boolean IsSpawnPointFree(double x, double y, int pathIndex) {
		// First drawing a circle around this spawn point to look if any car is
		// intersecting with this spawn point.
		Circle circle = new Circle();
		circle.setCenterX(x);
		circle.setCenterY(y);
		// Radius is determined according to size of the cars.
		circle.setRadius(25);
		// Checking every car on the map.
		for (int i = 0; i < carsOnMap.size(); i++) {
			// This condition is essantial so that it does not check cars that have nothing
			// to do with this path It checks only the cars related to this path.
			
			if (carsOnMap.get(i).index == pathIndex) {
				// If any car intersecting with circle drawn here it immediately returns false
				// meaning that this spawn point is not free.
				if (circle.getBoundsInParent().intersects(carsOnMap.get(i).Car.getBoundsInParent())) {
					return false;
				}
			}
		}
		// After looking every car related with this path if no car is around it returns true.
		return true;
	}

	// This method is called from the main and it returns a random spawn point.
	public int returnFreeSpawn() {
		// This loop is changes the IsFree field of the spawnoints by calling the method
		// IsSpawnPointFree.
		for (int i = 0; i < spawnPoints.size(); i++) {
			spawnPoints.get(i).IsFree = IsSpawnPointFree(spawnPoints.get(i).getX(), spawnPoints.get(i).getY(), i);
		}
		// First counting the free spawn points so if there is no free spawnpoint method
		// returns -1 indicating that
		// There should be no spawn in any path on the map.
		int count = 0;
		for (int i = 0; i < spawnPoints.size(); i++) {
			if (spawnPoints.get(i).IsFree == true)
				count++;

		}
		if (count == 0) {

			return -1;
		}

		// This loop returns a random path index for spawning a car.
		// This loop could also be a infinite loop for finding a car but in that case if  one path is available
		// then it would be guarantee that this path is goint to spawn a car every 1 second resulting
		// the car spawn being not randomly.
		for (int i = 0; i < MAX_ITERATION_FOR_FINDING_PATH; i++) {
			// Generating random number for car spawn limited with the spawnPoints size.
			int random = (int) (Math.random() * (spawnPoints.size()));
			// It checks if it is a free point because random number doesn't excludes busy
			// spawn points.
			if (spawnPoints.get(random).IsFree == true) {
				return random;
			}
		}
		// If this loop can't find a position for car spawn after 5 iterations this
		// method returns -1
		return -1;
	}
}
