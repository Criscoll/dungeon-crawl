package unsw.dungeon;

import java.util.Arrays;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * An entity in the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class Entity {

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
	private String type;
    private IntegerProperty x, y;
    private boolean obstructsMovement; 

    /**
     * Create an entity positioned in square (x,y)
     * @param x
     * @param y
     */
    public Entity(int x, int y, String type) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.type = type; 
        
        String[] obstructingEntities = {"wall", "enemy", "door", "exit", "boulder"};       
        this.obstructsMovement = Arrays.asList(obstructingEntities).contains(type) ? true : false; 
        
    }

    public IntegerProperty x() {
        return x;
    }

    public IntegerProperty y() {
        return y;
    }

    
    
    /**
     * Getters & Setters
     * @return
     */
    public int getY() {
        return y().get();
    }

    public int getX() {
        return x().get();
    }
    
    public String getType() {
    	return this.type; 
    }
    
    public boolean obstructsMovement() {
    	return this.obstructsMovement; 
    }
    
}
