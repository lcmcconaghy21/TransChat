package com.lcmcconaghy.java.transchat.pager;

import org.bukkit.command.CommandSender;

import com.lcmcconaghy.java.transchat.store.Channel;
import com.lcmcconaghy.java.transchat.store.ChannelCollection;
import com.lcmcconaghy.java.transchat.store.ChatUser;
import com.lcmcconaghy.java.transcore.Message;
import com.lcmcconaghy.java.transcore.pager.PagerAbstract;

import net.md_5.bungee.api.ChatColor;

public class PagerChannels extends PagerAbstract<Channel>
{
	
	// { SINGLETON } //
	
	private static PagerChannels i = new PagerChannels();
	public static PagerChannels get() { return PagerChannels.i; } 
	
	// { CONSTRUCTOR } //
	
	public PagerChannels()
	{
		super("Channel List", ChannelCollection.get());
	}
	
	// { PAGER } //

	@Override
	public Message sendLine(Channel arg0, CommandSender arg1)
	{
		ChatUser user = ChatUser.get(arg1);
		String preColor = "<e>";
		boolean canFocus = true;
		boolean canListen = true;
		
		if (!arg1.hasPermission(arg0.getPermissionNode()))
		{
			preColor = "<c>";
			canFocus = false;
			canListen = false;
		}
		
		Message name = new Message(preColor+arg0.getDisplayName()+"<f>: ");
		Message listen = new Message("[L] ");
		Message focus = new Message("[F] ");
		
		if (!user.getFocused().getID().equalsIgnoreCase(arg0.getID()))
		{
			// TODO link focus command
		}
		if (!user.isListening(arg0) && canListen)
		{
			// TODO link listen command
		}
		
		if (!canListen)
		{
			listen.color(ChatColor.RED);
		}
		else
		{
			listen.color(ChatColor.GREEN);
		}
		
		if (!canFocus)
		{
			focus.color(ChatColor.RED);
		}
		else
		{
			focus.color(ChatColor.GREEN);
		}
		
		return new Message(name, focus, listen);
	}
}
