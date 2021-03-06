package unsw.dungeon;

import java.util.ArrayList;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

	private Dungeon dungeon;
    private ArrayList<Entity> inventory;
	private ArrayList<MovementObserver> movementObservers;
	private ArrayList<AttackObserver> attackObservers; 
	private BooleanProperty invincible; 

	private BooleanProperty sword; 
	private BooleanProperty wand;

	private boolean hasWeapon; 
	private BooleanProperty isDead; 
	
	
	private Entity weapon; 

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        inventory = new ArrayList<>();
        movementObservers = new ArrayList<>();
        attackObservers = new ArrayList<>(); 
        this.invincible = new SimpleBooleanProperty(false);
        this.sword = new SimpleBooleanProperty(false); 
        this.wand = new SimpleBooleanProperty(false); 

        this.weapon = null; 
        this.hasWeapon = false; 

        this.isDead = new SimpleBooleanProperty(false);
    }

    public void moveUp() {
    	notifyMovementObservers(getX(), getY() - 1);
    	if (dungeon.isObstructed(getX(), getY() - 1)) return;
        if (getY() > 0)
            y().set(getY() - 1);
    }

    public void moveDown() {
    	notifyMovementObservers(getX(), getY() + 1);
    	if (dungeon.isObstructed(getX(), getY() + 1)) return;
        if (getY() < dungeon.getHeight() - 1)
            y().set(getY() + 1);
    }

    public void moveLeft() {
    	notifyMovementObservers(getX() - 1, getY());
    	if (dungeon.isObstructed(getX() - 1, getY())) return;
        if (getX() > 0)
            x().set(getX() - 1);
    }

    public void moveRight() {
    	notifyMovementObservers(getX() + 1, getY());
    	if (dungeon.isObstructed(getX() + 1, getY())) return;
        if (getX() < dungeon.getWidth() - 1)
            x().set(getX() + 1);
    }
    
    
    public void attackUp() {
    	if (!this.hasWeapon()) return; 
    	notifyAttackObservers(getX(), getY() - 1);
    }

    public void attackDown() {
    	if (!this.hasWeapon()) return; 
    	notifyAttackObservers(getX(), getY() + 1);
    }

    public void attackLeft() {
    	if (!this.hasWeapon()) return; 
    	notifyAttackObservers(getX() - 1, getY());
    }

    public void attackRight() {
    	if (!this.hasWeapon()) return; 
    	notifyAttackObservers(getX() + 1, getY());
    }    
    /**
     * apply observer pattern to player
     * So that the change that due to the movement of the player are dynamically handled
     */
    public void notifyMovementObservers(int x, int y) {
		for(MovementObserver o : movementObservers) {
			o.update(x, y, this);
		}
	}

	public void attachMovementObserver(MovementObserver o) {
		movementObservers.add(o);
		
	}

	public void detachMovementObserver(MovementObserver o) {
		movementObservers.remove(o);
	}
    
	
	/**
	 * Apply observer pattern to player
	 * Any change due to player attacks are dynamically handled
	 */
	public void notifyAttackObservers(int x, int y) {
		for (AttackObserver o : attackObservers) {
			o.update(x, y, this);
		}
	}
	
	public void attachAttackObserver(AttackObserver o) {
		attackObservers.add(o); 
	}
	
	public void detachAttackObserver(AttackObserver o) {
		attackObservers.remove(o); 
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

	public BooleanProperty getInvincible () {
		return this.invincible; 
	}
	
	public boolean invincible () {
		return this.invincible.get(); 
	}
	
	public void setInvinicibility(boolean value) {
		this.invincible.set(value); 
	}
	
	public BooleanProperty sword() {
		return this.sword; 
	}
	
	public void setSword(boolean value) {
		this.sword.set(value);
	}
	
	public BooleanProperty wand() {
		return this.wand;
	}
	
	public void setWand(boolean value) {
		this.wand.set(value); 
	}
	
	public void setHasWeapon(boolean value) {
		this.hasWeapon = value; 
	}
	
	public boolean hasWeapon() {
		return this.hasWeapon; 
	}
	
	public Entity getWeapon() {
		return this.weapon;
	}

	public void setWeapon(Entity entity) {
		this.weapon = entity;
		this.setHasWeapon(true);
	}
	
	public void removeWeapon() {
		this.weapon = null;
		this.setHasWeapon(false);
	}
	
	public boolean dead() {
		return this.isDead.get(); 
	}
	
	public BooleanProperty getIsDead() {
		return this.isDead; 
	}
	
	public void setDead(boolean value) {
		this.isDead.set(value); 
	}

}
