
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

//This class represents a ScoreBoard object. It is used for counting scores and crashes and
//responsible for displaying score and crash counts and images on the screen.
public class ScoreBoard extends Pane {
	// This score and crash values are being updated whenever a car finishes its
	// path or there is crash occurs.
	public static int Score = 0;
	public static int Crash = 0;

	private static Label scoreLabel;
	private static Label CrashLabel;
	private ImageView scoreImageV;
	private ImageView crashImageV;

	public ScoreBoard() {
		// creating a crash and score images.
		Image crashImage = new Image("crash.png");
		Image scoreImage = new Image("score.png");
		crashImageV = new ImageView(crashImage);
		scoreImageV = new ImageView(scoreImage);

		// setting the positions of image Views.
		crashImageV.setFitWidth(50);
		scoreImageV.setFitWidth(50);
		crashImageV.setFitHeight(50);
		scoreImageV.setFitHeight(50);

		// Creating labels with score and crash values and images.
		scoreLabel = new Label("" + Score, scoreImageV);
		CrashLabel = new Label("" + Crash, crashImageV);

	}

	// This method resets the score and crash when a level ends.
	public static void resetScore() {
		Score = 0;
		Crash = 0;
	}

	public Pane createScoreBoard() {
		// setting the layout coordinates of crash label
		crashImageV.setFitWidth(75);
		scoreImageV.setFitWidth(75);
		crashImageV.setFitHeight(75);
		scoreImageV.setFitHeight(75);

		// Creating labels with score and crash values and images.
		scoreLabel = new Label("" + Score + "/" + Car.CarWin);
		CrashLabel = new Label("" + Crash + "/" + Car.CarCrash);
		// Setting the style of labels.
		scoreLabel.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");
		CrashLabel.setStyle("-fx-font-size: 20;-fx-font-weight: bold;");

		// setting the layout coordinates of score label.
		scoreLabel.setLayoutX(50);
		scoreLabel.setLayoutY(25);
		CrashLabel.setLayoutX(50);
		CrashLabel.setLayoutY(25);

		// this stackpane is for putting score label onto score image.
		StackPane stackPane = new StackPane();
		stackPane.getChildren().addAll(scoreImageV, scoreLabel);
		stackPane.setLayoutX(60);
		stackPane.setLayoutY(40);

		// this stackpane is for putting crash label onto crash image.
		StackPane stackPane2 = new StackPane();
		stackPane2.getChildren().addAll(crashImageV, CrashLabel);
		stackPane2.setLayoutX(60);
		stackPane2.setLayoutY(40);

		// putting score and crash stackpanes to vertical box so they display togethar.
		VBox vbox = new VBox(10);
		vbox.getChildren().addAll(stackPane, stackPane2);
		vbox.setLayoutX(60);
		vbox.setLayoutY(40);

		return vbox;
	}

	// This method updates the score count and set the text of the label
	public static void UpdateScore() {
		Score++;
		scoreLabel.setText("" + Score + "/" + Car.CarWin);
	}

	// This method updates the crash count and set the text of the label
	public static void UpdateCrash() {
		Crash++;
		CrashLabel.setText("" + Crash + "/" + Car.CarCrash);
	}

	// This method returs the score board as node.
	public Node getScoreboard() {
		return createScoreBoard();
	}
}
