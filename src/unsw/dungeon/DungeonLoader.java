package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height);

        JSONArray jsonEntities = json.getJSONArray("entities");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }
        return dungeon;
    }

    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");

        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y);
            // add observers here
            ItemPickUpHandler itemObserver = new ItemPickUpHandler(dungeon);
        	PortalHandler portalObserver = new PortalHandler(dungeon); 
            player.attach(itemObserver);
        	player.attach(portalObserver);
            doorOpenHandler doorObserver = new doorOpenHandler(dungeon);
            player.attach(doorObserver);
            boulderPushHandler boulderObserver = new boulderPushHandler(dungeon);
            player.attach(boulderObserver);

            dungeon.setPlayer(player);
            onLoad(player);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall(x, y);
            onLoad(wall);
            entity = wall;
            break;
            
        // TODO Handle other possible entities
            
        case "exit":
        	Exit exit = new Exit(x, y);
        	onLoad(exit);
        	entity = exit;
            break;
            
        case "key": 
        	int id = json.getInt("id");
        	Key key = new Key(x, y, id);
        	onLoad(key);
        	entity = key;
        	break; 
        	
        case "portal": 
        	id = json.getInt("id");
        	Portal portal = new Portal(x, y, id);
        	onLoad(portal);
        	entity = portal;
        	break; 
        	
        case "treasure":
        	// TODO Handle treasure entity
        	break; 
        case "sword":
        	// TODO Handle sword entity
        	break; 
        case "boulder":
        	Boulder boulder = new Boulder(x, y); 
        	onLoad(boulder); 
        	entity = boulder; 
        	break;
        	
        case "switch":
        	FloorSwitch pressure_plate = new FloorSwitch(x, y); 
        	onLoad(pressure_plate); 
        	entity = pressure_plate; 
        	break;
        	
        case "invincibility":
        	// TODO Handle invincibility entity
        	break; 
        }
        dungeon.addEntity(entity);
    }

    public abstract void onLoad(Entity player);

    public abstract void onLoad(Wall wall);

    // TODO Create additional abstract methods for the other entities

    public abstract void onLoad(Exit exit);
    
    public abstract void onLoad(Key key);
    
    public abstract void onLoad(Portal portal); 
    
    public abstract void onLoad(Boulder boulder);
    
    public abstract void onLoad(FloorSwitch boulder); 

    
    
}
