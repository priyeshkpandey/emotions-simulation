package com.occ.entities;

import com.occ.rules.infra.Rule;

public class Emotion {
	
	private String name;
	private Rule rule;
	private Double potential;
	private Double intensity;
	private Double threshold;
	
	public Emotion(String name, Rule rule, Double threshold) {
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

	public Double getPotential() {
		return potential;
	}

	public Double getIntensity() {
		return intensity;
	}

	public Double getThreshold() {
		return threshold;
	}

	public void setPotential(Double potential) {
		this.potential = potential;
	}

	public void setIntensity(Double intensity) {
		this.intensity = intensity;
	}

}
