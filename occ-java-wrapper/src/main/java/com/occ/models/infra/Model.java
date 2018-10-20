package com.occ.models.infra;

import java.util.HashSet;
import java.util.Set;

import com.occ.entities.Variable;

public class Model {
	
	private Set<Variable> variables = new HashSet<Variable>();
	
	public Model add(Variable variable) {
		variables.add(variable);
		return this;
	}
	
	public Set<Variable> getVariables() {
		return this.variables;
	}

}
