package com.lcmcconaghy.java.transchat.store;

import com.lcmcconaghy.java.transcore.TransPlugin;
import com.lcmcconaghy.java.transcore.store.UserCollection;

public class ChatUserCollection extends UserCollection<ChatUser>
{
	
	// { SINGLETON } //
	
	private static ChatUserCollection i = new ChatUserCollection();
	public static ChatUserCollection get() { return ChatUserCollection.i; }
	
	// { SERIAL ID } //
	
	private static final long serialVersionUID = 1L;

	// { CONSTRUCTOR } //
	
	public ChatUserCollection()
	{
		super("players", ChatUser.class);
	}
	
	// { INITS } //
	
	@Override
	public void initialize(boolean arg0, TransPlugin arg1)
	{
		super.initialize(arg0, arg1);
		
		// TODO CATCH WHERE CHANNELS ARE REMOVED WHILE SERVERS ARE OFFLINE
	}
	
}
