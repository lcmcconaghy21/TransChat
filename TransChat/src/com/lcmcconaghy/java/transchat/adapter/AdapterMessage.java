package com.lcmcconaghy.java.transchat.adapter;

import org.bukkit.entity.Player;

import com.lcmcconaghy.java.transchat.FormatPriority;
import com.lcmcconaghy.java.transchat.store.Channel;
import com.lcmcconaghy.java.transcore.Message;
import com.lcmcconaghy.java.transcore.store.transcore.IUser;

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
		IUser sender = IUser.get(arg1);
		IUser receiver = IUser.get(arg2);
		
		if ( arg0.hasRadius() && arg0.willObfuscate() )
		{
			double distance = sender.distanceBetween(receiver);
			
			Message temp = new Message(arg3).obfuscate(distance-arg0.getRadiusInner(), 
					                                   arg0.getRadiusOuter()-arg0.getRadiusInner());
			
			return temp.getText();
		}
		
		return arg3;
	}
	
}
