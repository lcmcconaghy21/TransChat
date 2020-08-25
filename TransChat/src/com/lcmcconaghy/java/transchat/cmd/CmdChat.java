package com.lcmcconaghy.java.transchat.cmd;

import com.lcmcconaghy.java.transchat.TransChat;
import com.lcmcconaghy.java.transcore.command.TransCommandHelp;
import com.lcmcconaghy.java.transcore.command.TransCommandVersion;
import com.lcmcconaghy.java.transcore.exception.TransCommandException;

public class CmdChat extends ChatCommand
{
	
	// { CONSTRUCTOR } //
	
	public CmdChat() throws TransCommandException
	{
		super("chat", "ch");
		
		this.addSubCommand(new TransCommandHelp(this));
		this.addSubCommand(new CmdList());
		this.addSubCommand(new TransCommandVersion(TransChat.get()));
	}
	
}
