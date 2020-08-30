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
	
	@EventHandler(priority=EventPriority.LOW)
	public void quickSwitch(AsyncPlayerChatEvent event)
	{
		// CHECK IF EVENT SHOULD RUN, AND IF QUICK SWITCH IS BEING USED
		if (event.isCancelled()) return;
		if (!event.getMessage().contains(":")) return;
		
		int locColon = event.getMessage().indexOf(":");
		
		String preColon = event.getMessage().substring(0, locColon);
		
		// IF THE PRE-COLON MESSAGE HAS A SPACE, IT IS LIKELY NOT A QUICK-SWITCH
		if (preColon.contains(" ")) return;
		
		Channel swtch = Channel.getByQuickSwitch(preColon);
		
		// IF THERE IS NO CHANNEL WITH THE QUICK SWITCH, CONTINUE AS NORMAL
		if (swtch == null) return;
		
		ChatUser user = ChatUser.get(event.getPlayer());
		
		user.focus(swtch);
		user.message(new Message("<a>You are now speaking in the <d>"+swtch.getDisplayName()+" Channel<a>.")
				     .format());
		
		String set = event.getMessage().substring(locColon+1, event.getMessage().length());
		
		// IF THERE IS NO MESSAGE FOLLOWING THE COLON, DO NOT CONTINUE...
		//...WITH A NEW CHAT EVENT
		if (set.length() <= 0 || set.equalsIgnoreCase("") || set.equalsIgnoreCase(" "))
		{
			event.setCancelled(true);
			return;
		}
		
		event.setMessage( set );
	}
	
	// TODO Minimize executing AsyncPlayerChatEvent to only cases where Players are...
	// ...the same distance or within the inner radius
	@EventHandler(priority=EventPriority.NORMAL)
	public void executeChat(AsyncPlayerChatEvent event)
	{
		// CHECK IF CHATTING IS ALLOWED
		if (event.isCancelled()) return;
		
		final ChatUser user = ChatUser.get(event.getPlayer());
		Channel channel = user.getFocused();
		ChatUser recipient = null;
		
		// SEND THE PLAYER A PERSONAL, UNPARSED MESSAGE
		user.message( format(channel, user, user, event.getMessage(), true) );
		
		// IF LOCAL
		
		if (channel.hasRadius())
		{
			for (Player players : event.getRecipients())
			{
				recipient = ChatUser.get(players);
				
				// IF RECIPIENT IS OUTSIDE OUTER RADIUS
				if (recipient.distanceBetween(user) > channel.getRadiusOuter()) continue;
				
				if (recipient.equals(user)) continue;
				if (recipient.hasBlocked(user)) continue;
				
				// IF RECIPIENT IS OUTSIDE INNER RADIUS, BUT INSIDE OUTER RADIUS
				if (user.distanceBetween(recipient)>=channel.getRadiusInner())
				{
					recipient.message( format(channel, user, recipient, event.getMessage(), !channel.willObfuscate()) );
					continue;
				}
				
				// IF INSIDE BOTH INNER AND OUTER RADII
				recipient.message( format(channel, user, recipient, event.getMessage(), true) );
			}
			
			event.setCancelled(true);
			return;
		}
		
		// IF CHANNEL IS NOT LOCALIZED
		for (Player entry : event.getRecipients())
		{
			recipient = ChatUser.get(entry);
			
			if (recipient.equals(user)) continue;
			if (recipient.hasBlocked(user)) continue;
			
			recipient.message( format(channel, user, recipient, event.getMessage(), true) );
			
			event.setCancelled(true);
		}
		
		event.setCancelled(true);
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
				sent = sent.obfuscate(arg1.distanceBetween(arg2)-arg0.getRadiusInner(), arg0.getRadiusOuter()-arg0.getRadiusInner());
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
		if (message.contains(LOCAL))
		{
			Distance dist = Distance.getFromString(arg3);
			String to = dist.getDescriptor();
			
			message = message.replace(LOCAL, to);
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
