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

public class LoveTest extends BaseTest {
	
	private static final Double POSITIVE_VALUE = 0.3;
	private static final Double NEGATIVE_VALUE = -0.3;
	private static final Double ZERO_VALUE = 0.0;
	
	private Variable appealingness;
	private Variable familiarity;
	
	@BeforeClass
	public void setupBeforeClass(ITestContext context) {
		Rule rule = RulesBuilder.buildLoveRule();
		this.emotion = new Emotion("Love", rule, THRESHOLD);
		this.model = (Model) context.getAttribute("model");
	}
	
	@AfterMethod
	public void setupAfterMethod() {
		this.model
		.remove(this.appealingness)
		.remove(this.familiarity);
		
		this.emotion.setIntensity(null);
		this.emotion.setPotential(null); 
	}
	
	@Test(description = "Test the emotion Love occurs with satisfying constraints")
	public void testLove() throws Exception {
		this.appealingness = new Variable(VariableType.APPEALINGNESS, POSITIVE_VALUE.toString());
		this.familiarity = new Variable(VariableType.FAMILIARITY, POSITIVE_VALUE.toString());
		
		this.model
		.add(this.appealingness)
		.add(this.familiarity);
		
		Assert.assertTrue(Evaluator.evaluate(this.emotion, this.model), "Emotion Love didn't occur with the satisfying constraints");
		Assert.assertNotNull(this.emotion.getIntensity(), "Emotion intensity is null"); 
		Assert.assertTrue(this.emotion.getIntensity() > 0.0, "Emotion intensity is not greater than zero");
	}
	
	@Test(description = "Test the emotion Love occurs with zero familiarity")
	public void testLoveOccursZeroFamiliarity() throws Exception {
		this.appealingness = new Variable(VariableType.APPEALINGNESS, POSITIVE_VALUE.toString());
		this.familiarity = new Variable(VariableType.FAMILIARITY, ZERO_VALUE.toString());
		
		this.model
		.add(this.appealingness)
		.add(this.familiarity);
		
		Assert.assertTrue(Evaluator.evaluate(this.emotion, this.model), "Emotion Love didn't occur with the satisfying constraints");
		Assert.assertNotNull(this.emotion.getIntensity(), "Emotion intensity is null"); 
		Assert.assertTrue(this.emotion.getIntensity() > 0.0, "Emotion intensity is not greater than zero");
	}
	
	@Test(description = "Test the emotion Love doesn't occur with negative appealingness")
	public void testLoveNegativeAppealingness() throws Exception {
		this.appealingness = new Variable(VariableType.APPEALINGNESS, NEGATIVE_VALUE.toString());
		this.familiarity = new Variable(VariableType.FAMILIARITY, POSITIVE_VALUE.toString());
		
		this.model
		.add(this.appealingness)
		.add(this.familiarity);
		
		Assert.assertFalse(Evaluator.evaluate(this.emotion, this.model), "Emotion Love occurred");
		Assert.assertNull(this.emotion.getIntensity(), "Emotion intensity is not null");
	}
	
	@Test(description = "Test the emotion Love doesn't occur with negative familiarity")
	public void testLoveNegativeFamiliarity() throws Exception {
		this.appealingness = new Variable(VariableType.APPEALINGNESS, POSITIVE_VALUE.toString());
		this.familiarity = new Variable(VariableType.FAMILIARITY, NEGATIVE_VALUE.toString());
		
		this.model
		.add(this.appealingness)
		.add(this.familiarity);
		
		Assert.assertFalse(Evaluator.evaluate(this.emotion, this.model), "Emotion Love occurred");
		Assert.assertNull(this.emotion.getIntensity(), "Emotion intensity is not null");
	}
	
	@Test(description = "Test the emotion Love doesn't occur with negative variable values")
	public void testLoveAllNegative() throws Exception {
		this.appealingness = new Variable(VariableType.APPEALINGNESS, NEGATIVE_VALUE.toString());
		this.familiarity = new Variable(VariableType.FAMILIARITY, NEGATIVE_VALUE.toString());
		
		this.model
		.add(this.appealingness)
		.add(this.familiarity);
		
		Assert.assertFalse(Evaluator.evaluate(this.emotion, this.model), "Emotion Love occurred");
		Assert.assertNull(this.emotion.getIntensity(), "Emotion intensity is not null");
	}
	
	@Test(description = "Test the emotion Love doesn't occur with missing familiarity")
	public void testLoveMissingFamiliarity() throws Exception {
		this.appealingness = new Variable(VariableType.APPEALINGNESS, POSITIVE_VALUE.toString());
		this.familiarity = new Variable(VariableType.FAMILIARITY, POSITIVE_VALUE.toString());
		
		this.model
		.add(this.appealingness);
		
		Assert.assertFalse(Evaluator.evaluate(this.emotion, this.model), "Emotion Love occurred");
		Assert.assertNull(this.emotion.getIntensity(), "Emotion intensity is not null");
	}

}
