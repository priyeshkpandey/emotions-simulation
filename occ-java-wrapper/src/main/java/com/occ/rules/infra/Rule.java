package com.occ.rules.infra;

import java.util.HashMap;
import java.util.Map;

import com.occ.meta.VariableType;

public class Rule {
	
	private Map<VariableType, Constraints> rules;
	
	public Rule fromEmpty() {
		rules = new HashMap<VariableType, Constraints>();
		return this;
	}
	
	public Rule add(VariableType varType, Constraints constraints) {
		rules.put(varType, constraints);
		return this;
	}
	
	public Constraints getConstraintsForVariable(VariableType varType) {
		return rules.get(varType);
	}

}
