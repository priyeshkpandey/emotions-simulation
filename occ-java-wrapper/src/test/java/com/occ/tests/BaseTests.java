package com.occ.tests;

import org.testng.annotations.BeforeSuite;

import com.occ.common.VariableType;
import com.occ.entities.Emotion;
import com.occ.entities.Variable;
import com.occ.models.infra.Model;

public abstract class BaseTests {
	
	protected Emotion emotion;
	protected Model model;
	protected static final Double THRESHOLD = 0.3;
	protected static final Double GLOBAL_VALUES = 0.5;
	
	@BeforeSuite
	public void setupBeforeSuite() {
		this.model = new Model();
		this.model
		.add(new Variable(VariableType.SENSE_OF_REALITY, GLOBAL_VALUES.toString()))
		.add(new Variable(VariableType.PROXIMITY, GLOBAL_VALUES.toString()))
		.add(new Variable(VariableType.UNEXPECTEDNESS, GLOBAL_VALUES.toString()))
		.add(new Variable(VariableType.AROUSAL, GLOBAL_VALUES.toString()));
	}

}
