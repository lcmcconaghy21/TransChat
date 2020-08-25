package com.lcmcconaghy.java.transchat.cmd;

import com.lcmcconaghy.java.transcore.command.TransCommand;
import com.lcmcconaghy.java.transcore.exception.TransCommandException;

public abstract class ChatCommand extends TransCommand
{
	
	// { CONSTRUCTORS } //
	
	public ChatCommand(String... aliases) throws TransCommandException
	{
		super(aliases);
	}
	
	
}
