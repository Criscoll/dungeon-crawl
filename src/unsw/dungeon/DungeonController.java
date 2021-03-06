package unsw.dungeon;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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
    
    private Timeline Potiontimeline;
    private Timeline Enemytimeline;
    private Timeline HoundTimeline;
    
    private PauseScreen pauseScreen;

	private GameCompleteScreen completeScreen;

	private PlayerDiedScreen playerDiedScreen;
    
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
        entitiesListObserver();
        invincibleStateCountDownHandler();
        levelCompleteHandler();
        playerDiedHandler();
        changePlayerHoldWeapon(); 
        
        
        stopDungeon(true);
       
        
    }

    private void changePlayerHoldWeapon() {
		player.sword().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue.booleanValue()) {
					squares.getChildren().remove(player.getView());
					Image playerHoldSwordImage = new Image("/human_sword.png");
					ImageView view = new ImageView(playerHoldSwordImage);
					view.setFitHeight(32);
					view.setFitWidth(32);
					DungeonControllerLoader.trackPosition(player, view);
					player.setView(view);
					squares.getChildren().add(view);
				} else {
					squares.getChildren().remove(player.getView());
					Image playerImage = new Image("/human_new.png");
					ImageView view = new ImageView(playerImage);
					view.setFitHeight(32);
					view.setFitWidth(32);
					DungeonControllerLoader.trackPosition(player, view);
					player.setView(view);
					squares.getChildren().add(view);
				}
					
			}
        });
		
		player.wand().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue.booleanValue()) {
					squares.getChildren().remove(player.getView());
					Image playerHoldSwordImage = new Image("/human_wand.png");
					ImageView view = new ImageView(playerHoldSwordImage);
					view.setFitHeight(32);
					view.setFitWidth(32);
					DungeonControllerLoader.trackPosition(player, view);
					player.setView(view);
					squares.getChildren().add(view);
				} else {
					squares.getChildren().remove(player.getView());
					Image playerImage = new Image("/human_new.png");
					ImageView view = new ImageView(playerImage);
					view.setFitHeight(32);
					view.setFitWidth(32);
					DungeonControllerLoader.trackPosition(player, view);
					player.setView(view);
					squares.getChildren().add(view);
				}
					
			}
        });
		
	
	}
    

    
    
	private void playerDiedHandler() {
		player.getIsDead().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue.booleanValue())
					playerDiedScreen.start();	
			}
        });
	}

	private void levelCompleteHandler() {
		dungeon.getLevelCompleted().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue.booleanValue())
					completeScreen.start();
			}
        });
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
		Media potionActive = new Media(new File("sounds/potion_up.mp3").toURI().toString());
		Media potionInactive = new Media(new File("sounds/potion_down.mp3").toURI().toString());
		MediaPlayer active = new MediaPlayer(potionActive);
		MediaPlayer inactive = new MediaPlayer(potionInactive);

		Potiontimeline.getKeyFrames().add(keyFrame);
		player.getInvincible().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue.booleanValue()) {
					Potiontimeline.play();
					active.play();
				}
				// pick another potion while in effect 
				else {
					Potiontimeline.stop();
					inactive.play();
				}
			}
        	
        });
	}
	
	/**
	 * if the entities list in the dungeon class remove a entity,
	 * then remove that entity from the map too
	 */
	private void entitiesListObserver() {
		dungeon.getobservableListEntity().addListener(new ListChangeListener<Entity>() {
			@Override
			public void onChanged(Change<? extends Entity> c) {
				while(c.next()) {
					if(c.wasRemoved()) {
						for(Entity e : c.getRemoved()) {
							squares.getChildren().remove(e.getView());
						}
					}
					else if(c.wasAdded()) {
						Entity addedEntity = c.getAddedSubList().get(c.getAddedSubList().size() - 1);
						squares.getChildren().add(addedEntity.getView());
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
		
		
		HoundHandler houndHandler = new HoundHandler(dungeon);
		HoundTimeline = new Timeline();
		HoundTimeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame HouldKeyFrame = new KeyFrame(Duration.millis(250), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				houndHandler.update(player.getX(), player.getY(), player);
			}
		});
		
		Enemytimeline.getKeyFrames().add(keyFrame);
		HoundTimeline.getKeyFrames().add(HouldKeyFrame);
//		Enemytimeline.play();
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
			HoundTimeline.stop();
		}else {
			Enemytimeline.play();
			Potiontimeline.play();
			HoundTimeline.play();
		}
	}

	public void setCompleteScreen(GameCompleteScreen completeScreen) {
		this.completeScreen = completeScreen;
	}

	public void resetDungeon(String filename) throws FileNotFoundException {
		squares.getChildren().clear();
		DungeonControllerLoader dl = new DungeonControllerLoader(filename);
		this.initialEntities = dl.getEntities();
		this.dungeon = dl.load();
		this.player = dungeon.getPlayer();	
		initialize();
	}
	public Map<String, Boolean> getGoals() {
		Map<String, Boolean> goals = new HashMap<String, Boolean>();
		
		if(dungeon.getGoal().isTreasureGoal()) {
			goals.put("Truesure", dungeon.getGoal().isTreasureGoalTrue());
		}
		if(dungeon.getGoal().isBoulderGoal()) {
			goals.put("Boulder", dungeon.getGoal().isBoulderGoalTrue());
		}
		if(dungeon.getGoal().isEnemyGoal()) {
			goals.put("Enemy", dungeon.getGoal().isEnemyGoalTrue());
		}
		if(dungeon.getGoal().isExitGoal()) {
			goals.put("Exit", false);
		}
		return goals;
	}

	public String getGoalRelation() {
		return dungeon.getGoal().toString();
	}

	public void setPlayerDiedScreen(PlayerDiedScreen playerDiedScreen) {
		this.playerDiedScreen = playerDiedScreen;
		
	}
}

