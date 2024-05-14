
//This class represents possible spawnpoints for each car and initialized to beginning of the paths.
public class SpawnPoint {

	// X coordinate of the Spawn Point and also equals to X coordinate of MoveTo
	// element of the Path.
	private double x;
	// Y coordinate of the Spawn Point and also equals to Y coordinate of MoveTo
	// element of the Path.
	private double y;

	// This boolean variable represents whether a car can spawn here or not if no
	// other car is nearby then IsFree is true.
	public boolean IsFree = false;

	public SpawnPoint(double x, double y) {
		this.x = x;
		this.y = y;
	}
	// Getter and Setter Methods Of this class.

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
		;
	}
}