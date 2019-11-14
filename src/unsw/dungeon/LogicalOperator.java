package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

public class LogicalOperator implements GoalComponent{

	private String operator;
	private List <GoalComponent> subGoals;
	
	public LogicalOperator(String operator) {
		this.operator = operator;
		subGoals = new ArrayList<>();
	}

	@Override
	public boolean getValue() {
		boolean value = subGoals.get(0).getValue();
		
		if(this.operator.equals("AND")) {
			for (int i = 1; i < subGoals.size(); i++) {
				value = (value && subGoals.get(i).getValue());
			}
		}
		else if(this.operator.equals("OR")) {
			for (int i = 1; i < subGoals.size(); i++) {
				value = (value || subGoals.get(i).getValue());
			}
		}
		
		return value;
	}

	@Override
	public String getName() {
		return operator;
	}

	@Override
	public boolean isChild(String name) {
		for(GoalComponent subGoal : subGoals) {
			if(subGoal.isChild(name))
				return true;
		}
		return false;
	}

	
	public void add(GoalComponent loadGoal) {
		subGoals.add(loadGoal);
	}

	@Override
	public void setGoalToTrue(String name) {
		for(GoalComponent subGoal : subGoals) {
			subGoal.setGoalToTrue(name);
		}
		
	}

	@Override
	public void setGoalToFalse(String name) {
		for(GoalComponent subGoal : subGoals) {
			subGoal.setGoalToFalse(name);
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
		String returnString = "Complete";
		if(this.operator.equals("AND")) {
			returnString += "\n";
			for (int i = 0; i < subGoals.size(); i++) {
				returnString += subGoals.get(i).toString() + "\n";
				if(i != subGoals.size()-1)
					returnString += "AND\n";
			}
		}
		else if(this.operator.equals("OR")) {
			returnString += " one of :\n";
			for (int i = 0; i < subGoals.size(); i++) {
				returnString += subGoals.get(i).toString() + "\n";
			}
		}
		return returnString;
		
	}

	@Override
	public boolean isBoulderGoalTrue() {
		return isGoalTrue("boulders");
	}

	@Override
	public boolean isEnemyGoalTrue() {
		return isGoalTrue("enemies");
	}

	@Override
	public boolean isExitGoalTrue() {
		return isGoalTrue("exit");
	}

	@Override
	public boolean isTreasureGoalTrue() {
		return isGoalTrue("treasure");
	}

	@Override
	public boolean isGoalTrue(String name) {
		boolean returnBoolean = false;
		for(GoalComponent subgoal : subGoals) {
			if(subgoal instanceof Goal && subgoal.isGoalTrue(name))
				returnBoolean = true;
			else if(subgoal instanceof LogicalOperator && subgoal.isGoalTrue(name))
				returnBoolean = true;
		}
		return returnBoolean;
	}
}
