package com.lcmcconaghy.java.transchat;

import com.lcmcconaghy.java.transcore.PermissionBase;
import com.lcmcconaghy.java.transcore.util.UtilPerms;

public enum Perm implements PermissionBase
{
	LISTEN,
	IGNORE,
	FOCUS,
	LIST,
	
	CREATE,
	REMOVE,
	
	EDIT_PERMISSION,
	EDIT_SWITCH,
	EDIT_TAGGING,
	
	MUTE;
	
	// { PERMISSIONBASE } //
	
	@Override
	public String getNode()
	{
		return UtilPerms.toNode(this, TransChat.get());
	}
}
