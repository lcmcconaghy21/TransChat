package com.lcmcconaghy.java.transchat.pager;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.lcmcconaghy.java.transchat.cmd.CmdChatFocus;
import com.lcmcconaghy.java.transchat.cmd.CmdChatListen;
import com.lcmcconaghy.java.transchat.store.Channel;
import com.lcmcconaghy.java.transchat.store.ChannelCollection;
import com.lcmcconaghy.java.transchat.store.ChatUser;
import com.lcmcconaghy.java.transcore.Message;
import com.lcmcconaghy.java.transcore.pager.PagerAbstract;

import net.md_5.bungee.api.ChatColor;

public class PagerChannels extends PagerAbstract<Channel>
{
	
	// { FIELDS } //
	
	private CmdChatFocus cmdFocus;
	private CmdChatListen cmdListen;
	
	// { SINGLETON } //
	
	private static PagerChannels i = new PagerChannels();
	public static PagerChannels get() { return PagerChannels.i; } 
	
	// { CONSTRUCTOR } //
	
	public PagerChannels()
	{
		super("Channel List", ChannelCollection.get());
		
		this.cmdFocus = CmdChatFocus.get();
		this.cmdListen = CmdChatListen.get();
	}
	
	// { PAGER } //

	@Override
	public Message sendLine(Channel arg0, CommandSender arg1)
	{
		ChatUser user = ChatUser.get(arg1);
		
		Message name = new Message("<a>"+arg0.getDisplayName()+"<f>: ").format();
		Message listen = new Message("[L] ").color(ChatColor.RED);
		Message focus = new Message("[F] ").color(ChatColor.RED);
		
		if (arg1 instanceof Player)
		{
			if (!user.getFocused().getID().equalsIgnoreCase(arg0.getID()) && 
				user.getPlayer().hasPermission( arg0.getFocusNode() ))
			{
				focus = focus.color(ChatColor.GREEN)
					 .command(this.cmdFocus, arg0.getDisplayName())
				     .hover("<6>CLICK TO FOCUS THIS CHANNEL");
			}
			if (!user.isListening(arg0) && user.getPlayer().hasPermission( arg0.getListenNode() ))
			{
				listen = listen.color(ChatColor.GREEN)
					  .command(this.cmdListen, arg0.getDisplayName())
				      .hover("<6>CLICK TO LISTEN TO THIS CHANNEL");
			}
		}
		
		return new Message(name, focus, listen);
	}
}
