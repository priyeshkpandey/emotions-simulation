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

public class HappyForTests extends BaseTests {
	
	private static final Double POSITIVE_VALUE = 0.3;
	private static final Double NEGATIVE_VALUE = -0.3;
	
	private Variable desirability;
	private Variable deservingness;
	private Variable liking;
	private Variable desirabilityForOthers;
	
	@BeforeClass
	public void setupBeforeClass() {
		Rule rule = RulesBuilder.buildHappyForRule();
		this.emotion = new Emotion("HappyFor", rule, THRESHOLD);
	}
	
	@AfterMethod
	public void setupAfterMethod() {
		this.model
		.remove(this.desirability)
		.remove(this.deservingness)
		.remove(this.liking)
		.remove(this.desirabilityForOthers);
		
		this.emotion.setIntensity(null);
		this.emotion.setPotential(null); 
	}
	
	@Test(description = "Test for the occurrence of HappyFor Emotion")
	public void testHappyFor() throws Exception {
		this.desirability = new Variable(VariableType.DESIRABILITY, POSITIVE_VALUE.toString());
		this.deservingness = new Variable(VariableType.DESERVINGNESS, POSITIVE_VALUE.toString());
		this.liking = new Variable(VariableType.LIKING, POSITIVE_VALUE.toString());
		this.desirabilityForOthers = new Variable(VariableType.DESIRABILITY_FOR_OTHER, POSITIVE_VALUE.toString());
		this.model
		.add(this.desirability)
		.add(this.deservingness)
		.add(this.liking)
		.add(this.desirabilityForOthers);
		
		Assert.assertTrue(Evaluator.evaluate(this.emotion, this.model), "Emotion HappyFor didn't occur with the satisfying constraints");
		Assert.assertNotNull(this.emotion.getIntensity(), "Emotion intensity is null"); 
		Assert.assertTrue(this.emotion.getIntensity() > 0.0, "Emotion intensity is not greater than zero"); 
	}
	
	@Test(description = "Test that HappyFor emotion doesn't occur if desirability is negative")
	public void testHappyForNegativeDesirability() throws Exception {
		this.desirability = new Variable(VariableType.DESIRABILITY, NEGATIVE_VALUE.toString());
		this.deservingness = new Variable(VariableType.DESERVINGNESS, POSITIVE_VALUE.toString());
		this.liking = new Variable(VariableType.LIKING, POSITIVE_VALUE.toString());
		this.desirabilityForOthers = new Variable(VariableType.DESIRABILITY_FOR_OTHER, POSITIVE_VALUE.toString());
		this.model
		.add(this.desirability)
		.add(this.deservingness)
		.add(this.liking)
		.add(this.desirabilityForOthers);
		
		Assert.assertFalse(Evaluator.evaluate(this.emotion, this.model), "Emotion HappyFor occurred");
		Assert.assertNull(this.emotion.getIntensity(), "Emotion intensity is not null"); 
	}
	
	@Test(description = "Test that HappyFor emotion doesn't occur if deservingness is negative")
	public void testHappyForNegativeDeservingness() throws Exception {
		this.desirability = new Variable(VariableType.DESIRABILITY, POSITIVE_VALUE.toString());
		this.deservingness = new Variable(VariableType.DESERVINGNESS, NEGATIVE_VALUE.toString());
		this.liking = new Variable(VariableType.LIKING, POSITIVE_VALUE.toString());
		this.desirabilityForOthers = new Variable(VariableType.DESIRABILITY_FOR_OTHER, POSITIVE_VALUE.toString());
		this.model
		.add(this.desirability)
		.add(this.deservingness)
		.add(this.liking)
		.add(this.desirabilityForOthers);
		
		Assert.assertFalse(Evaluator.evaluate(this.emotion, this.model), "Emotion HappyFor occurred");
		Assert.assertNull(this.emotion.getIntensity(), "Emotion intensity is not null"); 
	}
	
	@Test(description = "Test that HappyFor emotion doesn't occur if liking is negative")
	public void testHappyForNegativeLiking() throws Exception {
		this.desirability = new Variable(VariableType.DESIRABILITY, POSITIVE_VALUE.toString());
		this.deservingness = new Variable(VariableType.DESERVINGNESS, POSITIVE_VALUE.toString());
		this.liking = new Variable(VariableType.LIKING, NEGATIVE_VALUE.toString());
		this.desirabilityForOthers = new Variable(VariableType.DESIRABILITY_FOR_OTHER, POSITIVE_VALUE.toString());
		this.model
		.add(this.desirability)
		.add(this.deservingness)
		.add(this.liking)
		.add(this.desirabilityForOthers);
		
		Assert.assertFalse(Evaluator.evaluate(this.emotion, this.model), "Emotion HappyFor occurred");
		Assert.assertNull(this.emotion.getIntensity(), "Emotion intensity is not null"); 
	}
	
	@Test(description = "Test that HappyFor emotion doesn't occur if 'desirability for others' is negative")
	public void testHappyForNegativeDesirabilityForOthers() throws Exception {
		this.desirability = new Variable(VariableType.DESIRABILITY, POSITIVE_VALUE.toString());
		this.deservingness = new Variable(VariableType.DESERVINGNESS, POSITIVE_VALUE.toString());
		this.liking = new Variable(VariableType.LIKING, POSITIVE_VALUE.toString());
		this.desirabilityForOthers = new Variable(VariableType.DESIRABILITY_FOR_OTHER, NEGATIVE_VALUE.toString());
		this.model
		.add(this.desirability)
		.add(this.deservingness)
		.add(this.liking)
		.add(this.desirabilityForOthers);
		
		Assert.assertFalse(Evaluator.evaluate(this.emotion, this.model), "Emotion HappyFor occurred");
		Assert.assertNull(this.emotion.getIntensity(), "Emotion intensity is not null"); 
	}
	
	@Test(description = "Test that HappyFor emotion doesn't occur if all variables are negative")
	public void testHappyForAllNegative() throws Exception {
		this.desirability = new Variable(VariableType.DESIRABILITY, NEGATIVE_VALUE.toString());
		this.deservingness = new Variable(VariableType.DESERVINGNESS, NEGATIVE_VALUE.toString());
		this.liking = new Variable(VariableType.LIKING, NEGATIVE_VALUE.toString());
		this.desirabilityForOthers = new Variable(VariableType.DESIRABILITY_FOR_OTHER, NEGATIVE_VALUE.toString());
		this.model
		.add(this.desirability)
		.add(this.deservingness)
		.add(this.liking)
		.add(this.desirabilityForOthers);
		
		Assert.assertFalse(Evaluator.evaluate(this.emotion, this.model), "Emotion HappyFor occurred");
		Assert.assertNull(this.emotion.getIntensity(), "Emotion intensity is not null"); 
	}
	
}
