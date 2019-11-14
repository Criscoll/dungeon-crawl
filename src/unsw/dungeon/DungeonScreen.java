package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonScreen {
	private Stage stage;
	private DungeonController controller;
	private Scene scene;
	
	public DungeonScreen(Stage primaryStage) throws IOException {
		this.stage = primaryStage;
		
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("advanced.json");
        this.controller = dungeonLoader.loadController();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        this.scene = new Scene(root);
        root.requestFocus();
	}
	public void start() {
		this.stage.setTitle("Dungeon");
		this.stage.setScene(this.scene);
        this.stage.show();
	}

	public void resetDungeon() {
		try {
			controller.resetDungeon();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public DungeonController getController() {
		return this.controller;
	}
}
