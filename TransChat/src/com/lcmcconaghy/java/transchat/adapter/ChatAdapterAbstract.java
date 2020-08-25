package com.lcmcconaghy.java.transchat.adapter;

import com.lcmcconaghy.java.transchat.TransChat;
import com.lcmcconaghy.java.transcore.Init;
import com.lcmcconaghy.java.transcore.TransPlugin;

public abstract class ChatAdapterAbstract implements ChatAdapter, Init
{
	
	// { METHODS } //
	
	@Override
	public void initialize(boolean arg0, TransPlugin arg1)
	{
		if (!arg0) return;
		
		TransChat.get().registerAdapter(this);
	}

	@Override
	public boolean isInitialized()
	{
		return TransChat.get().containsAdapter(this);
	}
}
