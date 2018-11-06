package com.occ.tests;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.occ.common.Evaluator;
import com.occ.common.VariableType;
import com.occ.entities.Emotion;
import com.occ.entities.Variable;
import com.occ.models.infra.Model;
import com.occ.rules.infra.Rule;
import com.occ.rules.infra.RulesBuilder;

public class SatisfactionTest extends BaseTest {
	
	private static final Double POSITIVE_VALUE = 0.3;
	private static final Double NEGATIVE_VALUE = -0.3;
	private static final Double ZERO_VALUE = 0.0;
	
	private Variable desirability;
	private Variable likelihood;
	private Variable realization;
	private Variable effort;
	
	@BeforeClass
	public void setupBeforeClass(ITestContext context) {
		Rule rule = RulesBuilder.buildSatisfactionRule();
		this.emotion = new Emotion("Satisfaction", rule, THRESHOLD);
		this.model = (Model) context.getAttribute("model");
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
	
	@Test(description = "Emotion Satisfaction doesn't occur with negative desirabitliy")
	public void testSatisfactionNegativeDesirability() throws Exception {
		this.desirability = new Variable(VariableType.DESIRABILITY, NEGATIVE_VALUE.toString());
		this.likelihood = new Variable(VariableType.LIKELIHOOD, POSITIVE_VALUE.toString());
		this.realization = new Variable(VariableType.REALIZATION, POSITIVE_VALUE.toString());
		this.effort = new Variable(VariableType.EFFORT, POSITIVE_VALUE.toString());
		this.model
		.add(this.desirability)
		.add(likelihood)
		.add(this.realization)
		.add(this.effort);
		
		Assert.assertFalse(Evaluator.evaluate(this.emotion, this.model), "Emotion Satisfaction occurred");
		Assert.assertNull(this.emotion.getIntensity(), "Emotion intensity is not null");
	}
	
	@Test(description = "Emotion Satisfaction doesn't occur with negative likelihood")
	public void testSatisfactionNegativeLikelihood() throws Exception {
		this.desirability = new Variable(VariableType.DESIRABILITY, POSITIVE_VALUE.toString());
		this.likelihood = new Variable(VariableType.LIKELIHOOD, NEGATIVE_VALUE.toString());
		this.realization = new Variable(VariableType.REALIZATION, POSITIVE_VALUE.toString());
		this.effort = new Variable(VariableType.EFFORT, POSITIVE_VALUE.toString());
		this.model
		.add(this.desirability)
		.add(likelihood)
		.add(this.realization)
		.add(this.effort);
		
		Assert.assertFalse(Evaluator.evaluate(this.emotion, this.model), "Emotion Satisfaction occurred");
		Assert.assertNull(this.emotion.getIntensity(), "Emotion intensity is not null");
	}
	
	@Test(description = "Emotion Satisfaction doesn't occur with negative realization")
	public void testSatisfactionNegativeRealization() throws Exception {
		this.desirability = new Variable(VariableType.DESIRABILITY, POSITIVE_VALUE.toString());
		this.likelihood = new Variable(VariableType.LIKELIHOOD, POSITIVE_VALUE.toString());
		this.realization = new Variable(VariableType.REALIZATION, NEGATIVE_VALUE.toString());
		this.effort = new Variable(VariableType.EFFORT, POSITIVE_VALUE.toString());
		this.model
		.add(this.desirability)
		.add(likelihood)
		.add(this.realization)
		.add(this.effort);
		
		Assert.assertFalse(Evaluator.evaluate(this.emotion, this.model), "Emotion Satisfaction occurred");
		Assert.assertNull(this.emotion.getIntensity(), "Emotion intensity is not null");
	}
	
	@Test(description = "Emotion Satisfaction does occur with zero effort")
	public void testSatisfactionZeroEffort() throws Exception {
		this.desirability = new Variable(VariableType.DESIRABILITY, POSITIVE_VALUE.toString());
		this.likelihood = new Variable(VariableType.LIKELIHOOD, POSITIVE_VALUE.toString());
		this.realization = new Variable(VariableType.REALIZATION, POSITIVE_VALUE.toString());
		this.effort = new Variable(VariableType.EFFORT, ZERO_VALUE.toString());
		this.model
		.add(this.desirability)
		.add(likelihood)
		.add(this.realization)
		.add(this.effort);
		
		Assert.assertTrue(Evaluator.evaluate(this.emotion, this.model), "Emotion Satisfaction didn't occur with zero effort");
		Assert.assertNotNull(this.emotion.getIntensity(), "Emotion intensity is null"); 
		Assert.assertTrue(this.emotion.getIntensity() > 0.0, "Emotion intensity is not greater than zero"); 
	}
	
	@Test(description = "Emotion Satisfaction doesn't occur with missing effort")
	public void testSatisfactionMissingEffort() throws Exception {
		this.desirability = new Variable(VariableType.DESIRABILITY, POSITIVE_VALUE.toString());
		this.likelihood = new Variable(VariableType.LIKELIHOOD, POSITIVE_VALUE.toString());
		this.realization = new Variable(VariableType.REALIZATION, POSITIVE_VALUE.toString());
		this.effort = new Variable(VariableType.EFFORT, POSITIVE_VALUE.toString());
		this.model
		.add(this.desirability)
		.add(likelihood)
		.add(this.realization);
		
		Assert.assertFalse(Evaluator.evaluate(this.emotion, this.model), "Emotion Satisfaction occurred");
		Assert.assertNull(this.emotion.getIntensity(), "Emotion intensity is not null");
	}
	
	@Test(description = "Emotion Satisfaction doesn't occur with all negative variables")
	public void testSatisfactionAllNegative() throws Exception {
		this.desirability = new Variable(VariableType.DESIRABILITY, NEGATIVE_VALUE.toString());
		this.likelihood = new Variable(VariableType.LIKELIHOOD, NEGATIVE_VALUE.toString());
		this.realization = new Variable(VariableType.REALIZATION, NEGATIVE_VALUE.toString());
		this.effort = new Variable(VariableType.EFFORT, NEGATIVE_VALUE.toString());
		this.model
		.add(this.desirability)
		.add(likelihood)
		.add(this.realization)
		.add(this.effort);
		
		Assert.assertFalse(Evaluator.evaluate(this.emotion, this.model), "Emotion Satisfaction occurred");
		Assert.assertNull(this.emotion.getIntensity(), "Emotion intensity is not null");
	}
	
	@Test(description = "Emotion Satisfaction doesn't occur with a missing variable")
	public void testSatisfactionMissingVariable() throws Exception {
		this.desirability = new Variable(VariableType.DESIRABILITY, POSITIVE_VALUE.toString());
		this.likelihood = new Variable(VariableType.LIKELIHOOD, POSITIVE_VALUE.toString());
		this.realization = new Variable(VariableType.REALIZATION, POSITIVE_VALUE.toString());
		this.effort = new Variable(VariableType.EFFORT, POSITIVE_VALUE.toString());
		this.model
		.add(likelihood)
		.add(this.realization)
		.add(this.effort);
		
		Assert.assertFalse(Evaluator.evaluate(this.emotion, this.model), "Emotion Satisfaction occurred");
		Assert.assertNull(this.emotion.getIntensity(), "Emotion intensity is not null");
	}

}
