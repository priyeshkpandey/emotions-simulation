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

public class HopeTest extends BaseTest {
	
	private static final Double POSITIVE_VALUE = 0.3;
	private static final Double NEGATIVE_VALUE = -0.3;
	
	@BeforeClass
	public void setupBeforeClass(ITestContext context) {
		Rule rule = RulesBuilder.buildHopeRule();
		this.emotion = new Emotion("Hope", rule, THRESHOLD);
		this.model = (Model) context.getAttribute("model");
	}
	
	private Variable desirability;
	private Variable likelihood;
	
	@AfterMethod
	public void setupAfterMethod() {
		this.model
		.remove(this.desirability)
		.remove(this.likelihood);
		
		this.emotion.setIntensity(null);
		this.emotion.setPotential(null); 
	}
	
	@Test(description = "Test for the occurrence of Hope Emotion")
	public void testHope() throws Exception {
		this.desirability = new Variable(VariableType.DESIRABILITY, POSITIVE_VALUE.toString());
		this.likelihood = new Variable(VariableType.LIKELIHOOD, POSITIVE_VALUE.toString());
		this.model
		.add(this.desirability)
		.add(this.likelihood);
		
		Assert.assertTrue(Evaluator.evaluate(this.emotion, this.model), "Emotion Hope didn't occur with the satisfying constraints");
		Assert.assertNotNull(this.emotion.getIntensity(), "Emotion intensity is null"); 
		Assert.assertTrue(this.emotion.getIntensity() > 0.0, "Emotion intensity is not greater than zero"); 
	}
	
	@Test(description = "Test that Hope Emotion doesn't occur if desirability is negative")
	public void testHopeNegativeDesirability() throws Exception {
		this.desirability = new Variable(VariableType.DESIRABILITY, NEGATIVE_VALUE.toString());
		this.likelihood = new Variable(VariableType.LIKELIHOOD, POSITIVE_VALUE.toString());
		this.model
		.add(this.desirability)
		.add(this.likelihood);
		
		Assert.assertFalse(Evaluator.evaluate(this.emotion, this.model), "Emotion Hope occurred");
		Assert.assertNull(this.emotion.getIntensity(), "Emotion intensity is not null"); 
	}
	
	@Test(description = "Test that Hope Emotion doesn't occur if likelihood is negative")
	public void testHopeNegativeLikelihood() throws Exception {
		this.desirability = new Variable(VariableType.DESIRABILITY, POSITIVE_VALUE.toString());
		this.likelihood = new Variable(VariableType.LIKELIHOOD, NEGATIVE_VALUE.toString());
		this.model
		.add(this.desirability)
		.add(this.likelihood);
		
		Assert.assertFalse(Evaluator.evaluate(this.emotion, this.model), "Emotion Hope occurred");
		Assert.assertNull(this.emotion.getIntensity(), "Emotion intensity is not null"); 
	}
	
	@Test(description = "Test that Hope Emotion doesn't occur if desirability is missing")
	public void testHopeMissingDesirability() throws Exception {
		this.desirability = new Variable(VariableType.DESIRABILITY, POSITIVE_VALUE.toString());
		this.likelihood = new Variable(VariableType.LIKELIHOOD, POSITIVE_VALUE.toString());
		this.model
		.add(this.likelihood);
		
		Assert.assertFalse(Evaluator.evaluate(this.emotion, this.model), "Emotion Hope occurred");
		Assert.assertNull(this.emotion.getIntensity(), "Emotion intensity is not null"); 
	}
	
	@Test(description = "Test that Hope Emotion doesn't occur if likelihood is missing")
	public void testHopeMissingLikelihood() throws Exception {
		this.desirability = new Variable(VariableType.DESIRABILITY, POSITIVE_VALUE.toString());
		this.likelihood = new Variable(VariableType.LIKELIHOOD, POSITIVE_VALUE.toString());
		this.model
		.add(desirability);
		
		Assert.assertFalse(Evaluator.evaluate(this.emotion, this.model), "Emotion Hope occurred");
		Assert.assertNull(this.emotion.getIntensity(), "Emotion intensity is not null"); 
	}

}
