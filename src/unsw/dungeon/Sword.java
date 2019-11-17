package unsw.dungeon;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sword extends Entity{
	private int durability; 

	
	public Sword(int x, int y) {
		super(x, y);
		this.setDurability(5);
		setPickupable(true);
	}

	public void attack() {
		this.setDurability(this.getDurability() - 1);

		Media sound = new Media(new File("sounds/sword_swing.mp3").toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();
	}

	public int getDurability() {
		return durability;
	}

	public void setDurability(int durability) {
		this.durability = durability;
	}
	
	
}
