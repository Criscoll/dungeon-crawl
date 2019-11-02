package unsw.dungeon;



public class Portal extends Entity {

	
	private int id;
	
	public Portal(int x, int y, int id) {
		super(x, y);
		this.id = id;
		setObstructsMovement(false);
		this.setPortal(true);
	}
	
	public int getId() {
		return id;
	}


	
}
