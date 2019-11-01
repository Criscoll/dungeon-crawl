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
//			can only pick a key up if no key in the inventory
			if(e instanceof Key && player.getKey() == null) {
//				e = dungeon.popEntity(x, y);
				dungeon.removeEntity(x, y);
//				e.x().set(-1);
//				e.y().set(-1);
				player.getInventory().add(e);		
			}
		}
	}

}
