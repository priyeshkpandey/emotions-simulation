package com.occ.common;

import java.text.NumberFormat;

import com.occ.entities.Emotion;
import com.occ.models.infra.Model;
import com.occ.rules.infra.ConstraintKeys;
import com.occ.rules.infra.Rule;

public class Evaluator {
	
	public static void evaluate(final Emotion emo, final Model model) {
		final Rule rule = emo.getRule();
	}
	
	private boolean evaluateConstraint(ConstraintKeys constraintKey, String value) throws Exception {
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

}
