package com.lcmcconaghy.java.transchat;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lcmcconaghy.java.transchat.adapter.AdapterChannel;
import com.lcmcconaghy.java.transchat.adapter.AdapterDistance;
import com.lcmcconaghy.java.transchat.adapter.AdapterLocal;
import com.lcmcconaghy.java.transchat.adapter.AdapterMessage;
import com.lcmcconaghy.java.transchat.adapter.AdapterPlayer;
import com.lcmcconaghy.java.transchat.adapter.AdapterProfile;
import com.lcmcconaghy.java.transchat.adapter.AdapterWorld;
import com.lcmcconaghy.java.transchat.adapter.FormatAdapter;
import com.lcmcconaghy.java.transchat.cmd.CmdChat;
import com.lcmcconaghy.java.transchat.cmd.CmdPlayerMute;
import com.lcmcconaghy.java.transchat.engine.EngineChannel;
import com.lcmcconaghy.java.transchat.engine.EngineChat;
import com.lcmcconaghy.java.transchat.store.ChannelCollection;
import com.lcmcconaghy.java.transchat.store.ChatUserCollection;
import com.lcmcconaghy.java.transcore.TransPlugin;
import com.lcmcconaghy.java.transcore.command.TransCommand;
import com.lcmcconaghy.java.transcore.engine.Engine;
import com.lcmcconaghy.java.transcore.store.Config;
import com.lcmcconaghy.java.transcore.store.StoreCollection;
import com.lcmcconaghy.java.transcore.store.serializable.Serializable;
import com.lcmcconaghy.java.transcore.util.UtilGeneral;

public class TransChat extends TransPlugin
{
	
	// { FIELDS } //
	
	private Map<FormatPriority, List<FormatAdapter>> adapters = new HashMap<FormatPriority, List<FormatAdapter>>();
	
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
		this.registerFormatAdapters(AdapterMessage.get(),
				                    AdapterPlayer.get(),
				                    AdapterProfile.get(),
				                    
				                    AdapterWorld.get(),
				                    AdapterDistance.get(),
				                    
				                    AdapterChannel.get(),
				                    AdapterLocal.get());
		
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
		return UtilGeneral.list(new CmdChat(),
				                new CmdPlayerMute());
	}

	@Override
	public List<Engine> getEngines()
	{
		return UtilGeneral.list(EngineChat.get(),
				                EngineChannel.get());
	}

	@Override
	public Config getTransConfig()
	{
		return ChatConfig.get();
	}
	
	// { ADAPTERS } //
	
	public void registerFormatAdapters(FormatAdapter... adapters)
	{
		for (FormatAdapter adapter : adapters)
		{
			if ( !this.adapters.containsKey(adapter.getPriority()) )
			{
				this.adapters.put(adapter.getPriority(), UtilGeneral.list(adapter));
				continue;
			}
			
			List<FormatAdapter> internalAdapters = this.adapters.get(adapter.getPriority());
			internalAdapters.add(adapter);
			
			this.adapters.put(adapter.getPriority(), internalAdapters);
		}
	}
	
	public Collection<FormatAdapter> getFormatAdapters(FormatPriority arg0)
	{
		return this.adapters.get(arg0);
	}
	
}
