package com.lcmcconaghy.java.transchat.store;

import com.lcmcconaghy.java.transchat.ChatConfig;
import com.lcmcconaghy.java.transcore.TransPlugin;
import com.lcmcconaghy.java.transcore.store.StoreCollection;

public class ChannelCollection extends StoreCollection<Channel>
{
	
	// { SERIAL ID } //
	
	private static final long serialVersionUID = 1L;
	
	public static String DEFAULT_CHANNEL_ID;
	
	// { SINGLETON } //
	
	private static ChannelCollection i = new ChannelCollection();
	public static ChannelCollection get() { return ChannelCollection.i; }
	
	// { SINGLETON } //
	
	public ChannelCollection()
	{
		super("channels", Channel.class);
	}
	
	public Channel getByName(String arg0)
	{
		Channel ret = null;
		 
		for (Channel channel : this)
		{
			if (!channel.getDisplayName().equalsIgnoreCase(arg0)) continue;
			
			ret = channel;
			break;
		}
		
		return ret;
	}
	
	public boolean containsByName(String arg0)
	{
		return getByName(arg0)!=null;
	}
	
	// { INITIALIZE } //
	
	@Override
	public void initialize(boolean arg0, TransPlugin arg1)
	{
		super.initialize(arg0, arg1);
		
		if (!arg0) return;
		
		String configName = ChatConfig.get().getDefaultChatName();
		
		if (containsByName(configName))
		{
			DEFAULT_CHANNEL_ID = getByName(configName).getID();
			return;
		}
		
		Channel def = create();
		def.setDisplayName(configName);
		def.toggleTagging(true);
		def.isDef = true;
		
		DEFAULT_CHANNEL_ID = def.getID();
	}
	
	
}
