package lab;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.scene.Node;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;

import javafx.scene.control.Label;


import javafx.scene.input.KeyEvent;

import javafx.stage.Stage;

public class GameController {

	private World world;
	private ArrayList<Score> scorelist = new ArrayList<Score>();
	private Scene firstscene;
	

	public boolean space_pressed = false;

	@FXML
	private Canvas canvas;

	@FXML
	KeyEvent key;

	@FXML
	public Label text;

	@FXML
	private Label points;

	@FXML
	private Label lives;

	@FXML
	private Label player;
	private int person_points;

	private AnimationTimer animationTimer;
	private FrameCounter framecounter = new FrameCounter();

	public GameController() {

	}

	public void startGame() {

		this.world = new World(canvas.getWidth(), canvas.getHeight());
		this.world.setGameListener(new GameListenerImpl());
		// Draw scene on a separate thread to avoid blocking UI.

		animationTimer = new AnimationTimer() {
			private Long previous;

			@Override
			public void handle(long now) {
				if (previous == null) {
					previous = now;
				} else {
					drawScene((now - previous) / 1e9);

					previous = now;
				}
			}
		};

		animationTimer.start();
	}

	public void stopGame() {
		
		animationTimer.stop();
	}

	private void drawScene(double deltaT) {
		// pocita frames
		if (framecounter.checkCounter(deltaT) == true) {
			world.changeFrame();
		}
		world.draw(canvas);
		world.simulate(deltaT);
	}

	@FXML
	void keyPressed(KeyEvent event) {
		// switch (event.getCode()) {

		// case L:
		if (space_pressed == true && event.getCode().toString().equals("D")) {
			world.jump(1, -1);

		} else if (space_pressed == true && event.getCode().toString().equals("A")) {
			world.jump(-1, -1);

		} else if (space_pressed == true && event.getCode().toString().equals("W")) {
			world.jump(0, -1);

		} else if (event.getCode().toString().equals("A")) {
			world.setSpeed(-1, 0);

		} else if (event.getCode().toString().equals("D")) {
			world.setSpeed(1, 0);

		} else if (event.getCode().toString().equals("W")) {
			world.setSpeed(0, -1);
		} else if (event.getCode().toString().equals("S")) {
			world.setSpeed(0, 1);
		}
		// break;
		// case G:

		// break;
		// default:
		// break;
		// }
	}

	@FXML
	void keyReleased(KeyEvent event) {
		world.setSpeed(0, 0);

	}

	@FXML
	void gameOverPressed() {
		Score score = new Score(person_points, player.getText());
		scorelist.add(score);
		stopGame();

	}

	@FXML
	void saveScores() throws IOException {
	
		try(PrintWriter pw = new PrintWriter(new FileWriter("scores.csv"))){
			for(Score score: scorelist) {
				
				pw.printf("%s;%d\n", score.getName(), score.getScore());
			}

		}
	}

	public void setPlayer(String s) {
		player.setText("" + s);
	}

	private class GameListenerImpl implements GameListener {

		@Override
		public void changeStats(int points, int lives) {
			person_points = points;
			GameController.this.points.setText("" + points);
			GameController.this.lives.setText("" + lives);
			text.setText(":(");
		}

		@Override
		public void gameOver() {
			
			
			
			stopGame();
			
		}

	}

	public void setFirstScene(Scene scene) {
		firstscene = scene;
	}

	public void openFirstScene(ActionEvent actionEvent) {
		Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
		primaryStage.setScene(firstscene);
		stopGame();
	}

}
