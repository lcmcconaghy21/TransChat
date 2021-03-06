package com.lcmcconaghy.java.transchat.store;

import java.util.ArrayList;
import java.util.List;

import com.lcmcconaghy.java.transchat.ChatConfig;
import com.lcmcconaghy.java.transcore.store.UserItem;
import com.lcmcconaghy.java.transcore.util.UtilGeneral;

public class ChatUser extends UserItem
{
	
	// { STATIC } //
	
	public static ChatUser get(Object arg0) { return ChatUserCollection.get().get(arg0); }
	
	// { FIELDS } //
	
	protected String idFocusedChannel = ChannelCollection.DEFAULT_CHANNEL_ID;
	protected List<String> idsChannelsListening = UtilGeneral.list( ChannelCollection.get().getByName( ChatConfig.get().getDefaultChatName() ).getID() );
	private String nameRoleplay;
	
	private boolean muted = false;
	
	private List<String> idsBlockedPlayers = new ArrayList<String>();
	
	// { SETTERS } //
	
	public void focus(Channel arg0)
	{
		this.idFocusedChannel = arg0.getID();
	}
	
	public void listen(Channel... args)
	{
		for (Channel channel : args)
		{
			this.idsChannelsListening.add(channel.getID());
		}
	}
	
	public void ignore(Channel...args)
	{
		for (Channel channel : args)
		{
			this.idsChannelsListening.remove(channel.getID());
		}
	}
	
	public boolean block(ChatUser arg0)
	{
		if (this.idsBlockedPlayers.contains(arg0.getID())) return false;
		
		this.idsBlockedPlayers.add(arg0.getID());
		
		return true;
	}
	
	public boolean unblock(ChatUser arg0)
	{
		if (!this.idsBlockedPlayers.contains(arg0.getID())) return false;
		
		this.idsBlockedPlayers.remove(arg0.getID());
		
		return true;
	}
	
	public void setMuted(boolean arg0)
	{
		this.muted = arg0;
	}
	
	// { GETTERS } //
	
	public Channel getFocused()
	{
		return Channel.get(idFocusedChannel);
	}
	
	public boolean isListening(Channel arg0)
	{
		return this.idsChannelsListening.contains(arg0.getID());
	}
	
	public boolean hasBlocked(ChatUser arg0)
	{
		return this.idsBlockedPlayers.contains(arg0.getID());
	}
	
	public String getRoleplayName()
	{
		if (this.nameRoleplay == null || this.nameRoleplay.equalsIgnoreCase(""))
		{
			return this.getPlayer().getDisplayName();
		}
		
		return this.nameRoleplay;
	}
	
	public boolean isMuted()
	{
		return this.muted;
	}
	
}
