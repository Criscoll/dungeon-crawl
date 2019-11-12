package unsw.dungeon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class GameCompleteController {

    @FXML
    private Button BackToStartButton;

    @FXML
    private Button ReplayButton;

    private DungeonScreen dungeonScreen;
    private StartScreen startScreen;
    
    @FXML
    void BackToStartHandler(ActionEvent event) {
    	this.startScreen.start();
    }

    // TODO not yet implemented the reset
    @FXML
    void ReplayButtonHandler(ActionEvent event) {
    	this.dungeonScreen.start();
    }

    public void setDungeonScreen(DungeonScreen dungeonScreen) {
        this.dungeonScreen = dungeonScreen;
    }

    public void setStartScreen(StartScreen startScreen) {
        this.startScreen = startScreen;
    }
    
}
