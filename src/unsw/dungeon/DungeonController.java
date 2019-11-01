package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

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

    }

    @FXML
    public void handleKeyPress(KeyEvent event) {
    	List<Entity> entityList = this.dungeon.getEntities();  
    	boolean playerObstructed = false; 
    	
        switch (event.getCode()) {
        
        case UP:
        	for (Entity i : entityList) {
        		if (i.getY() == (player.getY() - 1) && i.getX() == player.getX()) { // an entity is above player
        			playerObstructed = i.obstructsMovement();
        			break; 
        		}
        	}
        	if (!playerObstructed) player.moveUp(); 
        	playerObstructed = false;
            break;
            
        case DOWN:
        	for (Entity i : entityList) {
        		if (i.getY() == (player.getY() + 1) && i.getX() == player.getX()) { // an entity is below player
        			playerObstructed = i.obstructsMovement();
        			break; 
        		}
        	}
        	if (!playerObstructed) player.moveDown(); 
        	playerObstructed = false;
            break;
            
        case LEFT:
        	for (Entity i : entityList) {
        		if (i.getY() == player.getY() && i.getX() == (player.getX() - 1 )) { // an entity is left of the player
        			playerObstructed = i.obstructsMovement();
        			break; 
        		}
        	}
        	if (!playerObstructed) player.moveLeft(); 
        	playerObstructed = false;
            break;
            
        case RIGHT:
        	for (Entity i : entityList) {
        		if (i.getY() == player.getY() && i.getX() == (player.getX() + 1 )) { // an entity is right of the playe
        			playerObstructed = i.obstructsMovement();
        			break; 
        		}
        	}
        	if (!playerObstructed) player.moveRight(); 
        	playerObstructed = false;
            break;
            
        default:
            break;
        }
    }

}

