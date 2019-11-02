package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Door extends Entity{

	private int id;
	private BooleanProperty status;
	
	public Door(int x, int y, int id) {
		super(x, y);
		this.id = id;
		setObstructsMovement(true);
		this.status = new SimpleBooleanProperty(false);
	}
	
	public int getId() {
		return id;
	}
	
	public boolean isOpen() {
		return this.status.get();
	}
	
	public void openDoor() {
		status.set(true);
		setObstructsMovement(false);
	}
}
