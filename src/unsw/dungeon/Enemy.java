package unsw.dungeon;

import java.util.ArrayList;

public class Enemy extends Entity {
	
	private int hp; 
    private Dungeon dungeon;
	private ArrayList<MovementObserver> observers;
	private EnemyState state; 
    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Enemy(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.hp = 1; 
        this.dungeon = dungeon;
        observers = new ArrayList<>();
        this.state = new NormalState(this); 
        this.setObstructsMovement(true);
        
    }

    public void EnemyMovement(int pX, int pY, Dungeon dungeon) {
    	this.state.move(pX, pY, dungeon);
    }
    
    public void changeState(EnemyState state) {
    	this.state = state; 
    	if (this.state instanceof InvincibilityState) this.setObstructsMovement(false);
    	else this.setObstructsMovement(true);
    }
    
    
    
    /**
     * Reduces the enemy hp by 1. Called when hit by player sword. 
     */
    public void enemyHit() {
    	this.hp -= 1; 
    }
    
    public void killEnemy() {
    	dungeon.removeEntity(this);
    }
    
    public void moveUp() {
    	if (dungeon.isObstructed(getX(), getY() - 1)) return;
        if (getY() > 0)
            y().set(getY() - 1);
    }

    public void moveDown() {
    	if (dungeon.isObstructed(getX(), getY() + 1)) return;
        if (getY() < dungeon.getHeight() - 1)
            y().set(getY() + 1);
    }

    public void moveLeft() {
    	if (dungeon.isObstructed(getX() - 1, getY())) return;
        if (getX() > 0)
            x().set(getX() - 1);
    }

    public void moveRight() {
    	if (dungeon.isObstructed(getX() + 1, getY())) return;
        if (getX() < dungeon.getWidth() - 1)
            x().set(getX() + 1);
    }
    
    /**
     * Getters & Setters
     * @return
     */
    public int hp() {
    	return this.hp; 
    }
    
}
