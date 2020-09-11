package com.lcmcconaghy.java.transchat.adapter;

import org.bukkit.entity.Player;

import com.lcmcconaghy.java.transchat.store.Channel;

public class AdapterPlayer extends AdapterAbstract
{
	
	// { SINGLETON } //
	
	private static AdapterPlayer i = new AdapterPlayer();
	public static AdapterPlayer get() { return AdapterPlayer.i; }
	
	// { CONSTRUCTOR } //
	
	public AdapterPlayer()
	{
		super("playerName");
	}
	
	// { READ } //
	
	@Override
	public String read(Channel arg0, Player arg1, Player arg2, String arg3)
	{
		return arg1.getName();
	}
	
}
