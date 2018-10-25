package com.occ.common;

import java.text.NumberFormat;

import com.occ.entities.Emotion;
import com.occ.entities.Variable;
import com.occ.models.infra.Model;
import com.occ.rules.infra.ConstraintKeys;
import com.occ.rules.infra.Constraints;
import com.occ.rules.infra.Rule;

public class Evaluator {
	
	public static boolean evaluate(final Emotion emo, final Model model) throws Exception {
		if (evaluateModelConstraints(emo, model)) {
			return evaluateEmotion(emo, model);
		}
		return false;
	}
	
	private static boolean evaluateModelConstraints(final Emotion emo, final Model model) throws Exception {
		final Rule rule = emo.getRule();
		boolean localConstraintEvaluation = false;
		for (VariableType ruleVariableType : rule.getAtomicRules().keySet()) {
			Constraints constraintsForVariable = rule.getConstraintsForVariable(ruleVariableType);
			Variable variable = model.getVariableOfType(ruleVariableType);
			for (ConstraintKeys constraintKey : ConstraintKeys.values()) {
				Boolean constraintValue = constraintsForVariable.getConstraintValue(constraintKey);
				if (null != constraintValue && constraintValue) {
					localConstraintEvaluation = evaluateConstraint(constraintKey, variable.getValue());
				}
				
				if (!localConstraintEvaluation) {
					return localConstraintEvaluation;
				}
			}
		}
		return true;
	}
	
	private static boolean evaluateConstraint(ConstraintKeys constraintKey, String value) throws Exception {
		NumberFormat numberFormat = NumberFormat.getInstance();
		switch(constraintKey) {
		case IS_NEGATIVE:
			return (numberFormat.parse(value).doubleValue() < 0.0);
		case IS_POSITIVE:
			return (numberFormat.parse(value).doubleValue() > 0.0);
		case IS_PRESENT:
			return (null != value && !value.trim().equals(""));
		case IS_ZERO:
			return (numberFormat.parse(value).doubleValue() == 0.0);
		default:
			return false;
		}
	}
	
	private static boolean evaluateEmotion(final Emotion emo, final Model model) throws Exception {
		final Rule rule = emo.getRule();
		int numOfVariables = rule.getAtomicRules().keySet().size();
		double potential = 0.0;
		NumberFormat numberFormat = NumberFormat.getInstance();
		for (VariableType ruleVariableType : rule.getAtomicRules().keySet()) {
			Variable variable = model.getVariableOfType(ruleVariableType); 
			potential += Math.abs(numberFormat.parse(variable.getValue()).doubleValue());
		}
		potential = potential/numOfVariables;
		emo.setPotential(potential);
		double threshold = emo.getThreshold();
		emo.setIntensity(potential - threshold);
		return true;
	}
	
	

}
