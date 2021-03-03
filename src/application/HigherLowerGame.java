package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class HigherLowerGame extends Application {
	
	private TextField input = new TextField();
	private Label result = new Label();
	private Label imageResult = null;
	private int target = randomNumber();
	
	private VBox pane = new VBox();
	int counter = 0;

	public int randomNumber() {
		int randomNo = (int) Math.floor(Math.random() * 100) + 1;
		System.out.println(randomNo);
		return randomNo;
	}


	public void gamePlay() {
		
		if ((input.getText().matches("\\d+") )){

			int guess = Integer.parseInt(input.getText());
			if (counter == 10) {
				result.setText("Game over! You have already palyed 10 times!");
				input.setText("");
			} else {
				if (guess == target) { // nested loop
					result.setText("Congratulation: Your Guess is Right");

					Image i = new Image(getClass().getResourceAsStream("congrats.png"));
					imageResult = new Label("Yayyy!", new ImageView(i));
					imageResult.maxHeight(80);
					pane.getChildren().add(imageResult);

				} else if (guess > target) {
					result.setText("Too High");
					counter++;
				} else if (guess < target) {
					result.setText("Too Low");
					counter++;
				}
					
			}
		} else {
			result.setText("Enter a valid number");
		}
	}

//	public void restartTheGame () {
//		input.clear();
//		imageResult.setText("");
//		imageResult.setGraphic(null);
//		result.setText("");
//		gamePlay();
//		target = randomNumber();
//	}
//	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			input.setPromptText("Press New Game Button!");
			primaryStage.setTitle("Guessing Game");
			Label l = new Label("YOU GOT 10 CHANCE TO GUESS");
			l.setFont(Font.font("Cambria", 15)); // or Font.getDefault();
			l.setTextFill(Color.RED);
			Label l1 = new Label("Enter a random number from 1 to 100:");
			l1.setTextFill(Color.BLUE);

			// Adding the shadow to Button1 when the mouse cursor is on
			DropShadow shadow = new DropShadow();
			Button button1 = new Button("Guess");
			button1.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					button1.setEffect(shadow);
				}
			});
			// Removing the shadow to Button1 when the mouse cursor is off
			button1.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					button1.setEffect(null);
				}
			});

			// Adding the shadow to Button2 when the mouse cursor is on
			Button button2 = new Button("Restart the Game");
			button2.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					button2.setEffect(shadow);
				}
			});

			// Removing the shadow to Button2 when the mouse cursor is off
			button2.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					button2.setEffect(null);
				}
			});

			button1.setOnAction(action -> { // click event Guessing button
				gamePlay();

			});

			// clear the screen and let the user play again
			button2.setOnAction(actiom -> { 
				input.clear();
				imageResult.setText("");
				imageResult.setGraphic(null);
				result.setText("");
				gamePlay();
				target = randomNumber();
				
				
				//restartTheGame ();

			});

			Button button3 = new Button("Exit");
			button3.setOnAction(action -> Platform.exit());

			// VBox pane = new VBox(l,l1, input, result);
			pane.getChildren().addAll(l, l1, input, result);

			pane.setSpacing(5); // space between two buttons
			pane.setAlignment(Pos.TOP_CENTER);
			pane.setPadding(new Insets(20, 20, 20, 20)); // top, right, bottom, left
			pane.getChildren().addAll(button1, button2, button3);

//			pane.getChildren().add(button1);
//			pane.getChildren().add(button2);
//			pane.getChildren().add(button3);

			Scene scene = new Scene(pane, 400, 400);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public static void main(String[] args) {
		launch(args);
		// start();
	}
}
