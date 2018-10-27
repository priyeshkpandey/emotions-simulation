package com.occ.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.occ.common.Evaluator;
import com.occ.common.VariableType;
import com.occ.entities.Emotion;
import com.occ.entities.Variable;
import com.occ.rules.infra.Rule;
import com.occ.rules.infra.RulesBuilder;

public class SatisfactionTests extends BaseTests {
	
	private static final Double POSITIVE_VALUE = 0.3;
	private static final Double NEGATIVE_VALUE = -0.3;
	private static final Double ZERO_VALUE = 0.0;
	
	private Variable desirability;
	private Variable likelihood;
	private Variable realization;
	private Variable effort;
	
	@BeforeClass
	public void setupBeforeClass() {
		Rule rule = RulesBuilder.buildSatisfactionRule();
		this.emotion = new Emotion("Satisfaction", rule, THRESHOLD);
	}
	
	@AfterMethod
	public void setupAfterMethod() {
		this.model
		.remove(this.desirability)
		.remove(this.likelihood)
		.remove(this.realization)
		.remove(this.effort);
		
		this.emotion.setIntensity(null);
		this.emotion.setPotential(null); 
	}
	
	@Test(description = "Test for the occurrence of Satisfaction Emotion")
	public void testSatisfaction() throws Exception {
		this.desirability = new Variable(VariableType.DESIRABILITY, POSITIVE_VALUE.toString());
		this.likelihood = new Variable(VariableType.LIKELIHOOD, POSITIVE_VALUE.toString());
		this.realization = new Variable(VariableType.REALIZATION, POSITIVE_VALUE.toString());
		this.effort = new Variable(VariableType.EFFORT, POSITIVE_VALUE.toString());
		this.model
		.add(this.desirability)
		.add(likelihood)
		.add(this.realization)
		.add(this.effort);
		
		Assert.assertTrue(Evaluator.evaluate(this.emotion, this.model), "Emotion Satisfaction didn't occur with the satisfying constraints");
		Assert.assertNotNull(this.emotion.getIntensity(), "Emotion intensity is null"); 
		Assert.assertTrue(this.emotion.getIntensity() > 0.0, "Emotion intensity is not greater than zero"); 
	}
	
	public void testSatisfactionNegativeDesirability() throws Exception {
		
	}
	
	public void testSatisfactionNegativeLikelihood() throws Exception {
		
	}
	
	public void testSatisfactionNegativeRealization() throws Exception {
		
	}

}
