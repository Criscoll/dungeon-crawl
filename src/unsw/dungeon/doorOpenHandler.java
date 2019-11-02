package unsw.dungeon;

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
			Key key = player.getKey();
			if(key != null && ((Door) e).getId() == key.getId()) {
				((Door) e).openDoor();
				player.getInventory().remove(key);
			}
		}
	}

}