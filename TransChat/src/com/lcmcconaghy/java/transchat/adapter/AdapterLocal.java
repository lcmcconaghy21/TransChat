package com.lcmcconaghy.java.transchat.adapter;

import org.bukkit.entity.Player;

import com.lcmcconaghy.java.transchat.store.Channel;

public class AdapterLocal extends AdapterAbstract
{
	
	// { SINGLETON } //
	
	private static AdapterLocal i = new AdapterLocal();
	public static AdapterLocal get() { return AdapterLocal.i; }
	
	// { CONSTRUCTOR } //
	
	public AdapterLocal()
	{
		super("local");
	}
	
	// { READ } //
	
	@Override
	public String read(Channel arg0, Player arg1, Player arg2, String arg3)
	{
		String ret = "says";
		
		if (arg3.endsWith("!!"))
		{
			ret = "screams";
		}
		else if (arg3.endsWith("!"))
		{
			ret = "yells";
		}
		else if (arg3.endsWith("~"))
		{
			ret = "whispters";
		}
		
		return ret;
	}
	
}
