package com.lcmcconaghy.java.transchat.cmd;

import com.lcmcconaghy.java.transchat.Perm;
import com.lcmcconaghy.java.transchat.event.ChannelCreateEvent;
import com.lcmcconaghy.java.transchat.store.Channel;
import com.lcmcconaghy.java.transchat.store.ChannelCollection;
import com.lcmcconaghy.java.transcore.command.argument.primitive.ArgumentString;
import com.lcmcconaghy.java.transcore.exception.TransCommandException;

public class CmdChatCreate extends ChatCommand
{
	
	// { SINGLETON } //
	
	private static CmdChatCreate i = new CmdChatCreate();
	public static CmdChatCreate get() { return CmdChatCreate.i; }
	
	// { CONSTRUCTOR } //
	
	public CmdChatCreate()
	{
		this.addAlias("create");
		
		this.setPerm(Perm.CREATE);
		this.addArgument(ArgumentString.get(), "name");
		
	}
	// { EXECUTE } //
	
	public void execute() throws TransCommandException
	{
		String name = this.readArgument();
		
		if (ChannelCollection.get().containsByName(name))
		{
			error("A channel with the name "+name+" <c>already exists!");
			return;
		}
		
		Channel create = ChannelCollection.get().create();
		create.setDisplayName(name);
		
		ChannelCreateEvent createEvent = new ChannelCreateEvent(create);
		createEvent.run();
		
		message("<a>You have created the <d>"+name+" Channel<a>!");
	}
	
}
