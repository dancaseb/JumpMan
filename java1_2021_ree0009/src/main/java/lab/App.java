package lab;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *  Class <b>App</b> - extends class Application and it is an entry point of the program
 * @author     Java I
 */
public class App extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	//private Canvas canvas;
	private GameController controller;
	private SceneController scenecontroller;
	@Override
	public void start(Stage stage) {
		try {
			//Construct a main window with a canvas.  
			FXMLLoader firstloader =  new FXMLLoader(this.getClass().getResource("FirstScene.fxml"));
			BorderPane firstroot = firstloader.load();
			Scene firstscene = new Scene(firstroot);
			
			
			
			FXMLLoader loader = new FXMLLoader(this.getClass().getResource("GameView.fxml"));
			BorderPane root = loader.load();
			//scenecontroller = loader.getController();
			//Parent root = FXMLLoader.load(getClass().getResource("FirstScene.fxml"));
			Scene scene = new Scene(root);
			
			scenecontroller   = firstloader.getController();
			scenecontroller.setMainScene(scene);
			controller = loader.getController();
			controller.setFirstScene(firstscene);
			scenecontroller.setController(controller);
			//controller.startGame();
			
			root.addEventFilter(KeyEvent.KEY_PRESSED, event->{
	            if (event.getCode() == KeyCode.SPACE) {
	            	controller.space_pressed = true;
	                
	            }
	        });
			root.addEventFilter(KeyEvent.KEY_RELEASED, event->{
	            if (event.getCode() == KeyCode.SPACE) {
	            	controller.space_pressed = false;
	              
	            }
	        });
			

			
			
			stage.setScene(firstscene);
			stage.resizableProperty().set(false);
			stage.setTitle("JumpMan");
			stage.show();
			
			
			//controller = new GameController(canvas);
			//controller.startGame();
			//Exit program when main window is closed
			//primaryStage.setOnCloseRequest(this::exitProgram);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	
	private void exitProgram(WindowEvent evt) {
		controller.stopGame();
		System.exit(0);
	}
}