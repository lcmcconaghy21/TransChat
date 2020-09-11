package com.lcmcconaghy.java.transchat.adapter;

import org.bukkit.entity.Player;

import com.lcmcconaghy.java.transchat.FormatPriority;
import com.lcmcconaghy.java.transchat.store.Channel;

public class AdapterMessage extends AdapterAbstract
{
	
	// { SINGLETON } //
	
	private static AdapterMessage i = new AdapterMessage();
	public static AdapterMessage get() { return AdapterMessage.i; }
	
	// { CONSTRUCTOR } //
	
	public AdapterMessage()
	{
		super("message", FormatPriority.MESSAGE);
	}
	
	// { READ } //
	
	@Override
	public String read(Channel arg0, Player arg1, Player arg2, String arg3)
	{
		return arg3;
	}
	
}
