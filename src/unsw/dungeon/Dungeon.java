/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

    private int width, height;
    private List<Entity> entities;
    private Player player;
    private List<Entity> floorSwitches;
    private GoalComponent goal;
	public boolean levelCompleted;
    
    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.floorSwitches = new ArrayList<>();
        this.player = null;
        goal = null;
        levelCompleted = false;
    }
    
    
    public void addEntity(Entity entity) {
    	if(entity instanceof FloorSwitch) {
    		floorSwitches.add(entity);
    	}else {
    		entities.add(entity);    		
    	}
    }
    
    public ArrayList<Entity> getFloorSwitches() {
    	return (ArrayList<Entity>) this.floorSwitches;
    }

    public FloorSwitch getFloorSwitch(int x, int y) {
    	for(Entity entity : floorSwitches) {
    		if(x == entity.getX() && y == entity.getY()) {
    			return (FloorSwitch) entity;
    		}
    	}
    	return null;
    }
    
    /**
     * Returns all the entities in the current dungeon. Ensures no null entities are returned in the list. 
     * @return
     */
    public List<Entity> getEntities() {
    	List<Entity> entityList = new ArrayList<>(); 
    	for (Entity i : this.entities) {
    		if (i != null) 
    			entityList.add(i); 
    	}
    	return entityList; 
    }
    
    public List<Enemy> getEnemies() {
    	List<Enemy> enemyList = new ArrayList<>(); 
    	for (Entity i : this.entities) {
    		if (i instanceof Enemy) 
    			enemyList.add((Enemy)i); 
    	}
    	return enemyList;
    }


    /**
     * Getters & Setters
     * @return
     */
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }
    
    public void setPlayer(Player player) {
        this.player = player;
    }
    
    public void killPlayer() {
    	this.player.setDead(true);
    }
    
    public Entity getEntity(int x, int y) {
    	for(Entity e : entities) {
    		if(e == null) continue;
    		if(e.getX() == x && e.getY() == y)
    			return e;
    	}
    	return null;
    }
        
    public boolean isObstructed(int x, int y) {
    	if(getEntity(x, y) == null)
    		return false;
    	return getEntity(x, y).obstructsMovement();
    }

    public void removeEntity(int x, int y) {
    	if(getEntity(x, y) != null) {
    		entities.remove(getEntity(x, y));
    	}
    }


	public void setGoal(GoalComponent rootGoal) {
		this.goal = rootGoal;
	}

	public GoalComponent getGoal() {
		return goal;
	}
	
	public boolean canPlayerExit() {
		return goal.getValue();
	}


	public void setLevelCompleted(boolean value) {
		this.levelCompleted = true;
	}


	public boolean isLevelCompleted() {
		return levelCompleted;
	}
}
