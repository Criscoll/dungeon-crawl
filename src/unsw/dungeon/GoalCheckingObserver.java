package unsw.dungeon;

import java.util.List;

public class GoalCheckingObserver implements MovementObserver{
	private Dungeon dungeon;
	private boolean treasureGoalExist;
	private boolean enemyGoalExist;
	private boolean switchGoalExist;
	private boolean treasureGoal;
	private boolean enemyGoal;
	private boolean exitGoalExist;
	
	public GoalCheckingObserver(Dungeon dungeon) {
		this.dungeon = dungeon;
		this.treasureGoal = false;
		this.enemyGoal = false;
		
		if(dungeon.getGoal().isChild("treasure")) 
			treasureGoalExist = true;
		if(dungeon.getGoal().isChild("enemies")) 
			enemyGoalExist = true;
		if(dungeon.getGoal().isChild("boulders")) 
			switchGoalExist = true;
		if(dungeon.getGoal().isChild("exit")) 
			exitGoalExist = true;
		
	}
	
	@Override
	public void update(int x, int y, Player player) {
		//checking all treasures are in player's inventory
		if(this.treasureGoalExist && !this.treasureGoal) {
			List<Entity> entities = dungeon.getEntities();
			boolean tmp = true;
			for(Entity entity : entities) {
				if(entity instanceof Treasure) {
					tmp = false;
				}
			}
			if(tmp) {
				dungeon.getGoal().setGoalToTrue("treasure");
				// since the once the treasure goal is true it would always be true
				// so no need to check this value again
				this.treasureGoal = true;
			}
		}
		
		if(this.switchGoalExist) {
			List<Entity> entities = dungeon.getFloorSwitches();
			boolean tmp = true;
			for(Entity entity : entities) {
				if(!((FloorSwitch) entity).isActive()) {
					tmp = false;
				}
			}
			if(tmp) {
				dungeon.getGoal().setGoalToTrue("boulders");
			}else {
				dungeon.getGoal().setGoalToFalse("boulders");
			}
		}
		
		if(this.enemyGoalExist && !this.enemyGoal) {
			List<Enemy> enemies = this.dungeon.getEnemies();
			if(enemies.size() == 0) {
				dungeon.getGoal().setGoalToTrue("enemies");	
				this.enemyGoal = true;
			}
		}
		
//		System.out.println(dungeon.canPlayerExit());
		if(exitGoalExist && this.dungeon.getEntity(x, y) instanceof Exit 
				&& dungeon.canPlayerExit()) {
			this.dungeon.setLevelCompleted(true);
		}else if(!exitGoalExist && dungeon.canPlayerExit()) {
			this.dungeon.setLevelCompleted(true);	
		}
//		System.out.println("Level= " + dungeon.isLevelCompleted());
		
	}

}
