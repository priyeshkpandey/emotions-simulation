package com.occ.rules.infra;

import java.util.List;

import com.occ.entities.Variable;
import com.occ.meta.VariableType;
import com.occ.models.infra.Model;

public abstract class AbstractRule implements Rule {

	protected static Model model;
	protected List<Variable> currentVariables;
	protected boolean isNode = false;
	protected boolean isEvaluate = false;
	protected boolean isSufficient = false;
	protected List<VariableType> rulePath;
	
	

}
