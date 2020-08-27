package com.lcmcconaghy.java.transchat.cmd;

import com.lcmcconaghy.java.transchat.Perm;
import com.lcmcconaghy.java.transchat.cmd.argument.ArgumentChannel;
import com.lcmcconaghy.java.transchat.store.Channel;
import com.lcmcconaghy.java.transcore.command.argument.primitive.ArgumentDouble;
import com.lcmcconaghy.java.transcore.exception.TransCommandException;

public class CmdChatEditDistance extends ChatCommand
{
	
	// { CONSTRUCTOR } //
	
	public CmdChatEditDistance() throws TransCommandException
	{
		super("distance");
		
		this.setDesc("set distance");
		this.setPerm(Perm.EDIT_DISTANCE);
		this.addArgument(ArgumentChannel.get(), "channel");
		this.addArgument(ArgumentDouble.get(), "innerRadius");
		this.addArgument(ArgumentDouble.get(), "outerRadius");
	}
	
	// { EXECUTE } //
	
	@Override
	public void execute() throws TransCommandException
	{
		Channel channel = this.readArgument();
		double inner = this.readArgument();
		double outer = this.readArgument();
		
		if (inner >= outer)
		{
			error("Inner radius must always be smaller than the outer radius");
			return;
		}
		
		channel.setRadius(outer, inner);
		
		message("Set the chat radius for the <d>"+channel.getDisplayName()+" Channel <e>to:");
		message("Inner: <d>"+inner);
		message("Outer: <d>"+outer);
	}
}
