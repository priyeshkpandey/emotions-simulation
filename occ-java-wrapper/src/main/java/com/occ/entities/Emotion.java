package com.occ.entities;

import java.util.List;

import com.occ.meta.VariableType;

public class Emotion {
	
	private String emotion;
	private List<VariableType> variableTypes;
	private Integer potential;
	private Integer intensity;
	private Integer threshold;
	
	public Emotion(String emotion, List<VariableType> variableTypes) {
		this.emotion = emotion;
		this.variableTypes = variableTypes;
	}

	public String getEmotion() {
		return emotion;
	}

	public List<VariableType> getVariableTypes() {
		return variableTypes;
	}
	
	

}
