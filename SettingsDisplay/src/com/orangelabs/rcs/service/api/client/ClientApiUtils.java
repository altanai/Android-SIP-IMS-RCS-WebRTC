package com.orangelabs.rcs.service.api.client;

import java.util.List;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;

/**
 * Client API utils
 * 
 * @author jexa7410
 */
public class ClientApiUtils {
    /**
	 * Is service started
	 *
	 * @param ctx Context
	 * @return Boolean
	 */
	public static boolean isServiceStarted(Context ctx) {
	    ActivityManager activityManager = (ActivityManager)ctx.getSystemService(Context.ACTIVITY_SERVICE);
	    List<ActivityManager.RunningServiceInfo> serviceList = activityManager.getRunningServices(Integer.MAX_VALUE);
	     for(int i = 0; i < serviceList.size(); i++) {
	           ActivityManager.RunningServiceInfo serviceInfo = serviceList.get(i);
	           ComponentName serviceName = serviceInfo.service;
	           if (serviceName.getClassName().equals("com.orangelabs.rcs.service.RcsCoreService")) {
	                 if (serviceInfo.pid != 0) {
	                      return true;
	                 } else {
	                      return false;
	                 }
	           }
	     }
	     return false;
	}	
}
