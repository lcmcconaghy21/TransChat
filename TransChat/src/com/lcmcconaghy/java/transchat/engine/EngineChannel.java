package com.lcmcconaghy.java.transchat.engine;

import org.bukkit.event.EventHandler;

import com.lcmcconaghy.java.transchat.ChatConfig;
import com.lcmcconaghy.java.transchat.event.ChannelCreateEvent;
import com.lcmcconaghy.java.transchat.event.ChannelRemoveEvent;
import com.lcmcconaghy.java.transchat.store.Channel;
import com.lcmcconaghy.java.transchat.store.ChatUser;
import com.lcmcconaghy.java.transchat.store.ChatUserCollection;
import com.lcmcconaghy.java.transcore.engine.Engine;

public class EngineChannel extends Engine
{
	
	// { SINGLETON } //
	
	private static EngineChannel i = new EngineChannel();
	public static EngineChannel get() { return EngineChannel.i; }
	
	// { ENGINE } //
	
	@EventHandler
	public void updateUsersChannelCreate(ChannelCreateEvent createEvent)
	{
		for (ChatUser user : ChatUserCollection.get())
		{
			// IF PLAYER CANNOT BE CHECKED FOR LISTEN PERMISSION, SKIP
			if (!user.isOnline()) continue;
			
			// IF PLAYER DOES NOT HAVE LISTEN PERMISSION, SKIP
			if (!user.getPlayer().hasPermission(createEvent.getChannel().getListenNode())) continue;
			
			user.listen( createEvent.getChannel() );
		}
	}
	
	@EventHandler
	public void updateUsersChannelRemove(ChannelRemoveEvent removeEvent)
	{
		for (ChatUser user : ChatUserCollection.get())
		{
			// IF PLAYER IS LISTENING TO A CHANNEL, REMOVE IT FROM THEIR...
			// ...LISTENING LIST
			if (user.isListening( removeEvent.getChannel() ))
			{
				user.ignore( removeEvent.getChannel() );
			}
			
			// IF PLAYER IS FOCUSED ON A CHANNEL, REFOCUS ON DEFAULT CHANNEL
			if (user.getFocused().getID().equalsIgnoreCase(removeEvent.getChannel().getID()))
			{
				user.focus( Channel.getByName( ChatConfig.get().getDefaultChatName() ) );
			}
		}
	}
}
