package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.ImageView;

public class Door extends Entity{

	private int id;
	private BooleanProperty status;
	private ImageView openDoorView = new ImageView();

	public Door(int x, int y, int id) {
		super(x, y);
		this.id = id;
		setObstructsMovement(true);
		this.status = new SimpleBooleanProperty(false);
	}
	
	public int getId() {
		return id;
	}
	
	public BooleanProperty getStatus() {
		return status;
	}

	public boolean isOpen() {
		return this.status.get();
	}
	
	public void openDoor() {
		status.set(true);
		setObstructsMovement(false);
	}

	public void setOpenDoorView(ImageView view) {
		this.openDoorView = view;
	}
	
	public ImageView getOpenDoorView() {
		return openDoorView;
	}
}
