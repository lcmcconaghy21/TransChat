package com.lcmcconaghy.java.transchat.cmd.argument;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.CommandSender;

import com.lcmcconaghy.java.transchat.store.Channel;
import com.lcmcconaghy.java.transchat.store.ChannelCollection;
import com.lcmcconaghy.java.transcore.command.argument.ArgumentAbstract;
import com.lcmcconaghy.java.transcore.exception.TransCommandException;

public class ArgumentChannel extends ArgumentAbstract<Channel>
{
	
	// { SINGLETON } //
	
	private static ArgumentChannel i = new ArgumentChannel();
	public static ArgumentChannel get() { return ArgumentChannel.i; }
	
	// { ARGUMENTS } //
	
	@Override
	public Channel read(String arg0, CommandSender arg1) throws TransCommandException
	{
		Channel ret = ChannelCollection.get().getByName(arg0);
		
		if ( ret == null )
		{
			throw new TransCommandException("There is no <f>"+arg0+" Channel <c>registered.");
		}
		
		return ret;
	}

	@Override
	public List<String> getTabCompleteList(CommandSender arg0)
	{
		List<String> channelList = new ArrayList<String>();
		
		for (Channel channels : ChannelCollection.get())
		{
			channelList.add(channels.getDisplayName());
		}
			
		return channelList;
	}
	
}
