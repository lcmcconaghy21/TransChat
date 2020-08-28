package com.lcmcconaghy.java.transchat.engine;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.lcmcconaghy.java.transchat.Distance;
import com.lcmcconaghy.java.transchat.store.Channel;
import com.lcmcconaghy.java.transchat.store.ChatUser;
import com.lcmcconaghy.java.transcore.Message;
import com.lcmcconaghy.java.transcore.engine.Engine;

public class EngineChat extends Engine
{
	
	// { CONSTANTS } //	
	
	public static final String MESSAGE = "%message%";
	public static final String PLAYER = "%playerName%";
	public static final String CHARACTER = "%character%";
	public static final String LOCAL = "%local%";
	public static final String CHANNEL = "%channel%";
	public static final String WORLD = "%world%";
	public static final String DISTANCE = "%distance%";
		
	// { SINGLETON } //
	
	private static EngineChat i = new EngineChat();
	public static EngineChat get() { return EngineChat.i; }
	
	// { EVENT HANDLERS } //
	
	@EventHandler(priority=EventPriority.NORMAL)
	public void quickSwitch(AsyncPlayerChatEvent event)
	{
		if (!event.getMessage().contains(":")) return;
		
		int locColon = event.getMessage().indexOf(":");
		
		String preColon = event.getMessage().substring(0, locColon);
		
		if (preColon.contains(" ")) return;
		
		Channel swtch = Channel.getByQuickSwitch(preColon);
		
		if (swtch == null) return;
		
		ChatUser user = ChatUser.get(event.getPlayer());
		
		user.focus(swtch);
		user.message(new Message("<a>You are now speaking in the <d>"+swtch.getID()+" Channel<a>.")
				     .format());
		
		String set = event.getMessage().substring(locColon+1);
		
		if (set.length()<=0) return;
		
		event.setMessage(event.getMessage().substring(locColon+1));
	}
	
	// TODO Minimize executing AsyncPlayerChatEvent to only cases where Players are...
	// ...the same distance or within the inner radius
	@EventHandler(priority=EventPriority.LOW)
	public void executeChat(AsyncPlayerChatEvent event)
	{
		ChatUser user = ChatUser.get(event.getPlayer());
		Channel channel = user.getFocused();
		ChatUser recipient = null;
		
		user.message( format(channel, user, user, event.getMessage(), true) );
		
		// if local
		
		if (channel.hasRadius())
		{
			for (Player players : user.getNearbyPlayers(channel.getRadiusOuter()))
			{
				recipient = ChatUser.get(players);
				
				if (recipient.equals(user)) continue;
				if (recipient.hasBlocked(user)) continue;
				
				// if inside outer radius but outside inner radius
				if (user.distanceBetween(recipient)<=channel.getRadiusInner())
				{
					recipient.message( format(channel, user, recipient, event.getMessage(), false) );
					continue;
				}
				
				// if inside both outside and inner radii
				recipient.message( format(channel, user, recipient, event.getMessage(), true) );
			}
			
			event.setCancelled(true);
			return;
		}
		
		// if not localized
		for (Player entry : event.getRecipients())
		{
			recipient = ChatUser.get(entry);
			
			if (recipient.equals(user)) continue;
			if (recipient.hasBlocked(user)) continue;
			
			recipient.message( format(channel, user, recipient, event.getMessage(), true) );
			
			event.setCancelled(true);
		}
		
	}
	
	/**
	 * Format a message
	 * @param arg0 Channel
	 * @param arg1 ChatUser sender
	 * @param arg2 ChatUser receiver
	 * @param arg3 String message
	 * @param arg4 Boolean override obfuscation
	 * @return
	 */
	protected Message format(Channel arg0, ChatUser arg1, ChatUser arg2, String arg3, boolean arg4)
	{
		Message message = new Message(arg0.getFormat());
		
		if (message.contains(CHANNEL))
		{
			message = message.replace(CHANNEL, arg0.getDisplayName());
		}
		if (message.contains(MESSAGE))
		{
			Message sent = new Message(arg3);
			
			if (arg0.hasRadius() && !arg4)
			{
				sent = sent.obfuscate(arg1.distanceBetween(arg2), arg0.getRadiusOuter()-arg0.getRadiusInner());
			}
			
			message = message.replace(MESSAGE, sent.getText());
		}
		if (message.contains(WORLD))
		{
			message = message.replace(WORLD, arg1.getTransLocation().getWorld().getName());
		}
		if (message.contains(PLAYER))
		{
			message = message.replace(PLAYER, arg1.getPlayer().getName());
		}
		if (message.contains(CHARACTER))
		{
			message = message.replace(CHARACTER, arg1.getRoleplayName());
		}
		if (message.contains(DISTANCE))
		{
			message = message.replace(CHARACTER, Distance.getFromString(arg3)
					                                     .getDescriptor());
		}
		if (message.contains(DISTANCE))
		{
			double distance = arg1.distanceBetween(arg2);
			
			if (distance<0)
			{
				message = message.replace(DISTANCE, arg1.getTransLocation()
						                                .getWorld()
						                                .getName());
				
				return message;
			}
			
			message = new Message(distance+"");
		}
		
		return message;
	}
	
}
