package unsw.dungeon;

import java.util.List;

public class GoalCheckingObserver implements MovementObserver{
	private Dungeon dungeon;
	private boolean treasureGoal;
	private boolean enemyGoal;
	
	public GoalCheckingObserver(Dungeon dungeon) {
		this.dungeon = dungeon;
		// since the once treasure or enemy is satisfied, it will always stay satisfied
		// so no need to always check all the conditions 
		this.treasureGoal = false;
		this.enemyGoal = false;
		
	}
	
	@Override
	public void update(int x, int y, Player player) {
		//checking all treasures are in player's inventory
		if(dungeon.getGoal().isTreasureGoal() && !this.treasureGoal) {
			List<Entity> entities = dungeon.getEntities();
			boolean tmp = true;
			for(Entity entity : entities) {
				if(entity instanceof Treasure) {
					tmp = false;
				}
			}
			if(tmp) {
				dungeon.getGoal().setTreasureGoal(true);
				// since the once the treasure goal is true it would always be true
				// so no need to check this value again
				this.treasureGoal = true;
			}
		}
		
		if(dungeon.getGoal().isBoulderGoal()) {
			List<Entity> entities = dungeon.getFloorSwitches();
			boolean tmp = true;
			for(Entity entity : entities) {
				if(!((FloorSwitch) entity).isActive()) {
					tmp = false;
				}
			}
			if(tmp) {
				dungeon.getGoal().setBoulderGoal(true);
			}else {
				dungeon.getGoal().setBoulderGoal(false);
			}
		}
		
		if(dungeon.getGoal().isEnemyGoal() && !this.enemyGoal) {
			List<Enemy> enemies = this.dungeon.getEnemies();
			if(enemies.size() == 0) {
				dungeon.getGoal().setEnemyGoal(true);
				this.enemyGoal = true;
			}
		}
		
		//player move to the square that contain exit and ready to exit
		if(dungeon.getGoal().isExitGoal() && this.dungeon.getEntity(x, y) instanceof Exit 
				&& dungeon.canPlayerExit()) {
			this.dungeon.setLevelCompleted(true);
		}
		// if the level don't have goal of exit and player is ready to exit
		// which mean the level should be completed
		else if(!dungeon.getGoal().isExitGoal() && dungeon.canPlayerExit()) {
			this.dungeon.setLevelCompleted(true);	
		}
	}

}
