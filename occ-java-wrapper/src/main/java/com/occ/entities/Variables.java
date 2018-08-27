package com.occ.entities;

public abstract class Variables {
	
	protected String name;
	protected String value;
	
	public Variables(String name, String value) {
		this.name = name;
		this.value =value;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

}
