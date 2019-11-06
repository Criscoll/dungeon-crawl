package unsw.dungeon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartController {

    @FXML
    private Button startButton;

    private DungeonScreen dungeonScreen;

    @FXML
    void handleStartButton(ActionEvent event) {
    	dungeonScreen.start();
    }

    public void setDungeonScreen(DungeonScreen dungeonScreen) {
        this.dungeonScreen = dungeonScreen;
    }

}

