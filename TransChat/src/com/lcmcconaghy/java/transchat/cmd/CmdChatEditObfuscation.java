package com.lcmcconaghy.java.transchat.cmd;

import com.lcmcconaghy.java.transchat.Perm;
import com.lcmcconaghy.java.transchat.cmd.argument.ArgumentChannel;
import com.lcmcconaghy.java.transchat.store.Channel;
import com.lcmcconaghy.java.transcore.command.argument.primitive.ArgumentBoolean;
import com.lcmcconaghy.java.transcore.exception.TransCommandException;

public class CmdChatEditObfuscation extends ChatCommand
{
	
	// { SINGLETON } //
	
	private static CmdChatEditObfuscation i = new CmdChatEditObfuscation();
	public static CmdChatEditObfuscation get() { return CmdChatEditObfuscation.i; }
	
	// { CONSTRUCTOR } //
	
	public CmdChatEditObfuscation()
	{
		addAliases("obfuscation", "obf");
		
		this.setPerm(Perm.EDIT_OBFUSCATION);
		this.setDesc("toggle chat obfuscation");
		this.addArgument(ArgumentChannel.get(), "channelName");
		this.addArgument(ArgumentBoolean.get(), "willObfuscate");
	}
	
	// { EXECUTION } //
	
	@Override
	public void execute() throws TransCommandException
	{
		Channel channel = this.readArgument();
		
		if (channel.getRadiusOuter()<=0)
		{
			error("This channel is not local, please check the chat radii again.");
			return;
		}
		
		boolean willObfuscate = this.readArgument();
		
		channel.toggleObfuscation(willObfuscate);
		
		String response = "The <d>"+channel.getDisplayName()+" Channel <e>will now obfuscate chat messages.";
		
		if (!willObfuscate)
		{
			response = "The <d>"+channel.getDisplayName()+" Channel <e>will not be obfuscating chat messages.";
		}
		
		message(response);
	}
}
