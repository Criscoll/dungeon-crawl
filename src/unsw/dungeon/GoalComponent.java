package unsw.dungeon;

public interface GoalComponent {

	public boolean getValue(); 
	public String getName();
	public boolean isChild(String name);
	public void setGoalToTrue(String name);
	public void setGoalToFalse(String name);
	public boolean isGoalTrue(String name);
	public boolean isBoulderGoal();
	public boolean isEnemyGoal();
	public boolean isExitGoal();
	public boolean isTreasureGoal(); 
	public boolean isBoulderGoalTrue();
	public boolean isEnemyGoalTrue();
	public boolean isExitGoalTrue();
	public boolean isTreasureGoalTrue(); 
	public void setTreasureGoal(boolean value);
	public void setBoulderGoal(boolean value);
	public void setEnemyGoal(boolean value);
	
}
