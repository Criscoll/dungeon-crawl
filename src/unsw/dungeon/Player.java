package unsw.dungeon;

import java.util.ArrayList;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

    private Dungeon dungeon;
    private ArrayList<Entity> inventory;
	private ArrayList<MovementObserver> observers;
	
    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        inventory = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public void moveUp() {
    	if (dungeon.isObstructed(getX(), getY() - 1)) return;
    	notifyObservers(getX(), getY() - 1);
        if (getY() > 0)
            y().set(getY() - 1);
    }

    public void moveDown() {
    	if (dungeon.isObstructed(getX(), getY() + 1)) return;
    	notifyObservers(getX(), getY() + 1);
        if (getY() < dungeon.getHeight() - 1)
            y().set(getY() + 1);
    }

    public void moveLeft() {
    	if (dungeon.isObstructed(getX() - 1, getY())) return;
    	notifyObservers(getX() - 1, getY());
        if (getX() > 0)
            x().set(getX() - 1);
    }

    public void moveRight() {
    	if (dungeon.isObstructed(getX() + 1, getY())) return;
    	notifyObservers(getX() + 1, getY());
        if (getX() < dungeon.getWidth() - 1)
            x().set(getX() + 1);
    }
    
    /**
     * apply observer pattern to player
     * So that the change that due to the movement of the player are dynamically
     */
    public void notifyObservers(int x, int y) {
		for(MovementObserver o : observers) {
			o.update(x, y);
		}
	}

	public void attach(MovementObserver o) {
		observers.add(o);
		
	}

	public void detach(MovementObserver o) {
		observers.remove(o);
	}
    
	public ArrayList<Entity> getInventory() {
		return this.inventory;
	}
	
}
