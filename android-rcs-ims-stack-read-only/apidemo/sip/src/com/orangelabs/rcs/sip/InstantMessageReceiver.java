package com.orangelabs.rcs.sip;

import com.orangelabs.rcs.sip.utils.Utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;

/**
 * Instant message receiver
 * 
 * @author jexa7410
 */
public class InstantMessageReceiver extends BroadcastReceiver {
	/**
	 * UI handler
	 */
	private final Handler handler = new Handler();

	@Override
	public void onReceive(Context context, Intent intent) {
		final Context ctx = context;
		final String msg = ctx.getString(R.string.label_recv_im, intent.getStringExtra("contact")) +
			"\n" + intent.getStringExtra("content");
		handler.post(new Runnable(){
			public void run(){
		        // Display received message
				Utils.displayLongToast(ctx, msg);
			}
		});
    }
}
