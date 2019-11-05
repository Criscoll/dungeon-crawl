package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
    }

	private void removeHandler() {
		dungeon.getobservableListEntity().addListener(new ListChangeListener<Entity>() {
			@Override
			public void onChanged(Change<? extends Entity> c) {
				while(c.next()) {
					if(c.wasRemoved()) {
						System.out.println("removed!");
						for(Entity e : c.getRemoved()) {
							squares.getChildren().remove(e.getView());
							
						}
					}					
				}
			}
        });
	}

	private void enemyMoveHandler() {
		EnemyHandler enemyHandler = new EnemyHandler(dungeon);
        Timeline timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame keyFrame = new KeyFrame(Duration.millis(500), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				enemyHandler.update(player.getX(), player.getY(), player);
			}
		});
		timeline.getKeyFrames().add(keyFrame);
		timeline.play();
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
            
        default:
            break;
        }
    }


}

