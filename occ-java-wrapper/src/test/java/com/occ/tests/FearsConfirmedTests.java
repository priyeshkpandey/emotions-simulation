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

public class FearsConfirmedTests extends BaseTests {
	
	private static final Double POSITIVE_VALUE = 0.3;
	private static final Double NEGATIVE_VALUE = -0.3;
	private static final Double ZERO_VALUE = 0.0;
	
	private Variable desirability;
	private Variable likelihood;
	private Variable realization;
	private Variable effort;
	
	@BeforeClass
	public void setupBeforeClass() {
		Rule rule = RulesBuilder.buildFearsConfirmedRule();
		this.emotion = new Emotion("FearsConfirmed", rule, THRESHOLD);
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
	
	@Test(description = "Test for the occurrence of FearsConfirmed Emotion")
	public void testFearsConfirmed() throws Exception {
		this.desirability = new Variable(VariableType.DESIRABILITY, NEGATIVE_VALUE.toString());
		this.likelihood = new Variable(VariableType.LIKELIHOOD, POSITIVE_VALUE.toString());
		this.realization = new Variable(VariableType.REALIZATION, POSITIVE_VALUE.toString());
		this.effort = new Variable(VariableType.EFFORT, POSITIVE_VALUE.toString());
		this.model
		.add(this.desirability)
		.add(likelihood)
		.add(this.realization)
		.add(this.effort);
		
		Assert.assertTrue(Evaluator.evaluate(this.emotion, this.model), "Emotion FearsConfirmed didn't occur with the satisfying constraints");
		Assert.assertNotNull(this.emotion.getIntensity(), "Emotion intensity is null"); 
		Assert.assertTrue(this.emotion.getIntensity() > 0.0, "Emotion intensity is not greater than zero"); 
	}
	
	@Test(description = "Emotion FearsConfirmed doesn't occur with positive desirabitliy")
	public void testFearsConfirmedPositiveDesirability() throws Exception {
		this.desirability = new Variable(VariableType.DESIRABILITY, POSITIVE_VALUE.toString());
		this.likelihood = new Variable(VariableType.LIKELIHOOD, POSITIVE_VALUE.toString());
		this.realization = new Variable(VariableType.REALIZATION, POSITIVE_VALUE.toString());
		this.effort = new Variable(VariableType.EFFORT, POSITIVE_VALUE.toString());
		this.model
		.add(this.desirability)
		.add(likelihood)
		.add(this.realization)
		.add(this.effort);
		
		Assert.assertFalse(Evaluator.evaluate(this.emotion, this.model), "Emotion FearsConfirmed occurred");
		Assert.assertNull(this.emotion.getIntensity(), "Emotion intensity is not null");
	}
	
	@Test(description = "Emotion FearsConfirmed doesn't occur with negative likelihood")
	public void testFearsConfirmedNegativeLikelihood() throws Exception {
		this.desirability = new Variable(VariableType.DESIRABILITY, NEGATIVE_VALUE.toString());
		this.likelihood = new Variable(VariableType.LIKELIHOOD, NEGATIVE_VALUE.toString());
		this.realization = new Variable(VariableType.REALIZATION, POSITIVE_VALUE.toString());
		this.effort = new Variable(VariableType.EFFORT, POSITIVE_VALUE.toString());
		this.model
		.add(this.desirability)
		.add(likelihood)
		.add(this.realization)
		.add(this.effort);
		
		Assert.assertFalse(Evaluator.evaluate(this.emotion, this.model), "Emotion FearsConfirmed occurred");
		Assert.assertNull(this.emotion.getIntensity(), "Emotion intensity is not null");
	}
	
	@Test(description = "Emotion FearsConfirmed doesn't occur with negative realization")
	public void testFearsConfirmedNegativeRealization() throws Exception {
		this.desirability = new Variable(VariableType.DESIRABILITY, NEGATIVE_VALUE.toString());
		this.likelihood = new Variable(VariableType.LIKELIHOOD, POSITIVE_VALUE.toString());
		this.realization = new Variable(VariableType.REALIZATION, NEGATIVE_VALUE.toString());
		this.effort = new Variable(VariableType.EFFORT, POSITIVE_VALUE.toString());
		this.model
		.add(this.desirability)
		.add(likelihood)
		.add(this.realization)
		.add(this.effort);
		
		Assert.assertFalse(Evaluator.evaluate(this.emotion, this.model), "Emotion FearsConfirmed occurred");
		Assert.assertNull(this.emotion.getIntensity(), "Emotion intensity is not null");
	}
	
	@Test(description = "Emotion FearsConfirmed does occur with zero effort")
	public void testFearsConfirmedZeroEffort() throws Exception {
		this.desirability = new Variable(VariableType.DESIRABILITY, NEGATIVE_VALUE.toString());
		this.likelihood = new Variable(VariableType.LIKELIHOOD, POSITIVE_VALUE.toString());
		this.realization = new Variable(VariableType.REALIZATION, POSITIVE_VALUE.toString());
		this.effort = new Variable(VariableType.EFFORT, ZERO_VALUE.toString());
		this.model
		.add(this.desirability)
		.add(likelihood)
		.add(this.realization)
		.add(this.effort);
		
		Assert.assertTrue(Evaluator.evaluate(this.emotion, this.model), "Emotion FearsConfirmed didn't occur with zero effort");
		Assert.assertNotNull(this.emotion.getIntensity(), "Emotion intensity is null"); 
		Assert.assertTrue(this.emotion.getIntensity() > 0.0, "Emotion intensity is not greater than zero"); 
	}
	
	@Test(description = "Emotion FearsConfirmed doesn't occur with missing effort")
	public void testFearsConfirmedMissingEffort() throws Exception {
		this.desirability = new Variable(VariableType.DESIRABILITY, NEGATIVE_VALUE.toString());
		this.likelihood = new Variable(VariableType.LIKELIHOOD, POSITIVE_VALUE.toString());
		this.realization = new Variable(VariableType.REALIZATION, POSITIVE_VALUE.toString());
		this.effort = new Variable(VariableType.EFFORT, POSITIVE_VALUE.toString());
		this.model
		.add(this.desirability)
		.add(likelihood)
		.add(this.realization);
		
		Assert.assertFalse(Evaluator.evaluate(this.emotion, this.model), "Emotion FearsConfirmed occurred");
		Assert.assertNull(this.emotion.getIntensity(), "Emotion intensity is not null");
	}
	
	@Test(description = "Emotion FearsConfirmed doesn't occur with all negative variables")
	public void testFearsConfirmedAllNegative() throws Exception {
		this.desirability = new Variable(VariableType.DESIRABILITY, NEGATIVE_VALUE.toString());
		this.likelihood = new Variable(VariableType.LIKELIHOOD, NEGATIVE_VALUE.toString());
		this.realization = new Variable(VariableType.REALIZATION, NEGATIVE_VALUE.toString());
		this.effort = new Variable(VariableType.EFFORT, NEGATIVE_VALUE.toString());
		this.model
		.add(this.desirability)
		.add(likelihood)
		.add(this.realization)
		.add(this.effort);
		
		Assert.assertFalse(Evaluator.evaluate(this.emotion, this.model), "Emotion FearsConfirmed occurred");
		Assert.assertNull(this.emotion.getIntensity(), "Emotion intensity is not null");
	}
	
	@Test(description = "Emotion FearsConfirmed doesn't occur with a missing variable")
	public void testFearsConfirmedMissingVariable() throws Exception {
		this.desirability = new Variable(VariableType.DESIRABILITY, NEGATIVE_VALUE.toString());
		this.likelihood = new Variable(VariableType.LIKELIHOOD, POSITIVE_VALUE.toString());
		this.realization = new Variable(VariableType.REALIZATION, POSITIVE_VALUE.toString());
		this.effort = new Variable(VariableType.EFFORT, POSITIVE_VALUE.toString());
		this.model
		.add(desirability)
		.add(this.realization)
		.add(this.effort);
		
		Assert.assertFalse(Evaluator.evaluate(this.emotion, this.model), "Emotion FearsConfirmed occurred");
		Assert.assertNull(this.emotion.getIntensity(), "Emotion intensity is not null");
	}

}
