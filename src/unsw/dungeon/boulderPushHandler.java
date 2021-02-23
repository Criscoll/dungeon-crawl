package unsw.dungeon;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class boulderPushHandler implements MovementObserver{

	private Dungeon dungeon;
	public boulderPushHandler(Dungeon dungeon) {
		this.dungeon = dungeon;
	}
	
	@Override
	public void update(int x, int y, Player player) {
		Entity e = dungeon.getEntity(x, y);
		if(e == null) return;
		// player tried to move to the location of the boulder
		if(e instanceof Boulder) {
			Media sound = new Media(new File("sounds/boulder_push.mp3").toURI().toString());
			MediaPlayer boulderSound = new MediaPlayer(sound);
			//boulder above the player
			if(x == player.getX() && y == player.getY()-1) {
				// only push the boulder when there is no entity in the square
				if(e.getY() > 0 && dungeon.getEntity(x, y-1) == null) {
					
					//push away from a floor switch
					FloorSwitch fs = dungeon.getFloorSwitch(x, y);
					if(fs != null) {
						fs.setActive(false);
					}
					// push to a floor switch
					fs = dungeon.getFloorSwitch(x, y-1);
					if(fs != null) {
						fs.setActive(true);
					}
					
					e.y().set(e.getY()-1);
					boulderSound.play();


					
				}
			}
			//boulder below the player
			else if(x == player.getX() && y == player.getY()+1) {
				if(e.getY() < dungeon.getHeight() - 1 && dungeon.getEntity(x, y+1) == null) {
					//push away from a floor switch
					FloorSwitch fs = dungeon.getFloorSwitch(x, y);
					if(fs != null) {
						fs.setActive(false);
					}
					// push to a floor switch
					fs = dungeon.getFloorSwitch(x, y+1);
					if(fs != null) {
						fs.setActive(true);
					}
					
					e.y().set(e.getY()+1);
					boulderSound.play();

				}
			}
				
			//boulder on the left side of the player
			else if(x == player.getX() - 1 && y == player.getY()) {
				if(e.getX() > 0 && dungeon.getEntity(x-1, y) == null) {
					//push away from a floor switch
					FloorSwitch fs = dungeon.getFloorSwitch(x, y);
					if(fs != null) {
						fs.setActive(false);
					}
					// push to a floor switch
					fs = dungeon.getFloorSwitch(x-1, y);
					if(fs != null) {
						fs.setActive(true);
					}
					
					e.x().set(e.getX()-1);
					boulderSound.play();

				}
			}
			
			//boulder on the right side of the player
			else if(x == player.getX() + 1 && y == player.getY()){

				if(e.getX() < dungeon.getWidth() - 1 && dungeon.getEntity(x+1, y) == null) {
					//push away from a floor switch
					FloorSwitch fs = dungeon.getFloorSwitch(x, y);
					if(fs != null) {
						fs.setActive(false);
					}
					// push to a floor switch
					fs = dungeon.getFloorSwitch(x+1, y);
					if(fs != null) {
						fs.setActive(true);
					}
					e.x().set(e.getX()+1);
					boulderSound.play();

				}
			}
		}
	}

}
