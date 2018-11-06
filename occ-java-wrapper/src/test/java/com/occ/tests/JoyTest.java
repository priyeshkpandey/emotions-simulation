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

public class JoyTest extends BaseTest {
	
	private static final Double POSITIVE_VALUE = 0.3;
	private static final Double NEGATIVE_VALUE = -0.3;
	
	private Variable desirability;
	
	@BeforeClass
	public void setupBeforeClass(ITestContext context) {
		Rule rule = RulesBuilder.buildJoyRule();
		this.emotion = new Emotion("Joy", rule, THRESHOLD);
		this.model = (Model) context.getAttribute("model");
	}
	
	@AfterMethod
	public void setupAfterMethod() {
		this.model
		.remove(this.desirability);
		
		this.emotion.setIntensity(null);
		this.emotion.setPotential(null); 
	}
	
	@Test(description = "Test occurrence of Joy emotion")
	public void testJoy() throws Exception {
		this.desirability = new Variable(VariableType.DESIRABILITY, POSITIVE_VALUE.toString());
		this.model
		.add(this.desirability);
		
		Assert.assertTrue(Evaluator.evaluate(this.emotion, this.model), "Emotion Joy didn't occur with the satisfying constraints");
		Assert.assertNotNull(this.emotion.getIntensity(), "Emotion intensity is null"); 
		Assert.assertTrue(this.emotion.getIntensity() > 0.0, "Emotion intensity is not greater than zero"); 
		
	}
	
	@Test(description = "Test Joy emotion doesn't occur with negative desirability")
	public void testJoyNegativeDesirability() throws Exception {
		this.desirability = new Variable(VariableType.DESIRABILITY, NEGATIVE_VALUE.toString());
		this.model
		.add(this.desirability);
		
		Assert.assertFalse(Evaluator.evaluate(this.emotion, this.model), "Emotion Joy occurred");
		Assert.assertNull(this.emotion.getIntensity(), "Emotion intensity is not null");
	}

}
