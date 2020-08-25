package com.lcmcconaghy.java.transchat.cmd.argument;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.CommandSender;

import com.lcmcconaghy.java.transchat.store.Channel;
import com.lcmcconaghy.java.transchat.store.ChannelCollection;
import com.lcmcconaghy.java.transcore.command.argument.ArgumentAbstract;

public class ArgumentChannel extends ArgumentAbstract<Channel>
{
	
	// { SINGLETON } //
	
	private static ArgumentChannel i = new ArgumentChannel();
	public static ArgumentChannel get() { return ArgumentChannel.i; }
	
	// { ARGUMENTS } //
	
	@Override
	public Channel read(String arg0, CommandSender arg1)
	{
		return ChannelCollection.get().getByName(arg0);
	}

	@Override
	public List<String> getTabCompleteList()
	{
		List<String> channelList = new ArrayList<String>();
		
		for (Channel channels : ChannelCollection.get())
		{
			channelList.add(channels.getDisplayName());
		}
			
		return channelList;
	}
	
}
