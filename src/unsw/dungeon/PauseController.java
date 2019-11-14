package unsw.dungeon;

import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class PauseController {

    @FXML
    private Button backToGameButton;

    @FXML 
    private Button backToStartButton;
    
    @FXML
    private GridPane goal;
    
    @FXML
    private Label goalRelation;
    
    
    private DungeonScreen dungeonScreen;
    private StartScreen startScreen;

    @FXML
    void handlebackToGameButton(ActionEvent event) {
    	dungeonScreen.start();
    }

    @FXML
    void handlebackToStartButton(ActionEvent event) {
    	startScreen.start();
    }
    
    public void setDungeonScreen(DungeonScreen dungeonScreen) {
        this.dungeonScreen = dungeonScreen;
    }

    public void setStartScreen(StartScreen startScreen) {
        this.startScreen = startScreen;
    }

	public void setGoal() {
		goalRelation.setText(dungeonScreen.getGoalRelation());
		
		goal.getChildren().clear();
		int count = 0;
		
		for (Map.Entry<String, Boolean> item: dungeonScreen.getGoals().entrySet()) {
			count++;
			CheckBox cb = new CheckBox();
			cb.setText(item.getKey());
			cb.setSelected(item.getValue());
			cb.setPadding(new Insets(2, 2, 2, 2));
			goal.add(cb, 1, count);
		}
	}
}

