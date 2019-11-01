package unsw.dungeon;

public class ItemPickUpHandler implements MovementObserver{

	private Dungeon dungeon;
	
	public ItemPickUpHandler(Dungeon dungeon) {
		this.dungeon = dungeon;
	}

	@Override
	public void update(int x, int y) {
		Entity e = dungeon.getEntity(x, y);
		if(e == null) return;
		if(e.isPickupable()) {
//			e = dungeon.popEntity(x, y);
			dungeon.removeEntity(x, y);
//			e.x().set(-1);
//			e.y().set(-1);
			dungeon.getPlayer().getInventory().add(e);
		}
	}

}
