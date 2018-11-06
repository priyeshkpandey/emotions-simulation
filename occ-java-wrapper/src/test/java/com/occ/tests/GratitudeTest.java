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

public class GratitudeTest extends BaseTest {
	
	private static final Double POSITIVE_VALUE = 0.3;
	private static final Double NEGATIVE_VALUE = -0.3;
	private static final Double ZERO_VALUE = 0.0;
	
	private Variable desirability;
	private Variable praiseworthiness;
	private Variable expectationDeviation;
	
	@BeforeClass
	public void setupBeforeClass(ITestContext context) {
		Rule rule = RulesBuilder.buildGratitudeRule();
		this.emotion = new Emotion("Gratitude", rule, THRESHOLD);
		this.model = (Model) context.getAttribute("model");
	}
	
	@AfterMethod
	public void setupAfterMethod() {
		this.model
		.remove(this.desirability)
		.remove(this.praiseworthiness)
		.remove(this.expectationDeviation);
		
		this.emotion.setIntensity(null);
		this.emotion.setPotential(null); 
	}
	
	@Test(description = "Test Gratitude emotion occurs for satisfying constraints")
	public void testGratitude() throws Exception {
		this.desirability = new Variable(VariableType.DESIRABILITY, POSITIVE_VALUE.toString());
		this.praiseworthiness = new Variable(VariableType.PRAISEWORTHINESS, POSITIVE_VALUE.toString());
		this.expectationDeviation = new Variable(VariableType.EXPECTATION_DEVIATION, POSITIVE_VALUE.toString());
		
		this.model
		.add(this.desirability)
		.add(this.praiseworthiness)
		.add(this.expectationDeviation);
		
		Assert.assertTrue(Evaluator.evaluate(this.emotion, this.model), "Emotion Gratitude didn't occur with the satisfying constraints");
		Assert.assertNotNull(this.emotion.getIntensity(), "Emotion intensity is null"); 
		Assert.assertTrue(this.emotion.getIntensity() > 0.0, "Emotion intensity is not greater than zero"); 
	}
	
	@Test(description = "Test Gratitude emotion occurs for zero expectation deviation")
	public void testGratitudeOccursZeroExpDeviation() throws Exception {
		this.desirability = new Variable(VariableType.DESIRABILITY, POSITIVE_VALUE.toString());
		this.praiseworthiness = new Variable(VariableType.PRAISEWORTHINESS, POSITIVE_VALUE.toString());
		this.expectationDeviation = new Variable(VariableType.EXPECTATION_DEVIATION, ZERO_VALUE.toString());
		
		this.model
		.add(this.desirability)
		.add(this.praiseworthiness)
		.add(this.expectationDeviation);
		
		Assert.assertTrue(Evaluator.evaluate(this.emotion, this.model), "Emotion Gratitude didn't occur with the satisfying constraints");
		Assert.assertNotNull(this.emotion.getIntensity(), "Emotion intensity is null"); 
		Assert.assertTrue(this.emotion.getIntensity() > 0.0, "Emotion intensity is not greater than zero"); 
	}
	
	@Test(description = "Test Gratitude emotion doesn't occur with negative desirability")
	public void testGratitudeNegativeDesirability() throws Exception {
		this.desirability = new Variable(VariableType.DESIRABILITY, NEGATIVE_VALUE.toString());
		this.praiseworthiness = new Variable(VariableType.PRAISEWORTHINESS, POSITIVE_VALUE.toString());
		this.expectationDeviation = new Variable(VariableType.EXPECTATION_DEVIATION, POSITIVE_VALUE.toString());
		
		this.model
		.add(this.desirability)
		.add(this.praiseworthiness)
		.add(this.expectationDeviation);
		
		Assert.assertFalse(Evaluator.evaluate(this.emotion, this.model), "Emotion Gratitude occurred");
		Assert.assertNull(this.emotion.getIntensity(), "Emotion intensity is not null");
	}
	
	@Test(description = "Test Gratitude emotion doesn't occur with negative praiseworthiness")
	public void testGratitudeNegativePraiseworthiness() throws Exception {
		this.desirability = new Variable(VariableType.DESIRABILITY, POSITIVE_VALUE.toString());
		this.praiseworthiness = new Variable(VariableType.PRAISEWORTHINESS, NEGATIVE_VALUE.toString());
		this.expectationDeviation = new Variable(VariableType.EXPECTATION_DEVIATION, POSITIVE_VALUE.toString());
		
		this.model
		.add(this.desirability)
		.add(this.praiseworthiness)
		.add(this.expectationDeviation);
		
		Assert.assertFalse(Evaluator.evaluate(this.emotion, this.model), "Emotion Gratitude occurred");
		Assert.assertNull(this.emotion.getIntensity(), "Emotion intensity is not null");
	}
	
	@Test(description = "Test Gratitude emotion doesn't occur with missing expectation deviation")
	public void testGratitudeMissingExpDeviation() throws Exception {
		this.desirability = new Variable(VariableType.DESIRABILITY, POSITIVE_VALUE.toString());
		this.praiseworthiness = new Variable(VariableType.PRAISEWORTHINESS, POSITIVE_VALUE.toString());
		this.expectationDeviation = new Variable(VariableType.EXPECTATION_DEVIATION, POSITIVE_VALUE.toString());
		
		this.model
		.add(this.desirability)
		.add(this.praiseworthiness);
		
		Assert.assertFalse(Evaluator.evaluate(this.emotion, this.model), "Emotion Gratitude occurred");
		Assert.assertNull(this.emotion.getIntensity(), "Emotion intensity is not null");
	}
	
	@Test(description = "Test Gratitude emotion doesn't occur with all negative variable values")
	public void testGratitudeAllNegative() throws Exception {
		this.desirability = new Variable(VariableType.DESIRABILITY, NEGATIVE_VALUE.toString());
		this.praiseworthiness = new Variable(VariableType.PRAISEWORTHINESS, NEGATIVE_VALUE.toString());
		this.expectationDeviation = new Variable(VariableType.EXPECTATION_DEVIATION, NEGATIVE_VALUE.toString());
		
		this.model
		.add(this.desirability)
		.add(this.praiseworthiness)
		.add(this.expectationDeviation);
		
		Assert.assertFalse(Evaluator.evaluate(this.emotion, this.model), "Emotion Gratitude occurred");
		Assert.assertNull(this.emotion.getIntensity(), "Emotion intensity is not null");
	}

}
