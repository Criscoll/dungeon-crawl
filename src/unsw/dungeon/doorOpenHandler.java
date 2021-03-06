package unsw.dungeon;

import java.util.ArrayList;

public class doorOpenHandler implements MovementObserver{

	private Dungeon dungeon;
	
	public doorOpenHandler(Dungeon dungeon) {
		this.dungeon = dungeon;
	}

	@Override
	public void update(int x, int y, Player player) {
		Entity e = dungeon.getEntity(x, y);
		if(e == null) return;
		if(e instanceof Door && !((Door) e).isOpen()) {
			ArrayList<Entity> entities = player.getInventory();
			for(Entity entity : entities) {
				if(entity instanceof Key && ((Door) e).getId() == ((Key)entity).getId()) {
					((Door) e).openDoor();
					player.getInventory().remove(entity);		
					break;
				}
			}
		}
	}

}