
import javafx.geometry.Pos;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

/**
 * This class represents road tiles in the traffic control simulation game.
 * It is responsible for creating different types of road tiles based on the roadType and rotation parameters.
 */


public class RoadTile extends Pane {
    private int roadType;
    private int rotation;

    // Constructor. It takes two parameters (Road Type and Rotation).
    public RoadTile(int roadType, int rotation) {
        this.roadType = roadType;
        this.rotation = rotation;

    }

    // This method is used to build and return a specific road tile based on its type, rotation, and coordinates.
    public Pane buildRoad(double width, double height, double x, double y) {
        StackPane stackPane = new StackPane();
        switch (this.roadType) {
            // Straight Road Case
            case 0:
                // Build a straight road tile
                Rectangle rectangle = new Rectangle(x * width, y * height, width, height - 10);
                rectangle.setRotate(this.rotation);
                rectangle.setFill(Color.WHITE);

                // Add a center line
                Line centerLine = new Line(x * width, (y) * height, (x) * width, (y + 0.5) * height);
                centerLine.setStroke(Color.LAVENDER);
                centerLine.setStrokeWidth(2.5);
                centerLine.setRotate(90 - this.rotation);

                // Add the road tile and the center to the Stack Pane, and return it.
                stackPane.getChildren().addAll(rectangle, centerLine);
                rectangle.setOpacity(1);

                return stackPane;
            // Curved Road Tile case
            case 1:
                // HBox is used to set alignment properly
                HBox hBox = new HBox();
                HBox hBox2 = new HBox();

                // Build a curved road tile
                Arc curvedRoad = new Arc(x * width, y * height, width - 4, height - 4, 0, 90);
                curvedRoad.setType(ArcType.ROUND);
                curvedRoad.setFill(Color.WHITE);
                curvedRoad.setOpacity(1);

                // Add a center line
                Arc centerLineArc = new Arc(x * width / 2, y * height / 2, width - 20, height - 20, 0, 90);
                centerLineArc.setType(ArcType.OPEN); // OPEN type to show only an arc
                centerLineArc.setFill(Color.LAVENDER);
                centerLineArc.setOpacity(1);

                // Build curved road 2 to provide better view while connecting two straight roads.
                Arc curvedRoad2 = new Arc(x * width - 5, y * height - 5, 5, 5, 0, 90);
                curvedRoad2.setType(ArcType.ROUND);
                curvedRoad2.setFill(Color.LAVENDER);
                curvedRoad2.setOpacity(1);
                curvedRoad2.setRotate(rotation);

                // Set rotation and alignment based on rotation
                if (rotation == 0) {
                    hBox.setAlignment(Pos.BOTTOM_LEFT);
                    hBox2.setAlignment(Pos.BOTTOM_LEFT);
                } else if (rotation == 90) {
                    curvedRoad.setRotate(-90);
                    curvedRoad2.setRotate(-90);
                    hBox.setAlignment(Pos.BOTTOM_RIGHT);
                    hBox2.setAlignment(Pos.BOTTOM_RIGHT);
                } else if (rotation == 180) {
                    curvedRoad.setRotate(-180);
                    curvedRoad2.setRotate(-180);
                    hBox.setAlignment(Pos.TOP_RIGHT);
                    hBox2.setAlignment(Pos.TOP_RIGHT);
                } else if (rotation == 270) {
                    curvedRoad.setRotate(-270);
                    curvedRoad2.setRotate(-270);
                    hBox.setAlignment(Pos.TOP_LEFT);
                    hBox2.setAlignment(Pos.TOP_LEFT);
                }


                hBox.getChildren().addAll(curvedRoad);
                hBox2.getChildren().add(curvedRoad2);

                stackPane.getChildren().addAll(hBox);

                stackPane.getChildren().addAll(hBox2);
                return stackPane;

            case 2:
                // Circle is used to represent bends.
                Circle circle = new Circle(x * width, y * height, 5);
                circle.setFill(Color.LAVENDER);


                Rectangle horizantalR = new Rectangle(x * width, y * height, width - 10, height);
                horizantalR.setOpacity(1);
                horizantalR.setFill(Color.WHITE);

                Rectangle verticalR = new Rectangle(x * width, y * height, width - 10, height);
                verticalR.setOpacity(1);
                verticalR.setFill(Color.WHITE);

                verticalR.setRotate(90);
                stackPane.getChildren().addAll(horizantalR, verticalR, circle);

                return stackPane;

            case 3:
                // Circle is used to represent bends.
                Circle circle2 = new Circle(x * width, y * height, 5);
                circle2.setFill(Color.LAVENDER);

                // HBox and VBox is used to set alignment properly.
                HBox horizontalRoad = new HBox();
                VBox verticalRoad = new VBox();

                // Height is divided by two because we do not desire to see the other part of the rectangle
                Rectangle horizontalRect = new Rectangle(width - 10, height / 2.0);
                horizontalRect.setFill(Color.WHITE);

                Rectangle verticalRect = new Rectangle(width, height - 10);
                verticalRect.setFill(Color.WHITE);


                horizontalRoad.getChildren().add(horizontalRect);
                verticalRoad.getChildren().add(verticalRect);

                horizontalRoad.setAlignment(Pos.BASELINE_CENTER);
                verticalRoad.setAlignment(Pos.CENTER);

                // Set rotation based on rotation
                if (this.rotation == 0) {
                    verticalRoad.setRotate(180);
                    horizontalRoad.setRotate(180);
                } else if (this.rotation == 90) {
                    verticalRoad.setRotate(270);
                    horizontalRoad.setRotate(270);
                } else if (this.rotation == 180) {

                } else if (this.rotation == 270) {
                    verticalRoad.setRotate(90);
                    horizontalRoad.setRotate(90);
                }

                stackPane.getChildren().addAll(verticalRoad, horizontalRoad, circle2);

                return stackPane;

            default:
                return null;
        }
    }

}