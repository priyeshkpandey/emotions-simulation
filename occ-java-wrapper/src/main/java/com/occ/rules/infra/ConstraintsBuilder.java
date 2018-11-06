package com.occ.rules.infra;

public class ConstraintsBuilder {
	
	public static Constraints buildPresentConstraints() {
		Constraints constraints = new Constraints();
		constraints.fromEmpty()
		.add(ConstraintKeys.IS_PRESENT, true);
		return constraints;
	}
	
	public static Constraints buildPositiveConstraints() {
		Constraints positiveConstraints = new Constraints();
		positiveConstraints.fromEmpty()
		.add(ConstraintKeys.IS_PRESENT, true)
		.add(ConstraintKeys.IS_POSITIVE, true);
		return positiveConstraints;
	}
	
	public static Constraints buildNegativeConstraints() {
		Constraints negativeConstraints = new Constraints();
		negativeConstraints.fromEmpty()
		.add(ConstraintKeys.IS_PRESENT, true)
		.add(ConstraintKeys.IS_NEGATIVE, true);
		return negativeConstraints;
	}

}
