package com.lcmcconaghy.java.transchat.cmd;

import com.lcmcconaghy.java.transchat.Perm;
import com.lcmcconaghy.java.transchat.cmd.argument.ArgumentChannel;
import com.lcmcconaghy.java.transchat.event.ChannelRemoveEvent;
import com.lcmcconaghy.java.transchat.store.Channel;
import com.lcmcconaghy.java.transcore.exception.TransCommandException;

public class CmdChatRemove extends ChatCommand
{
	
	// { SINGLETON } //
	
	private static CmdChatRemove i = new CmdChatRemove();
	public static CmdChatRemove get() { return CmdChatRemove.i; }
	
	// { CONSTRUCTOR } //
	
	public CmdChatRemove()
	{
		this.addAlias("remove");
		
		this.setDesc("remove a channel");
		this.setPerm(Perm.REMOVE);
		this.addArgument(ArgumentChannel.get(), "channel");
	}
	
	// { EXECUTION } //
	
	@Override
	public void execute() throws TransCommandException
	{
		Channel channel = this.readArgument();
		String name = channel.getDisplayName();
		
		ChannelRemoveEvent removeEvent = new ChannelRemoveEvent(channel);
		removeEvent.run();
		
		channel.drop();
		
		message("You have removed the "+name+" Channel<e>!");
	}
}
