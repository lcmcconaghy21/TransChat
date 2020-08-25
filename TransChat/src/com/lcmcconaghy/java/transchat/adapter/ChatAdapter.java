package com.lcmcconaghy.java.transchat.adapter;

import com.lcmcconaghy.java.transchat.store.Channel;
import com.lcmcconaghy.java.transcore.Message;
import com.lcmcconaghy.java.transcore.store.UserItem;

public interface ChatAdapter
{
	
	/**
	 * Execute a chat command
	 * @param arg0 UserItem that sent the Message
	 * @param arg1 Channel where the Message was sent
	 * @param args Arguments of the chat adapter
	 */
	public Message execute(UserItem arg0, Channel arg1, Message arg2);
	
}
