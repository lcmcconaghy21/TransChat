package com.lcmcconaghy.java.transchat.adapter;

import org.bukkit.entity.Player;

import com.lcmcconaghy.java.transchat.store.Channel;
import com.lcmcconaghy.java.transcore.store.transcore.IUser;

public class AdapterDistance extends AdapterAbstract
{
	
	// { SINGLETON } //
	
	private static AdapterDistance i = new AdapterDistance();
	public static AdapterDistance get() { return AdapterDistance.i; }
	
	// { CONSTRUCTOR } //
	
	public AdapterDistance()
	{
		super("distance");
	}
	
	// { READ } //
	
	@Override
	public String read(Channel arg0, Player arg1, Player arg2, String arg3)
	{
		IUser sender = IUser.get(arg1);
		IUser receiver = IUser.get(arg2);
		
		double distance = sender.distanceBetween(receiver);
		
		return distance+"";
	}
	
}
