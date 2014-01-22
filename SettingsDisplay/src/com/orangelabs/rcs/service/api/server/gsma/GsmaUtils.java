package com.orangelabs.rcs.service.api.server.gsma;

import com.orangelabs.rcs.service.api.client.gsma.GsmaClientConnector;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;

/**
 * GSMA utility functions
 * 
 * @author jexa7410
 */
public class GsmaUtils {
	
    /**
     * Set RCS client activation state
     * 
     * @param ctx Context
     * @param state Activation state
     */
    public static void setClientActivationState(Context ctx, boolean state) {
        SharedPreferences preferences = null;
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.GINGERBREAD_MR1) {
            preferences = ctx.getSharedPreferences(GsmaClientConnector.GSMA_PREFS_NAME, Context.MODE_WORLD_READABLE);
        } else {
            preferences = ctx.getSharedPreferences(GsmaClientConnector.GSMA_PREFS_NAME, Context.MODE_WORLD_READABLE + Context.MODE_MULTI_PROCESS);
        }
		Editor editor = preferences.edit();
		editor.putBoolean(GsmaClientConnector.GSMA_CLIENT_ENABLED, state);
		editor.commit();
    }
}