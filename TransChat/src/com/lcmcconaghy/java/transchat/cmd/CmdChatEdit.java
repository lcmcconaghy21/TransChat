package com.lcmcconaghy.java.transchat.cmd;

public class CmdChatEdit extends ChatCommand
{
	
	// { SINGLETON } //
	
	private static CmdChatEdit i = new CmdChatEdit();
	public static CmdChatEdit get() { return CmdChatEdit.i; }
	
	// { CONSTRUCTOR } //
	
	public CmdChatEdit()
	{
		this.addAlias("edit");
		
		this.setDesc("edit channel properties");
		
		this.addSubCommand( CmdChatEditQuickswitch.get() );
		this.addSubCommand( CmdChatEditDistance.get() );
		this.addSubCommand( CmdChatEditPermission.get() );
	}
	
}
