package com.occ.rules.infra;

import java.util.HashMap;
import java.util.Map;

public class Constraints {
	
	private Map<ConstraintKeys, Boolean> constraints;
	
	public Constraints fromEmpty() {
		constraints = new HashMap<ConstraintKeys, Boolean>();
		return this;
	}
	
	public Constraints add(ConstraintKeys constraint, Boolean value) {
		constraints.put(constraint, value);
		return this;
	}
	
	public Boolean getConstraintValue(ConstraintKeys constraint) {
		return constraints.get(constraint);
	}

}
