package com.lcmcconaghy.java.transchat.cmd;

import com.lcmcconaghy.java.transchat.Perm;
import com.lcmcconaghy.java.transchat.cmd.argument.ArgumentChannel;
import com.lcmcconaghy.java.transchat.store.Channel;
import com.lcmcconaghy.java.transchat.store.ChatUser;
import com.lcmcconaghy.java.transcore.exception.TransCommandException;

public class CmdChatIgnore extends ChatCommand
{
	
	// { SINGLETON } //
	
	private static CmdChatIgnore i = new CmdChatIgnore();
	public static CmdChatIgnore get() { return CmdChatIgnore.i; }
	
	// { CONSTRUCTOR } //
	
	public CmdChatIgnore()
	{
		this.addAlias("ignore");
		
		this.setRequiresPlayer(true);
		this.setDesc("ignore all content of a channel");
		this.setPerm(Perm.IGNORE);
		this.addArgument(ArgumentChannel.get(), "channel");
	}
	
	// { EXECUTE } //
	
	@Override
	public void execute() throws TransCommandException
	{
		if ( !isPlayer() )
		{
			error("You must be a PLAYER to execute this command.");
			return;
		}
		
		ChatUser user = ChatUser.get(sender);
		Channel ignore = this.readArgument();
		
		user.ignore(ignore);
		
		message("You are ignoring the <d>"+ignore.getDisplayName()+" Channel<e>!");
	}
}
