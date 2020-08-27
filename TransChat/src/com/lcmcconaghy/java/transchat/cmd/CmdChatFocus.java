package com.lcmcconaghy.java.transchat.cmd;

import com.lcmcconaghy.java.transchat.Perm;
import com.lcmcconaghy.java.transchat.cmd.argument.ArgumentChannel;
import com.lcmcconaghy.java.transchat.store.Channel;
import com.lcmcconaghy.java.transchat.store.ChatUser;
import com.lcmcconaghy.java.transcore.command.TransCommand;
import com.lcmcconaghy.java.transcore.exception.TransCommandException;

public class CmdChatFocus extends TransCommand
{
	
	// { CONSTRUCTOR } //
	
	public CmdChatFocus() throws TransCommandException
	{
		super("focus");
		
		this.setDesc("focus on a channel");
		this.setPerm(Perm.FOCUS);
		this.addArgument(ArgumentChannel.get(), "channel");
	}
	
	// { EXECUTE } //
	
	@Override
	public void execute() throws TransCommandException
	{
		if ( !isPlayer() )
		{
			error("You must be a player to execute this command.");
			return;
		}
		
		ChatUser user = ChatUser.get(sender);
		Channel channel = this.readArgument();
		
		if ( !sender.hasPermission( channel.getFocusNode() ) )
		{
			error("You do not have permission to focus this channel.");
			return;
		}
		
		user.focus(channel);
		
		message("You have focused onto the <d>"+channel.getDisplayName()+" Channel<e>.");
	}
}
