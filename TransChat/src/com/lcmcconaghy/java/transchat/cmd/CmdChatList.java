package com.lcmcconaghy.java.transchat.cmd;

import com.lcmcconaghy.java.transchat.Perm;
import com.lcmcconaghy.java.transchat.pager.PagerChannels;
import com.lcmcconaghy.java.transcore.exception.TransCommandException;

public class CmdChatList extends ChatCommand
{
	
	// { CONSTRUCTOR } //
	
	public CmdChatList() throws TransCommandException
	{
		this.addAlias("list");
		
		this.setDesc("list all channels");
		this.setPerm(Perm.LIST);
	}
	
	@Override
	public void execute() throws TransCommandException
	{
		PagerChannels.get().sendPage(this, sender);
	}
}
