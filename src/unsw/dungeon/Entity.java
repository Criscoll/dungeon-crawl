package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.ImageView;

/**
 * An entity in the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class Entity {

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
    private IntegerProperty x, y;
    private boolean obstructsMovement = false; 
    private boolean pickupable = false;
    private boolean portal = false; 
    private ImageView view = new ImageView();
    
    /**
     * Create an entity positioned in square (x,y)
     * @param x
     * @param y
     */
    public Entity(int x, int y) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);        
    }

    public ImageView getView() {
		return view;
	}

	public void setView(ImageView view) {
		this.view = view;
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
    
    public boolean obstructsMovement() {
    	return this.obstructsMovement; 
    }
    
    public void setObstructsMovement(boolean value) {
    	this.obstructsMovement = value; 
    }

	public boolean isPickupable() {
		return pickupable;
	}

	public void setPickupable(boolean value) {
		this.pickupable = value;
	}
    
	public boolean isPortal() {
		return this.portal;
	}

	public void setPortal(boolean value) {
		this.portal = value;
	}
	
}
