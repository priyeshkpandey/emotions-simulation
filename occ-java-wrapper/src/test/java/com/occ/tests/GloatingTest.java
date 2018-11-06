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

public class GloatingTest extends BaseTest {
	
	private static final Double POSITIVE_VALUE = 0.3;
	private static final Double NEGATIVE_VALUE = -0.3;
	
	private Variable desirability;
	private Variable deservingness;
	private Variable liking;
	private Variable desirabilityForOthers;
	
	@BeforeClass
	public void setupBeforeClass(ITestContext context) {
		Rule rule = RulesBuilder.buildGloatingRule();
		this.emotion = new Emotion("Gloating", rule, THRESHOLD);
		this.model = (Model) context.getAttribute("model");
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
	
	@Test(description = "Test for the occurrence of Gloating Emotion")
	public void testGloating() throws Exception {
		this.desirability = new Variable(VariableType.DESIRABILITY, POSITIVE_VALUE.toString());
		this.deservingness = new Variable(VariableType.DESERVINGNESS, POSITIVE_VALUE.toString());
		this.liking = new Variable(VariableType.LIKING, NEGATIVE_VALUE.toString());
		this.desirabilityForOthers = new Variable(VariableType.DESIRABILITY_FOR_OTHER, NEGATIVE_VALUE.toString());
		this.model
		.add(this.desirability)
		.add(this.deservingness)
		.add(this.liking)
		.add(this.desirabilityForOthers);
		
		Assert.assertTrue(Evaluator.evaluate(this.emotion, this.model), "Emotion Gloating didn't occur with the satisfying constraints");
		Assert.assertNotNull(this.emotion.getIntensity(), "Emotion intensity is null"); 
		Assert.assertTrue(this.emotion.getIntensity() > 0.0, "Emotion intensity is not greater than zero"); 
	}
	
	@Test(description = "Test that Gloating emotion doesn't occur if desirability is negative")
	public void testGloatingNegativeDesirability() throws Exception {
		this.desirability = new Variable(VariableType.DESIRABILITY, NEGATIVE_VALUE.toString());
		this.deservingness = new Variable(VariableType.DESERVINGNESS, POSITIVE_VALUE.toString());
		this.liking = new Variable(VariableType.LIKING, NEGATIVE_VALUE.toString());
		this.desirabilityForOthers = new Variable(VariableType.DESIRABILITY_FOR_OTHER, NEGATIVE_VALUE.toString());
		this.model
		.add(this.desirability)
		.add(this.deservingness)
		.add(this.liking)
		.add(this.desirabilityForOthers);
		
		Assert.assertFalse(Evaluator.evaluate(this.emotion, this.model), "Emotion Gloating occurred");
		Assert.assertNull(this.emotion.getIntensity(), "Emotion intensity is not null"); 
	}
	
	@Test(description = "Test that Gloating emotion doesn't occur if deservingness is negative")
	public void testGloatingNegativeDeservingness() throws Exception {
		this.desirability = new Variable(VariableType.DESIRABILITY, POSITIVE_VALUE.toString());
		this.deservingness = new Variable(VariableType.DESERVINGNESS, NEGATIVE_VALUE.toString());
		this.liking = new Variable(VariableType.LIKING, NEGATIVE_VALUE.toString());
		this.desirabilityForOthers = new Variable(VariableType.DESIRABILITY_FOR_OTHER, NEGATIVE_VALUE.toString());
		this.model
		.add(this.desirability)
		.add(this.deservingness)
		.add(this.liking)
		.add(this.desirabilityForOthers);
		
		Assert.assertFalse(Evaluator.evaluate(this.emotion, this.model), "Emotion Gloating occurred");
		Assert.assertNull(this.emotion.getIntensity(), "Emotion intensity is not null"); 
	}
	
	@Test(description = "Test that Gloating emotion doesn't occur if liking is positive")
	public void testGloatingPositiveLiking() throws Exception {
		this.desirability = new Variable(VariableType.DESIRABILITY, POSITIVE_VALUE.toString());
		this.deservingness = new Variable(VariableType.DESERVINGNESS, POSITIVE_VALUE.toString());
		this.liking = new Variable(VariableType.LIKING, POSITIVE_VALUE.toString());
		this.desirabilityForOthers = new Variable(VariableType.DESIRABILITY_FOR_OTHER, NEGATIVE_VALUE.toString());
		this.model
		.add(this.desirability)
		.add(this.deservingness)
		.add(this.liking)
		.add(this.desirabilityForOthers);
		
		Assert.assertFalse(Evaluator.evaluate(this.emotion, this.model), "Emotion Gloating occurred");
		Assert.assertNull(this.emotion.getIntensity(), "Emotion intensity is not null"); 
	}
	
	@Test(description = "Test that Gloating emotion doesn't occur if 'desirability for others' is positive")
	public void testGloatingPositiveDesirabilityForOthers() throws Exception {
		this.desirability = new Variable(VariableType.DESIRABILITY, POSITIVE_VALUE.toString());
		this.deservingness = new Variable(VariableType.DESERVINGNESS, POSITIVE_VALUE.toString());
		this.liking = new Variable(VariableType.LIKING, NEGATIVE_VALUE.toString());
		this.desirabilityForOthers = new Variable(VariableType.DESIRABILITY_FOR_OTHER, POSITIVE_VALUE.toString());
		this.model
		.add(this.desirability)
		.add(this.deservingness)
		.add(this.liking)
		.add(this.desirabilityForOthers);
		
		Assert.assertFalse(Evaluator.evaluate(this.emotion, this.model), "Emotion Gloating occurred");
		Assert.assertNull(this.emotion.getIntensity(), "Emotion intensity is not null"); 
	}
	
	@Test(description = "Test that Gloating emotion doesn't occur if all variables are positive")
	public void testGloatingAllPositive() throws Exception {
		this.desirability = new Variable(VariableType.DESIRABILITY, POSITIVE_VALUE.toString());
		this.deservingness = new Variable(VariableType.DESERVINGNESS, POSITIVE_VALUE.toString());
		this.liking = new Variable(VariableType.LIKING, POSITIVE_VALUE.toString());
		this.desirabilityForOthers = new Variable(VariableType.DESIRABILITY_FOR_OTHER, POSITIVE_VALUE.toString());
		this.model
		.add(this.desirability)
		.add(this.deservingness)
		.add(this.liking)
		.add(this.desirabilityForOthers);
		
		Assert.assertFalse(Evaluator.evaluate(this.emotion, this.model), "Emotion Gloating occurred");
		Assert.assertNull(this.emotion.getIntensity(), "Emotion intensity is not null"); 
	}
	
	@Test(description = "Test that Gloating emotion doesn't occur if any variable is missing")
	public void testGloatingMissingVariable() throws Exception {
		this.desirability = new Variable(VariableType.DESIRABILITY, POSITIVE_VALUE.toString());
		this.deservingness = new Variable(VariableType.DESERVINGNESS, POSITIVE_VALUE.toString());
		this.liking = new Variable(VariableType.LIKING, NEGATIVE_VALUE.toString());
		this.desirabilityForOthers = new Variable(VariableType.DESIRABILITY_FOR_OTHER, NEGATIVE_VALUE.toString());
		this.model
		.add(desirability)
		.add(this.deservingness)
		.add(this.liking);
		
		Assert.assertFalse(Evaluator.evaluate(this.emotion, this.model), "Emotion Gloating occurred");
		Assert.assertNull(this.emotion.getIntensity(), "Emotion intensity is not null"); 
	}

}
