package unsw.dungeon;

import java.util.List;

public class EnemyHandler implements MovementObserver {
	
	private Dungeon dungeon;
	
	public EnemyHandler(Dungeon dungeon) {
		this.dungeon = dungeon;
	}

	@Override
	public void update(int x, int y, Player player) {
		List<Enemy> enemies = dungeon.getEnemies();

		
		for (Enemy enemy : enemies) {
			if (enemy.getX() == player.getX() && enemy.getY() == player.getY()) this.dungeon.killPlayer();
			
			if (player.invincible()) enemy.changeState(new InvincibilityState(enemy));
			else enemy.changeState(new NormalState(enemy));
			
			if (!(enemy.hp() <= 0)) enemy.EnemyMovement(x, y, this.dungeon);
			else this.dungeon.removeEntity(enemy.getX(), enemy.getY()); // remove enemies with 0 hp
		}
		
	}

}
