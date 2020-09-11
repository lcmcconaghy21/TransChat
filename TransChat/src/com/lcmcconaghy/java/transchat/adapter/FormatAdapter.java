package com.lcmcconaghy.java.transchat.adapter;

import org.bukkit.entity.Player;

import com.lcmcconaghy.java.transchat.FormatPriority;
import com.lcmcconaghy.java.transchat.store.Channel;

public interface FormatAdapter
{
	
	/**
	 * Read a Channel format
	 * @param arg0 Channel to read
	 * @param arg1 Player sending the Message
	 * @param arg2 Player receiving the Message
	 * @param arg3 String message
	 * @return formatted Channel format
	 */
	public String read(Channel arg0, Player arg1, Player arg2, String arg3);
	
	/**
	 * Check if a Channel contains the need for this Adapter
	 * @param arg0 Channel to check 
	 * @return whether Channel contains Format
	 */
	public boolean containsFormat(Channel arg0);
	
	public String getID();
	
	public FormatPriority getPriority();
	
}
