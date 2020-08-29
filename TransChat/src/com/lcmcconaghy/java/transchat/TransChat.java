package com.lcmcconaghy.java.transchat;

import java.util.List;

import com.lcmcconaghy.java.transchat.cmd.CmdChat;
import com.lcmcconaghy.java.transchat.cmd.CmdPlayerMute;
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
			return UtilGeneral.list(new CmdChat(),
					                new CmdPlayerMute());
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

	@Override
	public Config getTransConfig()
	{
		return ChatConfig.get();
	}
	
}
