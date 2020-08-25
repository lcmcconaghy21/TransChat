package com.lcmcconaghy.java.transchat.cmd;

import com.lcmcconaghy.java.transchat.Perm;
import com.lcmcconaghy.java.transchat.cmd.argument.ArgumentChannel;
import com.lcmcconaghy.java.transchat.store.Channel;
import com.lcmcconaghy.java.transcore.exception.TransCommandException;

public class CmdRemove extends ChatCommand
{
	
	// { CONSTRUCTOR } //
	
	public CmdRemove() throws TransCommandException
	{
		super("remove");
		
		this.setPerm(Perm.REMOVE);
		this.addArgument(ArgumentChannel.get(), "channel");
	}
	
	// { EXECUTION } //
	
	@Override
	public void execute() throws TransCommandException
	{
		Channel channel = this.readArgument();
		String name = channel.getDisplayName();
		
		channel.drop();
		
		message("You have removed the "+name+" Channel<e>!");
	}
}
