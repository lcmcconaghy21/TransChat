package com.lcmcconaghy.java.transchat.cmd;

import com.lcmcconaghy.java.transchat.TransChat;
import com.lcmcconaghy.java.transcore.command.TransCommandVersion;
import com.lcmcconaghy.java.transcore.exception.TransCommandException;

public class CmdChat extends ChatCommand
{
	
	// { CONSTRUCTOR } //
	
	public CmdChat() throws TransCommandException
	{
		this.addAliases("chat", "channel");
		
		this.addSubCommand(new CmdChatCreate());
		this.addSubCommand(new CmdChatRemove());
		this.addSubCommand(new CmdChatList());
		
		this.addSubCommand(new CmdChatEdit());
		
		this.addSubCommand(new CmdChatFocus());
		
		this.addSubCommand(new CmdChatListen());
		this.addSubCommand(new CmdChatIgnore());
		
		this.addSubCommand(new CmdPlayerMute());
		
		this.addSubCommand(new TransCommandVersion(TransChat.get()));
	}
	
}
