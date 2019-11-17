package unsw.dungeon;

import java.io.File;
import java.util.ArrayList;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SwordHandler implements AttackObserver {
	
	private Dungeon dungeon;
	public SwordHandler(Dungeon dungeon) {
		this.dungeon = dungeon; 
	}
	
	
	public void update (int x, int y) {
		
		ArrayList<Entity> inventory = dungeon.getPlayerInventory(); 
		
		for (int i = 0; i < inventory.size(); i++) {
			if (inventory.get(i) instanceof Sword) {
				Sword sword = (Sword)inventory.get(i); 
				sword.attack();
				if (sword.getDurability() <= 0) {
					dungeon.getPlayerInventory().remove(sword);
					dungeon.getPlayer().setSword(false);
					Media sound = new Media(new File("sounds/sword_break.mp3").toURI().toString());
					MediaPlayer mediaPlayer = new MediaPlayer(sound);
					mediaPlayer.play();
				}
			}
		}

	}

	
}
