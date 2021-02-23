package unsw.dungeon;

import java.io.File;
import java.util.List;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class EnemyHandler implements MovementObserver {
	
	private Dungeon dungeon;
	
	public EnemyHandler(Dungeon dungeon) {
		this.dungeon = dungeon;
	}

	@Override
	public void update(int x, int y, Player player) {
		List<Enemy> enemies = dungeon.getEnemies();

		
		for (Enemy enemy : enemies) {
			if(enemy instanceof Hound) continue;
			if (player.invincible()) enemy.changeState(new InvincibilityState(enemy));
			else enemy.changeState(new NormalState(enemy));
			
			if (!(enemy.hp() <= 0)) enemy.EnemyMovement(x, y, this.dungeon);
			else {
				dungeon.removeEntity(enemy);
			}
		}
		
	}
	
}