package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PauseScreen {
	private Stage stage;
	private String title;
	private PauseController controller;
	private Scene scene;
	
	public PauseScreen(Stage stage) throws IOException {
		this.stage=stage;
		title="Pause menu";
		
		controller = new PauseController();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("pause.fxml"));
		loader.setController(controller);
		
		Parent root = loader.load();
		scene = new Scene(root, 500, 500);	
	}
	
	public void start() {
		controller.setGoal();
		stage.setTitle(title);
		stage.setScene(scene);
		stage.show();
	}
	
	public PauseController getController() {
		return controller;
	}
	
}
