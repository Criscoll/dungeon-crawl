package unsw.dungeon;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

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
			if (e instanceof Sword) {
				player.setSword(true); // player now has a sword
				Media sound = new Media(new File("sounds/sword_draw.mp3").toURI().toString());
				MediaPlayer mediaPlayer = new MediaPlayer(sound);
				mediaPlayer.play();
				
			}
			if (e instanceof Potion) {
				player.setInvinicibility(false); // if the player is already invincible then the cooldown timer should reset
				player.setInvinicibility(true); // player is now invincible

				
			}
			
			if (e instanceof Treasure || e instanceof Key) {
				Media sound = new Media(new File("sounds/treasure_pickup.mp3").toURI().toString());
				MediaPlayer mediaPlayer = new MediaPlayer(sound);
				mediaPlayer.play();
			}
			
			dungeon.removeEntity(e);
			player.getInventory().add(e);		
			
		}
	}

}
