package com.lcmcconaghy.java.transchat.cmd;

import com.lcmcconaghy.java.transchat.Perm;
import com.lcmcconaghy.java.transchat.cmd.argument.ArgumentChannel;
import com.lcmcconaghy.java.transchat.store.Channel;
import com.lcmcconaghy.java.transcore.command.argument.primitive.ArgumentString;
import com.lcmcconaghy.java.transcore.exception.TransCommandException;

public class CmdChatEditQuickswitch extends ChatCommand
{
	
	// { SINGLETON } //
	
	private static CmdChatEditQuickswitch i = new CmdChatEditQuickswitch();
	public static CmdChatEditQuickswitch get() { return CmdChatEditQuickswitch.i; }
	
	// { CONSTRUCTOR } //
	
	public CmdChatEditQuickswitch()
	{
		addAlias("quickswitch");
		
		this.setPerm(Perm.EDIT_SWITCH);
		this.setDesc("add quick-switch to a channel");
		this.addArgument(ArgumentChannel.get(), "channel");
		this.addArgument(ArgumentString.get(), "switcher");
	}
	
	@Override
	public void execute() throws TransCommandException
	{
		Channel target = this.readArgument();
		String swtch = this.readArgument();
		
		if (Channel.getByQuickSwitch(swtch) != null)
		{
			error("A channel with this quick-switch label already exists.");
			return;
		}
		
		target.addQuickSwitch(swtch);
		
		message("Players can now switch to the <d>"+target.getDisplayName()+" Channel <e>by typing <1>"+swtch+": <b><message><e>.");
	}
	
}
