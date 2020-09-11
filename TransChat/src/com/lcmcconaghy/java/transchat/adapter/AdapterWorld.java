package com.lcmcconaghy.java.transchat.adapter;

import org.bukkit.entity.Player;

import com.lcmcconaghy.java.transchat.store.Channel;

public class AdapterWorld extends AdapterAbstract
{
	
	// { SINGLETON } //
	
	private static AdapterWorld i = new AdapterWorld();
	public static AdapterWorld get() { return AdapterWorld.i; }
	
	// { CONSTRUCTOR } //
	
	public AdapterWorld()
	{
		super("world");
	}
	
	// { READ } //
	
	@Override
	public String read(Channel arg0, Player arg1, Player arg2, String arg3)
	{
		return arg1.getWorld().getName();
	}
	
}
