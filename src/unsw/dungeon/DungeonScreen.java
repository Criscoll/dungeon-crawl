package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonScreen {
	private Stage stage;
	private DungeonController controller;
	private Scene scene;
	private ArrayList<String> dungeons = new ArrayList<>();
	private int currentLevel = 0;
	
	public DungeonScreen(Stage primaryStage) throws IOException {
		dungeons.add("maze.json");
		dungeons.add("boulders.json");
		dungeons.add("advanced.json");
		
		this.stage = primaryStage;
		
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(dungeons.get(currentLevel));
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
        controller.stopDungeon(false);
	}

	public void resetDungeon() {
		try {
			controller.resetDungeon(dungeons.get(currentLevel));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public DungeonController getController() {
		return this.controller;
	}
	public Map<String, Boolean> getGoals(){
		return controller.getGoals();
	}
	public String getGoalRelation() {
		return controller.getGoalRelation();
	}
	public void nextLevel() {
		if(currentLevel != dungeons.size()-1)
			currentLevel++;
		resetDungeon();
	}
	public void playFromStart() {
		currentLevel = 0;
		resetDungeon();
	}
}
