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
        
        GoalComponent rootGoal = loadGoal(json.getJSONObject("goal-condition"));
        dungeon.setGoal(rootGoal);
        GoalCheckingObserver goalObserver = new GoalCheckingObserver(dungeon);
        dungeon.getPlayer().attachMovementObserver(goalObserver);
        
        return dungeon;
    }


	private GoalComponent loadGoal(JSONObject object) {
		String goal = object.getString("goal");
		if(goal.equals("AND") || goal.equals("OR")) {
			LogicalOperator operator = new LogicalOperator(goal);
			JSONArray jsonSubGoals = object.getJSONArray("subgoals");
			for (int i = 0; i < jsonSubGoals.length(); i++) {
	            operator.add(loadGoal(jsonSubGoals.getJSONObject(i)));
	        }
			return operator;
		}
		else {
			return new Goal(goal);
		}
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
            doorOpenHandler doorObserver = new doorOpenHandler(dungeon);
            boulderPushHandler boulderObserver = new boulderPushHandler(dungeon);
//            EnemyHandler enemyHandler = new EnemyHandler(dungeon); 
            

            WeaponHandler weaponHandler = new WeaponHandler(dungeon); 
            AttackHandler attackHandler = new AttackHandler(dungeon); 
            
            player.attachMovementObserver(itemObserver);
        	player.attachMovementObserver(portalObserver);
            player.attachMovementObserver(doorObserver);
            player.attachMovementObserver(boulderObserver);
//            player.attachMovementObserver(enemyHandler);
            
            player.attachAttackObserver(attackHandler);
            player.attachAttackObserver(weaponHandler);
              
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
        
        case "enemy":
        	Enemy enemy = new Enemy(dungeon, x,y); 
        	onLoad(enemy); 
        	entity = enemy; 
        	break;
            
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
        	Treasure treasure = new Treasure(x,y); 
        	onLoad(treasure); 
        	entity = treasure; 
        	break; 
        case "sword":
        	Sword sword = new Sword(x,y); 
        	onLoad(sword); 
        	entity = sword; 
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
        	Potion potion = new Potion(x, y); 
        	onLoad(potion); 
        	entity = potion; 
        	break; 
        
	    case "door":
        	id = json.getInt("id");
	    	Door door = new Door(x, y, id); 
	    	onLoad(door); 
	    	entity = door; 
	    	break;
	    	
	    case "wand":
	    	Wand wand = new Wand(x, y);
	    	onLoad(wand);
	    	entity = wand;
	    	break;
	    	
	    case "hound":
	    	Hound hound = new Hound(dungeon, x, y);
	    	onLoad(hound);
	    	entity = hound;
	    	break; 	
	    }
        dungeon.addEntity(entity);
    }

    public abstract void onLoad(Entity player);

    public abstract void onLoad(Wall wall);

    // TODO Create additional abstract methods for the other entities
    
    public abstract void onLoad(Enemy enemy); 

    public abstract void onLoad(Exit exit);
    
    public abstract void onLoad(Key key);
    
    public abstract void onLoad(Sword sword); 
    
    public abstract void onLoad(Treasure treasure); 
    
    public abstract void onLoad(Potion potion); 
    
    public abstract void onLoad(Portal portal); 
    
    public abstract void onLoad(Boulder boulder);
    
    public abstract void onLoad(FloorSwitch boulder); 
  
    public abstract void onLoad(Door door); 

    public abstract void onLoad(Wand wand); 

    public abstract void onLoad(Hound hound); 
    
}
