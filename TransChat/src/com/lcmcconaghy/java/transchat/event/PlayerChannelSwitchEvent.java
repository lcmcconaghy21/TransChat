package com.lcmcconaghy.java.transchat.event;

import org.bukkit.event.HandlerList;

import com.lcmcconaghy.java.transchat.store.Channel;
import com.lcmcconaghy.java.transchat.store.ChatUser;
import com.lcmcconaghy.java.transcore.event.TransEvent;

public class PlayerChannelSwitchEvent extends TransEvent
{
	
	// { HANDLERS } //
	
	public static final HandlerList handlers = new HandlerList();
	public static HandlerList getHandlerList() { return handlers; }
	@Override public HandlerList getHandlers() { return handlers; }
	
	// { FIELDS } //
	
	private ChatUser user;
	private Channel origin;
	private Channel to;
	
	// { CONSTRUCTOR } //
	
	public PlayerChannelSwitchEvent(ChatUser arg0, Channel arg1, Channel arg2)
	{
		this.user = arg0;
		this.origin = arg1;
		this.to = arg2;
	}
	
	// { SETTERS } //
	
	public void setTo(Channel arg0)
	{
		this.to = arg0;
	}
	
	// { GETTERS } //
	
	public ChatUser getUser()
	{
		return this.user;
	}
	
	public Channel getFrom()
	{
		return this.origin;
	}
	
	public Channel getTo()
	{
		return this.to;
	}
	
}
