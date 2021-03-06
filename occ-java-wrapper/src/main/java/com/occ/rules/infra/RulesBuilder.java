package com.occ.rules.infra;

import com.occ.common.VariableType;

public class RulesBuilder {
	
	private static Rule buildGlobalVariablesPresentRule() {
		Constraints presentConstraints = ConstraintsBuilder.buildPresentConstraints();
		Rule rule = new Rule();
		rule.fromEmpty()
		.add(VariableType.UNEXPECTEDNESS, presentConstraints)
		.add(VariableType.SENSE_OF_REALITY, presentConstraints)
		.add(VariableType.AROUSAL, presentConstraints)
		.add(VariableType.PROXIMITY, presentConstraints);
		return rule;
	}
	
	public static Rule buildHappyForRule() {
		Rule rule = buildGlobalVariablesPresentRule();
		Constraints positiveConstraints = ConstraintsBuilder.buildPositiveConstraints();
		rule
		.add(VariableType.DESIRABILITY, positiveConstraints)
		.add(VariableType.DESERVINGNESS, positiveConstraints)
		.add(VariableType.LIKING, positiveConstraints)
		.add(VariableType.DESIRABILITY_FOR_OTHER, positiveConstraints);
		return rule;
	}
	
	public static Rule buildResentmentRule() {
		Rule rule = buildGlobalVariablesPresentRule();
		Constraints positiveConstraints = ConstraintsBuilder.buildPositiveConstraints();
		Constraints negativeConstraints = ConstraintsBuilder.buildNegativeConstraints();
		rule
		.add(VariableType.DESIRABILITY, negativeConstraints)
		.add(VariableType.DESERVINGNESS, negativeConstraints)
		.add(VariableType.LIKING, negativeConstraints)
		.add(VariableType.DESIRABILITY_FOR_OTHER, positiveConstraints);
		return rule;
	}
	
	public static Rule buildSorryForRule() {
		Rule rule = buildGlobalVariablesPresentRule();
		Constraints positiveConstraints = ConstraintsBuilder.buildPositiveConstraints();
		Constraints negativeConstraints = ConstraintsBuilder.buildNegativeConstraints();
		
		rule
		.add(VariableType.DESIRABILITY, negativeConstraints)
		.add(VariableType.LIKING, positiveConstraints)
		.add(VariableType.DESERVINGNESS, negativeConstraints)
		.add(VariableType.DESIRABILITY_FOR_OTHER, negativeConstraints);
		return rule;
	}
	
	public static Rule buildGloatingRule() {
		Rule rule = buildGlobalVariablesPresentRule();
		Constraints positiveConstraints = ConstraintsBuilder.buildPositiveConstraints();
		Constraints negativeConstraints = ConstraintsBuilder.buildNegativeConstraints();
		
		rule
		.add(VariableType.DESIRABILITY, positiveConstraints)
		.add(VariableType.LIKING, negativeConstraints)
		.add(VariableType.DESERVINGNESS, positiveConstraints)
		.add(VariableType.DESIRABILITY_FOR_OTHER, negativeConstraints);
		return rule;
	}
	
	public static Rule buildHopeRule() {
		Rule rule = buildGlobalVariablesPresentRule();
		Constraints positiveConstraints = ConstraintsBuilder.buildPositiveConstraints();
		
		rule
		.add(VariableType.DESIRABILITY, positiveConstraints)
		.add(VariableType.LIKELIHOOD, positiveConstraints);
		return rule;
	}
	
	public static Rule buildFearRule() {
		Rule rule = buildGlobalVariablesPresentRule();
		Constraints positiveConstraints = ConstraintsBuilder.buildPositiveConstraints();
		Constraints negativeConstraints = ConstraintsBuilder.buildNegativeConstraints();
		
		rule
		.add(VariableType.DESIRABILITY, negativeConstraints)
		.add(VariableType.LIKELIHOOD, positiveConstraints);
		return rule;
	}
	
	public static Rule buildSatisfactionRule() {
		Rule rule = buildGlobalVariablesPresentRule();
		Constraints positiveConstraints = ConstraintsBuilder.buildPositiveConstraints();
		Constraints presentConstraints = ConstraintsBuilder.buildPresentConstraints();
		
		rule
		.add(VariableType.DESIRABILITY, positiveConstraints)
		.add(VariableType.LIKELIHOOD, positiveConstraints)
		.add(VariableType.REALIZATION, positiveConstraints)
		.add(VariableType.EFFORT, presentConstraints);
		return rule;
	}
	
	public static Rule buildFearsConfirmedRule() {
		Rule rule = buildGlobalVariablesPresentRule();
		Constraints positiveConstraints = ConstraintsBuilder.buildPositiveConstraints();
		Constraints negativeConstraints = ConstraintsBuilder.buildNegativeConstraints();
		Constraints presentConstraints = ConstraintsBuilder.buildPresentConstraints();
		
		rule
		.add(VariableType.DESIRABILITY, negativeConstraints)
		.add(VariableType.LIKELIHOOD, positiveConstraints)
		.add(VariableType.REALIZATION, positiveConstraints)
		.add(VariableType.EFFORT, presentConstraints);
		return rule;
	}
	
	public static Rule buildReliefRule() {
		Rule rule = buildGlobalVariablesPresentRule();
		Constraints positiveConstraints = ConstraintsBuilder.buildPositiveConstraints();
		Constraints negativeConstraints = ConstraintsBuilder.buildNegativeConstraints();
		Constraints presentConstraints = ConstraintsBuilder.buildPresentConstraints();
		
		rule
		.add(VariableType.DESIRABILITY, negativeConstraints)
		.add(VariableType.LIKELIHOOD, positiveConstraints)
		.add(VariableType.REALIZATION, negativeConstraints)
		.add(VariableType.EFFORT, presentConstraints);
		return rule;
	}
	
	public static Rule buildDisappointmentRule() {
		Rule rule = buildGlobalVariablesPresentRule();
		Constraints positiveConstraints = ConstraintsBuilder.buildPositiveConstraints();
		Constraints negativeConstraints = ConstraintsBuilder.buildNegativeConstraints();
		Constraints presentConstraints = ConstraintsBuilder.buildPresentConstraints();
		
		rule
		.add(VariableType.DESIRABILITY, positiveConstraints)
		.add(VariableType.LIKELIHOOD, positiveConstraints)
		.add(VariableType.REALIZATION, negativeConstraints)
		.add(VariableType.EFFORT, presentConstraints);
		return rule;
	}
	
	public static Rule buildJoyRule() {
		Rule rule = buildGlobalVariablesPresentRule();
		Constraints positiveConstraints = ConstraintsBuilder.buildPositiveConstraints();
		
		rule
		.add(VariableType.DESIRABILITY, positiveConstraints);
		return rule;
	}
	
	public static Rule buildDistressRule() {
		Rule rule = buildGlobalVariablesPresentRule();
		Constraints negativeConstraints = ConstraintsBuilder.buildNegativeConstraints();
		
		rule
		.add(VariableType.DESIRABILITY, negativeConstraints);
		return rule;
	}
	
	public static Rule buildPrideRule() {
		Rule rule = buildGlobalVariablesPresentRule();
		Constraints positiveConstraints = ConstraintsBuilder.buildPositiveConstraints();
		Constraints presentConstraints = ConstraintsBuilder.buildPresentConstraints();
		
		rule
		.add(VariableType.PRAISEWORTHINESS, positiveConstraints)
		.add(VariableType.STRENGTH_OF_COG_UNIT, positiveConstraints)
		.add(VariableType.EXPECTATION_DEVIATION, presentConstraints);
		return rule;
	}
	
	public static Rule buildShameRule() {
		Rule rule = buildGlobalVariablesPresentRule();
		Constraints positiveConstraints = ConstraintsBuilder.buildPositiveConstraints();
		Constraints negativeConstraints = ConstraintsBuilder.buildNegativeConstraints();
		Constraints presentConstraints = ConstraintsBuilder.buildPresentConstraints();
		
		rule
		.add(VariableType.PRAISEWORTHINESS, negativeConstraints)
		.add(VariableType.STRENGTH_OF_COG_UNIT, positiveConstraints)
		.add(VariableType.EXPECTATION_DEVIATION, presentConstraints);
		return rule;
	}
	
	public static Rule buildAdmirationRule() {
		Rule rule = buildGlobalVariablesPresentRule();
		Constraints positiveConstraints = ConstraintsBuilder.buildPositiveConstraints();
		
		rule
		.add(VariableType.PRAISEWORTHINESS, positiveConstraints)
		.add(VariableType.EXPECTATION_DEVIATION, positiveConstraints);
		return rule;
	}
	
	public static Rule buildReproachRule() {
		Rule rule = buildGlobalVariablesPresentRule();
		Constraints negativeConstraints = ConstraintsBuilder.buildNegativeConstraints();
		Constraints positiveConstraints = ConstraintsBuilder.buildPositiveConstraints();
		
		rule
		.add(VariableType.PRAISEWORTHINESS, negativeConstraints)
		.add(VariableType.EXPECTATION_DEVIATION, positiveConstraints);
		return rule;
	}
	
	public static Rule buildGratificationRule() {
		Rule rule = buildGlobalVariablesPresentRule();
		Constraints positiveConstraints = ConstraintsBuilder.buildPositiveConstraints();
		
		rule
		.add(VariableType.DESIRABILITY, positiveConstraints)
		.add(VariableType.PRAISEWORTHINESS, positiveConstraints)
		.add(VariableType.STRENGTH_OF_COG_UNIT, positiveConstraints)
		.add(VariableType.EXPECTATION_DEVIATION, positiveConstraints);
		return rule;
	}
	
	public static Rule buildRemorseRule() {
		Rule rule = buildGlobalVariablesPresentRule();
		Constraints positiveConstraints = ConstraintsBuilder.buildPositiveConstraints();
		Constraints negativeConstraints = ConstraintsBuilder.buildNegativeConstraints();
		
		rule
		.add(VariableType.DESIRABILITY, negativeConstraints)
		.add(VariableType.PRAISEWORTHINESS, negativeConstraints)
		.add(VariableType.STRENGTH_OF_COG_UNIT, positiveConstraints)
		.add(VariableType.EXPECTATION_DEVIATION, positiveConstraints);
		return rule;
	}
	
	public static Rule buildGratitudeRule() {
		Rule rule = buildGlobalVariablesPresentRule();
		Constraints positiveConstraints = ConstraintsBuilder.buildPositiveConstraints();
		
		rule
		.add(VariableType.DESIRABILITY, positiveConstraints)
		.add(VariableType.PRAISEWORTHINESS, positiveConstraints)
		.add(VariableType.EXPECTATION_DEVIATION, positiveConstraints);
		return rule;
	}
	
	public static Rule buildAngerRule() {
		Rule rule = buildGlobalVariablesPresentRule();
		Constraints negativeConstraints = ConstraintsBuilder.buildNegativeConstraints();
		Constraints positiveConstraints = ConstraintsBuilder.buildPositiveConstraints();
		
		rule
		.add(VariableType.DESIRABILITY, negativeConstraints)
		.add(VariableType.PRAISEWORTHINESS, negativeConstraints)
		.add(VariableType.EXPECTATION_DEVIATION, positiveConstraints);
		return rule;
	}
	
	public static Rule buildLoveRule() {
		Rule rule = buildGlobalVariablesPresentRule();
		Constraints positiveConstraints = ConstraintsBuilder.buildPositiveConstraints();
		
		rule
		.add(VariableType.APPEALINGNESS, positiveConstraints)
		.add(VariableType.FAMILIARITY, positiveConstraints);
		return rule;
	}
	
	public static Rule buildHateRule() {
		Rule rule = buildGlobalVariablesPresentRule();
		Constraints negativeConstraints = ConstraintsBuilder.buildNegativeConstraints();
		Constraints positiveConstraints = ConstraintsBuilder.buildPositiveConstraints();
		
		rule
		.add(VariableType.APPEALINGNESS, negativeConstraints)
		.add(VariableType.FAMILIARITY, positiveConstraints);
		return rule;
	}

}
