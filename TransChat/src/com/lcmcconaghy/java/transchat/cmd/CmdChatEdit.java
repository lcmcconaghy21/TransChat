package com.lcmcconaghy.java.transchat.cmd;

import com.lcmcconaghy.java.transcore.exception.TransCommandException;

public class CmdChatEdit extends ChatCommand
{
	
	// { CONSTRUCTOR } //
	
	public CmdChatEdit() throws TransCommandException
	{
		this.addAlias("edit");
		
		this.setDesc("edit channel properties");
		
		this.addSubCommand(new CmdChatEditQuickswitch());
		this.addSubCommand(new CmdChatEditDistance());
		this.addSubCommand(new CmdChatEditPermission());
	}
	
}
