package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {

    @FXML
    private GridPane squares;

    private List<ImageView> initialEntities;

    private Player player;

    private Dungeon dungeon;

    Timeline Potiontimeline;
    Timeline Enemytimeline;
    
    private PauseScreen pauseScreen;
    
    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
    }

    @FXML
    public void initialize() {
        Image ground = new Image("/dirt_0_new.png");

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView entity : initialEntities)
            squares.getChildren().add(entity);

        enemyMoveHandler();
        removeHandler();
        doorOpenHandler();
        invincibleStateCountDownHandler();
    }

    /**
     * first created a timer, then listen to the BooleanProperty invincible in
     * the player class, if the value been changing to true start the timer
     * if the value been set to false stop the timer
     * because when the Invincibility potion is pick up this value will be set to false and then set to true
     * so pick up the potion while in the effect of invincibility will restart the timer if the effect is on going 
     */
	private void invincibleStateCountDownHandler() {
		Potiontimeline = new Timeline();
		Potiontimeline.setCycleCount(1);
		KeyFrame keyFrame = new KeyFrame(Duration.millis(5000), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				player.setInvinicibility(false);
			}
		});
		Potiontimeline.getKeyFrames().add(keyFrame);
		player.getInvincible().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue.booleanValue()) Potiontimeline.play();
				// pick another potion while in effect 
				else Potiontimeline.stop();
			}
        	
        });
	}
	/**
	 *  if a door is opened remove the closed door image 
	 *  and then add the opened door image to the same location		
	 */
	private void doorOpenHandler() {
		for(Entity entity : dungeon.getEntities()) {
        	if(entity instanceof Door) {
        		((Door) entity).getStatus().addListener(new ChangeListener<Boolean>() {
        			@Override
					public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
							Boolean newValue) {
						squares.getChildren().remove(entity.getView());
						squares.add(((Door) entity).getOpenDoorView(), entity.getX(), entity.getY());
					}
        		});
        	}
        }
	}

	/**
	 * if the entities list in the dungeon class remove a entity,
	 * then remove that entity from the map too
	 */
	private void removeHandler() {
		dungeon.getobservableListEntity().addListener(new ListChangeListener<Entity>() {
			@Override
			public void onChanged(Change<? extends Entity> c) {
				while(c.next()) {
					if(c.wasRemoved()) {
						for(Entity e : c.getRemoved()) {
							squares.getChildren().remove(e.getView());
						}
					}					
				}
			}
        });
	}

	/**
	 * make enemy move periodically(0.5s/move) 
	 */
	private void enemyMoveHandler() {
		EnemyHandler enemyHandler = new EnemyHandler(dungeon);
		Enemytimeline = new Timeline();
		Enemytimeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame keyFrame = new KeyFrame(Duration.millis(500), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				enemyHandler.update(player.getX(), player.getY(), player);
			}
		});
		Enemytimeline.getKeyFrames().add(keyFrame);
		Enemytimeline.play();
	}

    @FXML
    public void handleKeyPress(KeyEvent event) {
    	if (this.player.dead()) return; // if player dead, ignore inputs
        switch (event.getCode()) {
        case UP:
            player.moveUp();
            break;
        case DOWN:
            player.moveDown();
            break;
        case LEFT:
            player.moveLeft();
            break;
        case RIGHT:
            player.moveRight();
            break;
            
//		attack keys
        case A: 
        	player.attackLeft();
        	break;
        case D: 
        	player.attackRight();
        	break;
        case W: 
        	player.attackUp();
        	break;
        case S: 
        	player.attackDown();
        	break; 
            
        case ESCAPE:
        	stopDungeon(true);
        	this.pauseScreen.start();
        	break;
        	
        default:
            break;
        }
    }

	public void setPauseScreen(PauseScreen pauseScreen) {
		this.pauseScreen = pauseScreen;
	}

	// TODO
	// can be improved
	public void stopDungeon(boolean value) {
		if(value) {
			Enemytimeline.stop();
			Potiontimeline.stop();
		}else {
			Enemytimeline.play();
			Potiontimeline.play();			
		}
	}

}

