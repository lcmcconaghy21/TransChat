package com.lcmcconaghy.java.transchat.adapter;

import org.bukkit.entity.Player;

import com.lcmcconaghy.java.transchat.store.Channel;

public class AdapterChannel extends AdapterAbstract
{
	
	// { SINGLETON } //
	
	private static AdapterChannel i = new AdapterChannel();
	public static AdapterChannel get() { return AdapterChannel.i; }
	
	// { CONSTRUCTOR } //
	
	public AdapterChannel()
	{
		super("channel");
	}
	
	// { READ } //
	
	@Override
	public String read(Channel arg0, Player arg1, Player arg2, String arg3)
	{
		return arg0.getDisplayName();
	}
	
}
