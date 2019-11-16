package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PlayerDiedScreen {
	private Stage stage;
	private String title;
	private PlayerDiedController controller;
	private Scene scene;
	
	public PlayerDiedScreen(Stage stage) throws IOException {
		this.stage=stage;
		title="You died";
		
		controller = new PlayerDiedController();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("playerDied.fxml"));
		loader.setController(controller);
		
		Parent root = loader.load();
		scene = new Scene(root, 500, 500);	
	}
	
	public void start() {		
		stage.setTitle(title);
		stage.setScene(scene);
		stage.show();
	}
	
	public PlayerDiedController getController() {
		return controller;
	}
}
