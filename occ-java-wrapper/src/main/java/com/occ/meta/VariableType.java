package com.occ.meta;

public enum VariableType {
	
	UNEXPECTEDNESS("Unexpectedness", "global"),
	SENSE_OF_REALITY("Sense of reality", "global"),
	PROXIMITY("Proximity", "global"),
	AROUSAL("Arousal", "global"),
	
	// EVENT
	DESIRABILITY("Desirability", "event"),
	DESERVINGNESS("Deservingness", "event_for_others"),
	LIKING("Liking", "event_for_others"),
	DESIRABILITY_FOR_OTHER("Desirability for other", "event_for_others"),
	LIKELIHOOD("Likelihood", "event_future"),
	REALIZATION("Realization", "event_present"),
	EFFORT("Effort", "event_present"),
	
	// OBJECT
	APPEALINGNESS("Appealingness", "object"),
	
	// AGENT ACTION
	PRAISEWORTHINESS("Praiseworthiness", "agent"),
	STRENGTH_OF_COG_UNIT("Strength of cognitive unity", "agent"),
	EXPECTATION_DEVIATION("Expectation deviation", "agent");
	
	private String description;
	private String type;
	
	VariableType(String description, String type) {
		this.description = description;
		this.type = type;
	}
	
	public String getVarDescription() {
		return this.description;
	}
	
	public String getVarType() {
		return this.type;
	}

}
