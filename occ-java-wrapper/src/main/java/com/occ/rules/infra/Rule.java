package com.occ.rules.infra;

public interface Rule {

	public boolean isNext();
	public boolean next();
	public boolean isPositive();
	public boolean isNeutral();
	public boolean isNegative();
	public boolean isEvaluate();
	public void evaluate();
	
}
