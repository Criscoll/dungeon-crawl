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
			for (int i = 1; i <= subGoals.size(); i++) {
				value = (value && subGoals.get(i).getValue());
			}
		}
		else if(this.operator.equals("OR")) {
			for (int i = 1; i <= subGoals.size(); i++) {
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


}
