package jibe.sdk.demo.viewme;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Conduct sessions to receive
 *
 */
public class ChallengeReceiver extends BroadcastReceiver {

    private static final String LOG_TAG = ChallengeReceiver.class.getName();
 
    @Override
    public void onReceive(Context context, Intent intent) {
    	// Need to check for action==null since as of Android 4.x applications need to be activated to before they can receive
		// broadcasts which are not explicitly directed at a class. This is done via a "dummy intent".
		if (intent.getAction() == null) {
    		return;
    	}
		Log.d(LOG_TAG, "onReceive() - intent action = "+intent.getAction());
      
    	intent.setClass(context, ViewMeActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
		context.startActivity(intent);
    }
}
