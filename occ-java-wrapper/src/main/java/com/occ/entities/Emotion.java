package com.occ.entities;

import com.occ.models.infra.Model;
import com.occ.rules.infra.Rule;

public class Emotion {
	
	private String name;
	private Rule rule;
	private Integer potential;
	private Integer intensity;
	private Integer threshold;
	
	public Emotion(String name, Rule rule, Integer threshold) {
		this.name = name;
		this.rule = rule;
		this.threshold = threshold;
	}

	public String getName() {
		return name;
	}

	public Rule getRule() {
		return rule;
	}

	public Integer getPotential() {
		return potential;
	}

	public Integer getIntensity() {
		return intensity;
	}

	public Integer getThreshold() {
		return threshold;
	}

	public void evaluate(Model model) {
		
	}

}
