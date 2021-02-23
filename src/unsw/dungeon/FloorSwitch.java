package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class FloorSwitch extends Entity{

	private BooleanProperty active;
	public FloorSwitch(int x, int y) {
		super(x, y);
		active = new SimpleBooleanProperty(false);
	}
	
	public boolean isActive() {
		return active.get();
	}
	
	public void setActive(boolean value) {
		active.set(value);
	}
}
