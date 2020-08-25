package com.lcmcconaghy.java.transchat;

import com.lcmcconaghy.java.transcore.store.Config;

public class ChatConfig extends Config
{
	
	// { SINGLETON } //
	
	private static ChatConfig i = new ChatConfig();
	public static ChatConfig get() { return ChatConfig.i; }
	
	// { CONSTRUCTOR } //
	
	public ChatConfig()
	{
		super(TransChat.get());
	}
	
	// { FIELDS } //
	
	private String nameDefaultChat = "General";
	
	private int timeSecondsCooldown = 15;
	
	// { GETTERS } //
	
	
	public String getDefaultChatName()
	{
		return this.nameDefaultChat;
	}
	
	public int getTimeSecondsCooldown()
	{
		return this.timeSecondsCooldown;
	}
}
