package com.lcmcconaghy.java.transchat;

import java.util.ArrayList;
import java.util.List;

import com.lcmcconaghy.java.transchat.adapter.ChatAdapter;
import com.lcmcconaghy.java.transchat.cmd.CmdChat;
import com.lcmcconaghy.java.transchat.engine.EngineChat;
import com.lcmcconaghy.java.transchat.store.ChannelCollection;
import com.lcmcconaghy.java.transchat.store.ChatUserCollection;
import com.lcmcconaghy.java.transcore.TransPlugin;
import com.lcmcconaghy.java.transcore.command.TransCommand;
import com.lcmcconaghy.java.transcore.engine.Engine;
import com.lcmcconaghy.java.transcore.exception.TransCommandException;
import com.lcmcconaghy.java.transcore.store.Config;
import com.lcmcconaghy.java.transcore.store.StoreCollection;
import com.lcmcconaghy.java.transcore.store.serializable.Serializable;
import com.lcmcconaghy.java.transcore.util.UtilGeneral;

public class TransChat extends TransPlugin
{
	
	// { SINGLETON } //
	
	private static TransChat i;
	public static TransChat get() { return TransChat.i; }
	
	// { FIELDS } //
	
	private List<ChatAdapter> registeredAdapters = new ArrayList<ChatAdapter>();
	
	// { CONSTRUCTOR } //
	
	public TransChat()
	{
		TransChat.i = this;
	}
	
	// { STARTUP } //
	
	@Override
	public void startup()
	{
		return;
	}
	
	// { DISABLE } //
	
	@Override
	public void disable()
	{
		return;
	}
	
	// { REGISTRARS } //

	@Override
	public List<Serializable<?>> getAdapters()
	{
		return UtilGeneral.list();
	}

	@Override
	public List<StoreCollection<?>> getStoreCollections()
	{
		return UtilGeneral.list(ChannelCollection.get(),
					            ChatUserCollection.get());
	}

	@Override
	public List<TransCommand> getTransCommands()
	{
		try
		{
			return UtilGeneral.list(new CmdChat());
		}
		catch (TransCommandException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Engine> getEngines()
	{
		return UtilGeneral.list(EngineChat.get());
	}
	
	// { ADAPTER } //
	
	/**
	 * @param adapters Varargs of type ChatAdapter
	 */
	public void registerAdapter(ChatAdapter...adapters)
	{
		for (ChatAdapter adapter : adapters)
		{
			if (this.registeredAdapters.contains(adapter)) continue;
			
			this.registeredAdapters.add(adapter);
		}
	}
	
	/**
	 * @return registered List of ChatAdapters
	 */
	public List<ChatAdapter> getChatAdapters()
	{
		return this.registeredAdapters;
	}
	
	/**
	 * @param arg0 ChatAdapter type
	 * @return whether ChatAdapter is registered and present
	 */
	public boolean containsAdapter(ChatAdapter arg0)
	{
		return this.registeredAdapters.contains(arg0);
	}

	@Override
	public Config getTransConfig()
	{
		return ChatConfig.get();
	}
	
}
