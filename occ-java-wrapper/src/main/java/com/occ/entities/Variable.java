package com.occ.entities;

import com.occ.common.VariableType;

public class Variable {
	
	private VariableType varType;
	private String value;
	
	public Variable(VariableType varType, String value) {
		this.varType = varType;
		this.value = value;
	}
	
	public VariableType getVarType() {
		return varType;
	}

	public void setVarType(VariableType varType) {
		this.varType = varType;
	}

	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

}
