package unsw.dungeon;

public class ItemPickUpHandler implements MovementObserver{

	private Dungeon dungeon;
	
	public ItemPickUpHandler(Dungeon dungeon) {
		this.dungeon = dungeon;
	}

	@Override
	public void update(int x, int y, Player player) {
		Entity e = dungeon.getEntity(x, y);
		if(e == null) return;
		if(e.isPickupable()) {
//			can only pick a key / sword up if no key / sword in the inventory
			for(Entity entity : player.getInventory()) {
				if(e instanceof Key && entity instanceof Key)
					return;
				if(e instanceof Sword && entity instanceof Sword)
					return;
			}
			if (e instanceof Sword) player.setSword(true); // player now has a sword
			dungeon.removeEntity(x, y);
			player.getInventory().add(e);		
			
		}
	}

}
