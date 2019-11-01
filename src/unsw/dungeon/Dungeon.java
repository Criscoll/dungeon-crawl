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

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
    }
    
    
    public void addEntity(Entity entity) {
        entities.add(entity);
    }
    
    /**
     * Searches entities in the dungeon by name and returns each one in an arrayList. If no entities 
     * found, returns empty list. 
     * @param name
     * @return
     */
    public List<Entity> getEntity(String name) {
    	List<Entity> entityList = new ArrayList<>(); 
    	for (Entity i : this.entities) {
    		if (i != null) {
    			if (i.getType().equals(name)) 
    				entityList.add(i); 
    		}
    			
    	}
    	return entityList; 
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


}
