package com.occ.entities;

import java.util.List;
import java.util.Map;

public class EmotionalEntity {
	
	private Map<AgentAction, List<Variable>> agentActionPerception;
	private Map<EventConsequence, List<Variable>> eventConsequencePerception;
	private Map<ObjectAspect, List<Variable>> objectAspectPerception;
	private List<Emotion> observedEmotions;

}
