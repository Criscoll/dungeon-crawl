package unsw.dungeon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PauseController {

    @FXML
    private Button backToGameButton;

    @FXML 
    private Button backToStartButton;
    
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
}

