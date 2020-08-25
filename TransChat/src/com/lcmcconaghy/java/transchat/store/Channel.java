package com.lcmcconaghy.java.transchat.store;

import java.util.ArrayList;
import java.util.List;

import com.lcmcconaghy.java.transchat.engine.EngineChat;
import com.lcmcconaghy.java.transcore.store.StoreItem;

public class Channel extends StoreItem<Channel>
{
	
	// { QUICK GET } //
	
	public static Channel get(String arg0)
	{
		return ChannelCollection.get().get(arg0);
	}
	
	public static Channel getByName(String arg0)
	{
		Channel ret = null;
		
		for (Channel channel : ChannelCollection.get())
		{
			if (!channel.getDisplayName().equalsIgnoreCase(arg0)) continue;
			
			ret = channel;
			break;
		}
		
		return ret;
	}
	
	public static Channel getByQuickSwitch(String arg0)
	{
		Channel ret = null;
		
		for (Channel channel : ChannelCollection.get())
		{
			if (!channel.aliases.contains(arg0.toLowerCase())) continue;
			
			ret = channel;
			break;
		}
		
		return ret;
	}
	
	// { FIELDS } //
	
	private String displayId;
	private List<String> aliases = new ArrayList<String>();
	private String permissionNode;
	
	private String format = "<"+EngineChat.CHANNEL+" + "+EngineChat.PLAYER+"> "+EngineChat.MESSAGE;
	
	private double channelRadiusOuter = -1D;
	private double channelRadiusInner = -1D;
	
	private boolean hasCooldown = false;
	private boolean canTagUser = false;
	
	// { SETTERS } //
	
	public void setDisplayName(String arg0)
	{
		this.displayId = arg0;
		this.permissionNode = "transchat.chat."+arg0;
		
		this.aliases.add(arg0.toLowerCase());
	}
	
	public void addQuickSwitch(String arg0)
	{
		arg0 = arg0.toLowerCase();
		
		if (this.aliases.contains(arg0)) return;
		
		this.aliases.add(arg0);
	}
	
	public void removeQuickSwitch(String arg0)
	{
		arg0 = arg0.toLowerCase();
		
		if (!this.aliases.contains(arg0)) return;
		
		this.aliases.remove(arg0);
	}
	
	public void setRadius(double arg0, double arg1)
	{
		this.channelRadiusOuter = arg0;
		this.channelRadiusInner = arg1;
	}
	
	public void toggleCooldown(boolean arg0)
	{
		this.hasCooldown = arg0;
	}
	
	public void toggleTagging(boolean arg0)
	{
		this.canTagUser = arg0;
	}
	
	// { GETTERS } //
	
	public String getDisplayName()
	{
		return this.displayId;
	}
	
	public boolean containsQuickSwitch(String arg0)
	{
		arg0 = arg0.toLowerCase();
		
		return this.aliases.contains(arg0);
	}
	
	public List<String> getQuickSwitches()
	{
		return this.aliases;
	}
	
	public String getFormat()
	{
		return this.format;
	}
	
	public String getPermissionNode()
	{
		return this.permissionNode;
	}
	
	public double getRadiusOuter()
	{
		return this.channelRadiusOuter;
	}
	
	public double getRadiusInner()
	{
		return this.channelRadiusInner;
	}
	
	public boolean hasRadius()
	{
		return (this.channelRadiusOuter>0);
	}
	
	public boolean allowsTagging()
	{
		return this.canTagUser;
	}
	
	public boolean hasCooldown()
	{
		return this.hasCooldown;
	}
}
