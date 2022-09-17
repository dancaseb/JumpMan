package lab;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.Node;

import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SceneController {
	private Scene MainScene;
	private GameController gamecontroller;

	@FXML
	TextField Name;
	
	@FXML 
	ListView<Score> scoreView;
	
	private List<Score> scoreList = new ArrayList<Score>();

	public void setMainScene(Scene scene) {
		MainScene = scene;
	}

	public void openMainScene(ActionEvent actionEvent) {
		Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
		primaryStage.setScene(MainScene);
		gamecontroller.setPlayer(Name.getText());
		gamecontroller.startGame();
	}
	
	public void setController(GameController gamecontroller) {
		this.gamecontroller = gamecontroller;
	}
	public void loadScores() throws NumberFormatException, IOException {
		
		try(BufferedReader br = new BufferedReader(new FileReader("scores.csv"))){
			String line;
			
			while(null != (line = br.readLine())) {
				
				String [] tokens = line.split(";");
				
				scoreList.add(new Score(Integer.parseInt(tokens[1]), tokens[0]));
			}
		}
		scoreView.setItems(FXCollections.observableList(scoreList));
	}

}
