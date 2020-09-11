package com.lcmcconaghy.java.transchat.adapter;

import com.lcmcconaghy.java.transchat.FormatPriority;
import com.lcmcconaghy.java.transchat.store.Channel;

public abstract class AdapterAbstract implements FormatAdapter
{
	
	// { FIELDS } //
	
	private String id;
	private FormatPriority priority;
	
	// { CONSTRUCTOR } //
	
	public AdapterAbstract(String arg0, FormatPriority arg1)
	{
		this.id = arg0;
		this.priority = arg1;
	}
	
	public AdapterAbstract(String arg0)
	{
		this.id = arg0;
		this.priority = FormatPriority.MEDIUM;
	}
	
	// { ADAPTER } //
	
	@Override
	public boolean containsFormat(Channel arg0)
	{
		return arg0.getFormat().contains("%"+id.toLowerCase()+"%");
	}
	
	// { ID } //
	
	public String getID()
	{
		return this.id;
	}
	
	public String getFormat()
	{
		return "%"+this.id+"%";
	}
	
	// { PRIORITY } //
	
	public FormatPriority getPriority()
	{
		return this.priority;
	}
	
}