package unsw.dungeon;

import java.util.List;

public class AttackHandler implements AttackObserver{
	
	private Dungeon dungeon; 
	
	public AttackHandler(Dungeon dungeon) {
		this.dungeon = dungeon; 
	}
	
	
	public void update(int x, int y) {
	
		List<Enemy> enemies = dungeon.getEnemies();
	
		for (Enemy enemy : enemies) {
			if (enemy.getX() == x && enemy.getY() == y) {
				this.dungeon.removeEntity(enemy); 
			}	
		}
	}
}
