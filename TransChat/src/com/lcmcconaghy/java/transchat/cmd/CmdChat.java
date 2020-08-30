package com.lcmcconaghy.java.transchat.cmd;

import com.lcmcconaghy.java.transchat.TransChat;
import com.lcmcconaghy.java.transcore.command.TransCommandVersion;

public class CmdChat extends ChatCommand
{
	
	// { CONSTRUCTOR } //
	
	public CmdChat()
	{
		this.addAliases("chat", "ch");
		
		this.addSubCommand( CmdChatCreate.get() );
		this.addSubCommand( CmdChatRemove.get() );
		this.addSubCommand( CmdChatList.get() );
		
		this.addSubCommand( CmdChatEdit.get() );
		
		this.addSubCommand( CmdChatFocus.get() );
		
		this.addSubCommand( CmdChatListen.get() );
		this.addSubCommand( CmdChatIgnore.get() );
		
		this.addSubCommand( CmdPlayerMute.get() );
		
		this.addSubCommand(new TransCommandVersion(TransChat.get()));
	}
	
}
