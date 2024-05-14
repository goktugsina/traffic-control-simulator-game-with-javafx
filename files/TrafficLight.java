
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

//This class represent Traffic Light objects 
public class TrafficLight extends Pane {

	// This coordinates represents coordinates of the traffic light.
	private double x1;
	private double y1;
	private double x2;
	private double y2;
	private final double RADIUS_OF_TRAFFIC_LIGHT_CIRCLE = 6;

	// Color field is used for cars to check if the traffic light is red or green.
	private Color color = Color.GREEN;

	// Traffic Light itself as a node.
	Node trNode;

	public TrafficLight(double x1, double y1, double x2, double y2) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		constructTrafficLight();
	}

	// This method consctruct a traffic light with line and a circle and enables a
	// user interaction for circle.
	public void constructTrafficLight() {
		// Drawing the line with the coordinates in txt files.
		Line line = new Line();
		line.setStroke(Color.BLACK);
		line.setStartX(x1);
		line.setEndX(x2);
		line.setStartY(y1);
		line.setEndY(y2);

		// Calculating the middle of the line and constructing a circle object.
		Circle circle = new Circle(getCenterX(), getCenterY(), RADIUS_OF_TRAFFIC_LIGHT_CIRCLE, Color.GREEN);
		circle.setStroke(Color.BLACK);
		// Initializing to green in the beginning of the levels.
		circle.setFill(Color.GREEN);

		// If user clicks the circle it changes the color according to its current color.
		circle.setOnMouseClicked(e -> {
			if (circle.getFill().equals(Color.GREEN)) {
				this.color = color.RED;
				circle.setFill(Color.RED);
			} else {
				this.color = color.GREEN;
				circle.setFill(Color.GREEN);

			}
		});

		// Sınce Traffic Light object is the combimnation of line and a circle it goups the 2 node as 1 node.
		Group p = new Group();
		p.getChildren().addAll(line, circle);

		this.trNode = p;

	}

	// Access for traffic light nodes.
	public Node getTrNode() {
		return this.trNode;
	}

	// Calculates the x coordinate of middle point of the line for drawing a circle.
	public double getCenterX() {
		return (x1 + x2) / 2;
	}

	// Calculates the x coordinate of middle point of the line for drawing a circle.
	public double getCenterY() {
		return (y1 + y2) / 2;
	}

	// Getter method of the color for car objects.
	public Color getColor() {
		return this.color;
	}

}
