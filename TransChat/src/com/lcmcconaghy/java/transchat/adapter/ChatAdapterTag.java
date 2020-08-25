package com.lcmcconaghy.java.transchat.adapter;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import com.lcmcconaghy.java.transchat.store.Channel;
import com.lcmcconaghy.java.transcore.Message;
import com.lcmcconaghy.java.transcore.store.UserItem;

import net.md_5.bungee.api.ChatColor;

public class ChatAdapterTag extends ChatAdapterAbstract
{
	
	// { SINGLETON } //
	
	private static ChatAdapterTag i = new ChatAdapterTag();
	public static ChatAdapterTag get() { return ChatAdapterTag.i; }
	
	// { ADAPTER } //
	
	@SuppressWarnings("deprecation")
	@Override
	public Message execute(UserItem arg0, Channel arg1, Message arg2)
	{
		if (!arg1.allowsTagging()) return arg2;
		
		ChatColor after = ChatColor.of(arg2.getText());
		
		String[] parts = arg2.getWords();
		
		Message ret = new Message();
		
		for (int i = 0; i<parts.length; i++)
		{
			String test = parts[i];
			
			if (!test.startsWith("@") || test.length()>1)
			{
				ret.add(test);
				continue;
			}
			
			String name = test.substring(1);
			OfflinePlayer player = Bukkit.getOfflinePlayer(name);
			
			if (player==null || !player.isOnline())
			{
				continue;
			}
			
			Message tag = new Message(name).color(ChatColor.GOLD);
			
			tag.hover("<d>Message <6>"+name+after);
			ret.extra(tag);
			
			Player p = (Player) player;
			
			p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 3, 3);
		}
		
		return ret;
	}
}
