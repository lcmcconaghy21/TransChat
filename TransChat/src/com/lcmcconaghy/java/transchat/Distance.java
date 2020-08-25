package com.lcmcconaghy.java.transchat;

public enum Distance
{
	
	CLOSE("whispers"),
	NEAR("says"),
	DISTANT("yells"),
	FAR("screams");
	
	private String desc;
	
	Distance(String desc)
	{
		this.desc = desc;
	}
	
	public String getDescriptor()
	{
		return this.desc;
	}
	
	// { STATIC } //
	
	public static Distance getFromString(String arg0)
	{
		if (arg0.endsWith("!!"))
		{
			return FAR;
		}
		if (arg0.endsWith("!"))
		{
			return DISTANT;
		}
		if (arg0.endsWith("*"))
		{
			return CLOSE;
		}
		
		return NEAR;
	}
}
