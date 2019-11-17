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

	@Override
	public boolean isBoulderGoal() {
		return isChild("boulders");
	}

	@Override
	public boolean isEnemyGoal() {
		return isChild("enemies");	
	}

	@Override
	public boolean isExitGoal() {
		return isChild("exit");	
	}

	@Override
	public boolean isTreasureGoal() {
		return isChild("treasure");	
	}

	@Override
	public String toString() {
		String goal = "";
		if(this.goal.equals("treasure"))
			goal = "Collecting all treasure";
		if(this.goal.equals("enemies"))
			goal = "Destroying all enemies";
		if(this.goal.equals("boulders"))
			goal = " Having a boulder on all floor switches";
		if(this.goal.equals("exit"))
			goal = "Getting to an exit";
		return goal;
	}

	@Override
	public boolean isBoulderGoalTrue() {
		if(this.isBoulderGoal())	
			return this.value;
		return false;
	}

	@Override
	public boolean isEnemyGoalTrue() {
		if(this.isEnemyGoal())	
			return this.value;
		return false;
	}

	@Override
	public boolean isExitGoalTrue() {
		if(this.isExitGoal())	
			return this.value;
		return false;
	}

	@Override
	public boolean isTreasureGoalTrue() {
		if(this.isTreasureGoal())	
			return this.value;
		return false;
	}

	@Override
	public boolean isGoalTrue(String name) {
		if(isChild(name))
			return this.value;
		return false;
	}

	@Override
	public void setTreasureGoal(boolean value) {
		if(value)
			setGoalToTrue("treasure");
		else
			setGoalToFalse("treasure");
	}

	@Override
	public void setBoulderGoal(boolean value) {
		if(value)
			setGoalToTrue("boulders");
		else
			setGoalToFalse("boulders");
	}
	
	@Override
	public void setEnemyGoal(boolean value) {
		if(value)
			setGoalToTrue("enemies");
		else
			setGoalToFalse("enemies");	
	}

}
