package unsw.dungeon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class GameCompleteController {

    @FXML
    private Button BackToStartButton;

    @FXML
    private Button ReplayButton;

    @FXML
    private Button nextLevelButton;
    
    private DungeonScreen dungeonScreen;
    private StartScreen startScreen;
    
    @FXML
    void BackToStartHandler(ActionEvent event) {
    	this.startScreen.start();
    }

    @FXML
    void ReplayButtonHandler(ActionEvent event) {
    	this.dungeonScreen.resetDungeon();
    	this.dungeonScreen.start();
    }

    @FXML
    void nextLevelHandler(ActionEvent event) {
    	this.dungeonScreen.nextLevel();
    	this.dungeonScreen.start();
    }
    
    public void setDungeonScreen(DungeonScreen dungeonScreen) {
        this.dungeonScreen = dungeonScreen;
    }

    public void setStartScreen(StartScreen startScreen) {
        this.startScreen = startScreen;
    }
    
}
