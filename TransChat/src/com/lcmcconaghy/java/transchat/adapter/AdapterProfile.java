package com.lcmcconaghy.java.transchat.adapter;

import org.bukkit.entity.Player;

import com.lcmcconaghy.java.transchat.store.Channel;
import com.lcmcconaghy.java.transcore.store.transcore.IUser;

public class AdapterProfile extends AdapterAbstract
{
	
	// { SINGLETON } //
	
	private static AdapterProfile i = new AdapterProfile();
	public static AdapterProfile get() { return AdapterProfile.i; }
	
	// { CONSTRUCTOR } //
	
	public AdapterProfile()
	{
		super("profile");
	}
	
	// { READ } //
	
	@Override
	public String read(Channel arg0, Player arg1, Player arg2, String arg3)
	{
		return IUser.get(arg1).getActiveProfile().getName();
	}
	
}
