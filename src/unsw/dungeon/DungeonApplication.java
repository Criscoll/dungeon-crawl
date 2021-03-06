package unsw.dungeon;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

public class DungeonApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
    	StartScreen startScreen = new StartScreen(primaryStage);
    	PauseScreen pauseScreen = new PauseScreen(primaryStage);
    	GameCompleteScreen completeScreen = new GameCompleteScreen(primaryStage);
    	DungeonScreen dungeonScreen = new DungeonScreen(primaryStage);
    	PlayerDiedScreen playerDiedScreen = new PlayerDiedScreen(primaryStage);
    	
    	
        startScreen.getController().setDungeonScreen(dungeonScreen);
        
        dungeonScreen.getController().setPauseScreen(pauseScreen);
        dungeonScreen.getController().setCompleteScreen(completeScreen);
        dungeonScreen.getController().setPlayerDiedScreen(playerDiedScreen);
        
        pauseScreen.getController().setDungeonScreen(dungeonScreen);
        pauseScreen.getController().setStartScreen(startScreen);
        
        completeScreen.getController().setDungeonScreen(dungeonScreen);
        completeScreen.getController().setStartScreen(startScreen);
        
        playerDiedScreen.getController().setDungeonScreen(dungeonScreen);
        playerDiedScreen.getController().setStartScreen(startScreen);
        
        startScreen.start();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
