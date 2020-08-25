package com.lcmcconaghy.java.transchat.cmd;

import com.lcmcconaghy.java.transchat.TransChat;
import com.lcmcconaghy.java.transcore.command.TransCommandVersion;
import com.lcmcconaghy.java.transcore.exception.TransCommandException;

public class CmdChat extends ChatCommand
{
	
	// { CONSTRUCTOR } //
	
	public CmdChat() throws TransCommandException
	{
		super("chat", "ch");
		
		this.addSubCommand(new CmdCreate());
		this.addSubCommand(new CmdRemove());
		this.addSubCommand(new CmdList());
		
		this.addSubCommand(new CmdListen());
		this.addSubCommand(new CmdIgnore());
		
		this.addSubCommand(new TransCommandVersion(TransChat.get()));
	}
	
}
