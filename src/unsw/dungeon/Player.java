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
//	private String direction;
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
    	notifyObservers(getX(), getY() - 1);
    	if (dungeon.isObstructed(getX(), getY() - 1)) return;
        if (getY() > 0)
            y().set(getY() - 1);
    }

    public void moveDown() {
    	notifyObservers(getX(), getY() + 1);
    	if (dungeon.isObstructed(getX(), getY() + 1)) return;
        if (getY() < dungeon.getHeight() - 1)
            y().set(getY() + 1);
    }

    public void moveLeft() {
    	notifyObservers(getX() - 1, getY());
    	if (dungeon.isObstructed(getX() - 1, getY())) return;
        if (getX() > 0)
            x().set(getX() - 1);
    }

    public void moveRight() {
    	notifyObservers(getX() + 1, getY());
    	if (dungeon.isObstructed(getX() + 1, getY())) return;
        if (getX() < dungeon.getWidth() - 1)
            x().set(getX() + 1);
    }
    
    /**
     * apply observer pattern to player
     * So that the change that due to the movement of the player are dynamically
     */
    public void notifyObservers(int x, int y) {
		for(MovementObserver o : observers) {
			o.update(x, y, this);
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
	
	
	
	/**
	 * Getters & Setters
	 * @param x
	 */
	public void setX(int x) {
		this.x().set(x);
	}

	public void setY(int y) {
		this.y().set(y);
	}


//	public Key getKey() {
//		for(Entity e : getInventory()) {
//			if(e instanceof Key) {
//				return (Key) e;
//			}
//		}
//		return null;
//	}
}
