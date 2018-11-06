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

public class ResentmentTest extends BaseTest {
	
	private static final Double POSITIVE_VALUE = 0.3;
	private static final Double NEGATIVE_VALUE = -0.3;
	
	private Variable desirability;
	private Variable deservingness;
	private Variable liking;
	private Variable desirabilityForOthers;
	
	@BeforeClass
	public void setupBeforeClass(ITestContext context) {
		Rule rule = RulesBuilder.buildResentmentRule();
		this.emotion = new Emotion("Resentment", rule, THRESHOLD);
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
	
	@Test(description = "Test for the occurrence of Resentment Emotion")
	public void testResentment() throws Exception {
		this.desirability = new Variable(VariableType.DESIRABILITY, NEGATIVE_VALUE.toString());
		this.deservingness = new Variable(VariableType.DESERVINGNESS, NEGATIVE_VALUE.toString());
		this.liking = new Variable(VariableType.LIKING, NEGATIVE_VALUE.toString());
		this.desirabilityForOthers = new Variable(VariableType.DESIRABILITY_FOR_OTHER, POSITIVE_VALUE.toString());
		this.model
		.add(this.desirability)
		.add(this.deservingness)
		.add(this.liking)
		.add(this.desirabilityForOthers);
		
		Assert.assertTrue(Evaluator.evaluate(this.emotion, this.model), "Emotion Resentment didn't occur with the satisfying constraints");
		Assert.assertNotNull(this.emotion.getIntensity(), "Emotion intensity is null"); 
		Assert.assertTrue(this.emotion.getIntensity() > 0.0, "Emotion intensity is not greater than zero"); 
	}
	
	@Test(description = "Test that Resentment emotion doesn't occur if desirability is positive")
	public void testResentmentPositiveDesirability() throws Exception {
		this.desirability = new Variable(VariableType.DESIRABILITY, POSITIVE_VALUE.toString());
		this.deservingness = new Variable(VariableType.DESERVINGNESS, NEGATIVE_VALUE.toString());
		this.liking = new Variable(VariableType.LIKING, NEGATIVE_VALUE.toString());
		this.desirabilityForOthers = new Variable(VariableType.DESIRABILITY_FOR_OTHER, POSITIVE_VALUE.toString());
		this.model
		.add(this.desirability)
		.add(this.deservingness)
		.add(this.liking)
		.add(this.desirabilityForOthers);
		
		Assert.assertFalse(Evaluator.evaluate(this.emotion, this.model), "Emotion Resentment occurred");
		Assert.assertNull(this.emotion.getIntensity(), "Emotion intensity is not null"); 
	}
	
	@Test(description = "Test that Resentment emotion doesn't occur if deservingness is positive")
	public void testResentmentPositiveDeservingness() throws Exception {
		this.desirability = new Variable(VariableType.DESIRABILITY, NEGATIVE_VALUE.toString());
		this.deservingness = new Variable(VariableType.DESERVINGNESS, POSITIVE_VALUE.toString());
		this.liking = new Variable(VariableType.LIKING, NEGATIVE_VALUE.toString());
		this.desirabilityForOthers = new Variable(VariableType.DESIRABILITY_FOR_OTHER, POSITIVE_VALUE.toString());
		this.model
		.add(this.desirability)
		.add(this.deservingness)
		.add(this.liking)
		.add(this.desirabilityForOthers);
		
		Assert.assertFalse(Evaluator.evaluate(this.emotion, this.model), "Emotion Resentment occurred");
		Assert.assertNull(this.emotion.getIntensity(), "Emotion intensity is not null"); 
	}
	
	@Test(description = "Test that Resentment emotion doesn't occur if liking is positive")
	public void testResentmentPositiveLiking() throws Exception {
		this.desirability = new Variable(VariableType.DESIRABILITY, NEGATIVE_VALUE.toString());
		this.deservingness = new Variable(VariableType.DESERVINGNESS, NEGATIVE_VALUE.toString());
		this.liking = new Variable(VariableType.LIKING, POSITIVE_VALUE.toString());
		this.desirabilityForOthers = new Variable(VariableType.DESIRABILITY_FOR_OTHER, POSITIVE_VALUE.toString());
		this.model
		.add(this.desirability)
		.add(this.deservingness)
		.add(this.liking)
		.add(this.desirabilityForOthers);
		
		Assert.assertFalse(Evaluator.evaluate(this.emotion, this.model), "Emotion Resentment occurred");
		Assert.assertNull(this.emotion.getIntensity(), "Emotion intensity is not null"); 
	}
	
	@Test(description = "Test that Resentment emotion doesn't occur if 'desirability for others' is negative")
	public void testResentmentNegativeDesirabilityForOthers() throws Exception {
		this.desirability = new Variable(VariableType.DESIRABILITY, NEGATIVE_VALUE.toString());
		this.deservingness = new Variable(VariableType.DESERVINGNESS, NEGATIVE_VALUE.toString());
		this.liking = new Variable(VariableType.LIKING, NEGATIVE_VALUE.toString());
		this.desirabilityForOthers = new Variable(VariableType.DESIRABILITY_FOR_OTHER, NEGATIVE_VALUE.toString());
		this.model
		.add(this.desirability)
		.add(this.deservingness)
		.add(this.liking)
		.add(this.desirabilityForOthers);
		
		Assert.assertFalse(Evaluator.evaluate(this.emotion, this.model), "Emotion Resentment occurred");
		Assert.assertNull(this.emotion.getIntensity(), "Emotion intensity is not null"); 
	}
	
	@Test(description = "Test that Resentment emotion doesn't occur if all variables are positive")
	public void testResentmentAllPositive() throws Exception {
		this.desirability = new Variable(VariableType.DESIRABILITY, POSITIVE_VALUE.toString());
		this.deservingness = new Variable(VariableType.DESERVINGNESS, POSITIVE_VALUE.toString());
		this.liking = new Variable(VariableType.LIKING, POSITIVE_VALUE.toString());
		this.desirabilityForOthers = new Variable(VariableType.DESIRABILITY_FOR_OTHER, POSITIVE_VALUE.toString());
		this.model
		.add(this.desirability)
		.add(this.deservingness)
		.add(this.liking)
		.add(this.desirabilityForOthers);
		
		Assert.assertFalse(Evaluator.evaluate(this.emotion, this.model), "Emotion Resentment occurred");
		Assert.assertNull(this.emotion.getIntensity(), "Emotion intensity is not null"); 
	}
	
	@Test(description = "Test that Resentment emotion doesn't occur if any variable is missing")
	public void testResentmentMissingVariable() throws Exception {
		this.desirability = new Variable(VariableType.DESIRABILITY, NEGATIVE_VALUE.toString());
		this.deservingness = new Variable(VariableType.DESERVINGNESS, NEGATIVE_VALUE.toString());
		this.liking = new Variable(VariableType.LIKING, NEGATIVE_VALUE.toString());
		this.desirabilityForOthers = new Variable(VariableType.DESIRABILITY_FOR_OTHER, POSITIVE_VALUE.toString());
		this.model
		.add(this.desirability)
		.add(this.liking)
		.add(this.desirabilityForOthers);
		
		Assert.assertFalse(Evaluator.evaluate(this.emotion, this.model), "Emotion Resentment occurred");
		Assert.assertNull(this.emotion.getIntensity(), "Emotion intensity is not null"); 
	}

}
