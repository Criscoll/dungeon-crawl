package unsw.dungeon;

public abstract class EnemyState {
	
	protected Enemy enemy; 
	
	public EnemyState(Enemy enemy) {
		this.enemy = enemy; 
	}

	public abstract void move(int pX, int pY, Dungeon dungeon); 
	
}
