package com.lcmcconaghy.java.transchat.cmd;

import com.lcmcconaghy.java.transchat.Perm;
import com.lcmcconaghy.java.transchat.pager.PagerChannels;
import com.lcmcconaghy.java.transcore.command.argument.primitive.ArgumentInteger;
import com.lcmcconaghy.java.transcore.exception.TransCommandException;

public class CmdChatList extends ChatCommand
{
	
	// { CONSTRUCTOR } //
	
	public CmdChatList() throws TransCommandException
	{
		this.addAlias("list");
		
		this.setDesc("list all channels");
		this.setPerm(Perm.LIST);
		this.addArgument(ArgumentInteger.get(), "page", false, true);
	}
	
	@Override
	public void execute()
	{
		int page = this.readArgument(1);
		
		PagerChannels.get().sendPage(page, this, sender);
	}
}
