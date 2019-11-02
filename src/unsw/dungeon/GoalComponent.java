package unsw.dungeon;

public interface GoalComponent {

	public boolean getValue(); 
	public String getName();
	public boolean isChild(String name);
	public void setGoalToTrue(String name);
	public void setGoalToFalse(String name); 
	
}
