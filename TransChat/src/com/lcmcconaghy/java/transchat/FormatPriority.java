package com.lcmcconaghy.java.transchat;

public enum FormatPriority
{
	
	MESSAGE(3),
	
	LOW(2),
	MEDIUM(1),
	HIGH(0);
	
	// { FIELDS } //
	
	private int priority;
	
	// { CONSTRUCTOR } //
	
	FormatPriority(int arg0)
	{
		this.priority = arg0;
	}
	
	// { PRIORITIES } //
	
	public boolean higherThan(FormatPriority arg0)
	{
		return this.priority > arg0.priority;
	}
	
	public boolean lowerThan(FormatPriority arg0)
	{
		return this.priority < arg0.priority;
	}
	
	public boolean equals(FormatPriority arg0)
	{
		return this.priority == arg0.priority;
	}
	
}
