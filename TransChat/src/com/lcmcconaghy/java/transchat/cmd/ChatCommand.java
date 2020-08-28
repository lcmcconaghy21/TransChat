package com.lcmcconaghy.java.transchat.cmd;

import com.lcmcconaghy.java.transcore.command.TransCommand;

public abstract class ChatCommand extends TransCommand
{
	
	// { CONSTRUCTORS } //
	
	public ChatCommand()
	{
		super();
		this.setHidden(true);
	}
	
	
}
