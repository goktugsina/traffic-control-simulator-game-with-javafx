
import javafx.scene.Group;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

/**
This class represents buildings in the traffic control simulation game.
It is responsible for creating different types of buildings based on the type and rotation parameters.
*/


public class Building {
	
	int type;
	int rotate;
	int colorindex;
	String[] Colors = {"BLUE", "GREEN", "RED", "ORANGE"};
	String darkcolor = "DARK";
	int gridcellx;
	int gridcelly;
    
	// Constructor. It takes five parameters (type, rotate, colorindex, gridcellx, gridcelly).

    public  Building(int type, int rotate, int colorindex, int gridcellx, int gridcelly) {
        
    	this.type = type;
    	this.rotate = rotate;
    	this.colorindex = colorindex;
    	this.darkcolor += Colors[colorindex];
    	this.gridcellx = gridcellx;
    	this.gridcelly = gridcelly;}
    
    	// This method is used to build and return a specific building based on its type, rotation, and coordinates.

    	public Group buildBuilding() {
    	
    	//First we create group.
        Group group = new Group();
        Group group2 = new Group();
        
        //Type 0 building.
        if (type == 0) {
        Rectangle rectangle = new Rectangle(800/15.0*2-5, 800/15.0*3-3);
        rectangle.setArcWidth(20);
        rectangle.setArcHeight(20);
        rectangle.setStyle("-fx-fill: white; -fx-stroke: gray; -fx-stroke-width: 5;");
        
        Rectangle rectangle1 = new Rectangle(90, 90);
        rectangle1.setArcWidth(20);
        rectangle1.setArcHeight(20);
        rectangle1.setStyle("-fx-fill: "+Colors[colorindex]+"; -fx-stroke: "+darkcolor+"; -fx-stroke-width: 5;");
        
        rectangle1.setLayoutX(800/15.0-47);
        rectangle1.setLayoutY(13);
        
        Rectangle rectangle2 = new Rectangle(70, 70);
        rectangle2.setArcWidth(20);
        rectangle2.setArcHeight(20);
        rectangle2.setStyle("-fx-fill: "+Colors[colorindex]+"; -fx-stroke: "+darkcolor+"; -fx-stroke-width: 5;");
        
        rectangle2.setLayoutX(800/15.0-37);
        rectangle2.setLayoutY(23);
        
        //We rotate.
        if(rotate == 90 || rotate == 270) {
        	rotate -= 180;
        
        group.getChildren().addAll(rectangle, rectangle1, rectangle2);
     
        group.setTranslateX(0);
        group.setTranslateY(0);

        Rotate rotation = new Rotate(rotate, 0, 0);
        group.getTransforms().add(rotation);}
        
        else {
        	group.setRotate(rotate);
        	group.getChildren().addAll(rectangle, rectangle1, rectangle2);}
        
        //We add it first to HBox and then to the 2nd group.
        HBox hbox = new HBox(group);
        
        group2.getChildren().add(hbox);}
        
        //Type 1 building.
        else if (type == 1) {
        Rectangle rectangle = new Rectangle(800/15.0*2-5, 800/15.0*3-3);
        rectangle.setArcWidth(20);
        rectangle.setArcHeight(20);
        rectangle.setStyle("-fx-fill: white; -fx-stroke: gray; -fx-stroke-width: 5;");
        
        
        Circle circle = new Circle(45);
        circle.setStyle("-fx-fill: "+Colors[colorindex]+"; -fx-stroke: "+darkcolor+"; -fx-stroke-width: 5;");
        
        
        circle.setLayoutX(800/15.0-2.5);
        circle.setLayoutY(55);
        
        Circle circle2 = new Circle(35);
        circle2.setStyle("-fx-fill: "+Colors[colorindex]+"; -fx-stroke: "+darkcolor+"; -fx-stroke-width: 5;");
        
        circle2.setLayoutX(800/15.0-2.5);
        circle2.setLayoutY(55);
        
        //We rotate.
        if(rotate == 90 || rotate == 270) {
        	rotate -= 180;
        
        group.getChildren().addAll(rectangle, circle, circle2);
        
        group.setTranslateX(0);
        group.setTranslateY(0);

        Rotate rotation = new Rotate(rotate, 0, 0);
        group.getTransforms().add(rotation);}
        else {
        	group.setRotate(rotate);
        	group.getChildren().addAll(rectangle, circle, circle2);}
        
        //We add it first to HBox and then to the 2nd group.
        HBox hbox = new HBox(group);

        group2.getChildren().add(hbox);}
        
        //Type 2 building.
         if (type == 2) {
        Rectangle rectangle3 = new Rectangle(800/15.0-5, 800/15.0-5);
        rectangle3.setArcWidth(20);
        rectangle3.setArcHeight(20);
        rectangle3.setStyle("-fx-fill: "+Colors[colorindex]+"; -fx-stroke: "+darkcolor+"; -fx-stroke-width: 5;");
        
        group.getChildren().addAll(rectangle3);
        //We add it first to HBox and then to the 2nd group.
        HBox hbox = new HBox(group);
        group2.getChildren().add(hbox);}
        
        //Then we return group2.
        return group2;
     
    }
}