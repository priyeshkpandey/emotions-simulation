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

public class RemorseTest extends BaseTest {
	
	private static final Double POSITIVE_VALUE = 0.3;
	private static final Double NEGATIVE_VALUE = -0.3;
	private static final Double ZERO_VALUE = 0.0;
	
	private Variable desirability;
	private Variable praiseworthiness;
	private Variable strenghtOfCognitiveUnit;
	private Variable expectationDeviation;
	
	@BeforeClass
	public void setupBeforeClass(ITestContext context) {
		Rule rule = RulesBuilder.buildRemorseRule();
		this.emotion = new Emotion("Remorse", rule, THRESHOLD);
		this.model = (Model) context.getAttribute("model");
	}
	
	@AfterMethod
	public void setupAfterMethod() {
		this.model
		.remove(this.desirability)
		.remove(this.praiseworthiness)
		.remove(this.strenghtOfCognitiveUnit)
		.remove(this.expectationDeviation);
		
		this.emotion.setIntensity(null);
		this.emotion.setPotential(null); 
	}
	
	@Test(description = "Test Remorse emotion occurs for satisfying constraints")
	public void testRemorse() throws Exception {
		this.desirability = new Variable(VariableType.DESIRABILITY, NEGATIVE_VALUE.toString());
		this.praiseworthiness = new Variable(VariableType.PRAISEWORTHINESS, NEGATIVE_VALUE.toString());
		this.strenghtOfCognitiveUnit = new Variable(VariableType.STRENGTH_OF_COG_UNIT, POSITIVE_VALUE.toString());
		this.expectationDeviation = new Variable(VariableType.EXPECTATION_DEVIATION, POSITIVE_VALUE.toString());
		
		this.model
		.add(this.desirability)
		.add(this.praiseworthiness)
		.add(this.strenghtOfCognitiveUnit)
		.add(this.expectationDeviation);
		
		Assert.assertTrue(Evaluator.evaluate(this.emotion, this.model), "Emotion Remorse didn't occur with the satisfying constraints");
		Assert.assertNotNull(this.emotion.getIntensity(), "Emotion intensity is null"); 
		Assert.assertTrue(this.emotion.getIntensity() > 0.0, "Emotion intensity is not greater than zero"); 
	}
	
	@Test(description = "Test Remorse emotion occurs for zero expectation deviation")
	public void testRemorseOccursZeroExpDeviation() throws Exception {
		this.desirability = new Variable(VariableType.DESIRABILITY, NEGATIVE_VALUE.toString());
		this.praiseworthiness = new Variable(VariableType.PRAISEWORTHINESS, NEGATIVE_VALUE.toString());
		this.strenghtOfCognitiveUnit = new Variable(VariableType.STRENGTH_OF_COG_UNIT, POSITIVE_VALUE.toString());
		this.expectationDeviation = new Variable(VariableType.EXPECTATION_DEVIATION, ZERO_VALUE.toString());
		
		this.model
		.add(this.desirability)
		.add(this.praiseworthiness)
		.add(this.strenghtOfCognitiveUnit)
		.add(this.expectationDeviation);
		
		Assert.assertTrue(Evaluator.evaluate(this.emotion, this.model), "Emotion Remorse didn't occur with the satisfying constraints");
		Assert.assertNotNull(this.emotion.getIntensity(), "Emotion intensity is null"); 
		Assert.assertTrue(this.emotion.getIntensity() > 0.0, "Emotion intensity is not greater than zero"); 
	}
	
	@Test(description = "Test Remorse emotion doesn't occur with positive desirability")
	public void testRemorsePositiveDesirability() throws Exception {
		this.desirability = new Variable(VariableType.DESIRABILITY, POSITIVE_VALUE.toString());
		this.praiseworthiness = new Variable(VariableType.PRAISEWORTHINESS, NEGATIVE_VALUE.toString());
		this.strenghtOfCognitiveUnit = new Variable(VariableType.STRENGTH_OF_COG_UNIT, POSITIVE_VALUE.toString());
		this.expectationDeviation = new Variable(VariableType.EXPECTATION_DEVIATION, POSITIVE_VALUE.toString());
		
		this.model
		.add(this.desirability)
		.add(this.praiseworthiness)
		.add(this.strenghtOfCognitiveUnit)
		.add(this.expectationDeviation);
		
		Assert.assertFalse(Evaluator.evaluate(this.emotion, this.model), "Emotion Remorse occurred");
		Assert.assertNull(this.emotion.getIntensity(), "Emotion intensity is not null");
	}
	
	@Test(description = "Test Remorse emotion doesn't occur with positive praiseworthiness")
	public void testRemorsePositivePraiseworthiness() throws Exception {
		this.desirability = new Variable(VariableType.DESIRABILITY, NEGATIVE_VALUE.toString());
		this.praiseworthiness = new Variable(VariableType.PRAISEWORTHINESS, POSITIVE_VALUE.toString());
		this.strenghtOfCognitiveUnit = new Variable(VariableType.STRENGTH_OF_COG_UNIT, POSITIVE_VALUE.toString());
		this.expectationDeviation = new Variable(VariableType.EXPECTATION_DEVIATION, POSITIVE_VALUE.toString());
		
		this.model
		.add(this.desirability)
		.add(this.praiseworthiness)
		.add(this.strenghtOfCognitiveUnit)
		.add(this.expectationDeviation);
		
		Assert.assertFalse(Evaluator.evaluate(this.emotion, this.model), "Emotion Remorse occurred");
		Assert.assertNull(this.emotion.getIntensity(), "Emotion intensity is not null");
	}
	
	@Test(description = "Test Remorse emotion doesn't occur with negative strength of cognitive unit")
	public void testRemorseNegativeCogUnit() throws Exception {
		this.desirability = new Variable(VariableType.DESIRABILITY, NEGATIVE_VALUE.toString());
		this.praiseworthiness = new Variable(VariableType.PRAISEWORTHINESS, NEGATIVE_VALUE.toString());
		this.strenghtOfCognitiveUnit = new Variable(VariableType.STRENGTH_OF_COG_UNIT, NEGATIVE_VALUE.toString());
		this.expectationDeviation = new Variable(VariableType.EXPECTATION_DEVIATION, POSITIVE_VALUE.toString());
		
		this.model
		.add(this.desirability)
		.add(this.praiseworthiness)
		.add(this.strenghtOfCognitiveUnit)
		.add(this.expectationDeviation);
		
		Assert.assertFalse(Evaluator.evaluate(this.emotion, this.model), "Emotion Remorse occurred");
		Assert.assertNull(this.emotion.getIntensity(), "Emotion intensity is not null");
	}
	
	@Test(description = "Test Remorse emotion doesn't occur with missing expectation deviation")
	public void testRemorseMissingExpDeviation() throws Exception {
		this.desirability = new Variable(VariableType.DESIRABILITY, NEGATIVE_VALUE.toString());
		this.praiseworthiness = new Variable(VariableType.PRAISEWORTHINESS, NEGATIVE_VALUE.toString());
		this.strenghtOfCognitiveUnit = new Variable(VariableType.STRENGTH_OF_COG_UNIT, POSITIVE_VALUE.toString());
		this.expectationDeviation = new Variable(VariableType.EXPECTATION_DEVIATION, POSITIVE_VALUE.toString());
		
		this.model
		.add(this.desirability)
		.add(this.praiseworthiness)
		.add(this.strenghtOfCognitiveUnit);
		
		Assert.assertFalse(Evaluator.evaluate(this.emotion, this.model), "Emotion Remorse occurred");
		Assert.assertNull(this.emotion.getIntensity(), "Emotion intensity is not null");
	}
	
	@Test(description = "Test Remorse emotion doesn't occur with all negative variable values")
	public void testRemorseAllNegative() throws Exception {
		this.desirability = new Variable(VariableType.DESIRABILITY, NEGATIVE_VALUE.toString());
		this.praiseworthiness = new Variable(VariableType.PRAISEWORTHINESS, NEGATIVE_VALUE.toString());
		this.strenghtOfCognitiveUnit = new Variable(VariableType.STRENGTH_OF_COG_UNIT, NEGATIVE_VALUE.toString());
		this.expectationDeviation = new Variable(VariableType.EXPECTATION_DEVIATION, NEGATIVE_VALUE.toString());
		
		this.model
		.add(this.desirability)
		.add(this.praiseworthiness)
		.add(this.strenghtOfCognitiveUnit)
		.add(this.expectationDeviation);
		
		Assert.assertFalse(Evaluator.evaluate(this.emotion, this.model), "Emotion Remorse occurred");
		Assert.assertNull(this.emotion.getIntensity(), "Emotion intensity is not null");
	}

}
