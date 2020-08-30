package com.lcmcconaghy.java.transchat.cmd;

import com.lcmcconaghy.java.transchat.Perm;
import com.lcmcconaghy.java.transchat.cmd.argument.ArgumentChannel;
import com.lcmcconaghy.java.transchat.store.Channel;
import com.lcmcconaghy.java.transchat.store.ChatUser;
import com.lcmcconaghy.java.transcore.exception.TransCommandException;

public class CmdChatListen extends ChatCommand
{
	
	// { SINGLETON } //
	
	private static CmdChatListen i = new CmdChatListen();
	public static CmdChatListen get() { return CmdChatListen.i; }
	
	// { CONSTRUCTOR } //
	
	public CmdChatListen()
	{
		this.addAlias("listen");
		
		this.setPerm(Perm.LISTEN);
		this.addArgument(ArgumentChannel.get(), "channel");
	}
	
	// { EXECUTE } //
	
	@Override
	public void execute() throws TransCommandException
	{
		if ( !isPlayer() )
		{
			error("You must be a PLAYER to listen to a channel.");
			return;
		}
		ChatUser user = ChatUser.get(sender);
		Channel listen = this.readArgument();
		
		user.listen(listen);
		
		message("You are now listening to the <d>"+listen.getDisplayName()+" Channel<e>!");
	}
	
}
