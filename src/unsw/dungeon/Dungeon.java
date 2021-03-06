/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
	private BooleanProperty levelCompleted;
    
    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.entities = FXCollections.observableArrayList(entities);
        this.floorSwitches = new ArrayList<>();
        this.player = null;
        goal = null;
        levelCompleted = new SimpleBooleanProperty(false);
    }
    
    public ObservableList<Entity> getobservableListEntity(){
    	return (ObservableList<Entity>) entities;
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
    
    public ArrayList<Entity> getPlayerInventory() {
    	return this.player.getInventory();
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
		this.levelCompleted.set(value);
	}


	public BooleanProperty getLevelCompleted() {
		return this.levelCompleted;
	}
	
	public boolean isLevelCompleted() {
		return this.levelCompleted.get();
	}

	public void removeEntity(Entity entity) {
		if(entities.contains(entity))
			entities.remove(entity);
		
	}
}
