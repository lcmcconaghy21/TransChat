package com.lcmcconaghy.java.transchat.store;

import java.util.ArrayList;
import java.util.List;

import com.lcmcconaghy.java.transchat.adapter.AdapterChannel;
import com.lcmcconaghy.java.transchat.adapter.AdapterMessage;
import com.lcmcconaghy.java.transchat.adapter.AdapterPlayer;
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
	private String focusNode;
	private String listenNode;
	
	protected boolean isDef = false;
	private String format = "<"+AdapterChannel.get().getFormat()
			                +" + "+AdapterPlayer.get().getFormat()+"> "
			                +AdapterMessage.get().getFormat();
	
	private double channelRadiusOuter = -1D;
	private double channelRadiusInner = -1D;
	
	private boolean hasCooldown = false;
	private boolean canTagUser = false;
	private boolean willObfuscate = false;
	
	// { SETTERS } //
	
	public void setDisplayName(String arg0)
	{
		this.displayId = arg0;
		this.focusNode = "transchat.chat."+arg0;
		this.listenNode = "transchat.listen."+arg0;
		
		this.aliases.add(arg0.toLowerCase());
		this.update();
	}
	
	public void updatePerm(String arg0)
	{
		this.focusNode = "transchat.chat."+arg0;
		this.listenNode = "transchat.listen."+arg0;
		this.update();
	}
	
	public void addQuickSwitch(String arg0)
	{
		arg0 = arg0.toLowerCase();
		
		if (this.aliases.contains(arg0)) return;
		
		this.aliases.add(arg0);
		this.update();
	}
	
	public void removeQuickSwitch(String arg0)
	{
		arg0 = arg0.toLowerCase();
		
		if (!this.aliases.contains(arg0)) return;
		
		this.aliases.remove(arg0);
		this.update();
	}
	
	public void setRadius(double arg0, double arg1)
	{
		this.channelRadiusOuter = arg0;
		this.channelRadiusInner = arg1;
		this.update();
	}
	
	public boolean isDefault()
	{
		return this.isDef;
	}
	
	public void toggleCooldown(boolean arg0)
	{
		this.hasCooldown = arg0;
		this.update();
	}
	
	public void toggleTagging(boolean arg0)
	{
		this.canTagUser = arg0;
		this.update();
	}
	
	public void toggleObfuscation(boolean arg0)
	{
		this.willObfuscate = arg0;
		this.update();
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
	
	public String getFocusNode()
	{
		return this.focusNode;
	}
	
	public String getListenNode()
	{
		return this.listenNode;
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
	
	public boolean willObfuscate()
	{
		return this.willObfuscate;
	}
}
