package com.occ.rules.infra;

import com.occ.meta.VariableType;

public class RulesBuilder {
	
	private Rule buildGlobalVariablesPresentRule() {
		Constraints constraints = new Constraints();
		constraints.fromEmpty()
		.add(ConstraintKeys.IS_PRESENT, true);
		Rule rule = new Rule();
		rule.fromEmpty()
		.add(VariableType.UNEXPECTEDNESS, constraints)
		.add(VariableType.SENSE_OF_REALITY, constraints)
		.add(VariableType.AROUSAL, constraints)
		.add(VariableType.PROXIMITY, constraints);
		return rule;
	}
	
	public Rule buildHappyForRule() {
		Rule rule = buildGlobalVariablesPresentRule();
		Constraints constraints = new Constraints();
		constraints.fromEmpty()
		.add(ConstraintKeys.IS_PRESENT, true)
		.add(ConstraintKeys.IS_POSITIVE, true);
		rule
		.add(VariableType.DESERVINGNESS, constraints)
		.add(VariableType.LIKING, constraints)
		.add(VariableType.DESIRABILITY_FOR_OTHER, constraints);
		return rule;
	}
	
	public Rule buildSorryForRule() {
		Rule rule = buildGlobalVariablesPresentRule();
		Constraints constraints = new Constraints();
		constraints.fromEmpty()
		.add(ConstraintKeys.IS_PRESENT, true)
		.add(ConstraintKeys.IS_POSITIVE, true);
		rule
		.add(VariableType.LIKING, constraints);
		constraints.fromEmpty()
		.add(ConstraintKeys.IS_PRESENT, true)
		.add(ConstraintKeys.IS_NEGATIVE, true);
		rule
		.add(VariableType.DESERVINGNESS, constraints)
		.add(VariableType.DESIRABILITY_FOR_OTHER, constraints);
		return rule;
	}
	

}
