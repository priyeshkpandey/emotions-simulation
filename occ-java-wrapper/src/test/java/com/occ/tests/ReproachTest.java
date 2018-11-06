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

public class ReproachTest extends BaseTest {
	
	private static final Double POSITIVE_VALUE = 0.3;
	private static final Double NEGATIVE_VALUE = -0.3;
	private static final Double ZERO_VALUE = 0.0;
	
	private Variable praiseworthiness;
	private Variable expectationDeviation;
	
	@BeforeClass
	public void setupBeforeClass(ITestContext context) {
		Rule rule = RulesBuilder.buildReproachRule();
		this.emotion = new Emotion("Reproach", rule, THRESHOLD);
		this.model = (Model) context.getAttribute("model");
	}
	
	@AfterMethod
	public void setupAfterMethod() {
		this.model
		.remove(this.praiseworthiness)
		.remove(this.expectationDeviation);
		
		this.emotion.setIntensity(null);
		this.emotion.setPotential(null); 
	}
	
	@Test(description = "Test Reproach emotion occurs for satisfying constraints")
	public void testReproach() throws Exception {
		this.praiseworthiness = new Variable(VariableType.PRAISEWORTHINESS, NEGATIVE_VALUE.toString());
		this.expectationDeviation = new Variable(VariableType.EXPECTATION_DEVIATION, POSITIVE_VALUE.toString());
		
		this.model
		.add(this.praiseworthiness)
		.add(this.expectationDeviation);
		
		Assert.assertTrue(Evaluator.evaluate(this.emotion, this.model), "Emotion Reproach didn't occur with the satisfying constraints");
		Assert.assertNotNull(this.emotion.getIntensity(), "Emotion intensity is null"); 
		Assert.assertTrue(this.emotion.getIntensity() > 0.0, "Emotion intensity is not greater than zero"); 
	}
	
	@Test(description = "Test Reproach emotion occurs for zero expectation deviation")
	public void testReproachOccursZeroExpDeviation() throws Exception {
		this.praiseworthiness = new Variable(VariableType.PRAISEWORTHINESS, NEGATIVE_VALUE.toString());
		this.expectationDeviation = new Variable(VariableType.EXPECTATION_DEVIATION, ZERO_VALUE.toString());
		
		this.model
		.add(this.praiseworthiness)
		.add(this.expectationDeviation);
		
		Assert.assertTrue(Evaluator.evaluate(this.emotion, this.model), "Emotion Reproach didn't occur with the satisfying constraints");
		Assert.assertNotNull(this.emotion.getIntensity(), "Emotion intensity is null"); 
		Assert.assertTrue(this.emotion.getIntensity() > 0.0, "Emotion intensity is not greater than zero"); 
	}
	
	@Test(description = "Test Reproach emotion doesn't occur with positive praiseworthiness")
	public void testReproachPositivePraiseworthiness() throws Exception {
		this.praiseworthiness = new Variable(VariableType.PRAISEWORTHINESS, POSITIVE_VALUE.toString());
		this.expectationDeviation = new Variable(VariableType.EXPECTATION_DEVIATION, POSITIVE_VALUE.toString());
		
		this.model
		.add(this.praiseworthiness)
		.add(this.expectationDeviation);
		
		Assert.assertFalse(Evaluator.evaluate(this.emotion, this.model), "Emotion Reproach occurred");
		Assert.assertNull(this.emotion.getIntensity(), "Emotion intensity is not null");
	}
	
	@Test(description = "Test Reproach emotion doesn't occur with missing expectation deviation")
	public void testReproachMissingExpDeviation() throws Exception {
		this.praiseworthiness = new Variable(VariableType.PRAISEWORTHINESS, NEGATIVE_VALUE.toString());
		this.expectationDeviation = new Variable(VariableType.EXPECTATION_DEVIATION, POSITIVE_VALUE.toString());
		
		this.model
		.add(this.praiseworthiness);
		
		Assert.assertFalse(Evaluator.evaluate(this.emotion, this.model), "Emotion Reproach occurred");
		Assert.assertNull(this.emotion.getIntensity(), "Emotion intensity is not null");
	}
	
	@Test(description = "Test Reproach emotion doesn't occur with all negative variable values")
	public void testReproachAllNegative() throws Exception {
		this.praiseworthiness = new Variable(VariableType.PRAISEWORTHINESS, NEGATIVE_VALUE.toString());
		this.expectationDeviation = new Variable(VariableType.EXPECTATION_DEVIATION, NEGATIVE_VALUE.toString());
		
		this.model
		.add(this.praiseworthiness)
		.add(this.expectationDeviation);
		
		Assert.assertFalse(Evaluator.evaluate(this.emotion, this.model), "Emotion Reproach occurred");
		Assert.assertNull(this.emotion.getIntensity(), "Emotion intensity is not null");
	}

}
