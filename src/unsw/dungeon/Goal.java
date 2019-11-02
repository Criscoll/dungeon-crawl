package unsw.dungeon;

public class Goal implements GoalComponent{

	private String goal;
	private boolean value;
	
	public Goal(String goal) {
		this.goal = goal;
		this.value = false;
		if(goal.equals("exit")) {
			this.value = true;
		}
	}

	@Override
	public boolean getValue() {
		return value;
	}

	@Override
	public String getName() {
		return goal;
	}

	@Override
	public boolean isChild(String name) {
		if(this.goal.equals(name))
			return true;
		return false;
	}

	@Override
	public void setGoalToTrue(String name) {
		if(goal.equals(name)) {
			this.value = true;
		}
	}

	@Override
	public void setGoalToFalse(String name) {
		if(goal.equals(name)) {
			this.value = false;
		}	
	}

}
