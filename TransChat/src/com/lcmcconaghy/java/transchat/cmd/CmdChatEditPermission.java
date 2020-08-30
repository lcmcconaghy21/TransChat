package com.lcmcconaghy.java.transchat.cmd;

import org.apache.commons.lang.StringUtils;

import com.lcmcconaghy.java.transchat.Perm;
import com.lcmcconaghy.java.transchat.cmd.argument.ArgumentChannel;
import com.lcmcconaghy.java.transchat.store.Channel;
import com.lcmcconaghy.java.transcore.command.argument.primitive.ArgumentString;
import com.lcmcconaghy.java.transcore.exception.TransCommandException;

public class CmdChatEditPermission extends ChatCommand
{
	
	// { SINGLETON } //
	
	private static CmdChatEditPermission i = new CmdChatEditPermission();
	public static CmdChatEditPermission get() { return CmdChatEditPermission.i; }
	
	// { CONSTRUCTOR } //
	
	public CmdChatEditPermission()
	{
		this.addAliases("permissions", "perm");
		
		this.setPerm(Perm.EDIT_PERMISSION);
		this.addArgument(ArgumentChannel.get(), "channel");
		this.addArgument(ArgumentString.get(), "permission");
	}
	
	// { EXECUTE } //
	
	@Override
	public void execute() throws TransCommandException
	{
		Channel channel = this.readArgument();
		String permission = this.readArgument();
		
		if (!StringUtils.isAlphanumeric(permission))
		{
			error("Permission must be alphanumeric.");
			return;
		}
		
		channel.updatePerm(permission);
		
		message("Listen node updated to: <d>"+channel.getListenNode());
		message("Focus node updated to: <d>"+channel.getFocusNode());
	}
}
