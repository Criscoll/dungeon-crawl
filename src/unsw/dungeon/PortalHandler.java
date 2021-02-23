package unsw.dungeon;

import java.io.File;
import java.util.List;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class PortalHandler implements MovementObserver {
	
	private Dungeon dungeon;
	
	public PortalHandler(Dungeon dungeon) {
		this.dungeon = dungeon;
	}

	@Override
	public void update(int x, int y, Player player) {
		
		Entity e = dungeon.getEntity(x, y);
		if(e == null) return;
		
		if (e.isPortal() && e instanceof Portal) {
			int id = ((Portal) e).getId();
			List<Entity> entities = this.dungeon.getEntities(); 
			
			for (Entity entity : entities) {
				if (entity.isPortal() && !entity.equals(e)) {
					if (((Portal) entity).getId() == id) { // Set new player coordinates to that of corresponding portal
						player.setX(entity.getX());
						player.setY(entity.getY());
						Media sound = new Media(new File("sounds/portal.mp3").toURI().toString());
						MediaPlayer mediaPlayer = new MediaPlayer(sound);
						mediaPlayer.play();
						break;
					}
				}
			}
			
		}		
	}
	
}



