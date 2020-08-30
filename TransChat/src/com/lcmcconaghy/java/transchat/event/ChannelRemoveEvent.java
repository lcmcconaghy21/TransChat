package com.lcmcconaghy.java.transchat.event;

import org.bukkit.event.HandlerList;

import com.lcmcconaghy.java.transchat.store.Channel;
import com.lcmcconaghy.java.transcore.event.TransEvent;

public class ChannelRemoveEvent extends TransEvent
{
	
	// { HANDLERS } //
	
	public static final HandlerList handlers = new HandlerList();
	public static HandlerList getHandlerList() { return handlers; }
	@Override public HandlerList getHandlers() { return handlers; }
	
	// { FIELDS } //
	
	private Channel channel;
	
	// { CONSTRUCTOR } //
	
	public ChannelRemoveEvent(Channel arg0)
	{
		this.channel = arg0;
	}
	
	// { GETTERS } //
	
	public Channel getChannel()
	{
		return this.channel;
	}
}
