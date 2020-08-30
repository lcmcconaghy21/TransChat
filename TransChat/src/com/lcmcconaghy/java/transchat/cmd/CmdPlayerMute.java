package com.lcmcconaghy.java.transchat.cmd;

import com.lcmcconaghy.java.transchat.Perm;
import com.lcmcconaghy.java.transchat.store.ChatUser;
import com.lcmcconaghy.java.transchat.store.ChatUserCollection;
import com.lcmcconaghy.java.transcore.command.argument.bukkit.ArgumentUser;

public class CmdPlayerMute extends ChatCommand
{
	
	// { SINGLETON } //
	
	private static CmdPlayerMute i = new CmdPlayerMute();
	public static CmdPlayerMute get() { return CmdPlayerMute.i; }
	
	// { CONSTRUCTOR } //
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public CmdPlayerMute()
	{
		this.addAlias("mute");
		
		this.setDesc("mute a user");
		this.setPerm(Perm.MUTE);
		this.addArgument(new ArgumentUser(ChatUserCollection.get()), "user");
	}
	
	// { EXECUTE } //
	
	@Override
	public void execute()
	{
		ChatUser user = ChatUser.get(sender);
		
		boolean muted = !user.isMuted();
		
		user.setMuted( muted );
		
		if (muted == false)
		{
			message("You have <c>muted <d>"+user.getName()+"<e>.");
			return;
		}
		
		message("You have <f>unmuted <d>"+user.getName()+"<e>.");
	}
}