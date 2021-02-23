package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameCompleteScreen {
	private Stage stage;
	private String title;
	private GameCompleteController controller;
	private Scene scene;
	
	public GameCompleteScreen(Stage stage) throws IOException {
		this.stage=stage;
		title="Complete menu";
		
		controller = new GameCompleteController();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("complete.fxml"));
		loader.setController(controller);
		
		Parent root = loader.load();
		scene = new Scene(root, 500, 500);	
	}
	
	public void start() {		
		stage.setTitle(title);
		stage.setScene(scene);
		stage.show();
	}
	
	public GameCompleteController getController() {
		return controller;
	}
}
